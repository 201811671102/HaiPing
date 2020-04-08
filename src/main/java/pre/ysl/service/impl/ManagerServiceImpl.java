package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.ManagerMapper;
import pre.ysl.pojo.Manager;
import pre.ysl.pojo.ManagerExample;
import pre.ysl.service.ManagerService;

import java.util.List;

@Service
@Log4j2
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public Manager selectByMid(Integer mid) {
        try {
            return managerMapper.selectByPrimaryKey(mid);
        }catch (NullPointerException e) {
            return null;
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public int insertManager(Manager mangle) {
        try {
            return managerMapper.insertSelective(mangle);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Manager selectByNameAndAdress(String mname, String madress) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria().andMnameEqualTo(mname).andMaddressEqualTo(madress);
        try {
            List<Manager> managerList = managerMapper.selectByExample(managerExample);
            if (managerList.size() > 1){
                log.info("ManagerServiceImpl:selectByNameAndAdress 管理员地址 姓名重复 ");
            }else if (managerList.size() == 0){
                return null;
            }
            return managerList.get(0);
        }catch (NullPointerException e) {
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updateManager(Manager manager) {
        try {
            return managerMapper.updateByPrimaryKeySelective(manager);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteManager(Integer mid) {
        try {
            return managerMapper.deleteByPrimaryKey(mid);
        }catch (Exception e){
            throw e;
        }
    }
}
