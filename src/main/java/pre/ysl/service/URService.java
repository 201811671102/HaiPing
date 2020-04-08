package pre.ysl.service;

import pre.ysl.pojo.UR;
import pre.ysl.pojo.User;

import java.util.List;

public interface URService {
    /*根据用户id查询角色*/
    UR selectByUid(Integer uid);
    /*添加用户角色关系*/
    int insertNewUR(UR ur);
    /*更新用户角色关系*/
    int updateUR(UR ur);
    /*根据角色查询ur*/
    List<UR> selectURByRid(Integer rid,Integer start,Integer size);
    /*根据ur查询*/
    List<UR> selectURbyUR(UR ur,Integer start,Integer size);
    /*根据角色类型，角色id查询*/
    UR selectByRidId(Integer rid, Integer id);
    /*删除用户角色记录*/
    int deleteUR(Integer urid);
}
