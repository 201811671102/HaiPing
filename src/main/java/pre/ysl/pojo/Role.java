package pre.ysl.pojo;

public class Role {
    private Integer rid;

    private String roletype;

    public Role(Integer rid, String roletype) {
        this.rid = rid;
        this.roletype = roletype;
    }

    public Role() {
        super();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype == null ? null : roletype.trim();
    }
}