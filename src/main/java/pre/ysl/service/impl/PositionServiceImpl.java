package pre.ysl.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.PositionMapper;
import pre.ysl.pojo.Position;
import pre.ysl.pojo.PositionExample;
import pre.ysl.service.PositionService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private SolrClient solrClient;

    @Override
    public int insertPosition(Position position) throws IOException, SolrServerException {
        try {
            position.setUpdateTime(new Date());
            return positionMapper.insertSelective(position);
        }catch (Exception e){
            throw e;
        }finally {
            solrClient.addBean(position);
            solrClient.commit(true,true,true);
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
    public Position selectByPid(Integer pid) throws IOException, SolrServerException {
        try {
            SolrDocument solrDocument = solrClient.getById(pid.toString());
            Map<String,Object> map = solrDocument.getFieldValueMap();
            Position position = new Position();
            position.setPid((Integer) map.get("pid"));
            position.setPaddress((String) map.get("pAddress"));
            position.setPtype((String) map.get("pType"));
            position.setPwelfare((String) map.get("pWelfare"));
            position.setPcompensation((Integer) map.get("pCompensation"));
            position.setPname((String) map.get("pName"));
            position.setPrequirements((String) map.get("pRequirements"));
            position.setUpdateTime((Date) map.get("update_time"));
            return  position;
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int deleteByPid(Integer pid) throws IOException, SolrServerException {
        try {
            solrClient.deleteById(pid.toString());
            solrClient.commit(true,true,true);
            return positionMapper.deleteByPrimaryKey(pid);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updatePosition(Position position) throws IOException, SolrServerException {
        try {
            position.setUpdateTime(new Date());
            solrClient.addBean(position);
            solrClient.commit(true,true,true);
            return positionMapper.updateByPrimaryKeySelective(position);
        }catch (Exception e){
           throw e;
        }
    }

    @Override
    public List<Position> getPosititonByAttribute(Integer lowprice,Integer heightprice,Position position, Integer start, Integer size,Integer sort) throws IOException, SolrServerException {
        try {
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.set("q","*:*");
            if (!StringUtils.isBlank(position.getPname()))solrQuery.addFilterQuery("pName : "+position.getPname());
            if (!StringUtils.isBlank(position.getPtype()))solrQuery.addFilterQuery("pType : "+position.getPtype());
            if (!StringUtils.isBlank(position.getPdescribe()))solrQuery.addFilterQuery("pDescribe : "+position.getPdescribe());
            if (!StringUtils.isBlank(position.getPrequirements()))solrQuery.addFilterQuery("pRequirements : "+position.getPrequirements());
            String low = "*",height = "*";
            if (lowprice != null){
                low = lowprice.toString();
            }
            if (heightprice != null){
                height = heightprice.toString();
            }
            solrQuery.addFilterQuery("pCompensation : [ "+low+" TO "+height+"]");
            if (!StringUtils.isBlank(position.getPwelfare()))solrQuery.addFilterQuery("pWelfare : "+position.getPwelfare());
            if (!StringUtils.isBlank(position.getPaddress()))solrQuery.addFilterQuery("pAddress : "+position.getPaddress());
            solrQuery.setStart(start);
            solrQuery.setRows(size);
            if (sort == 0){
                solrQuery.setSort("pCompensation", SolrQuery.ORDER.asc);
            }else {
                solrQuery.setSort("pCompensation", SolrQuery.ORDER.desc);
            }
            QueryResponse queryResponse = solrClient.query(solrQuery);
            return queryResponse.getBeans(Position.class);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw e;
        }
    }
}
