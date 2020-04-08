package pre.ysl.service;

import pre.ysl.pojo.SEPC;

import java.util.List;

public interface SEPCService {
    /*添加招聘记录*/
    int insertSEPC(SEPC sepc);
    /*根据招聘类型,招聘状态,招聘者id查询*/
    List<SEPC> selectBySepcTypeSepcStateSecid(Integer sepcType,Integer sepcState,Integer secid,Integer start,Integer size);
    /*根据pid查询记录*/
    SEPC selectByPid(Integer pid);
    /*根据pid删除记录*/
    int deleteByPid(Integer pid);
    /*根据id修改记录*/
    int updateSEPCByid(SEPC sepc);
    /*根据sepctype,secid*/
    int deleteBySepcTypeSecid(Integer sepcType,Integer secid);
}
