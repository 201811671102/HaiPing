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
import pre.ysl.pojo.*;
import pre.ysl.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/SVController")
@Api(value = "学生志愿服务controller",tags = "添加，查询，删除")
@Log4j2
public class SVController {
    @Autowired
    private SVService svService;
    @Autowired
    private URService urService;
    @Autowired
    private VolunteerService volunteerService;

    @ResponseBody
    @PostMapping("/addSV")
    @ApiOperation(value = "添加志愿服务",notes = "500 报错")
    public ResultDTO addSC(
            @ApiParam(value = "用户id",required = true)@RequestParam(value = "uid",required = true)Integer uid,
            @ApiParam(value = "志愿服务名",required = true)@RequestParam(value = "vname",required = true)String vname,
            @ApiParam(value = "志愿服务类型",required = true)@RequestParam(value = "vtype",required = true)String vtype,
            @ApiParam(value = "志愿服务时间",required = true)@RequestParam(value = "vstime",required = true)String vstime){
        try {
            Integer sid = urService.selectByUid(uid).getId();
            Volunteer volunteer = new Volunteer();
            volunteer.setVname(vname);
            volunteer.setVtype(vtype);
            volunteer.setStime(vstime);
            Volunteer volunteer1 = volunteerService.selectByVolunteer(volunteer);
            Integer vid  = 0;
            if ( volunteer1 == null){
               volunteerService.insertVolunteer(volunteer);
               vid = volunteer.getVid();
            }else{
               vid = volunteer1.getVid();
            }
            SV sv = new SV();
            sv.setVid(vid);
            sv.setSid(sid);
            svService.insertSV(sv);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @DeleteMapping("/deleteSV")
    @ApiOperation(value = "删除志愿记录",notes = "500 报错")
    public ResultDTO deleteSV(@ApiParam(value = "志愿记录id",required = true)@RequestParam(value = "svid",required = true)Integer svid){
        try {
            svService.deleteBySVid(svid);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @GetMapping("/findSV")
    @ApiOperation(value = "查询志愿记录",notes = "500 报错")
    public ResultDTO findSV(
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size,
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid){
        try {
                List<SV> svList = svService.selectBySid(sid,start,size);
                List<Volunteer> volunteerList = new ArrayList<>();
                for(SV sv : svList){
                    volunteerList.add(volunteerService.selectByVid(sv.getVid()));
                }
                return new ResultUtil().Success(volunteerList);
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }
}
