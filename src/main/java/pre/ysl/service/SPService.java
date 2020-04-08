package pre.ysl.service;

import pre.ysl.pojo.SP;

import java.util.List;

public interface SPService {
    /*添加学生兼职记录*/
    int insertSP(SP sp);
    /*修改学生兼职记录*/
    int updateSP(SP sp);
    /*根据职位id查询记录*/
    List<SP> selectByPid(Integer pid,Integer start,Integer size);
    /*通过SP查询记录*/
    SP selectBySp(Integer pid,Integer sid,Integer sepcState);
    /*根据学生id查询记录*/
    List<SP> selectBySid(Integer sid,Integer start,Integer size);
    /*查询某兼职应聘人数*/
    long selectNumberByPid(Integer pid);
    /*删除兼职记录*/
    int deleteBySpid(Integer spid);
    /*根据sid删除记录*/
    int deleteBySid(Integer sid);
}
