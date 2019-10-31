package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品 标签 关联表
 *
 * @author wanghao
 */
@ApiModel(value = "商品 标签 关联实体")
public class LabelGoods {
    @ApiModelProperty(value = "主键")
    private String labelGoodsId;
    @ApiModelProperty(value = "标签 ID")
    private String labelId;
    @ApiModelProperty(value = "商品 Id")
    private String goodsId;

    public String getLabelGoodsId() {
        return labelGoodsId;
    }

    public void setLabelGoodsId(String labelGoodsId) {
        this.labelGoodsId = labelGoodsId;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}