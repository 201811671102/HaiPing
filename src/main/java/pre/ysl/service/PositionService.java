package pre.ysl.service;


import pre.ysl.pojo.Position;

import java.io.IOException;
import java.util.List;

public interface PositionService {
    /*添加招聘岗位*/
    int insertPosition(Position position) throws IOException;
    /*根据岗位查询招聘岗位*/
    Position selectPosition(Position position);
    /*根据id查询招聘岗位*/
    Position selectByPid(Integer pid) throws IOException;
    /*根据id删除招聘岗位*/
    int deleteByPid(Integer pid) throws IOException;
    /*修改招聘岗位*/
    int updatePosition(Position position) throws IOException;
    /*根据属性查询岗位*/
    List<Position> getPosititonByAttribute(Integer lowprice,Integer heightprice,Position position,Integer start,Integer size,Integer sort) throws IOException;
}
