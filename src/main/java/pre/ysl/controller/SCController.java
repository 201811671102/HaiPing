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
import pre.ysl.pojo.Certificate;
import pre.ysl.pojo.SC;
import pre.ysl.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/SCController")
@Log4j2
@Api(value = "学生证书controller",tags = {"添加证书","删除证书","查询证书"})
public class SCController {
    @Autowired
    private SCService scService;
    @Autowired
    private URService urService;
    @Autowired
    private CertificateService certificateService;

    @ResponseBody
    @PostMapping("/addSC")
    @ApiOperation(value = "添加证书",notes = "500 报错")
    public ResultDTO addSC(
            @ApiParam(value = "用户id",required = true)@RequestParam(value = "uid",required = true)Integer uid,
            @ApiParam(value = "证书名",required = true)@RequestParam(value = "cname",required = true)String cname,
            @ApiParam(value = "证书类型",required = true)@RequestParam(value = "stype",required = true)String stype){
        try {
            Integer sid = urService.selectByUid(uid).getId();
            Certificate certificate = new Certificate();
            certificate.setCname(cname);
            certificate.setStype(stype);
            //查询是否已经存在此类证书，有则无须新增
            Certificate certificate1 = certificateService.selectByCertificate(certificate);
            Integer cid = 0;
            if ( certificate1== null){
                certificateService.insertCertificate(certificate);
                cid =certificate.getCid();
            }else{
                cid = certificate1.getCid();
            }
            SC sc = new SC();
            sc.setCid(cid);
            sc.setSid(sid);
            scService.insertSC(sc);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @DeleteMapping("/deleteSC")
    @ApiOperation(value = "删除证书记录",notes = "500 报错")
    public ResultDTO deleteSC(@ApiParam(value = "证书记录id",required = true)@RequestParam(value = "scid",required = true)Integer scid){
        try {
            scService.deleteBySCid(scid);
            return new ResultUtil().Success();
        }catch (Exception e){
            log.info(e.toString());
            return new ResultUtil().Error("500",e.toString());
        }
    }

    @ResponseBody
    @GetMapping("/findSC")
    @ApiOperation(value = "查询证书",notes = "500 报错")
    public ResultDTO findSC(
            @ApiParam(value = "页码",required = true)@RequestParam(value = "start",required = false)Integer start,
            @ApiParam(value = "条数",required = true)@RequestParam(value = "size",required = false)Integer size,
            @ApiParam(value = "学生id",required = true)@RequestParam(value = "sid",required = true)Integer sid){
       try {
           List<SC> scList = scService.selectBySid(sid,start,size);
           List<Certificate> certificateList = new ArrayList<>();
           for(SC sc : scList){
               certificateList.add(certificateService.selectByCid(sc.getCid()));
           }
           return new ResultUtil().Success(certificateList);
       }catch (Exception e){
           log.info(e.toString());
           return new ResultUtil().Error("500",e.toString());
       }

    }
}
