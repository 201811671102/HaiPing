package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.UserMapper;
import pre.ysl.pojo.User;
import pre.ysl.pojo.UserExample;
import pre.ysl.redis.RedisUtil;
import pre.ysl.service.UserService;

import java.util.List;


@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public User selectByAccount(String account) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUaccountEqualTo(account);
        try {
            if (redisUtil.hasKey(account)){
                return (User) redisUtil.get(account);
            }
            List<User> userList =  userMapper.selectByExample(userExample);
            if (userList.size()>1){
                log.info("UserServiceImpl:selectByAccount 用户账号出现重复");
            }else if (userList.size() == 0){
                return null;
            }
            return userList.get(0);
        }catch (NullPointerException e){
                return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public boolean selectByEmail(String uemain) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUemailEqualTo(uemain);
        try {
            List<User> userList =  userMapper.selectByExample(userExample);
            if (userList.size()>1){
                log.info("UserServiceImpl:selectByAccount 用户邮箱出现重复");
            }else if (userList.size() == 0){
                return true;
            }
            return false;
        }catch (NullPointerException e){
            return true;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public boolean selectByPhone(String uphone) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUphoneEqualTo(uphone);
        try {
            List<User> userList =  userMapper.selectByExample(userExample);
            if (userList.size()>1){
                log.info("UserServiceImpl:selectByPhone 用户手机出现重复");
            }else if (userList.size() == 0){
                return true;
            }
            return false;
        }catch (NullPointerException e){
            return true;
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public int insertNewUser(User user) {
        try{
            redisUtil.set(user.getUaccount(),user);
            return userMapper.insertSelective(user);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updateUserPassowrd(String uaccount, String unewpassword) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUaccountEqualTo(uaccount);
        User user = new User();
        user.setUpassword(unewpassword);
        try {
            return userMapper.updateByExampleSelective(user,userExample);
        }catch (Exception e){
            throw e;
        }finally {
            user = selectByAccount(uaccount);
            redisUtil.del(uaccount);
            redisUtil.set(uaccount,user);
        }
    }

    @Override
    public  User selectByUid(Integer uid) {
        try {
            return userMapper.selectByPrimaryKey(uid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public int updateUserByUid(User user) {
        try{
            redisUtil.del(user.getUaccount());
            redisUtil.set(user.getUaccount(),user);
            return userMapper.updateByPrimaryKeySelective(user);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<User> showAll(Integer start,Integer size) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUidIsNotNull();
        try {
            if (size != 0){
                RowBounds rowBounds = new RowBounds(start,size);
                return userMapper.selectByExampleWithRowbounds(userExample,rowBounds);
            }
            return userMapper.selectByExample(userExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteUser(Integer uid,String uaccount) {
        try{
            redisUtil.del(uaccount);
            return userMapper.deleteByPrimaryKey(uid);
        }catch (Exception e){
            throw e;
        }
    }
}
