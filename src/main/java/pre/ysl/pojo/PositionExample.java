package pre.ysl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PositionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PositionExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPnameIsNull() {
            addCriterion("pName is null");
            return (Criteria) this;
        }

        public Criteria andPnameIsNotNull() {
            addCriterion("pName is not null");
            return (Criteria) this;
        }

        public Criteria andPnameEqualTo(String value) {
            addCriterion("pName =", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotEqualTo(String value) {
            addCriterion("pName <>", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameGreaterThan(String value) {
            addCriterion("pName >", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameGreaterThanOrEqualTo(String value) {
            addCriterion("pName >=", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLessThan(String value) {
            addCriterion("pName <", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLessThanOrEqualTo(String value) {
            addCriterion("pName <=", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLike(String value) {
            addCriterion("pName like", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotLike(String value) {
            addCriterion("pName not like", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameIn(List<String> values) {
            addCriterion("pName in", values, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotIn(List<String> values) {
            addCriterion("pName not in", values, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameBetween(String value1, String value2) {
            addCriterion("pName between", value1, value2, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotBetween(String value1, String value2) {
            addCriterion("pName not between", value1, value2, "pname");
            return (Criteria) this;
        }

        public Criteria andPtypeIsNull() {
            addCriterion("pType is null");
            return (Criteria) this;
        }

        public Criteria andPtypeIsNotNull() {
            addCriterion("pType is not null");
            return (Criteria) this;
        }

        public Criteria andPtypeEqualTo(String value) {
            addCriterion("pType =", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeNotEqualTo(String value) {
            addCriterion("pType <>", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeGreaterThan(String value) {
            addCriterion("pType >", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeGreaterThanOrEqualTo(String value) {
            addCriterion("pType >=", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeLessThan(String value) {
            addCriterion("pType <", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeLessThanOrEqualTo(String value) {
            addCriterion("pType <=", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeLike(String value) {
            addCriterion("pType like", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeNotLike(String value) {
            addCriterion("pType not like", value, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeIn(List<String> values) {
            addCriterion("pType in", values, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeNotIn(List<String> values) {
            addCriterion("pType not in", values, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeBetween(String value1, String value2) {
            addCriterion("pType between", value1, value2, "ptype");
            return (Criteria) this;
        }

        public Criteria andPtypeNotBetween(String value1, String value2) {
            addCriterion("pType not between", value1, value2, "ptype");
            return (Criteria) this;
        }

        public Criteria andPdescribeIsNull() {
            addCriterion("pDescribe is null");
            return (Criteria) this;
        }

        public Criteria andPdescribeIsNotNull() {
            addCriterion("pDescribe is not null");
            return (Criteria) this;
        }

        public Criteria andPdescribeEqualTo(String value) {
            addCriterion("pDescribe =", value, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeNotEqualTo(String value) {
            addCriterion("pDescribe <>", value, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeGreaterThan(String value) {
            addCriterion("pDescribe >", value, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeGreaterThanOrEqualTo(String value) {
            addCriterion("pDescribe >=", value, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeLessThan(String value) {
            addCriterion("pDescribe <", value, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeLessThanOrEqualTo(String value) {
            addCriterion("pDescribe <=", value, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeLike(String value) {
            addCriterion("pDescribe like", value, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeNotLike(String value) {
            addCriterion("pDescribe not like", value, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeIn(List<String> values) {
            addCriterion("pDescribe in", values, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeNotIn(List<String> values) {
            addCriterion("pDescribe not in", values, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeBetween(String value1, String value2) {
            addCriterion("pDescribe between", value1, value2, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPdescribeNotBetween(String value1, String value2) {
            addCriterion("pDescribe not between", value1, value2, "pdescribe");
            return (Criteria) this;
        }

        public Criteria andPrequirementsIsNull() {
            addCriterion("pRequirements is null");
            return (Criteria) this;
        }

        public Criteria andPrequirementsIsNotNull() {
            addCriterion("pRequirements is not null");
            return (Criteria) this;
        }

        public Criteria andPrequirementsEqualTo(String value) {
            addCriterion("pRequirements =", value, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsNotEqualTo(String value) {
            addCriterion("pRequirements <>", value, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsGreaterThan(String value) {
            addCriterion("pRequirements >", value, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsGreaterThanOrEqualTo(String value) {
            addCriterion("pRequirements >=", value, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsLessThan(String value) {
            addCriterion("pRequirements <", value, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsLessThanOrEqualTo(String value) {
            addCriterion("pRequirements <=", value, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsLike(String value) {
            addCriterion("pRequirements like", value, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsNotLike(String value) {
            addCriterion("pRequirements not like", value, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsIn(List<String> values) {
            addCriterion("pRequirements in", values, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsNotIn(List<String> values) {
            addCriterion("pRequirements not in", values, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsBetween(String value1, String value2) {
            addCriterion("pRequirements between", value1, value2, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPrequirementsNotBetween(String value1, String value2) {
            addCriterion("pRequirements not between", value1, value2, "prequirements");
            return (Criteria) this;
        }

        public Criteria andPcompensationIsNull() {
            addCriterion("pCompensation is null");
            return (Criteria) this;
        }

        public Criteria andPcompensationIsNotNull() {
            addCriterion("pCompensation is not null");
            return (Criteria) this;
        }

        public Criteria andPcompensationEqualTo(Integer value) {
            addCriterion("pCompensation =", value, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationNotEqualTo(Integer value) {
            addCriterion("pCompensation <>", value, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationGreaterThan(Integer value) {
            addCriterion("pCompensation >", value, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationGreaterThanOrEqualTo(Integer value) {
            addCriterion("pCompensation >=", value, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationLessThan(Integer value) {
            addCriterion("pCompensation <", value, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationLessThanOrEqualTo(Integer value) {
            addCriterion("pCompensation <=", value, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationIn(List<Integer> values) {
            addCriterion("pCompensation in", values, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationNotIn(List<Integer> values) {
            addCriterion("pCompensation not in", values, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationBetween(Integer value1, Integer value2) {
            addCriterion("pCompensation between", value1, value2, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPcompensationNotBetween(Integer value1, Integer value2) {
            addCriterion("pCompensation not between", value1, value2, "pcompensation");
            return (Criteria) this;
        }

        public Criteria andPwelfareIsNull() {
            addCriterion("pWelfare is null");
            return (Criteria) this;
        }

        public Criteria andPwelfareIsNotNull() {
            addCriterion("pWelfare is not null");
            return (Criteria) this;
        }

        public Criteria andPwelfareEqualTo(String value) {
            addCriterion("pWelfare =", value, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareNotEqualTo(String value) {
            addCriterion("pWelfare <>", value, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareGreaterThan(String value) {
            addCriterion("pWelfare >", value, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareGreaterThanOrEqualTo(String value) {
            addCriterion("pWelfare >=", value, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareLessThan(String value) {
            addCriterion("pWelfare <", value, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareLessThanOrEqualTo(String value) {
            addCriterion("pWelfare <=", value, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareLike(String value) {
            addCriterion("pWelfare like", value, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareNotLike(String value) {
            addCriterion("pWelfare not like", value, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareIn(List<String> values) {
            addCriterion("pWelfare in", values, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareNotIn(List<String> values) {
            addCriterion("pWelfare not in", values, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareBetween(String value1, String value2) {
            addCriterion("pWelfare between", value1, value2, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPwelfareNotBetween(String value1, String value2) {
            addCriterion("pWelfare not between", value1, value2, "pwelfare");
            return (Criteria) this;
        }

        public Criteria andPaddressIsNull() {
            addCriterion("pAddress is null");
            return (Criteria) this;
        }

        public Criteria andPaddressIsNotNull() {
            addCriterion("pAddress is not null");
            return (Criteria) this;
        }

        public Criteria andPaddressEqualTo(String value) {
            addCriterion("pAddress =", value, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressNotEqualTo(String value) {
            addCriterion("pAddress <>", value, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressGreaterThan(String value) {
            addCriterion("pAddress >", value, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressGreaterThanOrEqualTo(String value) {
            addCriterion("pAddress >=", value, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressLessThan(String value) {
            addCriterion("pAddress <", value, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressLessThanOrEqualTo(String value) {
            addCriterion("pAddress <=", value, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressLike(String value) {
            addCriterion("pAddress like", value, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressNotLike(String value) {
            addCriterion("pAddress not like", value, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressIn(List<String> values) {
            addCriterion("pAddress in", values, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressNotIn(List<String> values) {
            addCriterion("pAddress not in", values, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressBetween(String value1, String value2) {
            addCriterion("pAddress between", value1, value2, "paddress");
            return (Criteria) this;
        }

        public Criteria andPaddressNotBetween(String value1, String value2) {
            addCriterion("pAddress not between", value1, value2, "paddress");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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