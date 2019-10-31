package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 首页分类
 *
 * @author wanghao
 */
@ApiModel(value = "首页分类")
public class HomepageCate {
    @ApiModelProperty(value = "首页分类ID")
    private String homepagecateId;
    @ApiModelProperty(value = "主标题")
    private String title;
    @ApiModelProperty(value = "副标题")
    private String subtitle;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "是否启用")
    private String enable;
    @ApiModelProperty(value = "位置")
    private String location;
    @ApiModelProperty(value = "图片地址")
    private String picUrl;
    @ApiModelProperty(value = "商品列表")
    private List<Goods> goodsList;

    public String getHomepagecateId() {
        return homepagecateId;
    }

    public void setHomepagecateId(String homepagecateId) {
        this.homepagecateId = homepagecateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}