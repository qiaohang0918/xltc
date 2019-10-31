package com.qigan.qiganshop.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class TbExpland implements Serializable {

    @Excel(name = "渠道用户ID")
    private String explanduserid;

    @Excel(name = "渠道用户邀请的注册数")
    private String explandregisters;

    @Excel(name = "渠道用户Phone")
    private String phone;

    @Excel(name = "成功下单的注册数")
    private int orderedCount;

    public TbExpland() {
    }

    public String getExplanduserid() {
        return explanduserid;
    }

    public void setExplanduserid(String explanduserid) {
        this.explanduserid = explanduserid;
    }

    public String getExplandregisters() {
        return explandregisters;
    }

    public void setExplandregisters(String explandregisters) {
        this.explandregisters = explandregisters;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOrderedCount() {
        return orderedCount;
    }

    public void setOrderedCount(int orderedCount) {
        this.orderedCount = orderedCount;
    }
}