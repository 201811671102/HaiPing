package pre.ysl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import pre.ysl.pojo.Certificate;
import pre.ysl.pojo.CertificateExample;
@Component
public interface CertificateMapper {
    long countByExample(CertificateExample example);

    int deleteByExample(CertificateExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Certificate record);

    int insertSelective(Certificate record);

    List<Certificate> selectByExampleWithRowbounds(CertificateExample example, RowBounds rowBounds);

    List<Certificate> selectByExample(CertificateExample example);

    Certificate selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Certificate record, @Param("example") CertificateExample example);

    int updateByExample(@Param("record") Certificate record, @Param("example") CertificateExample example);

    int updateByPrimaryKeySelective(Certificate record);

    int updateByPrimaryKey(Certificate record);
}