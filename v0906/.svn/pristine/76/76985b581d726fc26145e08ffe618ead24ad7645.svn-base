package com.qigan.qiganshop.pojo;

import java.util.List;

import com.qigan.qiganshop.domain.TbCouponDetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 优惠券
 *
 * @author wanghao
 * @time 2019-05-04 23:19
 */
@ApiModel(value = "Coupon", description = "优惠券实体")
public class Coupon {
    @ApiModelProperty(value = "优惠券 ID")
    private String couponId;
    @ApiModelProperty(value = "优惠券类型 1,满减 2,免运费,3 鸡蛋券")
    private String type;
    @ApiModelProperty(value = "优惠券满金额")
    private Integer fullMoney;
    @ApiModelProperty(value = "优惠券减金额")
    private Integer reduceMoney;
    @ApiModelProperty(value = "鸡蛋券兑换比例(个数)")
    private Integer eggNumber;
    @ApiModelProperty(value = "开始时间")
    private String begin;
    @ApiModelProperty(value = "结束时间")
    private String end;
    @ApiModelProperty(value = "是否可用")
    private String enable;
    @ApiModelProperty(value = "启用时间")
    private String enableTime;
    @ApiModelProperty(value = "用户是否选择")
    private String isChoose;
    @ApiModelProperty(value = "关联 ID")
    private String userCouponId;
    private String couponName;
    
    private List<TbCouponDetails> couponDetails;
    
    public Coupon(){
    	
    }

    public Coupon(String type, String couponId){
    	this.type = type;
    	this.couponId = couponId;
    }

    public String getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(String userCouponId) {
        this.userCouponId = userCouponId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(Integer fullMoney) {
        this.fullMoney = fullMoney;
    }

    public Integer getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(Integer reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getEggNumber() {
        return eggNumber;
    }

    public void setEggNumber(Integer eggNumber) {
        this.eggNumber = eggNumber;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(String enableTime) {
        this.enableTime = enableTime;
    }

    public String getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(String isChoose) {
        this.isChoose = isChoose;
    }

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public List<TbCouponDetails> getCouponDetails() {
		return couponDetails;
	}

	public void setCouponDetails(List<TbCouponDetails> couponDetails) {
		this.couponDetails = couponDetails;
	}
	
}
