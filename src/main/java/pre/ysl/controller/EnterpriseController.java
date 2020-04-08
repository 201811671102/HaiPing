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

@RequestMapping("/enterpriseController")
@Api(value = "企业controller",tags = "企业信息认证，修改企业信息,查找企业")
@Controller
@Log4j2
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private UserService userService;
    @Autowired
    private URService urService;
    @Autowired
    private SEPCService sepcService;
    @Autowired
    private PositionService positionService;


    @ResponseBody
    @PostMapping("/addEnterpriseInfo")
    @ApiOperation(value = "新增企业信息",notes = "401 非企业用户 ，201 企业名字，地址，网页地址 重复 500 报错")
    public ResultDTO addEnterpriseInfo(
            @ApiParam(value = "用户id", required = true) @RequestParam(value = "uid",required = true) Integer uid,
            @ApiParam(value = "企业名字", required = true) @RequestParam(value = "ename",required = true) String ename,
            @ApiParam(value = "企业简介", required = true) @RequestParam(value = "eIntroduction",required = true) String eIntroduction,
            @ApiParam(value = "企业地址", required = true) @RequestParam(value = "eAddress",required = true) String eAddress,
            @ApiParam(value = "企业网页连接", required = false) @RequestParam(value = "eURL",required = false) String eURL) {
        try {
            UR ur = urService.selectByUid(uid);
            if (ur.getRid() != 2) {
                return new ResultUtil().Error("401", "非企业用户");
            }
            StringBuilder stringBuilder = new StringBuilder();
            Enterprise enterprise = new Enterprise();
            if (enterpriseService.selectByEname(ename) != null) {
                stringBuilder.append("企业名字重复");
            }
            if (eURL != null) {
                if (enterpriseService.selectByEurl(eURL) != null) {
                    stringBuilder.append("企业网页链接重复 ");
                }
                enterprise.setEurl(eURL);
            }
            if (enterpriseService.selectByEaddress(eAddress) != null) {
                stringBuilder.append("企业地址重复 ");
            }
            //stringBuilder不为空，有重复
            if (stringBuilder.length() != 0 ){
                return new ResultUtil().Error("201",stringBuilder.toString());
            }
            enterprise.setEintroduction(eIntroduction);
            enterprise.setEstate(0);
            enterprise.setEname(ename);
            enterprise.setEaddress(eAddress);
            enterpriseService.insertNewEnterprise(enterprise);
            //修改用户角色关系中对应的角色id
            ur.setId(enterprise.getEid());
            urService.updateUR(ur);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }


    @ResponseBody
    @PutMapping("/changeEnterpriseInfo")
    @ApiOperation(value = "修改企业信息",notes = "500报错")
    public ResultDTO changeEnterpriseInfo(
            @ApiParam(value = "企业id", required = true) @RequestParam(value = "eid",required = true) Integer eid,
            @ApiParam(value = "企业名字", required = false) @RequestParam(value = "ename",required = true) String ename,
            @ApiParam(value = "企业简介", required = false) @RequestParam(value = "eIntroduction",required = true) String eIntroduction,
            @ApiParam(value = "企业地址", required = false) @RequestParam(value = "eAddress",required = true) String eAddress,
            @ApiParam(value = "企业网页链接", required = false) @RequestParam(value = "eURL",required = true) String eURL) {
        try {
            Enterprise enterprise = new Enterprise();
            StringBuilder stringBuilder = new StringBuilder();
            enterprise.setEid(eid);
            if (eIntroduction != null) enterprise.setEintroduction(eIntroduction);
            if (ename != null){
                if (enterpriseService.selectByEname(ename) != null) {
                    stringBuilder.append("企业名字重复");
                }
                enterprise.setEname(ename);
            }
            if (eAddress != null) {
                if (enterpriseService.selectByEaddress(eAddress) != null) {
                    stringBuilder.append("企业地址重复 ");
                }
                enterprise.setEaddress(eAddress);
            }
            if (eURL != null) {
                if (enterpriseService.selectByEurl(eURL) != null) {
                    stringBuilder.append("企业网页链接重复 ");
                }
                enterprise.setEurl(eURL);
            }
            //stringBuilder不为空，有重复
            if (stringBuilder.length() != 0 ){
                return new ResultUtil().Error("201",stringBuilder.toString());
            }
            enterpriseService.updateEnterprise(enterprise);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @GetMapping(value = "/selectEnterprise")
    @ApiOperation(value = "查找企业",notes = "500报错")
    public ResultDTO selectEnterprise(@ApiParam(value = "企业id",required = true)@RequestParam(value = "eid",required = true)Integer eid){
        try {
            return new ResultUtil().Success(enterpriseService.selectByEid(eid));
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }

    }

    @GetMapping("/showEnterprisePosition")
    @ResponseBody
    @ApiOperation(value = "查询企业所有招聘职位")
    public ResultDTO showEnterprisePosition(
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size,
            @ApiParam(value = "企业id",required = true)@RequestParam(value = "eid",required = false)Integer eid,
            @ApiParam(value = "招聘状态(-1 全部 0招聘，1不再招聘)",required = true)@RequestParam(value = "sepcState",required = true)Integer sepcState){
        try {
            //查询符合条件的 兼职招聘方 记录
            List<SEPC> sepcList = sepcService.selectBySepcTypeSepcStateSecid(1,sepcState,eid,start,size);
            List<PositionDTO> positionDTOList = new ArrayList<>();
            for (SEPC sepc : sepcList){
                //查询相应的兼职信息
                PositionDTO positionDTO = new PositionDTO(positionService.selectByPid(sepc.getPid()));
                //查询企业信息
                Enterprise enterprise = enterpriseService.selectByEid(eid);
                UR ur = urService.selectByRidId(2,eid);
                //查询身份对应的用户信息
                UserDTO userDTO = new UserDTO(userService.selectByUid(ur.getUid()));
                userDTO.setUpassword(null);
                userDTO.setRole(ur.getRid());
                userDTO.setRoleObject(enterprise);
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
