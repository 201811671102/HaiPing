package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.SCMapper;
import pre.ysl.pojo.SC;
import pre.ysl.pojo.SCExample;
import pre.ysl.service.SCService;

import java.util.List;

@Service
@Log4j2
public class SCServiceImpl implements SCService {
    @Autowired
    private SCMapper scMapper;
    @Override
    public SC selectBySCid(Integer scid) {
        try{
            return scMapper.selectByPrimaryKey(scid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public List<SC> selectBySid(Integer sid,Integer start,Integer size) {
        SCExample scExample = new SCExample();
        scExample.createCriteria().andSidEqualTo(sid);
        try{
            if (size != 0 ){
                RowBounds rowBounds = new RowBounds(start,size);
                return scMapper.selectByExampleWithRowbounds(scExample,rowBounds);
            }
            return scMapper.selectByExample(scExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public Integer deleteBySCid(Integer scid) {
        try {
            return scMapper.deleteByPrimaryKey(scid);
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public Integer insertSC(SC sc) {
        try {
            return scMapper.insert(sc);
        }catch (Exception e){
           throw e;
        }
    }
}
