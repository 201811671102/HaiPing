package pre.ysl.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pre.ysl.base.ResultUtil;
import pre.ysl.base.dto.ResultDTO;
import pre.ysl.base.mail.MailBase;
import pre.ysl.dto.UserDTO;
import pre.ysl.ftp.FtpOperation;
import pre.ysl.mail.SendMail;
import pre.ysl.pojo.*;
import pre.ysl.redis.RedisUtil;
import pre.ysl.service.*;
import pre.ysl.websocket.WebSocketServer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/managerController")
@Api(value = "管理员Controller",tags = "添加管理员信息，修改管理员信息，查询所有用户，通过账户查询用户,查封用户")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private URService urService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private SEPCService sepcService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private SPService spService;
    @Autowired
    private FtpOperation ftpOperation;
    @Autowired
    private WebSocketServer webSocketServer;


    @PostMapping("/insertNewManager")
    @ResponseBody
    @ApiOperation(value = "添加管理员",notes = "201 信息重复 400 验证码错误 401 超时，验证码无效 500 报错 ")
    public ResultDTO insertNewManager(
            @ApiParam(value = "用户账号",required = true)@RequestParam(value = "uaccount",required = true)String uaccount,
            @ApiParam(value = "用户密码",required = true)@RequestParam(value = "upassword",required = true)String upassword,
            @ApiParam(value = "用户验证码",required = true)@RequestParam(value = "code",required = true)String code,
            @ApiParam(value = "用户手机",required = true)@RequestParam(value = "uphone",required = true)String uphone,
            @ApiParam(value = "邮箱",required = true)@RequestParam(value = "uemail",required = true) String uemail,
            @ApiParam(value = "默认头像",required = false)@RequestParam(value = "photourl",required = false) String photourl,
            @ApiParam(value = "用户头像",required = false)@RequestParam(value = "uphoto",required = false) MultipartFile uphoto,
            @ApiParam(value = "管理员姓名",required = true)@RequestParam(value = "mname",required = true)String mname,
            @ApiParam(value = "管理员地址",required = true)@RequestParam(value = "maddress",required = true)String maddress){
        try {
            if (redisUtil.hasKey(uemail)){
                if (redisUtil.get(uemail).equals(code)){
                    if (managerService.selectByNameAndAdress(mname,maddress) != null){
                        return new ResultUtil().Error("201","名字和地址皆重复");
                    }
                    User user = new User();
                    user.setUaccount(uaccount);
                    user.setUpassword(upassword);
                    user.setUemail(uemail);
                    user.setUphone(uphone);
                    //默认头像
                    if (uphoto == null || uphoto.isEmpty()){
                        if (photourl == null){
                            user.setUphoto("http://39.96.68.53:70/auto.jpg");
                        }else{
                            user.setUphoto(photourl);
                        }
                    }else{
                        String filename = UUID.randomUUID().toString().replace("-","")+uphoto.getOriginalFilename();
                        user.setUphoto("http://39.96.68.53:70/"+filename);
                        ftpOperation.connectToServer();
                        ftpOperation.uploadToFtp(uphoto.getInputStream(),filename,"/photo");
                        ftpOperation.closeConnect();
                    }
                    userService.insertNewUser(user);
                    redisUtil.set(uaccount,user);
                    Manager manager = new Manager();
                    manager.setMaddress(maddress);
                    manager.setMname(mname);
                    managerService.insertManager(manager);
                    UR ur = new UR();
                    ur.setUid(user.getUid());
                    ur.setRid(4);
                    ur.setId(manager.getMid());
                    urService.insertNewUR(ur);
                    return new ResultUtil().Success();
                }
                return new ResultUtil().Error("400","验证码错误");
            }
            return new ResultUtil().Error("403","超时");
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PutMapping("/changeManagerInfo")
    @ResponseBody
    @ApiOperation(value = "修改管理员信息",notes = "201信息重复,500报错")
    public ResultDTO changeManagerInfo(
            @ApiParam(value = "管理员id",required = true)@RequestParam(value = "mid",required = true)Integer mid,
            @ApiParam(value = "管理员名字",required = false)@RequestParam(value = "mname",required = false)String mname,
            @ApiParam(value = "管理员地址",required = false)@RequestParam(value = "maddress",required = false)String maddress){
        try {
            Manager manager = new Manager();
            if (mname != null)manager.setMname(mname);
            if (maddress != null)manager.setMaddress(maddress);
            if (mname !=null && maddress != null){
                if ( managerService.selectByNameAndAdress(mname,maddress) != null){
                    return new ResultUtil().Error("201","管理员姓名，地址皆重复");
                }
            }
            manager.setMid(mid);
            managerService.updateManager(manager);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }


    @GetMapping("/findAllUser")
    @ResponseBody
    @ApiOperation(value = "查询全部用户",notes = "500 报错")
    public ResultDTO showAllUser(
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size,
            @ApiParam(value = "用户类型，1学生，2企业，3学校，4管理员 -1 全部",required = true)@RequestParam(value = "rid",required = true) Integer rid){
        try {
            //根据用户类型查询用户角色记录
            List<UR> urList = urService.selectURByRid(rid,start,size);
            List<UserDTO> userDTOList = new ArrayList<>();
            //查询用户和角色信息
            for (int i=0;i<urList.size();i++){
                //查询用户
                UserDTO userDTO = new UserDTO(userService.selectByUid(urList.get(i).getUid()));
                userDTO.setUpassword(null);
                //查询角色
                switch (urList.get(i).getRid()){
                    case 1: userDTO.setRoleObject( studentService.selectBySid(urList.get(i).getId()));break;
                    case 2: userDTO.setRoleObject(enterpriseService.selectByEid(urList.get(i).getId()));break;
                    case 3: userDTO.setRoleObject(collegeService.selectByCid(urList.get(i).getId()));break;
                    case 4: userDTO.setRoleObject(managerService.selectByMid(urList.get(i).getId()));break;
                }
                userDTO.setRole(urList.get(i).getRid());
                userDTOList.add(userDTO);
            }
            return new ResultUtil().Success(userDTOList);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @GetMapping("/showUserByAccount")
    @ResponseBody
    @ApiOperation(value = "通过账户查询用户",notes = "500 报错")
    public ResultDTO showUserByAccount(@ApiParam(value = "用户账号",required = true)@RequestParam(value = "uaccount",required = true)String uaccount){
        try {
            //查询用户信息
            UserDTO userDTO = new UserDTO(userService.selectByAccount(uaccount));
            userDTO.setUpassword(null);
            UR ur = urService.selectByUid(userDTO.getUid());
            Object object = null;
            //查询角色信息
            switch (ur.getRid()){
                case 1: object = studentService.selectBySid(ur.getId());break;
                case 2: object = enterpriseService.selectByEid(ur.getId());break;
                case 3: object = collegeService.selectByCid(ur.getId());break;
                case 4: object = managerService.selectByMid(ur.getId());break;
            }
            userDTO.setRole(ur.getRid());
            userDTO.setRoleObject(object);
            return new ResultUtil().Success(userDTO);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PostMapping("/sealedAccount")
    @ResponseBody
    @ApiOperation(value = "查封账号",notes = "500 报错")
    public ResultDTO sealedAccount(
            @ApiParam(value = "被查封用户账号",required = true)@RequestParam(value = "uaccount",required = true)String uaccount,
            @ApiParam(value = "被查封用户邮箱",required = true)@RequestParam(value = "uemail",required = true)String uemail,
            @ApiParam(value = "查封原因",required = true)@RequestParam(value = "message",required = true)String message,
            @ApiParam(value = "封号秒数",required = true)@RequestParam(value = "sealed",required = true)long sealed){
        try {
            //查询是否被查封过，有就增加封号时长
            if (redisUtil.hasKey(uaccount+"sealed") && redisUtil.getExpire(uaccount+"sealed") > 0){
                sealed += redisUtil.getExpire(uaccount);
            }
            //计算解封日期
            Date date = new Date(new Date().getTime()+sealed*1000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
            //发送邮件
            MailBase mailBase = new MailBase();
            mailBase.setSubject("海聘:账号查封通知");
            mailBase.setContent("账号:"+uaccount+"\n"+"查封原因:"+message+"\n"+"解封时间:"+simpleDateFormat.format(date));
            mailBase.setRecipient(uemail);
            //redis存储封号信息
            redisUtil.set(uaccount+"sealed","sealed",sealed);
            return sendMail.sendSimpleMail(mailBase);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }
   /* @PostMapping("/unsealedAccount")
    @ResponseBody
    @ApiOperation(value = "解封账号",notes = "500 报错")
    public ResultDTO unsealedAccount(
            @ApiParam(value = "被查封用户账号",required = true)@RequestParam(value = "uaccount",required = true)String uaccount,
            @ApiParam(value = "被查封用户邮箱",required = true)@RequestParam(value = "uemail",required = true)String uemail){
        try {

            //发送邮件
            MailBase mailBase = new MailBase();
            mailBase.setSubject("海聘:账号解封通知");
            mailBase.setContent("账号:"+uaccount+"\n"+"已经解封");
            mailBase.setRecipient(uemail);
            //redis存储封号信息
            redisUtil.del(uaccount+"sealed");
            return sendMail.sendSimpleMail(mailBase);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }*/




    @GetMapping("/NoCertificationEnterprise")
    @ResponseBody
    @ApiOperation(value = "显示没有进行企业身份认证的用户",notes = "500 报错")
    public ResultDTO NoCertificationEnterprise(
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size){
       try {
           //查询企业身份的用户
           List<UR> urList = urService.selectURByRid(2,start,size);
           List<UserDTO> userDTOList = new ArrayList<>();
           Enterprise enterprise = new Enterprise();
           UserDTO userDTO = new UserDTO();
           for (UR ur : urList){
                enterprise = enterpriseService.selectByEid(ur.getId());
               //筛选没有认证的企业
               if (enterprise.getEstate() == 0){
                   userDTO.setUserDTO(userService.selectByUid(ur.getUid()));
                   userDTO.setRoleObject(enterprise);
                   userDTO.setRole(ur.getRid());
                   userDTOList.add(userDTO);
               }
           }
           return new ResultUtil().Success(userDTOList);
       }catch (Exception e){
           log.info(e.toString());
           return new ResultUtil().Error("500",e.toString());
       }
    }
    @GetMapping("/NoCertificationCollege")
    @ResponseBody
    @ApiOperation(value = "显示没有进行学校身份认证的用户",notes = "500 报错")
    public ResultDTO NoCertificationCollege(
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size){
        try {
            //查询学校身份用户
            List<UR> urList = urService.selectURByRid(3,start,size);
            List<UserDTO> userDTOList = new ArrayList<>();
            College college = new College();
            UserDTO userDTO = new UserDTO();
            for (UR ur : urList){
                 college = collegeService.selectByCid(ur.getId());
                //筛选没有认证的用户
                if (college.getCstate() == 0){
                    userDTO.setUserDTO(userService.selectByUid(ur.getUid()));
                    userDTO.setRoleObject(college);
                    userDTO.setRole(ur.getRid());
                    userDTOList.add(userDTO);
                }
            }
            return new ResultUtil().Success(userDTOList);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PostMapping("/CertificationEnterprise")
    @ResponseBody
    @ApiOperation(value = "企业认证授权",notes = "500 报错")
    public ResultDTO CertificationEnterprise(@ApiParam(value = "企业id",required = true)@RequestParam(value = "eid",required = true)Integer eid){
        try {
            Enterprise enterprise = new Enterprise();
            enterprise.setEid(eid);
            enterprise.setEstate(1);
            enterpriseService.updateEnterprise(enterprise);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }

    }
    @PostMapping("/CertificationCollege")
    @ResponseBody
    @ApiOperation(value = "学校认证授权",notes = "500 报错")
    public ResultDTO CertificationCollege(@ApiParam(value = "学校id",required = true)@RequestParam(value = "cid",required = true)Integer cid){
        try {
            College college = new College();
            college.setCid(cid);
            college.setCstate(1);
            collegeService.updateCollegeInfo(college);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @DeleteMapping("nanageDeletePosition")
    @ResponseBody
    @ApiOperation(value = "管理员删除招聘职位",notes = "500 报错")
    public ResultDTO manageDeletePosition(
            @ApiParam(value = "删除原因",required = true)@RequestParam(value = "message")String message,
            @ApiParam(value = "职位id",required = true)@RequestParam(value = "pid")Integer pid){
        try {
            //找出职位
            SEPC sepc = sepcService.selectByPid(pid);
            UR ur = new UR();
            User user = null;
            MailBase mailBase = new MailBase();
            message  = positionService.selectByPid(pid).getPname()+"已经被删除\n原因:"+message;
            mailBase.setSubject("海聘:招聘信息删除");
            mailBase.setContent(message);
            /*邮箱：通知已经投递简历的学生 兼职已经删除*/
            List<SP> spList = spService.selectByPid(pid,0,0);
            for (SP sp : spList){
                ur.setRid(1);
                ur.setId(sp.getSid());
                user = userService.selectByUid( urService.selectURbyUR(ur,0,0).get(0).getUid());
                mailBase.setRecipient(user.getUemail());
                sendMail.sendSimpleMail(mailBase);
                /*聊天*/
                webSocketServer.sendMessage(message,1,user.getUid());
                spService.deleteBySid(sp.getSid());
            }
            /*通知招聘方兼职已经删除*/
            if (sepc.getSepctype() == 0){
                ur.setId(sepc.getSecid());
                ur.setRid(3);
            }else{
                ur.setId(sepc.getSecid());
                ur.setRid(2);
            }
            ur = urService.selectURbyUR(ur,0,0).get(0);
            user = userService.selectByUid(ur.getUid());
            mailBase.setRecipient(user.getUemail());
            webSocketServer.sendMessage(message,1,user.getUid());
            positionService.deleteByPid(pid);
            sepcService.deleteByPid(pid);
            return sendMail.sendSimpleMail(mailBase);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @DeleteMapping("deleteUser")
    @ResponseBody
    @ApiOperation(value = "除默认管理员外 删除用户",notes = "400 不能删除默认管理员 500 报错")
    public ResultDTO deleteUser(@ApiParam(value = "用户id",required = true)@RequestParam(value = "uid")Integer uid){
        try {
            UR ur = urService.selectByUid(uid);
            switch (ur.getRid()){
                case 1:
                    studentService.deleteStudent(ur.getId());
                    spService.deleteBySid(ur.getId());
                    break;
                case 2:
                    List<SEPC> sepcList = sepcService.selectBySepcTypeSepcStateSecid(1,-1,ur.getId(),0,0);
                    for (SEPC sepc : sepcList){
                        positionService.deleteByPid(sepc.getPid());
                        manageDeletePosition("发布者已经被永久封号",sepc.getPid());
                    }
                    enterpriseService.deleteEnterprise(ur.getId());
                    break;
                case 3:
                    List<SEPC> sepcList2 = sepcService.selectBySepcTypeSepcStateSecid(0,-1,ur.getId(),0,0);
                    for (SEPC sepc : sepcList2){
                        positionService.deleteByPid(sepc.getPid());
                        manageDeletePosition("发布者已经被永久封号",sepc.getPid());
                    }
                    collegeService.deleteCollage(ur.getId());
                    break;
                case 4:
                    if (ur.getId() == 1){
                        return new ResultUtil().Error("400","不能删除默认管理员");
                    }
                    managerService.deleteManager(ur.getId());break;
            }
            userService.deleteUser(uid,userService.selectByUid(uid).getUaccount());
            urService.deleteUR(ur.getUrid());
            return new ResultUtil().Success();
        }catch (Exception e){
            return new ResultUtil().Error("500",e.toString());
        }
    }
}
