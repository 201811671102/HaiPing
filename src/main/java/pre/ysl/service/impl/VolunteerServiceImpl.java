package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.VolunteerMapper;
import pre.ysl.pojo.Volunteer;
import pre.ysl.pojo.VolunteerExample;
import pre.ysl.service.VolunteerService;

import java.util.List;

@Service
@Log4j2
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerMapper volunteerMapper;

    @Override
    public Volunteer selectByVolunteer(Volunteer volunteer) {
        VolunteerExample volunteerExample = new VolunteerExample();
        volunteerExample.createCriteria().andVnameEqualTo(volunteer.getVname())
                .andVtypeEqualTo(volunteer.getVtype())
                .andStimeEqualTo(volunteer.getStime());
        try {
            List<Volunteer> volunteerList = volunteerMapper.selectByExample(volunteerExample);
            if (volunteerList.size() > 1){
                log.info("VolunteerServiceImpl:selectByVolunteer 出现多个重复 志愿者");
            }else if (volunteerList.size() ==0){
                return null;
            }
            return volunteerList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Volunteer selectByVid(Integer vid) {
        try {
            return  volunteerMapper.selectByPrimaryKey(vid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Integer insertVolunteer(Volunteer volunteer) {
        try {
            return volunteerMapper.insertSelective(volunteer);
        }catch (Exception e){
            throw e;
        }
    }
}
