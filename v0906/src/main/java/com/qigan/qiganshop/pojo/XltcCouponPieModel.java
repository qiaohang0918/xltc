/**
 * 
 */
package com.qigan.qiganshop.pojo;

/**
 * @author wyy
 *
 */
public class XltcCouponPieModel {
	
	private String couponName;
	
	private String couponId;
	
	private Integer total;
	
	private Integer used;
	
	private Integer unUsed;

	//使用了该优惠卷的订单总金额
	private String sumTotalMoney;

	//使用了该优惠卷的订单总实际支付金额
	private String sumPayMoney;

	public String getSumTotalMoney() {
		return sumTotalMoney;
	}

	public void setSumTotalMoney(String sumTotalMoney) {
		this.sumTotalMoney = sumTotalMoney;
	}

	public String getSumPayMoney() {
		return sumPayMoney;
	}

	public void setSumPayMoney(String sumPayMoney) {
		this.sumPayMoney = sumPayMoney;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getUnUsed() {
		return unUsed;
	}

	public void setUnUsed(Integer unUsed) {
		this.unUsed = unUsed;
	}

	@Override
	public String toString() {
		return "XltcCouponPieModel [couponName=" + couponName + ", couponId=" + couponId + ", total=" + total
				+ ", used=" + used + ", unUsed=" + unUsed + "]";
	}

}
