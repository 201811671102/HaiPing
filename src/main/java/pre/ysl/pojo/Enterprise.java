package pre.ysl.pojo;



public class Enterprise {

    private Integer eid;

    private String ename;

    private String eintroduction;

    private String eaddress;

    private String eurl;

    private Integer estate;

    public Enterprise(Integer eid, String ename, String eintroduction, String eaddress, String eurl, Integer estate) {
        this.eid = eid;
        this.ename = ename;
        this.eintroduction = eintroduction;
        this.eaddress = eaddress;
        this.eurl = eurl;
        this.estate = estate;
    }

    public Enterprise() {
        super();
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getEintroduction() {
        return eintroduction;
    }

    public void setEintroduction(String eintroduction) {
        this.eintroduction = eintroduction == null ? null : eintroduction.trim();
    }

    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress == null ? null : eaddress.trim();
    }

    public String getEurl() {
        return eurl;
    }

    public void setEurl(String eurl) {
        this.eurl = eurl == null ? null : eurl.trim();
    }

    public Integer getEstate() {
        return estate;
    }

    public void setEstate(Integer estate) {
        this.estate = estate;
    }
}