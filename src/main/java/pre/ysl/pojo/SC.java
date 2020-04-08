package pre.ysl.pojo;

public class SC {
    private Integer scid;

    private Integer sid;

    private Integer cid;

    public SC(Integer scid, Integer sid, Integer cid) {
        this.scid = scid;
        this.sid = sid;
        this.cid = cid;
    }

    public SC() {
        super();
    }

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}