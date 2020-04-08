package pre.ysl.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("/collage")
@Api(value = "学校controller",tags = "大学用户添加个人信息，修改个人信息，查询没有认证学生，认证，查找勤工俭学岗位")
@Log4j2
public class CollageController {
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private UserService userService;
    @Autowired
    private URService urService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SEPCService sepcService;
    @Autowired
    private PositionService positionService;

    @PostMapping("/collageAddInfo")
    @ResponseBody
    @ApiOperation(value = "学校用户添加个人信息",notes = "学校名，电话，邮箱，地址，均不能重复(重复为201，报错500)")
    public ResultDTO collageAddInfo(
            @ApiParam(value = "用户id",required = true)@RequestParam(value = "uid",required = true) Integer uid,
            @ApiParam(value = "学校名",required = true)@RequestParam(value = "cname",required = true) String cname,
            @ApiParam(value = "学校地址",required = true)@RequestParam(value = "caddress",required = true)String caddress){
        try {
            StringBuilder stringBuilder = new StringBuilder();
            //如果数据不为空，且不重复，添加进修改
            if ( collegeService.selectByCName(cname) != null){
                stringBuilder.append("学校名已被注册");
            }
            if ( collegeService.selectByCaddress(caddress) != null){
                stringBuilder.append("学校地址已被注册");
            }
            if (stringBuilder.length() != 0){
                return new ResultUtil().Error("201",stringBuilder.toString());
            }
            College college = new College();
            college.setCname(cname);
            college.setCaddress(caddress);
            //默认为0，等待管理员审核
            college.setCstate(0);
            collegeService.insertNewCollege(college);
            UR ur = urService.selectByUid(uid);
            ur.setId(college.getCid());
            urService.updateUR(ur);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @PutMapping("/collageChangeInfo")
    @ResponseBody
    @ApiOperation(value = "学校用户修改个人信息",notes = "学校名，电话，邮箱，地址，均不能重复,重复201，错误500")
    public ResultDTO collageChangeInfo(
            @ApiParam(value = "学校id",required = true)@RequestParam(value = "cid",required = true) Integer cid,
            @ApiParam(value = "学校名",required = false)@RequestParam(value = "cname",required = false) String cname,
            @ApiParam(value = "学校地址",required = false)@RequestParam(value = "caddress",required = false)String caddress){
        try {
            College college = new College();
            StringBuilder stringBuilder = new StringBuilder();
            //如果数据不为空，且不重复，添加进修改
            if (cname != null){
                if ( collegeService.selectByCName(cname) != null){
                    stringBuilder.append("学校名已被注册");
                }
                college.setCname(cname);
            }
            if (caddress != null){
                if ( collegeService.selectByCaddress(caddress) != null){
                   stringBuilder.append("学校地址已被注册");
                }
                college.setCaddress(caddress);
            }
            if (stringBuilder.length() != 0){
                return new ResultUtil().Error("201",stringBuilder.toString());
            }
            college.setCid(cid);
            collegeService.updateCollegeInfo(college);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @GetMapping("/getNotCertification")
    @ApiOperation(value = "查询没有通过认证的学生",notes = "错误信息500")
    public ResultDTO getNotCertification(
            @ApiParam(value = "学校id",required = true)@RequestParam(value = "cid",required = false)Integer cid,
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size){
        try {
            //根据学校名查询
            College college = collegeService.selectByCid(cid);
            return new ResultUtil().Success(studentService.selectByState(0,start,size,college.getCname()));
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @PutMapping("studentCertification")
    @ApiOperation(value = "进行学生认证，授权",notes = "报错500")
    public ResultDTO studentCertification(
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid){
        try {
            Student student = new Student();
            student.setSid(sid);
            student.setSstate(1);
            studentService.updateStudentinfo(student);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @GetMapping("/showCollagePosition")
    @ResponseBody
    @ApiOperation(value = "查询勤工俭学招聘职位",notes = "500报错")
    public ResultDTO showCollagePosition(
            @ApiParam(value = "学校id",required = true)@RequestParam(value = "cid",required = true)Integer cid,
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size,
            @ApiParam(value = "招聘状态(-1 全部 0招聘 1不再招聘)",required = true)@RequestParam(value = "sepcState",required = true)Integer sepcState){
        try {
            //查询符合条件的 兼职招聘方 记录
            List<SEPC> sepcList = sepcService.selectBySepcTypeSepcStateSecid(0,sepcState,cid,start,size);
            List<PositionDTO> positionDTOList = new ArrayList<>();
            for (SEPC sepc : sepcList){
                //查询相应的兼职信息
                PositionDTO positionDTO = new PositionDTO(positionService.selectByPid(sepc.getPid()));
                //查询学校信息
                College college = collegeService.selectByCid(cid);
                UR ur = urService.selectByRidId(3,cid);
                //查询学校身份对应的用户信息
                UserDTO userDTO = new UserDTO(userService.selectByUid(ur.getUid()));
                userDTO.setUpassword(null);
                userDTO.setRole(ur.getRid());
                userDTO.setRoleObject(college);
                positionDTO.setUserObject(userDTO);
                positionDTO.setSepctype(sepc.getSepctype());
                positionDTOList.add(positionDTO);
            }
            return new ResultUtil().Success(positionDTOList);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }
}
