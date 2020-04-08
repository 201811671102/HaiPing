package pre.ysl.service;

import pre.ysl.pojo.SC;

import java.util.List;

public interface SCService {
    /*根据scid查询*/
    SC selectBySCid(Integer scid);
    /*根据sid查询*/
    List<SC> selectBySid(Integer sid,Integer start,Integer size);
    /*根据scid删除*/
    Integer deleteBySCid(Integer scid);
    /*新增sc*/
    Integer insertSC(SC sc);
}
