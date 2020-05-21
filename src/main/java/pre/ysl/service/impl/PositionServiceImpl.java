package pre.ysl.service.impl;

import pre.ysl.mapper.PositionMapper;
import pre.ysl.pojo.Position;
import pre.ysl.pojo.PositionExample;
import pre.ysl.service.PositionService;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;


    @Override
    public int insertPosition(Position position) throws IOException {
        try {
            position.setUpdateTime(new Date());
            return positionMapper.insertSelective(position);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Position selectPosition(Position position) {
        PositionExample positionExample = new PositionExample();
        positionExample.createCriteria().andPtypeEqualTo(position.getPtype())
                .andPdescribeEqualTo(position.getPdescribe())
                .andPrequirementsEqualTo(position.getPrequirements())
                .andPcompensationEqualTo(position.getPcompensation())
                .andPaddressEqualTo(position.getPaddress());
        try {
            List<Position> positionList = positionMapper.selectByExample(positionExample);
            if (positionList.size() > 1){
                log.info("PositionServiceImpl:selectPosition 职位完全重复");
            }else if (positionList.size() == 0){
                return null;
            }
            return positionList.get(0);
        }catch (NullPointerException e) {
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Position selectByPid(Integer pid) throws IOException {
        try {
            return positionMapper.selectByPrimaryKey(pid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteByPid(Integer pid) throws IOException {
        try {
            positionMapper.deleteByPrimaryKey(pid);
            return positionMapper.deleteByPrimaryKey(pid);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updatePosition(Position position) throws IOException {
        try {
            position.setUpdateTime(new Date());
            positionMapper.updateByPrimaryKeySelective(position);
            return positionMapper.updateByPrimaryKeySelective(position);
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public List<Position> getPosititonByAttribute(
            String pName,
            Integer start,
            Integer size) throws IOException {
        try {
            RowBounds rowBounds = new RowBounds(start,size);
            PositionExample positionExample = new PositionExample();
            PositionExample.Criteria criteria = positionExample.createCriteria();
            criteria.andPnameLike("%"+pName+"%");
            return positionMapper.selectByExampleWithRowbounds(positionExample,rowBounds);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }
}
