package pre.ysl.pojo;

public class Student {
    private Integer sid;

    private String sname;

    private String ssex;

    private String seducation;

    private String sworkexperience;

    private String swechar;

    private Integer sstate;

    private String scollege;

    private String sstudentnumber;

    private String sgrade;

    private String scollegeaddress;

    private String sresume;

    public Student(Integer sid, String sname, String ssex, String seducation, String sworkexperience, String swechar, Integer sstate, String scollege, String sstudentnumber, String sgrade, String scollegeaddress, String sresume) {
        this.sid = sid;
        this.sname = sname;
        this.ssex = ssex;
        this.seducation = seducation;
        this.sworkexperience = sworkexperience;
        this.swechar = swechar;
        this.sstate = sstate;
        this.scollege = scollege;
        this.sstudentnumber = sstudentnumber;
        this.sgrade = sgrade;
        this.scollegeaddress = scollegeaddress;
        this.sresume = sresume;
    }

    public Student() {
        super();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex == null ? null : ssex.trim();
    }

    public String getSeducation() {
        return seducation;
    }

    public void setSeducation(String seducation) {
        this.seducation = seducation == null ? null : seducation.trim();
    }

    public String getSworkexperience() {
        return sworkexperience;
    }

    public void setSworkexperience(String sworkexperience) {
        this.sworkexperience = sworkexperience == null ? null : sworkexperience.trim();
    }

    public String getSwechar() {
        return swechar;
    }

    public void setSwechar(String swechar) {
        this.swechar = swechar == null ? null : swechar.trim();
    }

    public Integer getSstate() {
        return sstate;
    }

    public void setSstate(Integer sstate) {
        this.sstate = sstate;
    }

    public String getScollege() {
        return scollege;
    }

    public void setScollege(String scollege) {
        this.scollege = scollege == null ? null : scollege.trim();
    }

    public String getSstudentnumber() {
        return sstudentnumber;
    }

    public void setSstudentnumber(String sstudentnumber) {
        this.sstudentnumber = sstudentnumber == null ? null : sstudentnumber.trim();
    }

    public String getSgrade() {
        return sgrade;
    }

    public void setSgrade(String sgrade) {
        this.sgrade = sgrade == null ? null : sgrade.trim();
    }

    public String getScollegeaddress() {
        return scollegeaddress;
    }

    public void setScollegeaddress(String scollegeaddress) {
        this.scollegeaddress = scollegeaddress == null ? null : scollegeaddress.trim();
    }

    public String getSresume() {
        return sresume;
    }

    public void setSresume(String sresume) {
        this.sresume = sresume == null ? null : sresume.trim();
    }
}