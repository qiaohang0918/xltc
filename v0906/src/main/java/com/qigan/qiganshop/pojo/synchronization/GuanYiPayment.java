package com.qigan.qiganshop.pojo.synchronization;


import io.swagger.annotations.ApiModelProperty;

/**
 * 支付方式
 *
 * @author wanghao
 * @time 2019-05-04 01:44
 */
public class GuanYiPayment {
    @ApiModelProperty(value = "支付方式")
    private String pay_type_code;
    @ApiModelProperty(value = "支付金额")
    private double payment;
    @ApiModelProperty(value = "支付时间")
    private Long paytime;
    @ApiModelProperty(value = "交易号")
    private String pay_code;
    @ApiModelProperty(value = "账号")
    private String account;

    public String getPay_type_code() {
        return pay_type_code;
    }

    public void setPay_type_code(String pay_type_code) {
        this.pay_type_code = pay_type_code;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Long getPaytime() {
        return paytime;
    }

    public void setPaytime(Long paytime) {
        this.paytime = paytime;
    }

    public String getPay_code() {
        return pay_code;
    }

    public void setPay_code(String pay_code) {
        this.pay_code = pay_code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
