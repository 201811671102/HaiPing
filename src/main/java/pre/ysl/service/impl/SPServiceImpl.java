package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.SPMapper;
import pre.ysl.pojo.SP;
import pre.ysl.pojo.SPExample;
import pre.ysl.service.SPService;

import java.util.List;

@Service
@Log4j2
public class SPServiceImpl implements SPService {
    @Autowired
    private SPMapper spMapper;
    @Override
    public int insertSP(SP sp) {
        try {
            return spMapper.insertSelective(sp);
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public int updateSP(SP sp) {
        try {
            return spMapper.updateByPrimaryKeySelective(sp);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<SP> selectByPid(Integer pid,Integer start,Integer size) {
        SPExample spExample = new SPExample();
        spExample.createCriteria().andPidEqualTo(pid);
        try {
            if ( size != 0 ){
                RowBounds rowBounds = new RowBounds(start,size);
                return spMapper.selectByExampleWithRowbounds(spExample,rowBounds);
            }
            return spMapper.selectByExample(spExample);
        }catch (NullPointerException e) {
            return null;
        }catch (Exception e) {
          throw e;
        }
    }


    @Override
    public SP selectBySp(Integer pid, Integer sid, Integer sepcState) {
        SPExample spExample = new SPExample();
        SPExample.Criteria criteria = spExample.createCriteria();
        if (pid != -1)criteria.andPidEqualTo(pid);
        if (sid != -1)criteria.andSidEqualTo(sid);
        if (sepcState != -1)criteria.andSepcstateEqualTo(sepcState);
        try {
            List<SP> spList = spMapper.selectByExample(spExample);
            if (spList.size() > 1){
                log.info("SPServiceImpl:selectBySp : 出现多个学生 兼职 记录相同");
            }else if (spList.size() == 0){
                return null;
            }
            return spList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<SP> selectBySid(Integer sid,Integer start,Integer size) {
        SPExample spExample = new SPExample();
        spExample.createCriteria().andSidEqualTo(sid);
        try {
            if ( size != 0){
                RowBounds rowBounds = new RowBounds(start,size);
                return spMapper.selectByExampleWithRowbounds(spExample,rowBounds);
            }
            return spMapper.selectByExample(spExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public long selectNumberByPid(Integer pid) {
        SPExample spExample = new SPExample();
        spExample.createCriteria().andPidEqualTo(pid);
        try {
            return spMapper.countByExample(spExample);
        }catch (NullPointerException e){
            return 0;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteBySpid(Integer spid) {
        try {
            return spMapper.deleteByPrimaryKey(spid);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteBySid(Integer sid) {
        SPExample spExample = new SPExample();
        SPExample.Criteria criteria = spExample.createCriteria();
        criteria.andSidEqualTo(sid);
        try {
            return spMapper.deleteByExample(spExample);
        }catch (Exception e){
            throw e;
        }
    }

}
