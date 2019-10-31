package com.qigan.qiganshop.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class TbRefundRecord implements Serializable {
    private String refundrecordid;

    @Excel(name = "订单编号")
    private String orderid;

    @Excel(name = "退款时间")
    private String opttime;

    @Excel(name = "操作者工号")
    private String no;

    @Excel(name = "操作者姓名")
    private String name;

    @Excel(name = "退款类型")
    private String refundtype;

    @Excel(name = "退款金额")
    private String money;
    //额外联查属性

    //下单时间
    @Excel(name = "下单时间")
    private String dealDateTime;
    //支付时间
    @Excel(name = "支付时间")
    private String payTime;
    //用户id
    @Excel(name = "下单用户编号")
    private String userId;
    //用户名
    @Excel(name = "下单用户姓名")
    private String nickName;
    //手机
    @Excel(name = "下单用户电话")
    private String phone;
    //支付金额
    @Excel(name = "下单实付金额")
    private String payMoney;

    public String getDealDateTime() {
        return dealDateTime;
    }

    public void setDealDateTime(String dealDateTime) {
        this.dealDateTime = dealDateTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    private static final long serialVersionUID = 1L;

    public String getRefundrecordid() {
        return refundrecordid;
    }

    public void setRefundrecordid(String refundrecordid) {
        this.refundrecordid = refundrecordid == null ? null : refundrecordid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getOpttime() {
        return opttime;
    }

    public void setOpttime(String opttime) {
        this.opttime = opttime == null ? null : opttime.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRefundtype() {
        return refundtype;
    }

    public void setRefundtype(String refundtype) {
        this.refundtype = refundtype == null ? null : refundtype.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", refundrecordid=").append(refundrecordid);
        sb.append(", orderid=").append(orderid);
        sb.append(", opttime=").append(opttime);
        sb.append(", no=").append(no);
        sb.append(", name=").append(name);
        sb.append(", refundtype=").append(refundtype);
        sb.append(", money=").append(money);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}