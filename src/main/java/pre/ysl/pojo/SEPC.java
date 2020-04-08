package pre.ysl.pojo;

public class SEPC {
    private Integer sepcid;

    private Integer secid;

    private Integer pid;

    private Integer sepctype;

    private Integer sepcstate;

    public SEPC(Integer sepcid, Integer secid, Integer pid, Integer sepctype, Integer sepcstate) {
        this.sepcid = sepcid;
        this.secid = secid;
        this.pid = pid;
        this.sepctype = sepctype;
        this.sepcstate = sepcstate;
    }

    public SEPC() {
        super();
    }

    public Integer getSepcid() {
        return sepcid;
    }

    public void setSepcid(Integer sepcid) {
        this.sepcid = sepcid;
    }

    public Integer getSecid() {
        return secid;
    }

    public void setSecid(Integer secid) {
        this.secid = secid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSepctype() {
        return sepctype;
    }

    public void setSepctype(Integer sepctype) {
        this.sepctype = sepctype;
    }

    public Integer getSepcstate() {
        return sepcstate;
    }

    public void setSepcstate(Integer sepcstate) {
        this.sepcstate = sepcstate;
    }
}