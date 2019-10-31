package com.qigan.qiganshop.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/24 21:42
 * @Modified By:
 */
public class DeliveryDetails {

    @Excel(name = "订单号")
    private String orderId;
    @Excel(name = "订单状态")
    private String status;
   // @Excel(name = "下单时间")
    private String dealDateTime;
    @Excel(name = "支付金额")
    private Double payMoney;
    @Excel(name = "收货地址")
    private String receiverAddress;
   // @Excel(name = "配送员姓名")
    private String name;
   // @Excel(name = "配送员工号")
    private String deliveryerId;
   // @Excel(name = "配送开始时间")
    private String outboundTime;
    @Excel(name = "配送完成时间")
    private String endTime;
    @Excel(name = "支付时间")
    private String payTime;
   // @Excel(name = "用户选择配送时间")
    private String userChooseTime;
    @Excel(name = "接收人手机")
    private String receiverMobile;
    @Excel(name = "下单人手机")
    private String phone;
    @Excel(name = "所购商品")
    private String byGoods;

    public String getByGoods() {
        return byGoods;
    }

    public void setByGoods(String byGoods) {
        this.byGoods = byGoods;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getUserChooseTime() {
        return userChooseTime;
    }

    public void setUserChooseTime(String userChooseTime) {
        this.userChooseTime = userChooseTime;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOutboundTime() {
        return outboundTime;
    }

    public void setOutboundTime(String outboundTime) {
        this.outboundTime = outboundTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public DeliveryDetails() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDealDateTime() {
        return dealDateTime;
    }

    public void setDealDateTime(String dealDateTime) {
        this.dealDateTime = dealDateTime;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryerId() {
        return deliveryerId;
    }

    public void setDeliveryerId(String deliveryerId) {
        this.deliveryerId = deliveryerId;
    }
}
