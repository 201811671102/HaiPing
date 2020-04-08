package pre.ysl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import pre.ysl.pojo.SEPC;
import pre.ysl.pojo.SEPCExample;
@Component
public interface SEPCMapper {
    long countByExample(SEPCExample example);

    int deleteByExample(SEPCExample example);

    int deleteByPrimaryKey(Integer sepcid);

    int insert(SEPC record);

    int insertSelective(SEPC record);

    List<SEPC> selectByExampleWithRowbounds(SEPCExample example, RowBounds rowBounds);

    List<SEPC> selectByExample(SEPCExample example);

    SEPC selectByPrimaryKey(Integer sepcid);

    int updateByExampleSelective(@Param("record") SEPC record, @Param("example") SEPCExample example);

    int updateByExample(@Param("record") SEPC record, @Param("example") SEPCExample example);

    int updateByPrimaryKeySelective(SEPC record);

    int updateByPrimaryKey(SEPC record);
}