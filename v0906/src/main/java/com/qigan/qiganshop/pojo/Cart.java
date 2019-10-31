package com.qigan.qiganshop.pojo;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * 购物车
 *
 * @author wanghao
 * @time 2019-05-04 17:06
 */
@ApiModel(value = "Cart", description = "购物车实体")
public class Cart extends XltcMobileBaseModel{

    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "坐标")
    private String coordinate;
    @ApiModelProperty(value = "商品集合 数据类型为:(\"itemId\":count)  itemId-->(String) count -->int ")
    private Map<String, Integer> items;
    @ApiModelProperty(value = "优惠券信息")
    private List<Coupon> coupons;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
