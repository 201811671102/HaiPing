package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.CollegeMapper;
import pre.ysl.pojo.College;
import pre.ysl.pojo.CollegeExample;
import pre.ysl.service.CollegeService;

import java.util.List;


@Service
@Log4j2
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;
    @Override
    public College selectByCid(Integer cid) {
        try {
            return collegeMapper.selectByPrimaryKey(cid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int insertNewCollege(College college) {
        try{
            return collegeMapper.insertSelective(college);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updateCollegeInfo(College college) {
        try{
            return collegeMapper.updateByPrimaryKeySelective(college);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public College selectByCName(String cname) {
        CollegeExample collegeExample = new CollegeExample();
        collegeExample.createCriteria().andCnameEqualTo(cname);
        try {
            List<College> collegeList = collegeMapper.selectByExample(collegeExample);
            if (collegeList.size()>1){
                log.info("出现多个同名校名");
            }else if (collegeList.size() == 0){
                return null;
            }
            return collegeList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public College selectByCaddress(String caddress) {
        CollegeExample collegeExample = new CollegeExample();
        collegeExample.createCriteria().andCaddressEqualTo(caddress);
        try {
            List<College> collegeList = collegeMapper.selectByExample(collegeExample);
            if (collegeList.size()>1){
                log.info("出现多个同名校名");
            }else if (collegeList.size() == 0){
                return null;
            }
            return collegeList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e) {
           throw e;
        }
    }


    @Override
    public College selectByCidCName(Integer cid, String cname) {
        CollegeExample collegeExample = new CollegeExample();
        collegeExample.createCriteria().andCnameEqualTo(cname).andCidEqualTo(cid);
        try {
            List<College> collegeList = collegeMapper.selectByExample(collegeExample);
            if (collegeList.size()>1){
                log.info("出现多个同名校名");
            }else if (collegeList.size() == 0){
                return null;
            }
            return collegeList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int deleteCollage(Integer cid) {
        try{
            return collegeMapper.deleteByPrimaryKey(cid);
        }catch (Exception e) {
            throw e;
        }
    }
}
