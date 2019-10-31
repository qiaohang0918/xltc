package com.qigan.qiganshop.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbPresellOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPresellOrderExample() {
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

        public Criteria andPresellorderidIsNull() {
            addCriterion("preSellOrderId is null");
            return (Criteria) this;
        }

        public Criteria andPresellorderidIsNotNull() {
            addCriterion("preSellOrderId is not null");
            return (Criteria) this;
        }

        public Criteria andPresellorderidEqualTo(String value) {
            addCriterion("preSellOrderId =", value, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidNotEqualTo(String value) {
            addCriterion("preSellOrderId <>", value, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidGreaterThan(String value) {
            addCriterion("preSellOrderId >", value, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidGreaterThanOrEqualTo(String value) {
            addCriterion("preSellOrderId >=", value, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidLessThan(String value) {
            addCriterion("preSellOrderId <", value, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidLessThanOrEqualTo(String value) {
            addCriterion("preSellOrderId <=", value, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidLike(String value) {
            addCriterion("preSellOrderId like", value, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidNotLike(String value) {
            addCriterion("preSellOrderId not like", value, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidIn(List<String> values) {
            addCriterion("preSellOrderId in", values, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidNotIn(List<String> values) {
            addCriterion("preSellOrderId not in", values, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidBetween(String value1, String value2) {
            addCriterion("preSellOrderId between", value1, value2, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPresellorderidNotBetween(String value1, String value2) {
            addCriterion("preSellOrderId not between", value1, value2, "presellorderid");
            return (Criteria) this;
        }

        public Criteria andPreselltimeIsNull() {
            addCriterion("preSellTime is null");
            return (Criteria) this;
        }

        public Criteria andPreselltimeIsNotNull() {
            addCriterion("preSellTime is not null");
            return (Criteria) this;
        }

        public Criteria andPreselltimeEqualTo(String value) {
            addCriterion("preSellTime =", value, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeNotEqualTo(String value) {
            addCriterion("preSellTime <>", value, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeGreaterThan(String value) {
            addCriterion("preSellTime >", value, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeGreaterThanOrEqualTo(String value) {
            addCriterion("preSellTime >=", value, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeLessThan(String value) {
            addCriterion("preSellTime <", value, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeLessThanOrEqualTo(String value) {
            addCriterion("preSellTime <=", value, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeLike(String value) {
            addCriterion("preSellTime like", value, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeNotLike(String value) {
            addCriterion("preSellTime not like", value, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeIn(List<String> values) {
            addCriterion("preSellTime in", values, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeNotIn(List<String> values) {
            addCriterion("preSellTime not in", values, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeBetween(String value1, String value2) {
            addCriterion("preSellTime between", value1, value2, "preselltime");
            return (Criteria) this;
        }

        public Criteria andPreselltimeNotBetween(String value1, String value2) {
            addCriterion("preSellTime not between", value1, value2, "preselltime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createTime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createTime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andIspushedIsNull() {
            addCriterion("isPushed is null");
            return (Criteria) this;
        }

        public Criteria andIspushedIsNotNull() {
            addCriterion("isPushed is not null");
            return (Criteria) this;
        }

        public Criteria andIspushedEqualTo(String value) {
            addCriterion("isPushed =", value, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedNotEqualTo(String value) {
            addCriterion("isPushed <>", value, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedGreaterThan(String value) {
            addCriterion("isPushed >", value, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedGreaterThanOrEqualTo(String value) {
            addCriterion("isPushed >=", value, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedLessThan(String value) {
            addCriterion("isPushed <", value, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedLessThanOrEqualTo(String value) {
            addCriterion("isPushed <=", value, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedLike(String value) {
            addCriterion("isPushed like", value, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedNotLike(String value) {
            addCriterion("isPushed not like", value, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedIn(List<String> values) {
            addCriterion("isPushed in", values, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedNotIn(List<String> values) {
            addCriterion("isPushed not in", values, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedBetween(String value1, String value2) {
            addCriterion("isPushed between", value1, value2, "ispushed");
            return (Criteria) this;
        }

        public Criteria andIspushedNotBetween(String value1, String value2) {
            addCriterion("isPushed not between", value1, value2, "ispushed");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andButCountIsNull() {
            addCriterion("but_count is null");
            return (Criteria) this;
        }

        public Criteria andButCountIsNotNull() {
            addCriterion("but_count is not null");
            return (Criteria) this;
        }

        public Criteria andButCountEqualTo(String value) {
            addCriterion("but_count =", value, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountNotEqualTo(String value) {
            addCriterion("but_count <>", value, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountGreaterThan(String value) {
            addCriterion("but_count >", value, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountGreaterThanOrEqualTo(String value) {
            addCriterion("but_count >=", value, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountLessThan(String value) {
            addCriterion("but_count <", value, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountLessThanOrEqualTo(String value) {
            addCriterion("but_count <=", value, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountLike(String value) {
            addCriterion("but_count like", value, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountNotLike(String value) {
            addCriterion("but_count not like", value, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountIn(List<String> values) {
            addCriterion("but_count in", values, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountNotIn(List<String> values) {
            addCriterion("but_count not in", values, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountBetween(String value1, String value2) {
            addCriterion("but_count between", value1, value2, "butCount");
            return (Criteria) this;
        }

        public Criteria andButCountNotBetween(String value1, String value2) {
            addCriterion("but_count not between", value1, value2, "butCount");
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