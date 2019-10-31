/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.math.BigDecimal;

/**
 * @author wyy
 *
 */
public class XltcUserCouponModel {
	
	private String userId;
	
	private String type;
	
	private String couponId;
	
	private String couponName;
	
	private String userCouponId;
	
	private String isUse;
	
	private String startTime;
	
	private String disableTime;
	
	private String timeOut;
	
	private String begin;
	
	private String end;
	
	private boolean isShow;
	
	private BigDecimal fullMoney;
	
	private BigDecimal reduceMoney;
	
	private boolean fullMoneyIsUse;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(String userCouponId) {
		this.userCouponId = userCouponId;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
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

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
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

	public BigDecimal getFullMoney() {
		return fullMoney;
	}

	public void setFullMoney(BigDecimal fullMoney) {
		this.fullMoney = fullMoney;
	}

	public BigDecimal getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(BigDecimal reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	
	public boolean isFullMoneyIsUse() {
		return fullMoneyIsUse;
	}

	public void setFullMoneyIsUse(boolean fullMoneyIsUse) {
		this.fullMoneyIsUse = fullMoneyIsUse;
	}

	@Override
	public String toString() {
		return "XltcUserCouponModel [userId=" + userId + ", type=" + type + ", couponId=" + couponId + ", couponName="
				+ couponName + ", userCouponId=" + userCouponId + ", isUse=" + isUse + ", startTime=" + startTime
				+ ", disableTime=" + disableTime + ", timeOut=" + timeOut + ", begin=" + begin + ", end=" + end
				+ ", isShow=" + isShow + ", fullMoney=" + fullMoney + ", reduceMoney=" + reduceMoney
				+ ", fullMoneyIsUse=" + fullMoneyIsUse + "]";
	}

}
