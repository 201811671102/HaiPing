package pre.ysl.controller;


import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@Api(value = "用户controller",tags = "用户注册，登录，修改密码,找回密码,发送邮箱验证码,邮箱注册，发送邮箱注册验证码,修改头像")
@RequestMapping("/user")
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private URService urService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private SPService spService;
    @Autowired
    private SEPCService sepcService;
    @Autowired
    private FtpOperation ftpOperation;
    @Autowired
    private WebSocketServer webSocketServer;

    @PostMapping("/userLog")
    @ResponseBody
    @ApiOperation(value = "用户登录",notes = "600 没有验证 404 用户不存在 400 密码错误 500 报错")
    public ResultDTO userLog(
            HttpServletRequest request,
            @ApiParam(value = "用户账号",required = true)@RequestParam(value = "uaccount",required = true)String uaccount,
            @ApiParam(value = "用户密码",required = true)@RequestParam(value = "upassword",required = true)String upassword){
        try {
            Subject subject = SecurityUtils.getSubject();
            //查询是否封号
            if(redisUtil.hasKey(uaccount+"sealed")){
                if (redisUtil.getExpire(uaccount+"sealed") > 0){
                    long time = redisUtil.getExpire(uaccount+"sealed");
                    Date date = new Date(new Date().getTime()+time*1000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
                    return new ResultUtil().Error("401","账号查封致"+simpleDateFormat.format(date));
                }else{
                    redisUtil.del(uaccount+"sealed");
                }
            }
            //shiro
            UsernamePasswordToken token = new UsernamePasswordToken(uaccount,upassword);
            subject.login(token);
            User user = (User) subject.getPrincipal();
            int port = request.getRemotePort();
            //重复登录问题
          /*  if (redisUtil.hasKey(uaccount+"port") && redisUtil.get(uaccount+"port").equals(port)){
                redisUtil.del(uaccount+"port");
                WebSocketServer.sendMessage("账户在别的浏览器登录",1,user.getUid());
            }else{
                redisUtil.set(uaccount+"port",port);
            }*/
            UR ur = urService.selectByUid(user.getUid());
            UserDTO userDTO = new UserDTO(user);
            userDTO.setRole(ur.getRid());
            //注册中断，没有进行身份信息填写
            if(ur.getId() == 0){
                return new ResultUtil().Success("600",userDTO);
            }
            /*查询角色信息*/
            Object object = null;
            switch (ur.getRid()){
                case 1: object = studentService.selectBySid(ur.getId());break;
                case 2: object = enterpriseService.selectByEid(ur.getId());;break;
                case 3: object = collegeService.selectByCid(ur.getId());;break;
                case 4: object = managerService.selectByMid(ur.getId());;break;
            }
            userDTO.setRoleObject(object);
            userDTO.setUpassword(null);
            return new ResultUtil().Success(userDTO);
        }catch (UnknownAccountException e){
            return new ResultUtil().Error("404","用户不存在");
        }catch (IncorrectCredentialsException e){
            return new ResultUtil().Error("400","密码错误");
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }



    @PutMapping("/userChangePassword")
    @ResponseBody
    @ApiOperation(value = "用户修改密码",notes = "500报错")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "uaccount", value = "用户账户", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "upassword", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "unewpassword", value = "用户新密码", required = true, dataType = "String")
    })
    public ResultDTO userChangePassword(String uaccount, String upassword,String unewpassword){
        try {
            userService.updateUserPassowrd(uaccount,unewpassword);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PostMapping("/changePhoto")
    @ResponseBody
    @ApiOperation(value = "用户修改头像",notes = "覆盖头像 500 报错")
    public ResultDTO changePhoto(
            @ApiParam(value = "默认头像",required = false)@RequestParam(value = "photourl",required = false) String photourl,
            @ApiParam(value = "用户id",required = true)@RequestParam(value = "uid",required = true)Integer uid,
            @ApiParam(value = "用户头像",required = true)@RequestParam(value = "uphoto",required = true) MultipartFile uphoto){
      try {
          User user = userService.selectByUid(uid);
          //使用系统自带头像
          if (uphoto == null || uphoto.isEmpty()){
              if (StringUtils.isBlank(photourl)){
                  user.setUphoto("http://39.96.68.53:70/auto.jpg");
              }else{
                  user.setUphoto(photourl);
              }
          }else{
              //删除原有头像
              String filename = user.getUphoto();
              ftpOperation.connectToServer();
              filename = filename.substring(filename.lastIndexOf("/")+1);
              ftpOperation.delectFile(filename,"/photo");
              //新增头像
              filename = UUID.randomUUID().toString().replace("-","")+uphoto.getOriginalFilename();
              user.setUphoto("http://39.96.68.53:70/"+filename);
              ftpOperation.uploadToFtp(uphoto.getInputStream(),filename,"/photo");
              ftpOperation.closeConnect();
          }
          userService.updateUserByUid(user);
          return new ResultUtil().Success();
      }catch (Exception e){
          log.info(e.toString());
          return new ResultUtil().Error("500",e.toString());
      }
    }

    @PostMapping("/userFindPasswordMail")
    @ResponseBody
    @ApiOperation(value = "邮箱验证码",notes = "400 邮箱错误 401 重复发送验证码 500 报错")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "uaccount",value = "用户账号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "email",value = "用户邮箱",required = true,dataType = "String"),
    })
    public ResultDTO userFindPasswordMail(String uaccount,String email){
       try {
           User user = userService.selectByAccount(uaccount);
           if (!user.getUemail().equals(email)){
               return new ResultUtil().Error("400","邮箱错误");
           }
           //验证码时长90秒
           if (redisUtil.hasKey(email)){
               if (redisUtil.getExpire(email) > 0){
                   //多次提交
                   return new ResultUtil().Error("401","已经发送验证码到邮箱"+email);
               }
               //删除没有使用的过期验证码
               redisUtil.del(email);
           }
           return SendCode(email,4);
       }catch (Exception e){
           log.info(e.toString());
           return new ResultUtil().Error("500",e.toString());
       }
    }


    @PutMapping("/userFindPassword")
    @ApiOperation(value = "修改新密码",notes = "400 验证码错误 401 超时，验证码无效 500 报错")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "uaccount",value = "用户账户",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "uemail",value = "用户邮箱",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "upassword",value = "用户新密码",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "code",value = "验证码",required = true,dataType = "String"),
    })
    public ResultDTO userFindPassword(String uemail,@RequestParam String uaccount,String upassword,String code){
        try {
            if (redisUtil.hasKey(uemail)){
                if (redisUtil.get(uemail).equals(code)){
                    redisUtil.del(uemail);
                    userService.updateUserPassowrd(uaccount,upassword);
                    return new ResultUtil().Success();
                }
                return new ResultUtil().Error("400","验证码错误");
            }
            return new ResultUtil().Error("401","超时，验证码无效");
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }




    @PostMapping("/userRegisteredEmailCode")
    @ResponseBody
    @ApiOperation(value = "邮箱注册验证码",notes = "201 邮箱,账户电话 已经注册 500 报错")
    public ResultDTO userRegisteredEmailCode(
            @ApiParam(value = "用户手机",required = true)@RequestParam(value = "uphone",required = true)String uphone,
            @ApiParam(value = "用户账号",required = true)@RequestParam(value = "uaccount",required = true)String uaccount,
            @ApiParam(value = "邮箱",required = true)@RequestParam(value = "uemail",required = true) String uemail){
        try {
            StringBuilder stringBuilder = new StringBuilder();
            if (!userService.selectByEmail(uemail)){
                stringBuilder.append("邮箱已注册");
            }
            if (userService.selectByAccount(uaccount) != null) {
                stringBuilder.append("账号已被注册");
            }
            if (!userService.selectByPhone(uphone)){
                stringBuilder.append("电话已被注册");
            }
            if (stringBuilder.length() != 0)
                return new ResultUtil().Error("201",stringBuilder.toString());
            return SendCode(uemail,4);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }



    @PostMapping("/userRegisteredByEmail")
    @ResponseBody
    @ApiOperation(value = "用户邮箱注册",notes = "400 验证码错误 401 超时，验证码无效 500 报错")
    public ResultDTO userRegisteredByEmail(
            @ApiParam(value = "用户账号",required = true)@RequestParam(value = "uaccount",required = true)String uaccount,
            @ApiParam(value = "用户密码",required = true)@RequestParam(value = "upassword",required = true)String upassword,
            @ApiParam(value = "用户验证码",required = true)@RequestParam(value = "code",required = true)String code,
            @ApiParam(value = "用户手机",required = true)@RequestParam(value = "uphone",required = true)String uphone,
            @ApiParam(value = "邮箱",required = true)@RequestParam(value = "uemail",required = true) String uemail,
            @ApiParam(value = "默认头像",required = false)@RequestParam(value = "photourl",required = false) String photourl,
            @ApiParam(value = "用户头像",required = false)@RequestParam(value = "uphoto",required = false) MultipartFile uphoto,
            @ApiParam(value = "用户类型",required = true)@RequestParam(value = "rid",required = true) Integer rid){
        try {
            User user = new User();
            if (redisUtil.hasKey(uemail)){
                if (redisUtil.get(uemail).equals(code)){
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
                    UR ur = new UR();
                    ur.setUid(user.getUid());
                    ur.setRid(rid);
                    ur.setId(0);
                    redisUtil.set(uaccount,user);
                    urService.insertNewUR(ur);
                    return new ResultUtil().Success(user);
                }
                return new ResultUtil().Error("400","验证码错误");
            }
            return new ResultUtil().Error("403","超时");
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }




    public  ResultDTO SendCode(String uemail,Integer codeLeng){
        MailBase mailBase = new MailBase();
        mailBase.setRecipient(uemail);
        mailBase.setSubject("海聘");
        String var = "";
        for (int i = 0; i < codeLeng;i++){
            var+=Math.round(Math.random()*9)+"";
        }
        mailBase.setContent("您的验证为:"+var+"\n"+"此验证码将于90秒后失效");
        if (redisUtil.hasKey(uemail)){
            redisUtil.del(uemail);
        }
        redisUtil.set(uemail,var);
        redisUtil.expire(uemail,90);
        return sendMail.sendSimpleMail(mailBase);
    }

    @PostMapping("/upResume")
    @ResponseBody
    @ApiOperation(value = "上传简历(覆盖)",notes = "500报错")
    public ResultDTO upResume(
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid,
            @ApiParam(value = "简历",required = true)@RequestParam(value = "resume",required = true)MultipartFile resume){
        try {
            Student student = studentService.selectBySid(sid);
            ftpOperation.connectToServer();
            //删除原有简历
            if ( !StringUtils.isBlank(student.getSresume())){
                String filename = student.getSresume();
                filename = filename.substring(filename.lastIndexOf("/")+1);
                ftpOperation.delectFile(filename,"/resume");
            }
            String resumename = UUID.randomUUID().toString().replace("-","")+resume.getOriginalFilename();
            student.setSresume("ftp://39.96.68.53:6060/resume/"+ resumename);
            ftpOperation.uploadToFtp(resume.getInputStream(),resumename,"/resume");
            ftpOperation.closeConnect();
            studentService.updateStudentinfo(student);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }
    @GetMapping("/ifAlreadyUPResume")
    @ResponseBody
    @ApiOperation(value = "查看学生是否已经上传简历",notes = "400 没有上传 500 报错")
    public ResultDTO ifAlreadyUPResume(
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid){
        try {
            Student student = studentService.selectBySid(sid);
            if (!StringUtils.isBlank(student.getSresume())){
                return new ResultUtil().Success("已经上传");
            }
            return new ResultUtil().Error("400","没有上传");
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @GetMapping("/downloadResume")
    @ResponseBody
    @ApiOperation(value = "下载简历",notes = "400 没有上传简历 500报错")
    public ResultDTO downloadResume(HttpServletRequest request, HttpServletResponse response,
                  @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid){
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        FileInputStream fileInputStream = null;
        try {
//设置响应的内容格式，force-download表示只要点击下载按钮就会自动下载文件
            response.setContentType("application/force-download");
            String browser = request.getHeader("User-Agent");
            Student student = studentService.selectBySid(sid);
            if (StringUtils.isBlank(student.getSresume())){
                return new ResultUtil().Error("400","没有上传简历");
            }
            String fileName = student.getSresume();
            if (-1 < browser.indexOf("MSIE 6.0") || -1 < browser.indexOf("MSIE 7.0")) {
                // IE6, IE7 浏览器
                response.addHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            } else if (-1 < browser.indexOf("MSIE 8.0")) {
                // IE8
                response.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("MSIE 9.0")) {
                // IE9
                response.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("Chrome")) {
                // 谷歌
                response.addHeader("content-disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("Safari")) {
                // 苹果
                response.addHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                // 火狐或者其他的浏览器
                response.addHeader("content-disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            }
            ftpOperation.connectToServer();
            fileName = fileName.substring(fileName.lastIndexOf("/")+1);
            InputStream inputStream = ftpOperation.downloadFile(fileName,"/resume");
            bufferedInputStream = new BufferedInputStream(inputStream);
            outputStream = response.getOutputStream();
            int temp = 0;
            while ((temp = bufferedInputStream.read()) != -1){
                outputStream.write(temp);
            }
            ftpOperation.closeConnect();
            return null;
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }finally {
            if (fileInputStream !=null ){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.info(e.toString());
                    return new ResultUtil().Error("500",e.toString());
                }
            }
            if (bufferedInputStream !=null ){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    log.info(e.toString());
                    return new ResultUtil().Error("500",e.toString());
                }
            }
            if (outputStream !=null ){
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    log.info(e.toString());
                    return new ResultUtil().Error("500",e.toString());
                }
            }
        }
    }

    @GetMapping("/downloadResumeByEmail")
    @ResponseBody
    @ApiOperation(value = "邮箱下载简历",notes = "400 没有上传简历 500 报错")
    public ResultDTO downloadResumeByEmail(
            @ApiParam(value = "目标邮箱",required = true)@RequestParam(value = "email",required = true)String email,
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid){
        try {
            Student student = studentService.selectBySid(sid);
            if (StringUtils.isBlank(student.getSresume())){
                return new ResultUtil().Error("400","该用户没有上传简历");
            }
            MailBase mailBase = new MailBase();
            mailBase.setSubject("海聘");
            mailBase.setContent(student.getSname()+"的简历");
            mailBase.setRecipient(email);
            ftpOperation.connectToServer();
            String fileName = student.getSresume();
            fileName = fileName.substring(fileName.lastIndexOf("/")+1);
            InputStream inputStream = ftpOperation.downloadFile(fileName,"/resume");
            return sendMail.sendAttachmentMail(mailBase,fileName,inputStream);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }finally {
            ftpOperation.closeConnect();
        }

    }

    @DeleteMapping("/deleteResume")
    @ResponseBody
    @ApiOperation(value = "删除简历",notes = "400 没有上传 500 报错")
    public ResultDTO deleteResume( @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid){
        try {
            Student student = studentService.selectBySid(sid);
            if (StringUtils.isBlank(student.getSresume())){
                return new ResultUtil().Error("400","该用户没有上传简历");
            }
            String fileName = student.getSresume();
            fileName = fileName.substring(fileName.lastIndexOf("/")+1);
            ftpOperation.connectToServer();
            ftpOperation.delectFile(fileName,"/resume");
            ftpOperation.closeConnect();
            student.setSresume(null);
            studentService.updateStudentinfo(student);
            return new ResultUtil().Success("成功删除"+student.getSname()+"的简历");
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PostMapping("/sentResume")
    @ResponseBody
    @ApiOperation(value = "向企业|学校投递简历",notes = "403 不能在等待回应状态重复投递简历 402 兼职已经停止招聘 400 没有上传 401 非已认证学生不能应聘勤工俭学兼职 500 报错")
    public ResultDTO sentResume(
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid,
            @ApiParam(value = "兼职id",required = true)@RequestParam(value = "pid",required = true)Integer pid,
            @ApiParam(value = "企业|学校邮箱",required = true)@RequestParam(value = "email",required = true)String email){
       try {
           Student student = studentService.selectBySid(sid);
           SEPC sepc = sepcService.selectByPid(pid);
           if (sepc.getSepcstate() == 1){
               return new ResultUtil().Error("402","该兼职已经停止招聘");
           }
           if (sepc.getSepctype() == 0 && student.getSstate() == 0){
               return  new ResultUtil().Error("401","非已认证学生不能应聘勤工俭学兼职");
           }
           if (spService.selectBySp(pid,sid,-1) != null){
               return new ResultUtil().Error("403","不能在等待回应状态重复投递简历");
           }
           SP sp  = new SP();
           sp.setPid(pid);
           sp.setSid(student.getSid());
           sp.setSepcstate(0);
           spService.insertSP(sp);
           sepc.setSepctype(0);
           sepcService.updateSEPCByid(sepc);
           if (!StringUtils.isBlank(student.getSresume())){
               MailBase mailBase = new MailBase();
               mailBase.setSubject("海聘:有一名学生向您递交了一份简历，请查看");
               mailBase.setContent(student.getSname()+"的简历");
               mailBase.setRecipient(email);
               ftpOperation.connectToServer();
               String fileName = student.getSresume();
               fileName = fileName.substring(fileName.lastIndexOf("/")+1);
               InputStream inputStream = ftpOperation.downloadFile(fileName,"/resume");
               return sendMail.sendAttachmentMail(mailBase,fileName,inputStream);
           }
           return new ResultUtil().Error("400","没有上传简历");
       }catch (Exception e){
           log.info(e.toString());
           return new ResultUtil().Error("500",e.toString());
       }finally {
           ftpOperation.closeConnect();
       }
    }
}
