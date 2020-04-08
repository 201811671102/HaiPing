package pre.ysl.pojo;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnterpriseExample() {
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

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(Integer value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(Integer value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(Integer value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(Integer value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(Integer value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(Integer value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<Integer> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<Integer> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(Integer value1, Integer value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(Integer value1, Integer value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEnameIsNull() {
            addCriterion("eName is null");
            return (Criteria) this;
        }

        public Criteria andEnameIsNotNull() {
            addCriterion("eName is not null");
            return (Criteria) this;
        }

        public Criteria andEnameEqualTo(String value) {
            addCriterion("eName =", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotEqualTo(String value) {
            addCriterion("eName <>", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameGreaterThan(String value) {
            addCriterion("eName >", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameGreaterThanOrEqualTo(String value) {
            addCriterion("eName >=", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLessThan(String value) {
            addCriterion("eName <", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLessThanOrEqualTo(String value) {
            addCriterion("eName <=", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLike(String value) {
            addCriterion("eName like", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotLike(String value) {
            addCriterion("eName not like", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameIn(List<String> values) {
            addCriterion("eName in", values, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotIn(List<String> values) {
            addCriterion("eName not in", values, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameBetween(String value1, String value2) {
            addCriterion("eName between", value1, value2, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotBetween(String value1, String value2) {
            addCriterion("eName not between", value1, value2, "ename");
            return (Criteria) this;
        }

        public Criteria andEintroductionIsNull() {
            addCriterion("eIntroduction is null");
            return (Criteria) this;
        }

        public Criteria andEintroductionIsNotNull() {
            addCriterion("eIntroduction is not null");
            return (Criteria) this;
        }

        public Criteria andEintroductionEqualTo(String value) {
            addCriterion("eIntroduction =", value, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionNotEqualTo(String value) {
            addCriterion("eIntroduction <>", value, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionGreaterThan(String value) {
            addCriterion("eIntroduction >", value, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionGreaterThanOrEqualTo(String value) {
            addCriterion("eIntroduction >=", value, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionLessThan(String value) {
            addCriterion("eIntroduction <", value, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionLessThanOrEqualTo(String value) {
            addCriterion("eIntroduction <=", value, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionLike(String value) {
            addCriterion("eIntroduction like", value, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionNotLike(String value) {
            addCriterion("eIntroduction not like", value, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionIn(List<String> values) {
            addCriterion("eIntroduction in", values, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionNotIn(List<String> values) {
            addCriterion("eIntroduction not in", values, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionBetween(String value1, String value2) {
            addCriterion("eIntroduction between", value1, value2, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEintroductionNotBetween(String value1, String value2) {
            addCriterion("eIntroduction not between", value1, value2, "eintroduction");
            return (Criteria) this;
        }

        public Criteria andEaddressIsNull() {
            addCriterion("eAddress is null");
            return (Criteria) this;
        }

        public Criteria andEaddressIsNotNull() {
            addCriterion("eAddress is not null");
            return (Criteria) this;
        }

        public Criteria andEaddressEqualTo(String value) {
            addCriterion("eAddress =", value, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressNotEqualTo(String value) {
            addCriterion("eAddress <>", value, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressGreaterThan(String value) {
            addCriterion("eAddress >", value, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressGreaterThanOrEqualTo(String value) {
            addCriterion("eAddress >=", value, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressLessThan(String value) {
            addCriterion("eAddress <", value, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressLessThanOrEqualTo(String value) {
            addCriterion("eAddress <=", value, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressLike(String value) {
            addCriterion("eAddress like", value, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressNotLike(String value) {
            addCriterion("eAddress not like", value, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressIn(List<String> values) {
            addCriterion("eAddress in", values, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressNotIn(List<String> values) {
            addCriterion("eAddress not in", values, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressBetween(String value1, String value2) {
            addCriterion("eAddress between", value1, value2, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEaddressNotBetween(String value1, String value2) {
            addCriterion("eAddress not between", value1, value2, "eaddress");
            return (Criteria) this;
        }

        public Criteria andEurlIsNull() {
            addCriterion("eURL is null");
            return (Criteria) this;
        }

        public Criteria andEurlIsNotNull() {
            addCriterion("eURL is not null");
            return (Criteria) this;
        }

        public Criteria andEurlEqualTo(String value) {
            addCriterion("eURL =", value, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlNotEqualTo(String value) {
            addCriterion("eURL <>", value, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlGreaterThan(String value) {
            addCriterion("eURL >", value, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlGreaterThanOrEqualTo(String value) {
            addCriterion("eURL >=", value, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlLessThan(String value) {
            addCriterion("eURL <", value, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlLessThanOrEqualTo(String value) {
            addCriterion("eURL <=", value, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlLike(String value) {
            addCriterion("eURL like", value, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlNotLike(String value) {
            addCriterion("eURL not like", value, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlIn(List<String> values) {
            addCriterion("eURL in", values, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlNotIn(List<String> values) {
            addCriterion("eURL not in", values, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlBetween(String value1, String value2) {
            addCriterion("eURL between", value1, value2, "eurl");
            return (Criteria) this;
        }

        public Criteria andEurlNotBetween(String value1, String value2) {
            addCriterion("eURL not between", value1, value2, "eurl");
            return (Criteria) this;
        }

        public Criteria andEstateIsNull() {
            addCriterion("eState is null");
            return (Criteria) this;
        }

        public Criteria andEstateIsNotNull() {
            addCriterion("eState is not null");
            return (Criteria) this;
        }

        public Criteria andEstateEqualTo(Integer value) {
            addCriterion("eState =", value, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateNotEqualTo(Integer value) {
            addCriterion("eState <>", value, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateGreaterThan(Integer value) {
            addCriterion("eState >", value, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("eState >=", value, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateLessThan(Integer value) {
            addCriterion("eState <", value, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateLessThanOrEqualTo(Integer value) {
            addCriterion("eState <=", value, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateIn(List<Integer> values) {
            addCriterion("eState in", values, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateNotIn(List<Integer> values) {
            addCriterion("eState not in", values, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateBetween(Integer value1, Integer value2) {
            addCriterion("eState between", value1, value2, "estate");
            return (Criteria) this;
        }

        public Criteria andEstateNotBetween(Integer value1, Integer value2) {
            addCriterion("eState not between", value1, value2, "estate");
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