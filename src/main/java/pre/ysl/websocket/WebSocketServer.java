package pre.ysl.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pre.ysl.base.ResultUtil;
import pre.ysl.base.websocket.ServerEncoder;
import pre.ysl.base.websocket.WebsocketBase;
import pre.ysl.dto.UserDTO;
import pre.ysl.pojo.UR;
import pre.ysl.pojo.User;
import pre.ysl.redis.RedisUtil;
import pre.ysl.service.*;


@ServerEndpoint(value = "/WebsocketServer/{userId}",encoders = {ServerEncoder.class})
@Component
@Log4j2
public class WebSocketServer {

    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<Integer,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private Integer userId;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private URService urService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private EnterpriseService enterpriseService;

    public static WebSocketServer webSocketServer;

    @PostConstruct
    public void init(){
        webSocketServer = this;
    }

    @OnOpen
    public void onOpen(Session session,@PathParam("userId") Integer userId) {
        this.session = session;
        this.userId=userId;
        webSocketMap.put(userId,this);
        //接受离线时间收到的信息
        if (webSocketServer.redisUtil.hasKey(userId.toString())){
            List<Object> messageList = webSocketServer.redisUtil.lGet(userId.toString(),0,-1);
            for (Object object : messageList){
                try {
                    WebsocketBase websocketBase = (WebsocketBase) object;
                    this.sendMessage(websocketBase.getMessage().toString(),websocketBase.getFromid(),this.userId);
                    webSocketServer.redisUtil.del(userId.toString());
                }catch (Exception e){
                    e.printStackTrace();
                    log.info(this.userId+"<-->:信息接受失败");
                }

            }
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userid")Integer userid) {
        webSocketMap.remove(userid);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message) {
        try {
            sendInfo(message);
        }catch (Exception e){
            log.info(e.toString());
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
    }

    public void sendInfo(Object object) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(object);
    }
    public void sendInfo(String message) throws IOException, EncodeException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendMessage(String message, @PathParam("userId")Integer fromid,Integer toid) throws IOException, EncodeException {
        UserDTO userDTO = new UserDTO();
        WebsocketBase websocketBase = new WebsocketBase();
        try {
            //查询用户信息
            userDTO.setUserDTO(webSocketServer.userService.selectByUid(fromid));
            userDTO.setUpassword(null);
            UR ur = webSocketServer.urService.selectByUid(userDTO.getUid());
            Object object = null;
            //查询角色信息
            switch (ur.getRid()){
                case 1: object = webSocketServer.studentService.selectBySid(ur.getId());break;
                case 2: object = webSocketServer.enterpriseService.selectByEid(ur.getId());break;
                case 3: object = webSocketServer.collegeService.selectByCid(ur.getId());break;
                case 4: object = webSocketServer.managerService.selectByMid(ur.getId());break;
            }
            userDTO.setRole(ur.getRid());
            userDTO.setRoleObject(object);
            websocketBase.setFromid(fromid);
            websocketBase.setMessage(message);
            websocketBase.setUserDTO(userDTO);
        }catch (Exception e){
            log.info("获取信息发送者的信息有误,用户id为"+toid);
            webSocketMap.get(toid).sendInfo("尊敬的客户，获取信息发送者的信息有误");
            websocketBase.setUserDTO(null);
        }
        websocketBase.setUphoto(webSocketServer.userService.selectByUid(fromid).getUphoto());
        if ( !webSocketMap.containsKey(toid)){
            webSocketServer.redisUtil.lSet(toid.toString(),websocketBase);
        }else{
            webSocketMap.get(toid).sendInfo(websocketBase);
        }
    }


    /**
     * 发送自定义群发消息
     * */
    public void sendInfoToAll(String message,@PathParam("userId")Integer fromid) throws IOException, EncodeException {
        if (StringUtils.isNotBlank(message)) {
            for (Integer key : webSocketMap.keySet()) {
                if (key != fromid || webSocketMap.get(key) != null){
                    webSocketMap.get(key).sendMessage(message,fromid,webSocketMap.get(key).userId);
                }
            }
        }
    }

    /**
     * 发送自定义群发消息
     * */
    public void sendStringInfoToAll(String message,@PathParam("userId")Integer fromid) throws IOException, EncodeException {
        if (StringUtils.isNotBlank(message)) {
            for (Integer key : webSocketMap.keySet()) {
                if (key != fromid || webSocketMap.get(key) != null){
                    webSocketMap.get(key).sendInfo(message);
                }
            }
        }
    }
}
