package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 商品二级分类
 */
public class Label {
    /**
     * 标签 ID
     */
    @ApiModelProperty(value = "标签 ID")


    protected String labelId;
    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")


    protected String labelName;
    /**
     * 上级分类 ID
     */
    @ApiModelProperty(value = "上级分类 ID")


    protected String cateId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")


    protected Integer sort;

    @ApiModelProperty(value = "图片")


    protected String labelImage;


    public Label(String labelId, String labelName, String cateId, Integer sort) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.cateId = cateId;
        this.sort = sort;
    }

    public Label() {
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

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
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
}