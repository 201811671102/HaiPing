package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.EnterpriseMapper;
import pre.ysl.pojo.Enterprise;
import pre.ysl.pojo.EnterpriseExample;
import pre.ysl.service.EnterpriseService;

import java.util.List;

@Service
@Log4j2
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Override
    public Enterprise selectByEid(Integer eid) {
        try {
            return enterpriseMapper.selectByPrimaryKey(eid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
          throw e;
        }
    }

    @Override
    public int insertNewEnterprise(Enterprise enterprise) {
        try {
            return enterpriseMapper.insertSelective(enterprise);
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public Enterprise selectByEname(String ename) {
        EnterpriseExample enterpriseExample = new EnterpriseExample();
        enterpriseExample.createCriteria().andEnameEqualTo(ename);
        try{
            List<Enterprise> enterpriseList = enterpriseMapper.selectByExample(enterpriseExample);
            if (enterpriseList.size() > 1){
                log.info("EnterpriseServiceImpl:selectByEname 企业名字重复");
            }else if (enterpriseList.size() == 0){
                return null;
            }
            return enterpriseList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Enterprise selectByEaddress(String eaddress){
        EnterpriseExample enterpriseExample = new EnterpriseExample();
        enterpriseExample.createCriteria().andEnameEqualTo(eaddress);
        try{
            List<Enterprise> enterpriseList = enterpriseMapper.selectByExample(enterpriseExample);
            if (enterpriseList.size() > 1){
                log.info("EnterpriseServiceImpl:selectByEaddress 企业地址重复");
            }else if (enterpriseList.size() == 0){
                return null;
            }
            return enterpriseList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Enterprise selectByEurl(String eurl) {
        EnterpriseExample enterpriseExample = new EnterpriseExample();
        enterpriseExample.createCriteria().andEnameEqualTo(eurl);
        try {
            List<Enterprise> enterpriseList = enterpriseMapper.selectByExample(enterpriseExample);
            if (enterpriseList.size() > 1){
                log.info("EnterpriseServiceImpl:selectByEurl 企业官网重复");
            }else if (enterpriseList.size() == 0){
                return null;
            }
            return enterpriseList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updateEnterprise(Enterprise enterprise) {
        try {
            return enterpriseMapper.updateByPrimaryKeySelective(enterprise);
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public List<Enterprise> showAllByState(Integer state,Integer start,Integer size) {
        EnterpriseExample enterpriseExample = new EnterpriseExample();
        enterpriseExample.createCriteria().andEstateEqualTo(state);
        try{
            if (size != 0){
                RowBounds rowBounds = new RowBounds(start,size);
                return  enterpriseMapper.selectByExampleWithRowbounds(enterpriseExample,rowBounds);
            }
            return  enterpriseMapper.selectByExample(enterpriseExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteEnterprise(Integer eid) {
        try {
            return enterpriseMapper.deleteByPrimaryKey(eid);
        }catch (Exception e){
            throw e;
        }
    }
}
