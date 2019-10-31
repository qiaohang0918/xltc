package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户拥有的优惠券
 *
 * @author wanghao
 */
@ApiModel(value = "UserCoupon", description = "用户优惠券实体")
public class UserCoupon extends CommonPage{
    @ApiModelProperty(value = "用户优惠券 ID")
    private String userCouponId;
    @ApiModelProperty(value = "用户 ID")
    private String userId;
    @ApiModelProperty(value = "优惠券 ID")
    private String couponId;
    @ApiModelProperty(value = "数量")
    private Integer number;
    @ApiModelProperty(value = "是否过期")
    private String timeOut;
    @ApiModelProperty(value = "是否过期")
    private String isUse;
    @ApiModelProperty(value = "是否过期")
    private String orderId;
    
    private String token;
    
    private String startTime;
    
    private String disableTime;
    
    private String createTime;
    
    private String useTime;
    
    private String phone;
    
    private Integer fullMoney;
    
    private Integer reduceMoney;
    
    private String mobileId;
    
    private String isSend;
    
    private String sendUser;
    
    public UserCoupon(){
    	
    }
    
    public UserCoupon(String userId, String couponId, String isSend, String sendUser){
    	this.userId = userId;
    	this.couponId = couponId;
    	this.isSend = isSend;
    	this.sendUser = sendUser;
    }
    
    public String getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(String userCouponId) {
        this.userCouponId = userCouponId == null ? null : userCouponId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut == null ? null : timeOut.trim();
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(String disableTime) {
		this.disableTime = disableTime;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	@Override
	public String toString() {
		return "UserCoupon [userCouponId=" + userCouponId + ", userId=" + userId + ", couponId=" + couponId
				+ ", number=" + number + ", timeOut=" + timeOut + ", isUse=" + isUse + ", orderId=" + orderId
				+ ", token=" + token + ", startTime=" + startTime + ", disableTime=" + disableTime + ", createTime="
				+ createTime + ", useTime=" + useTime + ", phone=" + phone + ", fullMoney=" + fullMoney
				+ ", reduceMoney=" + reduceMoney + ", mobileId=" + mobileId + ", isSend=" + isSend + ", sendUser="
				+ sendUser + "]";
	}
}