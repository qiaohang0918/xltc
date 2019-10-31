package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 库存实体类
 *
 * @author wanghao
 */
public class Stock implements Serializable {
    @ApiModelProperty(value = "库存记录 ID")
    private String stockId;
    @ApiModelProperty(value = "仓库 ID")
    private String warehouseId;
    @ApiModelProperty(value = "sku商品 ID")
    private String itemId;
    @ApiModelProperty(value = "SPU商品 ID")
    private String goodsId;
    @ApiModelProperty(value = "库存数量")
    private Integer stockNum;
    @ApiModelProperty(value = "锁定数量")
    private Integer lockNum;
    @ApiModelProperty(value = "可销售数量")
    private Integer salableNum;
    @ApiModelProperty(value = "是否停用")
    private Boolean isDel;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getLockNum() {
        return lockNum;
    }

    public void setLockNum(Integer lockNum) {
        this.lockNum = lockNum;
    }

    public Integer getSalableNum() {
        return salableNum;
    }

    public void setSalableNum(Integer salableNum) {
        this.salableNum = salableNum;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean del) {
        isDel = del;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}