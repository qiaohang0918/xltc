package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 首页分类中间表
 *
 * @author wanghao
 */
public class HomepageCateGoods implements Serializable {
    @ApiModelProperty(value = "主键")
    private String pageId;
    @ApiModelProperty(value = "首页分类 ID")
    private String homepageCateId;
    @ApiModelProperty(value = "商品 ID")
    private String goodsId;
    @ApiModelProperty(value = "排序")
    private Integer sort;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    public String getHomepageCateId() {
        return homepageCateId;
    }

    public void setHomepageCateId(String homepageCateId) {
        this.homepageCateId = homepageCateId == null ? null : homepageCateId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	@Override
	public String toString() {
		return "HomepageCateGoods [pageId=" + pageId + ", homepageCateId=" + homepageCateId + ", goodsId=" + goodsId
				+ ", sort=" + sort + "]";
	}
    
    
}