package com.qigan.qiganshop.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbActivityUnionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbActivityUnionExample() {
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

        public Criteria andUnionIdIsNull() {
            addCriterion("union_id is null");
            return (Criteria) this;
        }

        public Criteria andUnionIdIsNotNull() {
            addCriterion("union_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnionIdEqualTo(String value) {
            addCriterion("union_id =", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotEqualTo(String value) {
            addCriterion("union_id <>", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdGreaterThan(String value) {
            addCriterion("union_id >", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdGreaterThanOrEqualTo(String value) {
            addCriterion("union_id >=", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLessThan(String value) {
            addCriterion("union_id <", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLessThanOrEqualTo(String value) {
            addCriterion("union_id <=", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdLike(String value) {
            addCriterion("union_id like", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotLike(String value) {
            addCriterion("union_id not like", value, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdIn(List<String> values) {
            addCriterion("union_id in", values, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotIn(List<String> values) {
            addCriterion("union_id not in", values, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdBetween(String value1, String value2) {
            addCriterion("union_id between", value1, value2, "unionId");
            return (Criteria) this;
        }

        public Criteria andUnionIdNotBetween(String value1, String value2) {
            addCriterion("union_id not between", value1, value2, "unionId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(String value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(String value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(String value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(String value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(String value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(String value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLike(String value) {
            addCriterion("activity_id like", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotLike(String value) {
            addCriterion("activity_id not like", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<String> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<String> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(String value1, String value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(String value1, String value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andUnionSortIsNull() {
            addCriterion("union_sort is null");
            return (Criteria) this;
        }

        public Criteria andUnionSortIsNotNull() {
            addCriterion("union_sort is not null");
            return (Criteria) this;
        }

        public Criteria andUnionSortEqualTo(String value) {
            addCriterion("union_sort =", value, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortNotEqualTo(String value) {
            addCriterion("union_sort <>", value, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortGreaterThan(String value) {
            addCriterion("union_sort >", value, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortGreaterThanOrEqualTo(String value) {
            addCriterion("union_sort >=", value, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortLessThan(String value) {
            addCriterion("union_sort <", value, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortLessThanOrEqualTo(String value) {
            addCriterion("union_sort <=", value, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortLike(String value) {
            addCriterion("union_sort like", value, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortNotLike(String value) {
            addCriterion("union_sort not like", value, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortIn(List<String> values) {
            addCriterion("union_sort in", values, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortNotIn(List<String> values) {
            addCriterion("union_sort not in", values, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortBetween(String value1, String value2) {
            addCriterion("union_sort between", value1, value2, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionSortNotBetween(String value1, String value2) {
            addCriterion("union_sort not between", value1, value2, "unionSort");
            return (Criteria) this;
        }

        public Criteria andUnionNameIsNull() {
            addCriterion("union_name is null");
            return (Criteria) this;
        }

        public Criteria andUnionNameIsNotNull() {
            addCriterion("union_name is not null");
            return (Criteria) this;
        }

        public Criteria andUnionNameEqualTo(String value) {
            addCriterion("union_name =", value, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameNotEqualTo(String value) {
            addCriterion("union_name <>", value, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameGreaterThan(String value) {
            addCriterion("union_name >", value, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameGreaterThanOrEqualTo(String value) {
            addCriterion("union_name >=", value, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameLessThan(String value) {
            addCriterion("union_name <", value, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameLessThanOrEqualTo(String value) {
            addCriterion("union_name <=", value, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameLike(String value) {
            addCriterion("union_name like", value, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameNotLike(String value) {
            addCriterion("union_name not like", value, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameIn(List<String> values) {
            addCriterion("union_name in", values, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameNotIn(List<String> values) {
            addCriterion("union_name not in", values, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameBetween(String value1, String value2) {
            addCriterion("union_name between", value1, value2, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionNameNotBetween(String value1, String value2) {
            addCriterion("union_name not between", value1, value2, "unionName");
            return (Criteria) this;
        }

        public Criteria andUnionPictureIsNull() {
            addCriterion("union_picture is null");
            return (Criteria) this;
        }

        public Criteria andUnionPictureIsNotNull() {
            addCriterion("union_picture is not null");
            return (Criteria) this;
        }

        public Criteria andUnionPictureEqualTo(String value) {
            addCriterion("union_picture =", value, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureNotEqualTo(String value) {
            addCriterion("union_picture <>", value, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureGreaterThan(String value) {
            addCriterion("union_picture >", value, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureGreaterThanOrEqualTo(String value) {
            addCriterion("union_picture >=", value, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureLessThan(String value) {
            addCriterion("union_picture <", value, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureLessThanOrEqualTo(String value) {
            addCriterion("union_picture <=", value, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureLike(String value) {
            addCriterion("union_picture like", value, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureNotLike(String value) {
            addCriterion("union_picture not like", value, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureIn(List<String> values) {
            addCriterion("union_picture in", values, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureNotIn(List<String> values) {
            addCriterion("union_picture not in", values, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureBetween(String value1, String value2) {
            addCriterion("union_picture between", value1, value2, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionPictureNotBetween(String value1, String value2) {
            addCriterion("union_picture not between", value1, value2, "unionPicture");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlIsNull() {
            addCriterion("union_skip_url is null");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlIsNotNull() {
            addCriterion("union_skip_url is not null");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlEqualTo(String value) {
            addCriterion("union_skip_url =", value, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlNotEqualTo(String value) {
            addCriterion("union_skip_url <>", value, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlGreaterThan(String value) {
            addCriterion("union_skip_url >", value, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlGreaterThanOrEqualTo(String value) {
            addCriterion("union_skip_url >=", value, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlLessThan(String value) {
            addCriterion("union_skip_url <", value, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlLessThanOrEqualTo(String value) {
            addCriterion("union_skip_url <=", value, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlLike(String value) {
            addCriterion("union_skip_url like", value, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlNotLike(String value) {
            addCriterion("union_skip_url not like", value, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlIn(List<String> values) {
            addCriterion("union_skip_url in", values, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlNotIn(List<String> values) {
            addCriterion("union_skip_url not in", values, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlBetween(String value1, String value2) {
            addCriterion("union_skip_url between", value1, value2, "unionSkipUrl");
            return (Criteria) this;
        }

        public Criteria andUnionSkipUrlNotBetween(String value1, String value2) {
            addCriterion("union_skip_url not between", value1, value2, "unionSkipUrl");
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