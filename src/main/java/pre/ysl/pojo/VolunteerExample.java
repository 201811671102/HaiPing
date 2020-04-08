package pre.ysl.pojo;

import java.util.ArrayList;
import java.util.List;

public class VolunteerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VolunteerExample() {
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

        public Criteria andVidIsNull() {
            addCriterion("vid is null");
            return (Criteria) this;
        }

        public Criteria andVidIsNotNull() {
            addCriterion("vid is not null");
            return (Criteria) this;
        }

        public Criteria andVidEqualTo(Integer value) {
            addCriterion("vid =", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotEqualTo(Integer value) {
            addCriterion("vid <>", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThan(Integer value) {
            addCriterion("vid >", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vid >=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThan(Integer value) {
            addCriterion("vid <", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThanOrEqualTo(Integer value) {
            addCriterion("vid <=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidIn(List<Integer> values) {
            addCriterion("vid in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotIn(List<Integer> values) {
            addCriterion("vid not in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidBetween(Integer value1, Integer value2) {
            addCriterion("vid between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotBetween(Integer value1, Integer value2) {
            addCriterion("vid not between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVnameIsNull() {
            addCriterion("vName is null");
            return (Criteria) this;
        }

        public Criteria andVnameIsNotNull() {
            addCriterion("vName is not null");
            return (Criteria) this;
        }

        public Criteria andVnameEqualTo(String value) {
            addCriterion("vName =", value, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameNotEqualTo(String value) {
            addCriterion("vName <>", value, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameGreaterThan(String value) {
            addCriterion("vName >", value, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameGreaterThanOrEqualTo(String value) {
            addCriterion("vName >=", value, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameLessThan(String value) {
            addCriterion("vName <", value, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameLessThanOrEqualTo(String value) {
            addCriterion("vName <=", value, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameLike(String value) {
            addCriterion("vName like", value, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameNotLike(String value) {
            addCriterion("vName not like", value, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameIn(List<String> values) {
            addCriterion("vName in", values, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameNotIn(List<String> values) {
            addCriterion("vName not in", values, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameBetween(String value1, String value2) {
            addCriterion("vName between", value1, value2, "vname");
            return (Criteria) this;
        }

        public Criteria andVnameNotBetween(String value1, String value2) {
            addCriterion("vName not between", value1, value2, "vname");
            return (Criteria) this;
        }

        public Criteria andVtypeIsNull() {
            addCriterion("vType is null");
            return (Criteria) this;
        }

        public Criteria andVtypeIsNotNull() {
            addCriterion("vType is not null");
            return (Criteria) this;
        }

        public Criteria andVtypeEqualTo(String value) {
            addCriterion("vType =", value, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeNotEqualTo(String value) {
            addCriterion("vType <>", value, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeGreaterThan(String value) {
            addCriterion("vType >", value, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeGreaterThanOrEqualTo(String value) {
            addCriterion("vType >=", value, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeLessThan(String value) {
            addCriterion("vType <", value, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeLessThanOrEqualTo(String value) {
            addCriterion("vType <=", value, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeLike(String value) {
            addCriterion("vType like", value, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeNotLike(String value) {
            addCriterion("vType not like", value, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeIn(List<String> values) {
            addCriterion("vType in", values, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeNotIn(List<String> values) {
            addCriterion("vType not in", values, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeBetween(String value1, String value2) {
            addCriterion("vType between", value1, value2, "vtype");
            return (Criteria) this;
        }

        public Criteria andVtypeNotBetween(String value1, String value2) {
            addCriterion("vType not between", value1, value2, "vtype");
            return (Criteria) this;
        }

        public Criteria andStimeIsNull() {
            addCriterion("sTime is null");
            return (Criteria) this;
        }

        public Criteria andStimeIsNotNull() {
            addCriterion("sTime is not null");
            return (Criteria) this;
        }

        public Criteria andStimeEqualTo(String value) {
            addCriterion("sTime =", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotEqualTo(String value) {
            addCriterion("sTime <>", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeGreaterThan(String value) {
            addCriterion("sTime >", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeGreaterThanOrEqualTo(String value) {
            addCriterion("sTime >=", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeLessThan(String value) {
            addCriterion("sTime <", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeLessThanOrEqualTo(String value) {
            addCriterion("sTime <=", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeLike(String value) {
            addCriterion("sTime like", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotLike(String value) {
            addCriterion("sTime not like", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeIn(List<String> values) {
            addCriterion("sTime in", values, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotIn(List<String> values) {
            addCriterion("sTime not in", values, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeBetween(String value1, String value2) {
            addCriterion("sTime between", value1, value2, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotBetween(String value1, String value2) {
            addCriterion("sTime not between", value1, value2, "stime");
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