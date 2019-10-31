/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wyy
 *
 */
public class  XltcGoodsModel {
	
	private String goodsId;
	
	private String categoryCode;
	
	private String goodsName;
	
	private BigDecimal salesPrice;
	
	private BigDecimal costPrice;
	
	private BigDecimal VIPrice;
	
	private BigDecimal saleableNum;
	
	private String imageUrl;
	
	private List<String> imageUrls;
	
	private String labelId;
	
	private String simpleName;
	
	private String categoryName;
	
	private int sort;
	
	private String enable;
	
	private String goodsCode;

	//是限购商品(严格受限 / 宽泛受限) , default = 0
	private String isLimitedGoods = "0";

	//预售状态  0未开始  1进行中  2预售结束
	private String isSelling;
	// "" 非预售    not null 预售
	private String preSell;
	//预售开始时间
	private String beforeTimeStamp;
	//预售截止时间
	private String afterTimeStamp;
	//当前时间
	private String currentTimeStamp;
	//该商品本期售卖数量
	private String sellNum;
	//预计配送时间
	private String preSendTime;

	public String getIsLimitedGoods() {
		return isLimitedGoods;
	}

	public void setIsLimitedGoods(String isLimitedGoods) {
		this.isLimitedGoods = isLimitedGoods;
	}

	public String getBeforeTimeStamp() {
		return beforeTimeStamp;
	}

	public void setBeforeTimeStamp(String beforeTimeStamp) {
		this.beforeTimeStamp = beforeTimeStamp;
	}

	public String getAfterTimeStamp() {
		return afterTimeStamp;
	}

	public void setAfterTimeStamp(String afterTimeStamp) {
		this.afterTimeStamp = afterTimeStamp;
	}

	public String getCurrentTimeStamp() {
		return currentTimeStamp;
	}

	public void setCurrentTimeStamp(String currentTimeStamp) {
		this.currentTimeStamp = currentTimeStamp;
	}

	public String getSellNum() {
		return sellNum;
	}

	public void setSellNum(String sellNum) {
		this.sellNum = sellNum;
	}

	public String getPreSendTime() {
		return preSendTime;
	}

	public void setPreSendTime(String preSendTime) {
		this.preSendTime = preSendTime;
	}

	public String getIsSelling() {
		return isSelling;
	}

	public void setIsSelling(String isSelling) {
		this.isSelling = isSelling;
	}

	public String getPreSell() {
		return preSell;
	}

	public void setPreSell(String preSell) {
		this.preSell = preSell;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getVIPrice() {
		return VIPrice;
	}

	public void setVIPrice(BigDecimal vIPrice) {
		VIPrice = vIPrice;
	}

	public BigDecimal getSaleableNum() {
		return saleableNum;
	}

	public void setSaleableNum(BigDecimal saleableNum) {
		this.saleableNum = saleableNum;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	
	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "XltcGoodsModel [goodsId=" + goodsId + ", categoryCode=" + categoryCode + ", goodsName=" + goodsName
				+ ", salesPrice=" + salesPrice + ", costPrice=" + costPrice + ", VIPrice=" + VIPrice + ", saleableNum="
				+ saleableNum + ", imageUrl=" + imageUrl + ", imageUrls=" + imageUrls + ", labelId=" + labelId
				+ ", simpleName=" + simpleName + ", categoryName=" + categoryName + ", sort=" + sort + ", enable="
				+ enable + ", goodsCode=" + goodsCode + ", isLimitedGoods=" + isLimitedGoods + ", isSelling="
				+ isSelling + ", preSell=" + preSell + ", beforeTimeStamp=" + beforeTimeStamp + ", afterTimeStamp="
				+ afterTimeStamp + ", currentTimeStamp=" + currentTimeStamp + ", sellNum=" + sellNum + ", preSendTime="
				+ preSendTime + "]";
	}
	
}
