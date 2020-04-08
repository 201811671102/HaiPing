package pre.ysl.service;

import pre.ysl.pojo.Manager;

public interface ManagerService {
    /*根据id查询*/
    Manager selectByMid(Integer Mid);
    /*添加管理员*/
    int insertManager(Manager manager);
    /*根据姓名和地址查询*/
    Manager selectByNameAndAdress(String mname, String madress);
    /*更新姓名和地址*/
    int updateManager(Manager manager);
    /*删除管理员*/
    int deleteManager(Integer mid);
}
