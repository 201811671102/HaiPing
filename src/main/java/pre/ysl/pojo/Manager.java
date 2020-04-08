package pre.ysl.pojo;

public class Manager {
    private Integer mid;

    private String mname;

    private String maddress;

    public Manager(Integer mid, String mname, String maddress) {
        this.mid = mid;
        this.mname = mname;
        this.maddress = maddress;
    }

    public Manager() {
        super();
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    public String getMaddress() {
        return maddress;
    }

    public void setMaddress(String maddress) {
        this.maddress = maddress == null ? null : maddress.trim();
    }
}