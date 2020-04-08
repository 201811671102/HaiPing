package pre.ysl.service;

import pre.ysl.pojo.User;

import java.util.List;

public interface UserService {
    /*根据账号查询用户*/
    User selectByAccount(String account);
    /*根据邮箱查询用户*/
    boolean selectByEmail(String uemain);
    /*根据电话查询用户*/
    boolean selectByPhone(String uphone);
    /*添加用户*/
    int insertNewUser(User user);
    /*修改密码*/
    int updateUserPassowrd(String uaccount,String unewpassword);
    /*根据id查询用户*/
     User selectByUid(Integer uid);
    /*根据用户id修改用户简历*/
    int updateUserByUid(User user);
    /*查询全部用户*/
    List<User> showAll(Integer start,Integer size);
    /*删除用户*/
    int deleteUser(Integer uid,String uaccount);
}
