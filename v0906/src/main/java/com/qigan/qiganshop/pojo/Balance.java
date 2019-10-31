package com.qigan.qiganshop.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 余额明细实体
 *
 * @author wanghao
 */
public class Balance implements Serializable {
    private String balanceId;

    private String userId;

    private Double money;

    private String moneyFlow;

    private Double flowNum;

    private Double oldMoney;

    private String flowInfo;

    private String createTime;

    private String orderId;

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId == null ? null : balanceId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getMoneyFlow() {
        return moneyFlow;
    }

    public void setMoneyFlow(String moneyFlow) {
        this.moneyFlow = moneyFlow == null ? null : moneyFlow.trim();
    }

    public Double getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(Double flowNum) {
        this.flowNum = flowNum;
    }

    public String getFlowInfo() {
        return flowInfo;
    }

    public void setFlowInfo(String flowInfo) {
        this.flowInfo = flowInfo == null ? null : flowInfo.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(Double oldMoney) {
        this.oldMoney = oldMoney;
    }
}