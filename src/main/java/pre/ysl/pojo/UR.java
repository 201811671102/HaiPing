package pre.ysl.pojo;

public class UR {
    private Integer urid;

    private Integer uid;

    private Integer rid;

    private Integer id;

    public UR(Integer urid, Integer uid, Integer rid, Integer id) {
        this.urid = urid;
        this.uid = uid;
        this.rid = rid;
        this.id = id;
    }

    public UR() {
        super();
    }

    public Integer getUrid() {
        return urid;
    }

    public void setUrid(Integer urid) {
        this.urid = urid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}