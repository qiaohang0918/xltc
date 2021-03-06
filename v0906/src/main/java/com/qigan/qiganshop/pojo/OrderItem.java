package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "OrderItem", description = "订单商品详情")
public class OrderItem implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "订单商品 ID")
    private String orderItemId;
    @ApiModelProperty(value = "订单 ID")
    private String orderId;
    @ApiModelProperty(value = "用户 ID")
    private String userId;
    @ApiModelProperty(value = "SPU ID")
    private String goodsId;
    @ApiModelProperty(value = "SKU ID")
    private String itemId;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "规格名称")
    private String itemName;
    @ApiModelProperty(value = "购买时价格")
    private Double byPrice;
    @ApiModelProperty(value = "图片")
    private String picUrls;
    @ApiModelProperty(value = "数量")
    private Integer count;
    @ApiModelProperty(value = "计量单位")
    private String unit;
    @ApiModelProperty(value = "商品code")
    private String code;
    @ApiModelProperty(value = "小计")
    private Double subTotal;
    @ApiModelProperty(value = "是否是问题退款")
    private String isQuestionRefund;

    public OrderItem() {
    }

    public String getIsQuestionRefund() {
        return isQuestionRefund;
    }

    public void setIsQuestionRefund(String isQuestionRefund) {
        this.isQuestionRefund = isQuestionRefund;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId == null ? null : orderItemId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Double getByPrice() {
        return byPrice;
    }

    public void setByPrice(Double byPrice) {
        this.byPrice = byPrice;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls == null ? null : picUrls.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
}