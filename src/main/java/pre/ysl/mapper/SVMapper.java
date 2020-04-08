package pre.ysl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import pre.ysl.pojo.SV;
import pre.ysl.pojo.SVExample;
@Component
public interface SVMapper {
    long countByExample(SVExample example);

    int deleteByExample(SVExample example);

    int deleteByPrimaryKey(Integer svid);

    int insert(SV record);

    int insertSelective(SV record);

    List<SV> selectByExampleWithRowbounds(SVExample example, RowBounds rowBounds);

    List<SV> selectByExample(SVExample example);

    SV selectByPrimaryKey(Integer svid);

    int updateByExampleSelective(@Param("record") SV record, @Param("example") SVExample example);

    int updateByExample(@Param("record") SV record, @Param("example") SVExample example);

    int updateByPrimaryKeySelective(SV record);

    int updateByPrimaryKey(SV record);
}