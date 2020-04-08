package pre.ysl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import pre.ysl.pojo.SC;
import pre.ysl.pojo.SCExample;
@Component
public interface SCMapper {
    long countByExample(SCExample example);

    int deleteByExample(SCExample example);

    int deleteByPrimaryKey(Integer scid);

    int insert(SC record);

    int insertSelective(SC record);

    List<SC> selectByExampleWithRowbounds(SCExample example, RowBounds rowBounds);

    List<SC> selectByExample(SCExample example);

    SC selectByPrimaryKey(Integer scid);

    int updateByExampleSelective(@Param("record") SC record, @Param("example") SCExample example);

    int updateByExample(@Param("record") SC record, @Param("example") SCExample example);

    int updateByPrimaryKeySelective(SC record);

    int updateByPrimaryKey(SC record);
}