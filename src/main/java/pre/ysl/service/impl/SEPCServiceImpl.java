package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.SEPCMapper;
import pre.ysl.pojo.SEPC;
import pre.ysl.pojo.SEPCExample;
import pre.ysl.service.SEPCService;

import java.util.List;

@Service
@Log4j2
public class SEPCServiceImpl implements SEPCService {
    @Autowired
    private SEPCMapper sepcMapper;
    @Override
    public int insertSEPC(SEPC sepc) {
        try {
            return sepcMapper.insertSelective(sepc);
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public List<SEPC> selectBySepcTypeSepcStateSecid(Integer sepcType, Integer sepcState, Integer secid, Integer start, Integer size) {
        SEPCExample sepcExample = new SEPCExample();
        SEPCExample.Criteria criteria = sepcExample.createCriteria();
        criteria.andPidIsNotNull();
        if (sepcType != -1) criteria.andSepctypeEqualTo(sepcType);
        if (sepcState != -1) criteria.andSepcstateEqualTo(sepcState);
        if (secid != -1)criteria.andSecidEqualTo(secid);
        try {
            if ( size != 0){
                RowBounds rowBounds = new RowBounds(start,size);
                return sepcMapper.selectByExampleWithRowbounds(sepcExample,rowBounds);
            }
            return sepcMapper.selectByExample(sepcExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public SEPC selectByPid(Integer pid) {
        SEPCExample sepcExample = new SEPCExample();
        sepcExample.createCriteria().andPidEqualTo(pid);
        try {
            List<SEPC> sepcList = sepcMapper.selectByExample(sepcExample);
            if (sepcList.size() > 1 ){
                log.info("SEPCServiceImpl:selectByPid 一份兼职出现多个兼职招聘方记录");
            }else if(sepcList.size() == 0){
                return null;
            }
            return sepcList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteByPid(Integer pid) {
        SEPCExample sepcExample = new SEPCExample();
        SEPCExample.Criteria criteria = sepcExample.createCriteria();
        criteria.andPidEqualTo(pid);
        try {
            return sepcMapper.deleteByExample(sepcExample);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updateSEPCByid(SEPC sepc) {
        try {
            return sepcMapper.updateByPrimaryKeySelective(sepc);
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public int deleteBySepcTypeSecid(Integer sepcType, Integer secid) {
        SEPCExample sepcExample = new SEPCExample();
        SEPCExample.Criteria criteria = sepcExample.createCriteria();
        criteria.andSepctypeEqualTo(sepcType).andSecidEqualTo(secid);
        try {
            return sepcMapper.deleteByExample(sepcExample);
        }catch (Exception e){
            throw e;
        }
    }
}
