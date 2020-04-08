package pre.ysl.pojo;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

public class Position {
    @Field("pid")
    private Integer pid;
    @Field("pName")
    private String pname;
    @Field("pType")
    private String ptype;
    @Field("pDescribe")
    private String pdescribe;
    @Field("pRequirements")
    private String prequirements;
    @Field("pCompensation")
    private Integer pcompensation;
    @Field("pWelfare")
    private String pwelfare;
    @Field("pAddress")
    private String paddress;
    @Field("update_time")
    private Date updateTime;

    public Position(Integer pid, String pname, String ptype, String pdescribe, String prequirements, Integer pcompensation, String pwelfare, String paddress, Date updateTime) {
        this.pid = pid;
        this.pname = pname;
        this.ptype = ptype;
        this.pdescribe = pdescribe;
        this.prequirements = prequirements;
        this.pcompensation = pcompensation;
        this.pwelfare = pwelfare;
        this.paddress = paddress;
        this.updateTime = updateTime;
    }

    public Position() {
        super();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype == null ? null : ptype.trim();
    }

    public String getPdescribe() {
        return pdescribe;
    }

    public void setPdescribe(String pdescribe) {
        this.pdescribe = pdescribe == null ? null : pdescribe.trim();
    }

    public String getPrequirements() {
        return prequirements;
    }

    public void setPrequirements(String prequirements) {
        this.prequirements = prequirements == null ? null : prequirements.trim();
    }

    public Integer getPcompensation() {
        return pcompensation;
    }

    public void setPcompensation(Integer pcompensation) {
        this.pcompensation = pcompensation;
    }

    public String getPwelfare() {
        return pwelfare;
    }

    public void setPwelfare(String pwelfare) {
        this.pwelfare = pwelfare == null ? null : pwelfare.trim();
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress == null ? null : paddress.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}