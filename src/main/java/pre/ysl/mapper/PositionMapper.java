package pre.ysl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import pre.ysl.pojo.Position;
import pre.ysl.pojo.PositionExample;
@Component
public interface PositionMapper {
    long countByExample(PositionExample example);

    int deleteByExample(PositionExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Position record);

    int insertSelective(Position record);

    List<Position> selectByExampleWithRowbounds(PositionExample example, RowBounds rowBounds);

    List<Position> selectByExample(PositionExample example);

    Position selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Position record, @Param("example") PositionExample example);

    int updateByExample(@Param("record") Position record, @Param("example") PositionExample example);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}