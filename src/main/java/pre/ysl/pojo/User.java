package pre.ysl.pojo;


public class User {

    private Integer uid;

    private String uaccount;

    private String upassword;

    private String uphone;

    private String uemail;

    private String uphoto;

    public User(Integer uid, String uaccount, String upassword, String uphone, String uemail, String uphoto) {
        this.uid = uid;
        this.uaccount = uaccount;
        this.upassword = upassword;
        this.uphone = uphone;
        this.uemail = uemail;
        this.uphoto = uphoto;
    }

    public User() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount == null ? null : uaccount.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone == null ? null : uphone.trim();
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail == null ? null : uemail.trim();
    }

    public String getUphoto() {
        return uphoto;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto == null ? null : uphoto.trim();
    }
}