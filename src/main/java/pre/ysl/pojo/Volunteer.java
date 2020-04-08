package pre.ysl.pojo;

public class Volunteer {
    private Integer vid;

    private String vname;

    private String vtype;

    private String stime;

    public Volunteer(Integer vid, String vname, String vtype, String stime) {
        this.vid = vid;
        this.vname = vname;
        this.vtype = vtype;
        this.stime = stime;
    }

    public Volunteer() {
        super();
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname == null ? null : vname.trim();
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype == null ? null : vtype.trim();
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime == null ? null : stime.trim();
    }
}