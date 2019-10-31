package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class TbActivityUnionGoods implements Serializable {
    private String unionGoodsId;

    private String unionId;

    private String goodsId;

    private String goodsCode;

    private String sort;

    private static final long serialVersionUID = 1L;

    public String getUnionGoodsId() {
        return unionGoodsId;
    }

    public void setUnionGoodsId(String unionGoodsId) {
        this.unionGoodsId = unionGoodsId == null ? null : unionGoodsId.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", unionGoodsId=").append(unionGoodsId);
        sb.append(", unionId=").append(unionId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}