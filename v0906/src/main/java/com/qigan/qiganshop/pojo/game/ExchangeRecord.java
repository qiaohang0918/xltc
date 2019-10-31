package com.qigan.qiganshop.pojo.game;

import java.util.Date;

/**
 * 鸡蛋兑换鸡蛋券记录
 * @author starlord
 */
public class ExchangeRecord {
    private String userId;
    private int couponNum;
    private int eggNum;
    private String couponId;
    private Date createTime;

    private ExchangeRecord() {
    }

    public ExchangeRecord(String userId, int couponNum, int eggNum, String couponId) {
        this.userId = userId;
        this.couponNum = couponNum;
        this.eggNum = eggNum;
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public int getCouponNum() {
        return couponNum;
    }

    public int getEggNum() {
        return eggNum;
    }

    public String getCouponId() {
        return couponId;
    }
}
