package pre.ysl.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pre.ysl.base.ResultUtil;
import pre.ysl.base.dto.ResultDTO;
import pre.ysl.base.mail.MailBase;
import pre.ysl.dto.PositionDTO;
import pre.ysl.dto.StudentDTO;
import pre.ysl.dto.UserDTO;
import pre.ysl.mail.SendMail;
import pre.ysl.pojo.*;
import pre.ysl.service.*;
import pre.ysl.websocket.WebSocketServer;

import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value = "职位Controller",tags = "新增职位,删除职位，修改职位，查询勤工俭学职位，查询企业职位")
@RequestMapping("/positionController")
@Log4j2
public class PositionController {
    @Autowired
    private PositionService positionService;
    @Autowired
    private UserService userService;
    @Autowired
    private URService urService;
    @Autowired
    private SEPCService sepcService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private SPService spService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private WebSocketServer webSocketServer;


    @ResponseBody
    @PostMapping("/addPosition")
    @ApiOperation(value = "新增职位 ",notes = "400 非已认证学校，企业用户不能发布招聘 201 兼职信息重复 500 报错")
    public ResultDTO addPosition(
            @ApiParam(value = "用户id",required = true)@RequestParam(value = "uid",required = true)Integer uid,
            @ApiParam(value = "职位名字",required = true)@RequestParam(value = "pName",required = true)String pName,
            @ApiParam(value = "职位分类",required = true)@RequestParam(value = "pType",required = true)String pType,
            @ApiParam(value = "职位描述",required = true)@RequestParam(value = "pDescribe",required = true)String pDescribe,
            @ApiParam(value = "职位要求",required = true)@RequestParam(value = "pRequirements",required = true)String pRequirements,
            @ApiParam(value = "职位薪酬",required = true)@RequestParam(value = "pCompensation",required = true)Integer pCompensation,
            @ApiParam(value = "职位福利",required = true)@RequestParam(value = "pWelfare",required = true)String pWelfare,
            @ApiParam(value = "职位工作地址",required = true)@RequestParam(value = "pAddress",required = true)String pAddress){
        Position position = new Position();
        position.setPtype(pType);
        position.setPdescribe(pDescribe);
        position.setPname(pName);
        position.setPaddress(pAddress);
        position.setPrequirements(pRequirements);
        position.setPcompensation(pCompensation);
        if (pWelfare!=null)position.setPwelfare(pWelfare);
        position.setPaddress(pAddress);
        try {
            if (positionService.selectPosition(position) != null){
                return new ResultUtil().Error("201","所发布职位重复");
            }
            UR ur = urService.selectByUid(uid);
            if (ur.getRid() == 2){
                if (enterpriseService.selectByEid(ur.getId()).getEstate() == 0){
                    return new ResultUtil().Error("400","非已认证企业用户不能发布招聘");
                }
            }else if (ur.getRid() == 3){
                if (collegeService.selectByCid(ur.getId()).getCstate() == 0){
                    return new ResultUtil().Error("400","非已认证学校用户不能发布招聘");
                }
            }
            //添加兼职
            positionService.insertPosition(position);
            SEPC sepc = new SEPC();
            //默认招聘状态为0
            sepc.setSepcstate(0);
            sepc.setSecid(ur.getId());
            sepc.setPid(position.getPid());
            if (ur.getRid() == 3){
                sepc.setSepctype(0);
            }else if (ur.getRid() == 2){
                sepc.setSepctype(1);
            }
            //添加兼职招聘方记录
            sepcService.insertSEPC(sepc);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }


    @DeleteMapping("deletePosition")
    @ResponseBody
    @ApiOperation(value = "招聘方删除招聘职位",notes = "500 报错")
    public ResultDTO deletePosition(
            @ApiParam(value = "职位id",required = true)@RequestParam(value = "pid",required = true)Integer pid){
        try {
            SEPC sepc = sepcService.selectByPid(pid);
            MailBase mailBase = new MailBase();
            String message  = "十分抱歉，您所应聘的兼职“"+positionService.selectByPid(pid).getPname()+"”已经被删除";
            mailBase.setSubject("海聘:招聘信息删除");
            mailBase.setContent(message);
            Integer bossid;
            if (sepc.getSepctype() == 0){
                bossid = urService.selectByRidId(3,sepc.getSecid()).getUid();
            }else{
                bossid = urService.selectByRidId(2,sepc.getSecid()).getUid();
            }

            /*邮箱：通知已经投递简历的学生 兼职已经删除*/
            List<SP> spList = spService.selectByPid(pid,0,0);
            for (SP sp : spList){
                UR ur = new UR();
                ur.setRid(1);
                ur.setId(sp.getSid());
                User user = userService.selectByUid( urService.selectURbyUR(ur,0,0).get(0).getUid());
                mailBase.setRecipient(user.getUemail());
                sendMail.sendSimpleMail(mailBase);
                /*聊天*/
                webSocketServer.sendMessage(message,bossid,user.getUid());
                spService.deleteBySid(sp.getSid());
            }
            positionService.deleteByPid(pid);
            sepcService.deleteByPid(pid);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @PutMapping("/changePosition")
    @ApiOperation(value = "修改职位",notes = "500 报错")
    public ResultDTO changePosition(
            @ApiParam(value = "职位id",required = true)@RequestParam(value = "pid",required = true)Integer pid,
            @ApiParam(value = "职位名字",required = false)@RequestParam(value = "pName",required = false)String pName,
            @ApiParam(value = "职位分类",required = false)@RequestParam(value = "pType",required = false)String pType,
            @ApiParam(value = "职位描述",required = false)@RequestParam(value = "pDescribe",required = false)String pDescribe,
            @ApiParam(value = "职位要求",required = false)@RequestParam(value = "pRequirements",required = false)String pRequirements,
            @ApiParam(value = "职位薪酬",required = false)@RequestParam(value = "pCompensation",required = false)Integer pCompensation,
            @ApiParam(value = "职位福利",required = false)@RequestParam(value = "pWelfare",required = false)String pWelfare,
            @ApiParam(value = "职位工作地址",required = false)@RequestParam(value = "pAddress",required = false)String pAddress){
        try {
            Position position = positionService.selectByPid(pid);
            position.setPid(pid);
            if (pName != null)position.setPname(pName);
            if (pType != null)position.setPtype(pType);
            if (pDescribe != null)position.setPdescribe(pDescribe);
            if (pRequirements != null)position.setPrequirements(pRequirements);
            if (pCompensation != null)position.setPcompensation(pCompensation);
            if (pWelfare != null)position.setPwelfare(pWelfare);
            if (pAddress != null)position.setPaddress(pAddress);
            SEPC sepc = sepcService.selectByPid(pid);
            Integer bossid;
            if (sepc.getSepctype() == 0){
                bossid = urService.selectByRidId(3,sepc.getSecid()).getUid();
            }else{
                bossid = urService.selectByRidId(2,sepc.getSecid()).getUid();
            }
            /*招聘状态不为空*/
            if (sepc.getSepcstate() != 0){
                MailBase mailBase = new MailBase();
                String message  = positionService.selectByPid(pid).getPname()+"进行了修改";
                mailBase.setSubject("海聘:招聘信息进行了修改");
                mailBase.setContent(message);
                /*邮箱：通知已经投递简历的学生 兼职已经删除*/
                List<SP> spList = spService.selectByPid(pid,0,0);
                for (SP sp : spList){
                    UR ur = new UR();
                    ur.setRid(1);
                    ur.setId(sp.getSid());
                    User user = userService.selectByUid( urService.selectURbyUR(ur,0,0).get(0).getUid());
                    mailBase.setRecipient(user.getUemail());
                    sendMail.sendSimpleMail(mailBase);
                    /*聊天*/
                    webSocketServer.sendMessage(message,bossid,user.getUid());
                }
            }
            positionService.updatePosition(position);
            return new ResultUtil().Success( );
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }

    }

    @GetMapping("/showPositionByAttribute")
    @ResponseBody
    @ApiOperation(value = "根据属性查询招聘职位",notes = "401 非已经认证学生 500 报错")
    public ResultDTO showPositionByAttribute(
            @ApiParam(value = "用户id",required = true)@RequestParam(value = "uid",required = true)Integer uid,
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = true)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = true)Integer size,
            @ApiParam(value = "职位名字",required = true)@RequestParam(value = "pName",required = true)String pName){
        try {
            List<Position> positionList = positionService.getPosititonByAttribute(pName,start,size);
            UR ur = urService.selectByUid(uid);
            List<PositionDTO> positionDTOList = new ArrayList<>();
            UserDTO userDTO = new UserDTO();
            switch (ur.getRid()){
                case 1:
                    for (int i = 0 ;i < positionList.size(); i++){
                        PositionDTO positionDTO = new PositionDTO();
                        SEPC sepc = sepcService.selectByPid(positionList.get(i).getPid());
                        if (sepc.getSepctype() == 1){
                            Enterprise enterprise = enterpriseService.selectByEid(sepc.getSecid());
                            UR ur1 = urService.selectByRidId(2,sepc.getSecid());
                            userDTO.setUserDTO(userService.selectByUid(ur1.getUid()));
                            userDTO.setRoleObject(enterprise);
                            userDTO.setRole(ur.getRid());
                            positionDTO.PositionDTO(positionList.get(i));
                            positionDTO.setUserObject(userDTO);
                            positionDTO.setSepctype(sepc.getSepctype());
                            positionDTO.setSpState(sepc.getSepcstate());
                            positionDTO.setSpid(sepc.getSepcid());
                            positionDTOList.add(positionDTO);
                        }else{
                            Student student = studentService.selectBySid(ur.getId());
                            if (student.getSstate() == 0){
                                return new ResultUtil().Error("401 ","非已认证学生不能应聘勤工俭学兼职");
                            }
                            College college = collegeService.selectByCid(sepc.getSecid());
                            if (student.getScollege().equals(college.getCname())){
                                UR ur1 = urService.selectByRidId(3,sepc.getSecid());
                                userDTO.setUserDTO(userService.selectByUid(ur1.getUid()));
                                userDTO.setRoleObject(college);
                                userDTO.setRole(ur.getRid());
                                positionDTO.PositionDTO(positionList.get(i));
                                positionDTO.setUserObject(userDTO);
                                positionDTO.setSepctype(sepc.getSepctype());
                                positionDTO.setSpState(sepc.getSepcstate());
                                positionDTO.setSpid(sepc.getSepcid());
                                positionDTOList.add(positionDTO);
                            }
                        }
                    }
                    return new ResultUtil().Success(positionDTOList);
                case 2:
                    for (int i = 0 ;i < positionList.size(); i++){
                        PositionDTO positionDTO = new PositionDTO();
                        SEPC sepc = sepcService.selectByPid(positionList.get(i).getPid());
                        if (sepc.getSepctype() == 1){
                            Enterprise enterprise = enterpriseService.selectByEid(sepc.getSecid());
                            UR ur1 = urService.selectByRidId(2,sepc.getSecid());
                            userDTO.setUserDTO(userService.selectByUid(ur1.getUid()));
                            userDTO.setRoleObject(enterprise);
                            userDTO.setRole(ur.getRid());
                            positionDTO.PositionDTO(positionList.get(i));
                            positionDTO.setUserObject(userDTO);
                            positionDTO.setSepctype(sepc.getSepctype());
                            positionDTO.setSpState(sepc.getSepcstate());
                            positionDTO.setSpid(sepc.getSepcid());
                            positionDTOList.add(positionDTO);
                        }
                    }
                    return new ResultUtil().Success(positionDTOList);
                case 3:
                    for (int i = 0 ;i < positionList.size(); i++){
                        PositionDTO positionDTO = new PositionDTO();
                        SEPC sepc = sepcService.selectByPid(positionList.get(i).getPid());
                        if (sepc.getSepctype() == 1){
                            Enterprise enterprise = enterpriseService.selectByEid(sepc.getSecid());
                            UR ur1 = urService.selectByRidId(2,sepc.getSecid());
                            userDTO.setUserDTO(userService.selectByUid(ur1.getUid()));
                            userDTO.setRoleObject(enterprise);
                            userDTO.setRole(ur.getRid());
                            positionDTO.PositionDTO(positionList.get(i));
                            positionDTO.setUserObject(userDTO);
                            positionDTO.setSepctype(sepc.getSepctype());
                            positionDTO.setSpState(sepc.getSepcstate());
                            positionDTO.setSpid(sepc.getSepcid());
                            positionDTOList.add(positionDTO);
                        }else{
                            College college = collegeService.selectByCid(sepc.getSecid());
                            if(sepc.getSecid() == ur.getId()){
                                UR ur1 = urService.selectByRidId(3,sepc.getSecid());
                                userDTO.setUserDTO(userService.selectByUid(ur1.getUid()));
                                userDTO.setRoleObject(college);
                                userDTO.setRole(ur.getRid());
                                positionDTO.PositionDTO(positionList.get(i));
                                positionDTO.setUserObject(userDTO);
                                positionDTO.setSepctype(sepc.getSepctype());
                                positionDTO.setSpState(sepc.getSepcstate());
                                positionDTO.setSpid(sepc.getSepcid());
                                positionDTOList.add(positionDTO);
                            }
                        }
                    }
                    return new ResultUtil().Success(positionDTOList);
                case 4:
                    for (int i = 0 ;i < positionList.size(); i++){
                        PositionDTO positionDTO = new PositionDTO();
                        SEPC sepc = sepcService.selectByPid(positionList.get(i).getPid());
                        if (sepc.getSepctype() == 1){
                            Enterprise enterprise = enterpriseService.selectByEid(sepc.getSecid());
                            UR ur1 = urService.selectByRidId(2,sepc.getSecid());
                            userDTO.setUserDTO(userService.selectByUid(ur1.getUid()));
                            userDTO.setRoleObject(enterprise);
                            userDTO.setRole(ur.getRid());
                            positionDTO.PositionDTO(positionList.get(i));
                            positionDTO.setUserObject(userDTO);
                            positionDTO.setSepctype(sepc.getSepctype());
                            positionDTO.setSpState(sepc.getSepcstate());
                            positionDTO.setSpid(sepc.getSepcid());
                            positionDTOList.add(positionDTO);
                        }else{
                            College college = collegeService.selectByCid(sepc.getSecid());
                            UR ur1 = urService.selectByRidId(3,sepc.getSecid());
                            userDTO.setUserDTO(userService.selectByUid(ur1.getUid()));
                            userDTO.setRoleObject(college);
                            userDTO.setRole(ur.getRid());
                            positionDTO.PositionDTO(positionList.get(i));
                            positionDTO.setUserObject(userDTO);
                            positionDTO.setSepctype(sepc.getSepctype());
                            positionDTO.setSpState(sepc.getSepcstate());
                            positionDTO.setSpid(sepc.getSepcid());
                            positionDTOList.add(positionDTO);
                        }
                    }
                    return new ResultUtil().Success(positionDTOList);
            }
            return new ResultUtil().Success();
        }catch (IndexOutOfBoundsException e){
            return new ResultUtil().Success();
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @GetMapping("/showAllPositionOn")
    @ResponseBody
    @ApiOperation(value = "查询招聘情况 ",notes = "500 报错 ")
    public ResultDTO showAllPositionOn(
            @ApiParam(value = "招聘方类型(-1 全部 0学校，1企业)",required = true)@RequestParam(value = "sepcType",required = true)Integer sepcType,
            @ApiParam(value = "招聘状态(-1 全部 0招聘，1不招聘)",required = true)@RequestParam(value = "sepcState",required = true)Integer sepcState,
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size){
        try {
            //查询符合条件在职位
            List<SEPC> sepcList = sepcService.selectBySepcTypeSepcStateSecid(sepcType,sepcState,-1,start,size);
            List<PositionDTO> positionDTOList = new ArrayList<>();
            UserDTO userDTO = new UserDTO();
            UR ur = new UR();
            for (int i = 0 ;i < sepcList.size(); i++){
                PositionDTO positionDTO = new PositionDTO(positionService.selectByPid(sepcList.get(i).getPid()));
                //查询职位发布者信息
                switch (sepcList.get(i).getSepctype()){
                    case 0:
                        College college = collegeService.selectByCid(sepcList.get(i).getSecid());
                        ur = urService.selectByRidId(3,college.getCid());
                        userDTO.setUserDTO(userService.selectByUid(ur.getUid()));
                        userDTO.setUpassword(null);
                        userDTO.setRole(ur.getRid());
                        userDTO.setRoleObject(college);
                        positionDTO.setUserObject(userDTO);
                        positionDTO.setSepctype(sepcList.get(i).getSepctype());
                        positionDTO.setSpState(sepcList.get(i).getSepcstate());
                        positionDTO.setSpid(sepcList.get(i).getSepcid());
                        break;
                    case 1:
                        Enterprise enterprise = enterpriseService.selectByEid(sepcList.get(i).getSecid());
                        ur = urService.selectByRidId(2,enterprise.getEid());
                        userDTO.setUserDTO(userService.selectByUid(ur.getUid()));
                        userDTO.setUpassword(null);
                        userDTO.setRole(ur.getRid());
                        userDTO.setRoleObject(enterprise);
                        positionDTO.setUserObject(userDTO);
                        positionDTO.setSepctype(sepcList.get(i).getSepctype());
                        positionDTO.setSpState(sepcList.get(i).getSepcstate());
                        positionDTO.setSpid(sepcList.get(i).getSepcid());
                        break;
                }
                positionDTOList.add(positionDTO);
            }
            return new ResultUtil().Success(positionDTOList);
        }catch (NullPointerException e){
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }



    @GetMapping("checkPosition")
    @ResponseBody
    @ApiOperation(value = "查询应聘者信息",notes = "500 报错")
    public ResultDTO checkPosition(
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size,
            @ApiParam(value = "兼职id",required = true)@RequestParam(value = "pid",required = true)Integer pid){
        try {
            //查询符合要求的应聘记录
            List<SP> spList = spService.selectByPid(pid,start,size);
            List<UserDTO> userDTOList  = new ArrayList<>();
            //查询应聘者学生信息和用户信息
            for (SP sp : spList){
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.StudentDTO(studentService.selectBySid(sp.getSid()));
                studentDTO.setSpState(sp.getSepcstate());
                UR ur = urService.selectByRidId(1,studentDTO.getSid());
                UserDTO userDTO = new UserDTO(userService.selectByUid(ur.getUid()));
                userDTO.setRole(1);
                userDTO.setUpassword(null);
                userDTO.setRoleObject(studentDTO);
                userDTOList.add(userDTO);
            }
            return new ResultUtil().Success(userDTOList);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }

    }

    @PutMapping("passPosition")
    @ResponseBody
    @ApiOperation(value = "审核招聘者",notes = "setSepctype 0等候 1 中选 2 落选 500 报错")
    public ResultDTO passPosition(
            @ApiParam(value = "兼职id",required = true)@RequestParam(value = "pid",required = true)Integer pid,
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid,
            @ApiParam(value = "通过(1)淘汰(0)",required = true)@RequestParam(value = "ifpass")Integer ifpasss){
        try {
            SP sp = spService.selectBySp(pid,sid,-1);
            if (ifpasss == 1){
                sp.setSepcstate(1);
            }else{
                sp.setSepcstate(2);
            }
            spService.updateSP(sp);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PostMapping("/getNumberInPosition")
    @ResponseBody
    @ApiOperation(value = "兼职应聘者数量",notes = "500 报错")
    public ResultDTO getNumberInPosition(@ApiParam(value = "兼职id",required = true)@RequestParam(value = "pid",required = true)Integer pid){
        try {
            return new ResultUtil().Success(spService.selectNumberByPid(pid));
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PutMapping("/changeStateOFPosition")
    @ResponseBody
    @ApiOperation(value = "修改招聘状态，不再招人",notes = "500 报错")
    public ResultDTO changeStateOFPosition(
            @ApiParam(value = "兼职id",required = true)@RequestParam(value = "pid",required = true)Integer pid){
        try {
            SEPC sepc = sepcService.selectByPid(pid);
            sepc.setSepcstate(1);
            return new ResultUtil().Success(sepcService.updateSEPCByid(sepc));
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }
}
