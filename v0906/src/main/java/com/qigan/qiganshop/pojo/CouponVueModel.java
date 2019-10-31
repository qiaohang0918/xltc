/**
 * 
 */
package com.qigan.qiganshop.pojo;

/**
 * @author wyy
 *
 */
public class CouponVueModel {
	
	private String key;//code
	
	private String label;//name
	
	private String goodsId;
	
	private String categoryName;
	
	private String categoryCode;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "CouponVueModel [key=" + key + ", label=" + label + ", goodsId=" + goodsId + ", categoryName="
				+ categoryName + ", categoryCode=" + categoryCode + "]";
	}

}
