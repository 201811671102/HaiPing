package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.CertificateMapper;
import pre.ysl.pojo.Certificate;
import pre.ysl.pojo.CertificateExample;
import pre.ysl.service.CertificateService;

import java.util.List;

@Service
@Log4j2
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateMapper certificateMapper;
    @Override
    public Certificate selectByCertificate(Certificate certificate) {
        CertificateExample certificateExample = new CertificateExample();
        certificateExample.createCriteria().andCnameEqualTo(certificate.getCname()).andStypeEqualTo(certificate.getStype());
        try{
            List<Certificate> certificateList = certificateMapper.selectByExample(certificateExample);
            if (certificateList.size() == 0){
                return null;
            }else if (certificateList.size() > 1){
                log.info("CertificateServiceImpl:selectByCertificate 出现多个同类型证书");
            }
            return certificateList.get(0);
        }catch (NullPointerException e){
             return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Certificate selectByCid(Integer cid) {
        try{
             return certificateMapper.selectByPrimaryKey(cid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int insertCertificate(Certificate certificate) {
        try {
            return certificateMapper.insertSelective(certificate);
        }catch (Exception e){
           throw e;
        }
    }
}
