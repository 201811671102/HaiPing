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
import pre.ysl.dto.PositionDTO;
import pre.ysl.dto.UserDTO;
import pre.ysl.pojo.*;
import pre.ysl.service.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/student")
@Api(value = "学生Controller",tags = "添加个人信息,学生认证,修改个人信息,上传简历")
@Log4j2
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private URService urService;
    @Autowired
    private SPService spService;
    @Autowired
    private SEPCService sepcService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping("/studentAndInfo")
    @ResponseBody
    @ApiOperation(value = "学生添加个人信息",notes = "个人信息 201 微信号已经被注册 500 报错")
    public ResultDTO studentAndInfo(
            @ApiParam(value = "用户id",required = true)@RequestParam(value = "uid",required = true) Integer uid,
            @ApiParam(value = "学生姓名",required = true)@RequestParam(value = "sname",required = true) String sname,
            @ApiParam(value = "学生性别",required = true)@RequestParam(value = "ssex",required = true) String ssex,
            @ApiParam(value = "学生学历",required = true)@RequestParam(value = "seducation",required = true)String seducation,
            @ApiParam(value = "学生工作经验",required = false)@RequestParam(value = "sworkexperience",required = false)String sworkexperience,
            @ApiParam(value = "学生微信",required = false)@RequestParam(value = "swechar",defaultValue = "无",required = false) String swechar,
            @ApiParam(value = "学生学校名",required = false)@RequestParam(value = "scollege",defaultValue = "无",required = false)String scollege,
            @ApiParam(value = "学生学号",required = false)@RequestParam(value = "sstudentnumber",defaultValue = "无",required = false)String sstudentnumber,
            @ApiParam(value = "学生年级",required = false)@RequestParam(value = "sgrade",defaultValue = "无",required = false)String sgrade,
            @ApiParam(value = "学生学校地址",required = false)@RequestParam(value = "scollegeaddress",defaultValue = "无",required = false)String scollegeaddress){
        try {
            UR ur = urService.selectByUid(uid);
            Student student = new Student();
            student.setSname(sname);
            student.setSstate(0);
            student.setSsex(ssex);
            student.setSeducation(seducation);
            student.setSworkexperience(sworkexperience);
            if (studentService.selectByWechar(swechar) != null){
                return new ResultUtil().Error("201",swechar+"微信号已被注册");
            }
            student.setSwechar(swechar);
            student.setScollege(scollege);
            student.setSstudentnumber(sstudentnumber);
            student.setSgrade(sgrade);
            student.setScollegeaddress(scollegeaddress);
            /*添加学生个人信息*/
            studentService.insertNewStudent(student);
            ur.setId(student.getSid());
            /*更新角色记录*/
            urService.updateUR(ur);
            return new ResultUtil().Success(student);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @PostMapping("/studentCertification")
    @ApiOperation(value = "学生认证",notes = "400 重复认证 500 报错")
    public ResultDTO studentCertification(
           @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = false)Integer sid,
           @ApiParam(value = "学生学校名",required = true)@RequestParam(value = "scollege",required = false)String scollege,
           @ApiParam(value = "学生学号",required = true)@RequestParam(value = "sstudentnumber",required = false)String sstudentnumber,
           @ApiParam(value = "学生年级",required = true)@RequestParam(value = "sgrade",required = false)String sgrade,
           @ApiParam(value = "学生学校地址",required = true)@RequestParam(value = "scollegeaddress",required = false)String scollegeaddress){
        try {
            /*如果在学生添加信息部分已经认证，则无需使用该接口*/
            Student student = studentService.selectBySid(sid);
            if (student.getSstate() == 0){
                student.setScollege(scollege);
                student.setSstudentnumber(sstudentnumber);
                student.setSgrade(sgrade);
                student.setScollegeaddress(scollegeaddress);
                student.setSstate(1);
                return new ResultUtil().Success();
            }
            return new ResultUtil().Error("400","已经进行学生认证");
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @PutMapping("/studentChangeInfo")
    @ApiOperation(value = "学生修改个人信息",notes = "500 报错")
    public ResultDTO studentChangeInfo(
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid,
            @ApiParam(value = "学生姓名",required = false)@RequestParam(value = "sname",required = false) String sname,
            @ApiParam(value = "学生性别",required = false)@RequestParam(value = "ssex",required = false) String ssex,
            @ApiParam(value = "学生学历",required = false)@RequestParam(value = "seducation",required = false)String seducation,
            @ApiParam(value = "学生工作经验",required = false)@RequestParam(value = "sworkexperience",required = false)String sworkexperience,
            @ApiParam(value = "学生微信",required = false)@RequestParam(value = "swechar",required = false) String swechar,
            @ApiParam(value = "学生学校名",required = false)@RequestParam(value = "scollege",required = false)String scollege,
            @ApiParam(value = "学生学号",required = false)@RequestParam(value = "sstudentnumber",required = false)String sstudentnumber,
            @ApiParam(value = "学生年级",required = false)@RequestParam(value = "sgrade",required = false)String sgrade,
            @ApiParam(value = "学生学校地址",required = false)@RequestParam(value = "scollegeaddress",required = false)String scollegeaddress){
        try {
            Student student = studentService.selectBySid(sid);
            if (!StringUtils.isBlank(sname))student.setSname(sname);
            if (!StringUtils.isBlank(ssex ))student.setSsex(ssex);
            if (!StringUtils.isBlank(seducation))student.setSeducation(seducation);
            if (!StringUtils.isBlank(sworkexperience ))student.setSworkexperience(sworkexperience);
            if (!StringUtils.isBlank(swechar))student.setSwechar(swechar);
            if (!StringUtils.isBlank(scollege ))student.setScollege(scollege);
            if (!StringUtils.isBlank(sstudentnumber))student.setSstudentnumber(sstudentnumber);
            if (!StringUtils.isBlank(sgrade ))student.setSgrade(sgrade);
            if (!StringUtils.isBlank(scollegeaddress ))student.setScollegeaddress(scollegeaddress);
            studentService.updateStudentinfo(student);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @GetMapping("/getPositionInfo")
    @ResponseBody
    @ApiOperation(value = "查询学生个人应聘情况 spid 招聘记录id spState 招聘状态 0 等待 1录取 2落选",notes = "500 报错")
    public ResultDTO getPositionInfo(
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size,
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid){
        try {
            //查询学生应聘记录
            List<SP> spList = spService.selectBySid(sid,start,size);
            List<Position> positionList = new ArrayList<>();
            for (SP sp : spList){
                SEPC sepc = sepcService.selectByPid(sp.getPid());
                PositionDTO positionDTO = new PositionDTO(positionService.selectByPid(sp.getPid()));
                positionDTO.setSpid(sp.getSpid());
                positionDTO.setSpState(sp.getSepcstate());
                //查询兼职发布者信息和兼职信息

                UR ur = null;
                UserDTO userDTO = null;
                switch (sepc.getSepctype()){
                    case 0:
                        College college  = collegeService.selectByCid(sepc.getSecid());
                        ur = urService.selectByRidId(3,college.getCid());
                        userDTO = new UserDTO(userService.selectByUid(ur.getUid()));
                        userDTO.setUpassword(null);
                        userDTO.setRole(ur.getRid());
                        userDTO.setRoleObject(college);
                        positionDTO.setUserObject(userDTO);
                        positionDTO.setSepctype(sepc.getSepctype());
                        break;
                    case 1:
                        Enterprise enterprise = enterpriseService.selectByEid(sepc.getSecid());
                        ur = urService.selectByRidId(2,enterprise.getEid());
                        userDTO = new UserDTO(userService.selectByUid(ur.getUid()));
                        userDTO.setUpassword(null);
                        userDTO.setRole(ur.getRid());
                        userDTO.setRoleObject(enterprise);
                        positionDTO.setUserObject(userDTO);
                        positionDTO.setSepctype(sepc.getSepctype());
                }
                positionList.add(positionDTO);
            }
            return new ResultUtil().Success(positionList);
        }catch (Exception e){
            log.info(e.toString());
            return new  ResultUtil().Error("500",e.toString());
        }
    }

    @DeleteMapping("/deletePositionInfo")
    @ResponseBody
    @ApiOperation(value = "删除应聘记录",notes = "500 报错")
    public ResultDTO deletePositionInfo(@ApiParam(value = "兼职记录",required = true)@RequestParam(value = "spid",required = false)Integer spid){
        try {
            spService.deleteBySpid(spid);
            return new ResultUtil().Success();
        }catch (Exception e){
            return new ResultUtil().Error("500",e.toString());
        }
    }

}
