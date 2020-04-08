package pre.ysl.pojo;

public class Certificate {
    private Integer cid;

    private String cname;

    private String stype;

    public Certificate(Integer cid, String cname, String stype) {
        this.cid = cid;
        this.cname = cname;
        this.stype = stype;
    }

    public Certificate() {
        super();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype == null ? null : stype.trim();
    }
}