/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.util.List;

/**
 * @author wyy
 *
 */
public class XltcHomeTypeModel {
	
	private String id;
	
	private String title;
	
	private String subTitle;
	
	private int sort;
	
	private String enable;
	
	private String location;
	
	private String picUrl;
	
	private List<?> goods;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<?> getGoods() {
		return goods;
	}

	public void setGoods(List<?> goods) {
		this.goods = goods;
	}
	
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public String toString() {
		return "XltcHomeTypeModel [id=" + id + ", title=" + title + ", subTitle=" + subTitle + ", sort=" + sort
				+ ", enable=" + enable + ", location=" + location + ", picUrl=" + picUrl + ", goods=" + goods + "]";
	}
	

}
