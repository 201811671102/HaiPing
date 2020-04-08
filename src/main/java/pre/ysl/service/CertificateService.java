package pre.ysl.service;

import pre.ysl.pojo.Certificate;

public interface CertificateService {
    /*查询证书*/
    Certificate selectByCertificate(Certificate certificate);
    /*根据id查询证书*/
    Certificate selectByCid(Integer cid);
    /*添加证书*/
    int insertCertificate(Certificate certificate);
}
