package pre.ysl.service;

import pre.ysl.pojo.College;

public interface CollegeService {
    /*根据cid查找*/
    College selectByCid(Integer cid);
    /*添加学校用户*/
    int insertNewCollege(College college);
    /*修改学校用户信息*/
    int updateCollegeInfo(College college);
    /*根据学校名称查询学校*/
    College selectByCName(String cname);
    /*根据学校地址查询学校*/
    College selectByCaddress(String caddress);
    /*根据id和学校名查询*/
    College selectByCidCName(Integer cid,String cname);
    /*删除学校身份记录*/
    int deleteCollage(Integer cid);
}
