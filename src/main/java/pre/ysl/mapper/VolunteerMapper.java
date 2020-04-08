package pre.ysl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import pre.ysl.pojo.Volunteer;
import pre.ysl.pojo.VolunteerExample;
@Component
public interface VolunteerMapper {
    long countByExample(VolunteerExample example);

    int deleteByExample(VolunteerExample example);

    int deleteByPrimaryKey(Integer vid);

    int insert(Volunteer record);

    int insertSelective(Volunteer record);

    List<Volunteer> selectByExampleWithRowbounds(VolunteerExample example, RowBounds rowBounds);

    List<Volunteer> selectByExample(VolunteerExample example);

    Volunteer selectByPrimaryKey(Integer vid);

    int updateByExampleSelective(@Param("record") Volunteer record, @Param("example") VolunteerExample example);

    int updateByExample(@Param("record") Volunteer record, @Param("example") VolunteerExample example);

    int updateByPrimaryKeySelective(Volunteer record);

    int updateByPrimaryKey(Volunteer record);
}