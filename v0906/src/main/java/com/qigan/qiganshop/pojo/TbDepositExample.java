package com.qigan.qiganshop.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbDepositExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbDepositExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andDepositTypeIsNull() {
            addCriterion("deposit_type is null");
            return (Criteria) this;
        }

        public Criteria andDepositTypeIsNotNull() {
            addCriterion("deposit_type is not null");
            return (Criteria) this;
        }

        public Criteria andDepositTypeEqualTo(String value) {
            addCriterion("deposit_type =", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeNotEqualTo(String value) {
            addCriterion("deposit_type <>", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeGreaterThan(String value) {
            addCriterion("deposit_type >", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_type >=", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeLessThan(String value) {
            addCriterion("deposit_type <", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeLessThanOrEqualTo(String value) {
            addCriterion("deposit_type <=", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeLike(String value) {
            addCriterion("deposit_type like", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeNotLike(String value) {
            addCriterion("deposit_type not like", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeIn(List<String> values) {
            addCriterion("deposit_type in", values, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeNotIn(List<String> values) {
            addCriterion("deposit_type not in", values, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeBetween(String value1, String value2) {
            addCriterion("deposit_type between", value1, value2, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeNotBetween(String value1, String value2) {
            addCriterion("deposit_type not between", value1, value2, "depositType");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumIsNull() {
            addCriterion("out_trade_num is null");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumIsNotNull() {
            addCriterion("out_trade_num is not null");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumEqualTo(String value) {
            addCriterion("out_trade_num =", value, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumNotEqualTo(String value) {
            addCriterion("out_trade_num <>", value, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumGreaterThan(String value) {
            addCriterion("out_trade_num >", value, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumGreaterThanOrEqualTo(String value) {
            addCriterion("out_trade_num >=", value, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumLessThan(String value) {
            addCriterion("out_trade_num <", value, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumLessThanOrEqualTo(String value) {
            addCriterion("out_trade_num <=", value, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumLike(String value) {
            addCriterion("out_trade_num like", value, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumNotLike(String value) {
            addCriterion("out_trade_num not like", value, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumIn(List<String> values) {
            addCriterion("out_trade_num in", values, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumNotIn(List<String> values) {
            addCriterion("out_trade_num not in", values, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumBetween(String value1, String value2) {
            addCriterion("out_trade_num between", value1, value2, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andOutTradeNumNotBetween(String value1, String value2) {
            addCriterion("out_trade_num not between", value1, value2, "outTradeNum");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createtime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createtime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNull() {
            addCriterion("paytime is null");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNotNull() {
            addCriterion("paytime is not null");
            return (Criteria) this;
        }

        public Criteria andPaytimeEqualTo(String value) {
            addCriterion("paytime =", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotEqualTo(String value) {
            addCriterion("paytime <>", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThan(String value) {
            addCriterion("paytime >", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThanOrEqualTo(String value) {
            addCriterion("paytime >=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThan(String value) {
            addCriterion("paytime <", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThanOrEqualTo(String value) {
            addCriterion("paytime <=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLike(String value) {
            addCriterion("paytime like", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotLike(String value) {
            addCriterion("paytime not like", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIn(List<String> values) {
            addCriterion("paytime in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotIn(List<String> values) {
            addCriterion("paytime not in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeBetween(String value1, String value2) {
            addCriterion("paytime between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotBetween(String value1, String value2) {
            addCriterion("paytime not between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdIsNull() {
            addCriterion("deposit_card_id is null");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdIsNotNull() {
            addCriterion("deposit_card_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdEqualTo(String value) {
            addCriterion("deposit_card_id =", value, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdNotEqualTo(String value) {
            addCriterion("deposit_card_id <>", value, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdGreaterThan(String value) {
            addCriterion("deposit_card_id >", value, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_card_id >=", value, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdLessThan(String value) {
            addCriterion("deposit_card_id <", value, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdLessThanOrEqualTo(String value) {
            addCriterion("deposit_card_id <=", value, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdLike(String value) {
            addCriterion("deposit_card_id like", value, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdNotLike(String value) {
            addCriterion("deposit_card_id not like", value, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdIn(List<String> values) {
            addCriterion("deposit_card_id in", values, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdNotIn(List<String> values) {
            addCriterion("deposit_card_id not in", values, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdBetween(String value1, String value2) {
            addCriterion("deposit_card_id between", value1, value2, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositCardIdNotBetween(String value1, String value2) {
            addCriterion("deposit_card_id not between", value1, value2, "depositCardId");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitIsNull() {
            addCriterion("deposit_money_unit is null");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitIsNotNull() {
            addCriterion("deposit_money_unit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitEqualTo(String value) {
            addCriterion("deposit_money_unit =", value, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitNotEqualTo(String value) {
            addCriterion("deposit_money_unit <>", value, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitGreaterThan(String value) {
            addCriterion("deposit_money_unit >", value, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_money_unit >=", value, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitLessThan(String value) {
            addCriterion("deposit_money_unit <", value, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitLessThanOrEqualTo(String value) {
            addCriterion("deposit_money_unit <=", value, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitLike(String value) {
            addCriterion("deposit_money_unit like", value, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitNotLike(String value) {
            addCriterion("deposit_money_unit not like", value, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitIn(List<String> values) {
            addCriterion("deposit_money_unit in", values, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitNotIn(List<String> values) {
            addCriterion("deposit_money_unit not in", values, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitBetween(String value1, String value2) {
            addCriterion("deposit_money_unit between", value1, value2, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andDepositMoneyUnitNotBetween(String value1, String value2) {
            addCriterion("deposit_money_unit not between", value1, value2, "depositMoneyUnit");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIsNull() {
            addCriterion("transaction_id is null");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIsNotNull() {
            addCriterion("transaction_id is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionIdEqualTo(String value) {
            addCriterion("transaction_id =", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotEqualTo(String value) {
            addCriterion("transaction_id <>", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdGreaterThan(String value) {
            addCriterion("transaction_id >", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_id >=", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLessThan(String value) {
            addCriterion("transaction_id <", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLessThanOrEqualTo(String value) {
            addCriterion("transaction_id <=", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLike(String value) {
            addCriterion("transaction_id like", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotLike(String value) {
            addCriterion("transaction_id not like", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIn(List<String> values) {
            addCriterion("transaction_id in", values, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotIn(List<String> values) {
            addCriterion("transaction_id not in", values, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdBetween(String value1, String value2) {
            addCriterion("transaction_id between", value1, value2, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotBetween(String value1, String value2) {
            addCriterion("transaction_id not between", value1, value2, "transactionId");
            return (Criteria) this;
        }

        public Criteria andDepositContentIsNull() {
            addCriterion("deposit_content is null");
            return (Criteria) this;
        }

        public Criteria andDepositContentIsNotNull() {
            addCriterion("deposit_content is not null");
            return (Criteria) this;
        }

        public Criteria andDepositContentEqualTo(String value) {
            addCriterion("deposit_content =", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentNotEqualTo(String value) {
            addCriterion("deposit_content <>", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentGreaterThan(String value) {
            addCriterion("deposit_content >", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_content >=", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentLessThan(String value) {
            addCriterion("deposit_content <", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentLessThanOrEqualTo(String value) {
            addCriterion("deposit_content <=", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentLike(String value) {
            addCriterion("deposit_content like", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentNotLike(String value) {
            addCriterion("deposit_content not like", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentIn(List<String> values) {
            addCriterion("deposit_content in", values, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentNotIn(List<String> values) {
            addCriterion("deposit_content not in", values, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentBetween(String value1, String value2) {
            addCriterion("deposit_content between", value1, value2, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentNotBetween(String value1, String value2) {
            addCriterion("deposit_content not between", value1, value2, "depositContent");
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