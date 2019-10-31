/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wyy
 *
 */
public class XltcGoodsLabelModel {
	
	private String labelId;
	
	private String labelName;
	
	private Integer sort;
	
	private String labelImage;
	
	private List<XltcGoodsModel> goods = new ArrayList<>();
	
	public XltcGoodsLabelModel(){
		
	}
	
	public XltcGoodsLabelModel(String labelId, String labelName) {
		this.labelId = labelId;
		this.labelName = labelName;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public List<XltcGoodsModel> getGoods() {
		return goods;
	}

	public void setGoods(List<XltcGoodsModel> goods) {
		this.goods = goods;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getLabelImage() {
		return labelImage;
	}

	public void setLabelImage(String labelImage) {
		this.labelImage = labelImage;
	}

	@Override
	public String toString() {
		return "XltcGoodsLabelModel [labelId=" + labelId + ", labelName=" + labelName + ", sort=" + sort
				+ ", labelImage=" + labelImage + ", goods=" + goods + "]";
	}
	
}
