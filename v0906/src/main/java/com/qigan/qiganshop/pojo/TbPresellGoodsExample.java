package com.qigan.qiganshop.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbPresellGoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPresellGoodsExample() {
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

        public Criteria andPreSellIdIsNull() {
            addCriterion("pre_sell_id is null");
            return (Criteria) this;
        }

        public Criteria andPreSellIdIsNotNull() {
            addCriterion("pre_sell_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreSellIdEqualTo(String value) {
            addCriterion("pre_sell_id =", value, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdNotEqualTo(String value) {
            addCriterion("pre_sell_id <>", value, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdGreaterThan(String value) {
            addCriterion("pre_sell_id >", value, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sell_id >=", value, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdLessThan(String value) {
            addCriterion("pre_sell_id <", value, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdLessThanOrEqualTo(String value) {
            addCriterion("pre_sell_id <=", value, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdLike(String value) {
            addCriterion("pre_sell_id like", value, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdNotLike(String value) {
            addCriterion("pre_sell_id not like", value, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdIn(List<String> values) {
            addCriterion("pre_sell_id in", values, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdNotIn(List<String> values) {
            addCriterion("pre_sell_id not in", values, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdBetween(String value1, String value2) {
            addCriterion("pre_sell_id between", value1, value2, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPreSellIdNotBetween(String value1, String value2) {
            addCriterion("pre_sell_id not between", value1, value2, "preSellId");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeIsNull() {
            addCriterion("preSellGoodsCode is null");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeIsNotNull() {
            addCriterion("preSellGoodsCode is not null");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeEqualTo(String value) {
            addCriterion("preSellGoodsCode =", value, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeNotEqualTo(String value) {
            addCriterion("preSellGoodsCode <>", value, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeGreaterThan(String value) {
            addCriterion("preSellGoodsCode >", value, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeGreaterThanOrEqualTo(String value) {
            addCriterion("preSellGoodsCode >=", value, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeLessThan(String value) {
            addCriterion("preSellGoodsCode <", value, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeLessThanOrEqualTo(String value) {
            addCriterion("preSellGoodsCode <=", value, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeLike(String value) {
            addCriterion("preSellGoodsCode like", value, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeNotLike(String value) {
            addCriterion("preSellGoodsCode not like", value, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeIn(List<String> values) {
            addCriterion("preSellGoodsCode in", values, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeNotIn(List<String> values) {
            addCriterion("preSellGoodsCode not in", values, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeBetween(String value1, String value2) {
            addCriterion("preSellGoodsCode between", value1, value2, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andPresellgoodscodeNotBetween(String value1, String value2) {
            addCriterion("preSellGoodsCode not between", value1, value2, "presellgoodscode");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeIsNull() {
            addCriterion("duringBefore is null");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeIsNotNull() {
            addCriterion("duringBefore is not null");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeEqualTo(String value) {
            addCriterion("duringBefore =", value, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeNotEqualTo(String value) {
            addCriterion("duringBefore <>", value, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeGreaterThan(String value) {
            addCriterion("duringBefore >", value, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeGreaterThanOrEqualTo(String value) {
            addCriterion("duringBefore >=", value, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeLessThan(String value) {
            addCriterion("duringBefore <", value, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeLessThanOrEqualTo(String value) {
            addCriterion("duringBefore <=", value, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeLike(String value) {
            addCriterion("duringBefore like", value, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeNotLike(String value) {
            addCriterion("duringBefore not like", value, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeIn(List<String> values) {
            addCriterion("duringBefore in", values, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeNotIn(List<String> values) {
            addCriterion("duringBefore not in", values, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeBetween(String value1, String value2) {
            addCriterion("duringBefore between", value1, value2, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringbeforeNotBetween(String value1, String value2) {
            addCriterion("duringBefore not between", value1, value2, "duringbefore");
            return (Criteria) this;
        }

        public Criteria andDuringafterIsNull() {
            addCriterion("duringAfter is null");
            return (Criteria) this;
        }

        public Criteria andDuringafterIsNotNull() {
            addCriterion("duringAfter is not null");
            return (Criteria) this;
        }

        public Criteria andDuringafterEqualTo(String value) {
            addCriterion("duringAfter =", value, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterNotEqualTo(String value) {
            addCriterion("duringAfter <>", value, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterGreaterThan(String value) {
            addCriterion("duringAfter >", value, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterGreaterThanOrEqualTo(String value) {
            addCriterion("duringAfter >=", value, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterLessThan(String value) {
            addCriterion("duringAfter <", value, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterLessThanOrEqualTo(String value) {
            addCriterion("duringAfter <=", value, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterLike(String value) {
            addCriterion("duringAfter like", value, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterNotLike(String value) {
            addCriterion("duringAfter not like", value, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterIn(List<String> values) {
            addCriterion("duringAfter in", values, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterNotIn(List<String> values) {
            addCriterion("duringAfter not in", values, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterBetween(String value1, String value2) {
            addCriterion("duringAfter between", value1, value2, "duringafter");
            return (Criteria) this;
        }

        public Criteria andDuringafterNotBetween(String value1, String value2) {
            addCriterion("duringAfter not between", value1, value2, "duringafter");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(String value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(String value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(String value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(String value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(String value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(String value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLike(String value) {
            addCriterion("enabled like", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotLike(String value) {
            addCriterion("enabled not like", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<String> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<String> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(String value1, String value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(String value1, String value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
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

        public Criteria andNoIsNull() {
            addCriterion("no is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("no is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("no =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("no <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("no >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("no >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("no <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("no <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("no like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("no not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("no in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("no not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("no between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("no not between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andPictureurlIsNull() {
            addCriterion("pictureUrl is null");
            return (Criteria) this;
        }

        public Criteria andPictureurlIsNotNull() {
            addCriterion("pictureUrl is not null");
            return (Criteria) this;
        }

        public Criteria andPictureurlEqualTo(String value) {
            addCriterion("pictureUrl =", value, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlNotEqualTo(String value) {
            addCriterion("pictureUrl <>", value, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlGreaterThan(String value) {
            addCriterion("pictureUrl >", value, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlGreaterThanOrEqualTo(String value) {
            addCriterion("pictureUrl >=", value, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlLessThan(String value) {
            addCriterion("pictureUrl <", value, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlLessThanOrEqualTo(String value) {
            addCriterion("pictureUrl <=", value, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlLike(String value) {
            addCriterion("pictureUrl like", value, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlNotLike(String value) {
            addCriterion("pictureUrl not like", value, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlIn(List<String> values) {
            addCriterion("pictureUrl in", values, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlNotIn(List<String> values) {
            addCriterion("pictureUrl not in", values, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlBetween(String value1, String value2) {
            addCriterion("pictureUrl between", value1, value2, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andPictureurlNotBetween(String value1, String value2) {
            addCriterion("pictureUrl not between", value1, value2, "pictureurl");
            return (Criteria) this;
        }

        public Criteria andSellnumIsNull() {
            addCriterion("sellNum is null");
            return (Criteria) this;
        }

        public Criteria andSellnumIsNotNull() {
            addCriterion("sellNum is not null");
            return (Criteria) this;
        }

        public Criteria andSellnumEqualTo(String value) {
            addCriterion("sellNum =", value, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumNotEqualTo(String value) {
            addCriterion("sellNum <>", value, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumGreaterThan(String value) {
            addCriterion("sellNum >", value, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumGreaterThanOrEqualTo(String value) {
            addCriterion("sellNum >=", value, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumLessThan(String value) {
            addCriterion("sellNum <", value, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumLessThanOrEqualTo(String value) {
            addCriterion("sellNum <=", value, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumLike(String value) {
            addCriterion("sellNum like", value, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumNotLike(String value) {
            addCriterion("sellNum not like", value, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumIn(List<String> values) {
            addCriterion("sellNum in", values, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumNotIn(List<String> values) {
            addCriterion("sellNum not in", values, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumBetween(String value1, String value2) {
            addCriterion("sellNum between", value1, value2, "sellnum");
            return (Criteria) this;
        }

        public Criteria andSellnumNotBetween(String value1, String value2) {
            addCriterion("sellNum not between", value1, value2, "sellnum");
            return (Criteria) this;
        }

        public Criteria andPresendtimeIsNull() {
            addCriterion("preSendTime is null");
            return (Criteria) this;
        }

        public Criteria andPresendtimeIsNotNull() {
            addCriterion("preSendTime is not null");
            return (Criteria) this;
        }

        public Criteria andPresendtimeEqualTo(String value) {
            addCriterion("preSendTime =", value, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeNotEqualTo(String value) {
            addCriterion("preSendTime <>", value, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeGreaterThan(String value) {
            addCriterion("preSendTime >", value, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeGreaterThanOrEqualTo(String value) {
            addCriterion("preSendTime >=", value, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeLessThan(String value) {
            addCriterion("preSendTime <", value, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeLessThanOrEqualTo(String value) {
            addCriterion("preSendTime <=", value, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeLike(String value) {
            addCriterion("preSendTime like", value, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeNotLike(String value) {
            addCriterion("preSendTime not like", value, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeIn(List<String> values) {
            addCriterion("preSendTime in", values, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeNotIn(List<String> values) {
            addCriterion("preSendTime not in", values, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeBetween(String value1, String value2) {
            addCriterion("preSendTime between", value1, value2, "presendtime");
            return (Criteria) this;
        }

        public Criteria andPresendtimeNotBetween(String value1, String value2) {
            addCriterion("preSendTime not between", value1, value2, "presendtime");
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