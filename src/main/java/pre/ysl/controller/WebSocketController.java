package pre.ysl.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pre.ysl.base.ResultUtil;
import pre.ysl.base.dto.ResultDTO;
import pre.ysl.websocket.WebSocketServer;

import java.io.IOException;


@Controller
@Api(value = "聊天Controller ws://39.96.68.53:8080/WebsocketServer/{uid}")
@RequestMapping("webSocketController")
@Log4j2
public class WebSocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @PostMapping("/sendMessage")
    @ResponseBody
    @ApiOperation(value = "发送信息给特定用户",notes = "500报错")
    public ResultDTO sendMessage(
            @ApiParam(value = "发送者id",required = true)@RequestParam(value = "fromid",required = true)Integer fromid,
            @ApiParam(value = "接收者id",required = true)@RequestParam(value = "toid",required = true) Integer toid,
            @ApiParam(value = "信息",required = true)@RequestParam(value = "message",required = true)String message){
        try {
            webSocketServer.sendMessage(message,fromid,toid);
            return new ResultUtil().Success();
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }

    }



    @PostMapping("/sendMessageToAll")
    @ResponseBody
    @ApiOperation(value = "发送信息给全部登录过的用户",notes = "500报错")
    public ResultDTO sendMessageToAll(
            @ApiParam(value = "发送者id",required = true)@RequestParam(value = "fromid",required = true)Integer fromid,
            @ApiParam(value = "信息",required = true)@RequestParam(value = "message",required = true)String message){
        try {
            webSocketServer.sendInfoToAll(message,fromid);
            return new ResultUtil().Success();
        } catch (Exception e) {
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PostMapping("/sendSimpleMessageToAll")
    @ResponseBody
    @ApiOperation(value = "发送公告",notes = "500报错")
    public ResultDTO sendSimpleMessageToAll(
            @ApiParam(value = "发送者id",required = true)@RequestParam(value = "fromid",required = true)Integer fromid,
            @ApiParam(value = "信息",required = true)@RequestParam(value = "message",required = true)String message){
        try {
            webSocketServer.sendStringInfoToAll(message,fromid);
            return new ResultUtil().Success();
        } catch (Exception e) {
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }
}
