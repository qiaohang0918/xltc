package com.qigan.qiganshop.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 本地订单实体
 *
 * @author wanghao
 */
@SuppressWarnings("all")
@ApiModel(value = "order", description = "订单")
@ToString
public class Order extends CommonPage implements Serializable {

    /**
     * orderId   单号
     * dealDatetime 创建时间
     * receiverName 收货人
     * receiverMobile 收获人手机
     * receiverAddress 收货地址
     *  status 订单状态
     *  payMoney  订单总价
     */

    @ApiModelProperty(value = "订单 ID")
    @Excel(name = "订单编号")
    private String orderId;
    
    @JsonIgnore
    @ApiModelProperty(value = "管易订单 ID")
    private String guanyiId;
    
    @ApiModelProperty("用户 ID")
    private String userId;
    
    @ApiModelProperty(value = "用户收货地址 ID")
    private String userAddrId;
    
    @ApiModelProperty(value = "用户选择配送时间")
    private String userChooseTime;
    
    @ApiModelProperty(value = "下单时间")
    @Excel(name = "创建时间")
    private String dealDatetime;
    
    @ApiModelProperty(value = "支付时间")
    private String payTime;
    
    @ApiModelProperty(value = "配送时间")
    private String peisongTime;
    
    @ApiModelProperty(value = "送达时间")
    private String endTime;
    
    @ApiModelProperty(value = "店铺代码")
    private String shopCode;
    
    @ApiModelProperty(value = "仓库代码")
    private String warehouseCode;
    
    @ApiModelProperty(value = "物流公司代码")
    private String expressCode;
    
    @ApiModelProperty(value = "订单类型")
    private String orderTypeCode;
    
    @ApiModelProperty(value = "收货人名字")
    @Excel(name = "收货人")
    
    private String receiverName;
    @ApiModelProperty(value = "收货人联系电话")
    
    @Excel(name = "收货人手机")
    private String receiverMobile;

    @ApiModelProperty(value = "省")
    private String receiverProvince;

    @ApiModelProperty(value = "市")
    private String receiverCity;

    @ApiModelProperty(value = "区")
    private String receiverDistrict;

    @ApiModelProperty(value = "收货地址")
    @Excel(name ="收货地址")
    private String receiverAddress;

    @ApiModelProperty(value = "订单支付流水号")
    private String payCode;

    @ApiModelProperty(value = "订单金额")
    private Double money;

    @ApiModelProperty(value = "优惠金额")
    private Double couponMoney;

    @ApiModelProperty(value = "配送金额")
    private Double peisongfei;

    @ApiModelProperty(value = "支付金额")
    @Excel(name = "订单总价")
    private Double payMoney;

    @ApiModelProperty(value = "发票 ID")
    private Long invoiceId;

    @ApiModelProperty(value = "买家留言")
    private String buyerMemo;

    @ApiModelProperty(value = "订单状态")
    @Excel(name = "订单状态")
    private String status;

    @ApiModelProperty(value = "评论")
    private String comment;

    @ApiModelProperty(value = "待支付锁")
    private String waitingPay;

    @ApiModelProperty(value = "订单tag")
    private String tag;

    @ApiModelProperty(value = "预售期号")
    private String level;

    private String start;

    private String end;
    
    private String isDel;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGuanyiId() {
        return guanyiId;
    }

    public void setGuanyiId(String guanyiId) {
        this.guanyiId = guanyiId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAddrId() {
        return userAddrId;
    }

    public void setUserAddrId(String userAddrId) {
        this.userAddrId = userAddrId;
    }

    public String getUserChooseTime() {
        return userChooseTime;
    }

    public void setUserChooseTime(String userChooseTime) {
        this.userChooseTime = userChooseTime;
    }

    public String getDealDatetime() {
        return dealDatetime;
    }

    public void setDealDatetime(String dealDatetime) {
        this.dealDatetime = dealDatetime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPeisongTime() {
        return peisongTime;
    }

    public void setPeisongTime(String peisongTime) {
        this.peisongTime = peisongTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(Double couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Double getPeisongfei() {
        return peisongfei;
    }

    public void setPeisongfei(Double peisongfei) {
        this.peisongfei = peisongfei;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWaitingPay() {
        return waitingPay;
    }

    public void setWaitingPay(String waitingPay) {
        this.waitingPay = waitingPay;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
}