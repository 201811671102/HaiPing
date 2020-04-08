package pre.ysl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import pre.ysl.pojo.SP;
import pre.ysl.pojo.SPExample;
@Component
public interface SPMapper {
    long countByExample(SPExample example);

    int deleteByExample(SPExample example);

    int deleteByPrimaryKey(Integer spid);

    int insert(SP record);

    int insertSelective(SP record);

    List<SP> selectByExampleWithRowbounds(SPExample example, RowBounds rowBounds);

    List<SP> selectByExample(SPExample example);

    SP selectByPrimaryKey(Integer spid);

    int updateByExampleSelective(@Param("record") SP record, @Param("example") SPExample example);

    int updateByExample(@Param("record") SP record, @Param("example") SPExample example);

    int updateByPrimaryKeySelective(SP record);

    int updateByPrimaryKey(SP record);
}