package com.qigan.qiganshop.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbUserWishesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbUserWishesExample() {
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

        public Criteria andWishesidIsNull() {
            addCriterion("wishesId is null");
            return (Criteria) this;
        }

        public Criteria andWishesidIsNotNull() {
            addCriterion("wishesId is not null");
            return (Criteria) this;
        }

        public Criteria andWishesidEqualTo(String value) {
            addCriterion("wishesId =", value, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidNotEqualTo(String value) {
            addCriterion("wishesId <>", value, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidGreaterThan(String value) {
            addCriterion("wishesId >", value, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidGreaterThanOrEqualTo(String value) {
            addCriterion("wishesId >=", value, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidLessThan(String value) {
            addCriterion("wishesId <", value, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidLessThanOrEqualTo(String value) {
            addCriterion("wishesId <=", value, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidLike(String value) {
            addCriterion("wishesId like", value, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidNotLike(String value) {
            addCriterion("wishesId not like", value, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidIn(List<String> values) {
            addCriterion("wishesId in", values, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidNotIn(List<String> values) {
            addCriterion("wishesId not in", values, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidBetween(String value1, String value2) {
            addCriterion("wishesId between", value1, value2, "wishesid");
            return (Criteria) this;
        }

        public Criteria andWishesidNotBetween(String value1, String value2) {
            addCriterion("wishesId not between", value1, value2, "wishesid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andWishestypeIsNull() {
            addCriterion("wishesType is null");
            return (Criteria) this;
        }

        public Criteria andWishestypeIsNotNull() {
            addCriterion("wishesType is not null");
            return (Criteria) this;
        }

        public Criteria andWishestypeEqualTo(String value) {
            addCriterion("wishesType =", value, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeNotEqualTo(String value) {
            addCriterion("wishesType <>", value, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeGreaterThan(String value) {
            addCriterion("wishesType >", value, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeGreaterThanOrEqualTo(String value) {
            addCriterion("wishesType >=", value, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeLessThan(String value) {
            addCriterion("wishesType <", value, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeLessThanOrEqualTo(String value) {
            addCriterion("wishesType <=", value, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeLike(String value) {
            addCriterion("wishesType like", value, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeNotLike(String value) {
            addCriterion("wishesType not like", value, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeIn(List<String> values) {
            addCriterion("wishesType in", values, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeNotIn(List<String> values) {
            addCriterion("wishesType not in", values, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeBetween(String value1, String value2) {
            addCriterion("wishesType between", value1, value2, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishestypeNotBetween(String value1, String value2) {
            addCriterion("wishesType not between", value1, value2, "wishestype");
            return (Criteria) this;
        }

        public Criteria andWishesdetailIsNull() {
            addCriterion("wishesDetail is null");
            return (Criteria) this;
        }

        public Criteria andWishesdetailIsNotNull() {
            addCriterion("wishesDetail is not null");
            return (Criteria) this;
        }

        public Criteria andWishesdetailEqualTo(String value) {
            addCriterion("wishesDetail =", value, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailNotEqualTo(String value) {
            addCriterion("wishesDetail <>", value, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailGreaterThan(String value) {
            addCriterion("wishesDetail >", value, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailGreaterThanOrEqualTo(String value) {
            addCriterion("wishesDetail >=", value, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailLessThan(String value) {
            addCriterion("wishesDetail <", value, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailLessThanOrEqualTo(String value) {
            addCriterion("wishesDetail <=", value, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailLike(String value) {
            addCriterion("wishesDetail like", value, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailNotLike(String value) {
            addCriterion("wishesDetail not like", value, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailIn(List<String> values) {
            addCriterion("wishesDetail in", values, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailNotIn(List<String> values) {
            addCriterion("wishesDetail not in", values, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailBetween(String value1, String value2) {
            addCriterion("wishesDetail between", value1, value2, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishesdetailNotBetween(String value1, String value2) {
            addCriterion("wishesDetail not between", value1, value2, "wishesdetail");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeIsNull() {
            addCriterion("wishesCreateTime is null");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeIsNotNull() {
            addCriterion("wishesCreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeEqualTo(String value) {
            addCriterion("wishesCreateTime =", value, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeNotEqualTo(String value) {
            addCriterion("wishesCreateTime <>", value, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeGreaterThan(String value) {
            addCriterion("wishesCreateTime >", value, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("wishesCreateTime >=", value, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeLessThan(String value) {
            addCriterion("wishesCreateTime <", value, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeLessThanOrEqualTo(String value) {
            addCriterion("wishesCreateTime <=", value, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeLike(String value) {
            addCriterion("wishesCreateTime like", value, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeNotLike(String value) {
            addCriterion("wishesCreateTime not like", value, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeIn(List<String> values) {
            addCriterion("wishesCreateTime in", values, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeNotIn(List<String> values) {
            addCriterion("wishesCreateTime not in", values, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeBetween(String value1, String value2) {
            addCriterion("wishesCreateTime between", value1, value2, "wishescreatetime");
            return (Criteria) this;
        }

        public Criteria andWishescreatetimeNotBetween(String value1, String value2) {
            addCriterion("wishesCreateTime not between", value1, value2, "wishescreatetime");
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