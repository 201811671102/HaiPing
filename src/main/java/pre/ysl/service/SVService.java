package pre.ysl.service;

import pre.ysl.pojo.SV;

import java.util.List;

public interface SVService {
    /*根据scid查询*/
    SV selectBySVid(Integer svid);
    /*根据sid查询*/
    List<SV> selectBySid(Integer sid,Integer start,Integer size);
    /*根据scid删除*/
    int deleteBySVid(Integer svid);
    /*新增sv*/
    int insertSV(SV sv);
}
