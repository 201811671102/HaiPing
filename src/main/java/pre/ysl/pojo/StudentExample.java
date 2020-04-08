package pre.ysl.pojo;

import java.util.ArrayList;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Integer value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Integer value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Integer value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Integer value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Integer value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Integer> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Integer> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Integer value1, Integer value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Integer value1, Integer value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSnameIsNull() {
            addCriterion("sName is null");
            return (Criteria) this;
        }

        public Criteria andSnameIsNotNull() {
            addCriterion("sName is not null");
            return (Criteria) this;
        }

        public Criteria andSnameEqualTo(String value) {
            addCriterion("sName =", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotEqualTo(String value) {
            addCriterion("sName <>", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameGreaterThan(String value) {
            addCriterion("sName >", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameGreaterThanOrEqualTo(String value) {
            addCriterion("sName >=", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLessThan(String value) {
            addCriterion("sName <", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLessThanOrEqualTo(String value) {
            addCriterion("sName <=", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLike(String value) {
            addCriterion("sName like", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotLike(String value) {
            addCriterion("sName not like", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameIn(List<String> values) {
            addCriterion("sName in", values, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotIn(List<String> values) {
            addCriterion("sName not in", values, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameBetween(String value1, String value2) {
            addCriterion("sName between", value1, value2, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotBetween(String value1, String value2) {
            addCriterion("sName not between", value1, value2, "sname");
            return (Criteria) this;
        }

        public Criteria andSsexIsNull() {
            addCriterion("sSex is null");
            return (Criteria) this;
        }

        public Criteria andSsexIsNotNull() {
            addCriterion("sSex is not null");
            return (Criteria) this;
        }

        public Criteria andSsexEqualTo(String value) {
            addCriterion("sSex =", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexNotEqualTo(String value) {
            addCriterion("sSex <>", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexGreaterThan(String value) {
            addCriterion("sSex >", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexGreaterThanOrEqualTo(String value) {
            addCriterion("sSex >=", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexLessThan(String value) {
            addCriterion("sSex <", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexLessThanOrEqualTo(String value) {
            addCriterion("sSex <=", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexLike(String value) {
            addCriterion("sSex like", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexNotLike(String value) {
            addCriterion("sSex not like", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexIn(List<String> values) {
            addCriterion("sSex in", values, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexNotIn(List<String> values) {
            addCriterion("sSex not in", values, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexBetween(String value1, String value2) {
            addCriterion("sSex between", value1, value2, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexNotBetween(String value1, String value2) {
            addCriterion("sSex not between", value1, value2, "ssex");
            return (Criteria) this;
        }

        public Criteria andSeducationIsNull() {
            addCriterion("sEducation is null");
            return (Criteria) this;
        }

        public Criteria andSeducationIsNotNull() {
            addCriterion("sEducation is not null");
            return (Criteria) this;
        }

        public Criteria andSeducationEqualTo(String value) {
            addCriterion("sEducation =", value, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationNotEqualTo(String value) {
            addCriterion("sEducation <>", value, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationGreaterThan(String value) {
            addCriterion("sEducation >", value, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationGreaterThanOrEqualTo(String value) {
            addCriterion("sEducation >=", value, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationLessThan(String value) {
            addCriterion("sEducation <", value, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationLessThanOrEqualTo(String value) {
            addCriterion("sEducation <=", value, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationLike(String value) {
            addCriterion("sEducation like", value, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationNotLike(String value) {
            addCriterion("sEducation not like", value, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationIn(List<String> values) {
            addCriterion("sEducation in", values, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationNotIn(List<String> values) {
            addCriterion("sEducation not in", values, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationBetween(String value1, String value2) {
            addCriterion("sEducation between", value1, value2, "seducation");
            return (Criteria) this;
        }

        public Criteria andSeducationNotBetween(String value1, String value2) {
            addCriterion("sEducation not between", value1, value2, "seducation");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceIsNull() {
            addCriterion("sWorkExperience is null");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceIsNotNull() {
            addCriterion("sWorkExperience is not null");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceEqualTo(String value) {
            addCriterion("sWorkExperience =", value, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceNotEqualTo(String value) {
            addCriterion("sWorkExperience <>", value, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceGreaterThan(String value) {
            addCriterion("sWorkExperience >", value, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceGreaterThanOrEqualTo(String value) {
            addCriterion("sWorkExperience >=", value, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceLessThan(String value) {
            addCriterion("sWorkExperience <", value, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceLessThanOrEqualTo(String value) {
            addCriterion("sWorkExperience <=", value, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceLike(String value) {
            addCriterion("sWorkExperience like", value, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceNotLike(String value) {
            addCriterion("sWorkExperience not like", value, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceIn(List<String> values) {
            addCriterion("sWorkExperience in", values, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceNotIn(List<String> values) {
            addCriterion("sWorkExperience not in", values, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceBetween(String value1, String value2) {
            addCriterion("sWorkExperience between", value1, value2, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSworkexperienceNotBetween(String value1, String value2) {
            addCriterion("sWorkExperience not between", value1, value2, "sworkexperience");
            return (Criteria) this;
        }

        public Criteria andSwecharIsNull() {
            addCriterion("sWeChar is null");
            return (Criteria) this;
        }

        public Criteria andSwecharIsNotNull() {
            addCriterion("sWeChar is not null");
            return (Criteria) this;
        }

        public Criteria andSwecharEqualTo(String value) {
            addCriterion("sWeChar =", value, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharNotEqualTo(String value) {
            addCriterion("sWeChar <>", value, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharGreaterThan(String value) {
            addCriterion("sWeChar >", value, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharGreaterThanOrEqualTo(String value) {
            addCriterion("sWeChar >=", value, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharLessThan(String value) {
            addCriterion("sWeChar <", value, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharLessThanOrEqualTo(String value) {
            addCriterion("sWeChar <=", value, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharLike(String value) {
            addCriterion("sWeChar like", value, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharNotLike(String value) {
            addCriterion("sWeChar not like", value, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharIn(List<String> values) {
            addCriterion("sWeChar in", values, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharNotIn(List<String> values) {
            addCriterion("sWeChar not in", values, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharBetween(String value1, String value2) {
            addCriterion("sWeChar between", value1, value2, "swechar");
            return (Criteria) this;
        }

        public Criteria andSwecharNotBetween(String value1, String value2) {
            addCriterion("sWeChar not between", value1, value2, "swechar");
            return (Criteria) this;
        }

        public Criteria andSstateIsNull() {
            addCriterion("sState is null");
            return (Criteria) this;
        }

        public Criteria andSstateIsNotNull() {
            addCriterion("sState is not null");
            return (Criteria) this;
        }

        public Criteria andSstateEqualTo(Integer value) {
            addCriterion("sState =", value, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateNotEqualTo(Integer value) {
            addCriterion("sState <>", value, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateGreaterThan(Integer value) {
            addCriterion("sState >", value, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("sState >=", value, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateLessThan(Integer value) {
            addCriterion("sState <", value, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateLessThanOrEqualTo(Integer value) {
            addCriterion("sState <=", value, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateIn(List<Integer> values) {
            addCriterion("sState in", values, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateNotIn(List<Integer> values) {
            addCriterion("sState not in", values, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateBetween(Integer value1, Integer value2) {
            addCriterion("sState between", value1, value2, "sstate");
            return (Criteria) this;
        }

        public Criteria andSstateNotBetween(Integer value1, Integer value2) {
            addCriterion("sState not between", value1, value2, "sstate");
            return (Criteria) this;
        }

        public Criteria andScollegeIsNull() {
            addCriterion("sCollege is null");
            return (Criteria) this;
        }

        public Criteria andScollegeIsNotNull() {
            addCriterion("sCollege is not null");
            return (Criteria) this;
        }

        public Criteria andScollegeEqualTo(String value) {
            addCriterion("sCollege =", value, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeNotEqualTo(String value) {
            addCriterion("sCollege <>", value, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeGreaterThan(String value) {
            addCriterion("sCollege >", value, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeGreaterThanOrEqualTo(String value) {
            addCriterion("sCollege >=", value, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeLessThan(String value) {
            addCriterion("sCollege <", value, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeLessThanOrEqualTo(String value) {
            addCriterion("sCollege <=", value, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeLike(String value) {
            addCriterion("sCollege like", value, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeNotLike(String value) {
            addCriterion("sCollege not like", value, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeIn(List<String> values) {
            addCriterion("sCollege in", values, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeNotIn(List<String> values) {
            addCriterion("sCollege not in", values, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeBetween(String value1, String value2) {
            addCriterion("sCollege between", value1, value2, "scollege");
            return (Criteria) this;
        }

        public Criteria andScollegeNotBetween(String value1, String value2) {
            addCriterion("sCollege not between", value1, value2, "scollege");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberIsNull() {
            addCriterion("sStudentNumber is null");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberIsNotNull() {
            addCriterion("sStudentNumber is not null");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberEqualTo(String value) {
            addCriterion("sStudentNumber =", value, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberNotEqualTo(String value) {
            addCriterion("sStudentNumber <>", value, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberGreaterThan(String value) {
            addCriterion("sStudentNumber >", value, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberGreaterThanOrEqualTo(String value) {
            addCriterion("sStudentNumber >=", value, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberLessThan(String value) {
            addCriterion("sStudentNumber <", value, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberLessThanOrEqualTo(String value) {
            addCriterion("sStudentNumber <=", value, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberLike(String value) {
            addCriterion("sStudentNumber like", value, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberNotLike(String value) {
            addCriterion("sStudentNumber not like", value, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberIn(List<String> values) {
            addCriterion("sStudentNumber in", values, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberNotIn(List<String> values) {
            addCriterion("sStudentNumber not in", values, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberBetween(String value1, String value2) {
            addCriterion("sStudentNumber between", value1, value2, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSstudentnumberNotBetween(String value1, String value2) {
            addCriterion("sStudentNumber not between", value1, value2, "sstudentnumber");
            return (Criteria) this;
        }

        public Criteria andSgradeIsNull() {
            addCriterion("sGrade is null");
            return (Criteria) this;
        }

        public Criteria andSgradeIsNotNull() {
            addCriterion("sGrade is not null");
            return (Criteria) this;
        }

        public Criteria andSgradeEqualTo(String value) {
            addCriterion("sGrade =", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeNotEqualTo(String value) {
            addCriterion("sGrade <>", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeGreaterThan(String value) {
            addCriterion("sGrade >", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeGreaterThanOrEqualTo(String value) {
            addCriterion("sGrade >=", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeLessThan(String value) {
            addCriterion("sGrade <", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeLessThanOrEqualTo(String value) {
            addCriterion("sGrade <=", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeLike(String value) {
            addCriterion("sGrade like", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeNotLike(String value) {
            addCriterion("sGrade not like", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeIn(List<String> values) {
            addCriterion("sGrade in", values, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeNotIn(List<String> values) {
            addCriterion("sGrade not in", values, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeBetween(String value1, String value2) {
            addCriterion("sGrade between", value1, value2, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeNotBetween(String value1, String value2) {
            addCriterion("sGrade not between", value1, value2, "sgrade");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressIsNull() {
            addCriterion("sCollegeAddress is null");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressIsNotNull() {
            addCriterion("sCollegeAddress is not null");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressEqualTo(String value) {
            addCriterion("sCollegeAddress =", value, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressNotEqualTo(String value) {
            addCriterion("sCollegeAddress <>", value, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressGreaterThan(String value) {
            addCriterion("sCollegeAddress >", value, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressGreaterThanOrEqualTo(String value) {
            addCriterion("sCollegeAddress >=", value, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressLessThan(String value) {
            addCriterion("sCollegeAddress <", value, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressLessThanOrEqualTo(String value) {
            addCriterion("sCollegeAddress <=", value, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressLike(String value) {
            addCriterion("sCollegeAddress like", value, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressNotLike(String value) {
            addCriterion("sCollegeAddress not like", value, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressIn(List<String> values) {
            addCriterion("sCollegeAddress in", values, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressNotIn(List<String> values) {
            addCriterion("sCollegeAddress not in", values, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressBetween(String value1, String value2) {
            addCriterion("sCollegeAddress between", value1, value2, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andScollegeaddressNotBetween(String value1, String value2) {
            addCriterion("sCollegeAddress not between", value1, value2, "scollegeaddress");
            return (Criteria) this;
        }

        public Criteria andSresumeIsNull() {
            addCriterion("sResume is null");
            return (Criteria) this;
        }

        public Criteria andSresumeIsNotNull() {
            addCriterion("sResume is not null");
            return (Criteria) this;
        }

        public Criteria andSresumeEqualTo(String value) {
            addCriterion("sResume =", value, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeNotEqualTo(String value) {
            addCriterion("sResume <>", value, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeGreaterThan(String value) {
            addCriterion("sResume >", value, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeGreaterThanOrEqualTo(String value) {
            addCriterion("sResume >=", value, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeLessThan(String value) {
            addCriterion("sResume <", value, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeLessThanOrEqualTo(String value) {
            addCriterion("sResume <=", value, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeLike(String value) {
            addCriterion("sResume like", value, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeNotLike(String value) {
            addCriterion("sResume not like", value, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeIn(List<String> values) {
            addCriterion("sResume in", values, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeNotIn(List<String> values) {
            addCriterion("sResume not in", values, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeBetween(String value1, String value2) {
            addCriterion("sResume between", value1, value2, "sresume");
            return (Criteria) this;
        }

        public Criteria andSresumeNotBetween(String value1, String value2) {
            addCriterion("sResume not between", value1, value2, "sresume");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}