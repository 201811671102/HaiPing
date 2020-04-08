package pre.ysl.pojo;

public class SV {
    private Integer svid;

    private Integer sid;

    private Integer vid;

    public SV(Integer svid, Integer sid, Integer vid) {
        this.svid = svid;
        this.sid = sid;
        this.vid = vid;
    }

    public SV() {
        super();
    }

    public Integer getSvid() {
        return svid;
    }

    public void setSvid(Integer svid) {
        this.svid = svid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }
}