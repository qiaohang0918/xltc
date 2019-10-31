package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Pay", description = "订单支付")
public class Pay {
    @ApiModelProperty(value = "流水号")
    private String payCode;
    @ApiModelProperty(value = "本系统码")
    private String outPayCode;
    @ApiModelProperty(value = "订单号")
    private String orderId;
    @ApiModelProperty(value = "支付方式")
    private String payTypeCode;
    @ApiModelProperty(value = "支付金额")
    private String payment;
    @ApiModelProperty(value = "支付时间")
    private String paytime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOutPayCode() {
        return outPayCode;
    }

    public void setOutPayCode(String outPayCode) {
        this.outPayCode = outPayCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }
}
