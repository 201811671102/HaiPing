package pre.ysl.service;

import pre.ysl.pojo.Volunteer;

public interface VolunteerService {
    /*查询志愿服务*/
    Volunteer selectByVolunteer(Volunteer volunteer);
    /*根据id查询志愿服务*/
    Volunteer selectByVid(Integer vid);
    /*添加志愿服务*/
    Integer insertVolunteer(Volunteer volunteer);
}
