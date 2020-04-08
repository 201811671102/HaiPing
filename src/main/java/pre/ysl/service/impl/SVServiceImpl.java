package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.SVMapper;
import pre.ysl.pojo.SV;
import pre.ysl.pojo.SVExample;
import pre.ysl.service.SVService;

import java.util.List;

@Service
@Log4j2
public class SVServiceImpl implements SVService {

    @Autowired
    private SVMapper svMapper;
    @Override
    public SV selectBySVid(Integer svid) {
        try{
            return svMapper.selectByPrimaryKey(svid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public List<SV> selectBySid(Integer sid,Integer start,Integer size) {
        SVExample svExample = new SVExample();
        svExample.createCriteria().andSidEqualTo(sid);
        try {
            if (size != 0){
                RowBounds rowBounds = new RowBounds(start,size);
                return svMapper.selectByExampleWithRowbounds(svExample,rowBounds);
            }
            return svMapper.selectByExample(svExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteBySVid(Integer svid) {
        try {
            return svMapper.deleteByPrimaryKey(svid);
        }catch (Exception e){
          throw e;
        }
    }

    @Override
    public int insertSV(SV sv) {
        try {
            return svMapper.insert(sv);
        }catch (Exception e){
            throw e;
        }
    }
}
