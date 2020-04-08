package pre.ysl.service;

import pre.ysl.pojo.Enterprise;

import java.util.List;

public interface EnterpriseService {
    /*根据企业id查找*/
    Enterprise selectByEid(Integer eid);
    /*新增企业信息*/
    int insertNewEnterprise(Enterprise enterprise);
    /*根据企业名字查询*/
    Enterprise selectByEname(String ename);
    /*根据企业地址查询*/
    Enterprise selectByEaddress(String eaddress);
    /*根据企业url查询*/
    Enterprise selectByEurl(String eurl);
    /*修改企业信息*/
    int updateEnterprise(Enterprise enterprise);
    /*根据企业认证状态查询企业*/
    List<Enterprise> showAllByState(Integer state,Integer start,Integer size);
    /*删除企业身份信息*/
    int deleteEnterprise(Integer eid);
}
