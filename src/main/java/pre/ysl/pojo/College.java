package pre.ysl.pojo;

import java.io.Serializable;

public class College {

    private Integer cid;

    private String cname;

    private String caddress;

    private Integer cstate;

    public College(Integer cid, String cname, String caddress, Integer cstate) {
        this.cid = cid;
        this.cname = cname;
        this.caddress = caddress;
        this.cstate = cstate;
    }

    public College() {
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

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress == null ? null : caddress.trim();
    }

    public Integer getCstate() {
        return cstate;
    }

    public void setCstate(Integer cstate) {
        this.cstate = cstate;
    }
}