package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.URMapper;
import pre.ysl.pojo.UR;
import pre.ysl.pojo.URExample;
import pre.ysl.pojo.User;
import pre.ysl.service.URService;

import java.util.List;

@Service
@Log4j2
public class URServiceImpl implements URService {
    @Autowired
    private URMapper urMapper;
    @Override
    public UR selectByUid(Integer uid) {
        URExample urExample = new URExample();
        urExample.createCriteria().andUidEqualTo(uid);
        try {
            List<UR> urList = urMapper.selectByExample(urExample);
            if (urList.size() == 0 ){
                return null;
            }else if (urList.size() > 1){
                log.info("URServiceImpl:selectByUid 一个用户有多个角色");
            }
            return urList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int insertNewUR(UR ur) {
        try {
            return urMapper.insertSelective(ur);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updateUR(UR ur) {
        try{
            return urMapper.updateByPrimaryKeySelective(ur);
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public List<UR> selectURByRid(Integer rid,Integer start,Integer size) {
        URExample urExample = new URExample();
        URExample.Criteria criteria = urExample.createCriteria();
        if (rid == -1){
            criteria.andIdIsNotNull();
        }else{
            criteria.andRidEqualTo(rid);
        }
        try {
            if (size != 0 ){
                RowBounds rowBounds = new RowBounds(start,size);
                return urMapper.selectByExampleWithRowbounds(urExample,rowBounds);
            }
            return urMapper.selectByExample(urExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<UR> selectURbyUR(UR ur,Integer start,Integer size) {
        URExample urExample = new URExample();
        URExample.Criteria criteria = urExample.createCriteria();
        if (ur.getId()!=null)criteria.andIdEqualTo(ur.getId());
        if (ur.getRid()!=null)criteria.andRidEqualTo(ur.getRid());
        if (ur.getUid()!=null)criteria.andUidEqualTo(ur.getUid());
        if (ur.getUrid()!=null)criteria.andUridEqualTo(ur.getUrid());
        try {
            if (size != 0){
                RowBounds rowBounds = new RowBounds(start,size);
                return urMapper.selectByExampleWithRowbounds(urExample,rowBounds);
            }
            return urMapper.selectByExample(urExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public UR selectByRidId(Integer rid, Integer id) {
        URExample urExample = new URExample();
        urExample.createCriteria().andRidEqualTo(rid).andIdEqualTo(id);
        try {
            List<UR> urList = urMapper.selectByExample(urExample);
            if (urList.size() == 0 ){
                return null;
            }else if (urList.size() > 1){
                log.info("URServiceImpl:selectByRidId 一个用户 一个 角色 出现重复");
            }
            return urList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteUR(Integer urid) {
       try {
           return urMapper.deleteByPrimaryKey(urid);
       }catch (Exception e){
           throw e;
       }
    }
}
