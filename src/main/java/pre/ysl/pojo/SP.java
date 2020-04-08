package pre.ysl.pojo;

public class SP {
    private Integer spid;

    private Integer pid;

    private Integer sid;

    private Integer sepcstate;

    public SP(Integer spid, Integer pid, Integer sid, Integer sepcstate) {
        this.spid = spid;
        this.pid = pid;
        this.sid = sid;
        this.sepcstate = sepcstate;
    }

    public SP() {
        super();
    }

    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSepcstate() {
        return sepcstate;
    }

    public void setSepcstate(Integer sepcstate) {
        this.sepcstate = sepcstate;
    }
}