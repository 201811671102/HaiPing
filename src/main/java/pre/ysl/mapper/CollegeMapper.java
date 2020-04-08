package pre.ysl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import pre.ysl.pojo.College;
import pre.ysl.pojo.CollegeExample;
@Component
public interface CollegeMapper {
    long countByExample(CollegeExample example);

    int deleteByExample(CollegeExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(College record);

    int insertSelective(College record);

    List<College> selectByExampleWithRowbounds(CollegeExample example, RowBounds rowBounds);

    List<College> selectByExample(CollegeExample example);

    College selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") College record, @Param("example") CollegeExample example);

    int updateByExample(@Param("record") College record, @Param("example") CollegeExample example);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
}