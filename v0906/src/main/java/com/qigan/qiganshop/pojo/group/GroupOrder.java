package com.qigan.qiganshop.pojo.group;

import com.qigan.qiganshop.pojo.Coupon;
import com.qigan.qiganshop.pojo.Invoice;
import com.qigan.qiganshop.pojo.Pay;
import com.qigan.qiganshop.pojo.XltcMobileBaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * App 提交的订单
 *
 * @author wanghao
 * @time 2019-05-10 16:19
 */
@ApiModel(value = "订单接收类")
public class GroupOrder extends XltcMobileBaseModel{
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "用户地址 ID")
    private String userAddrId;
    @ApiModelProperty(value = "配送时间")
    private String userChooseTime;
    @ApiModelProperty(value = "商品集合 数据类型为:(\"itemId\":count)  itemId-->(String) count -->int ")
    private Map<String, Integer> items;
    @ApiModelProperty(value = "优惠券信息")
    private List<Coupon> coupons;
    @ApiModelProperty(value = "发票信息")
    private Invoice invoice;
    @ApiModelProperty(value = "买家留言")
    private String buyerMemo;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo;
    }
}
