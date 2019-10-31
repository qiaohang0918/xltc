/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wyy
 *
 */
public class XltcGoodsPojo extends CommonPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String goodsId;
	
	private String code;
	
	private String name;
	
	private BigDecimal salesPrice;
	
	private String categoryCode;
	
	private String categoryName;
	
	private String del;
	
	private String status;
	
	private BigDecimal vIPrice;
	
	private BigDecimal costPrice;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getvIPrice() {
		return vIPrice;
	}

	public void setvIPrice(BigDecimal vIPrice) {
		this.vIPrice = vIPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	@Override
	public String toString() {
		return "XltcGoodsPojo [goodsId=" + goodsId + ", code=" + code + ", name=" + name + ", salesPrice=" + salesPrice
				+ ", categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", del=" + del + ", status="
				+ status + ", vIPrice=" + vIPrice + ", costPrice=" + costPrice + "]";
	}
	
}
