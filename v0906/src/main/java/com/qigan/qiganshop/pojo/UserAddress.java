package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户收货地址
 *
 * @author wanghao
 */
public class UserAddress implements Serializable {
    @ApiModelProperty(value = "用户地址 ID 注册时不传")
    private String userAddId;
    @ApiModelProperty(value = "用户 ID")
    private String userId;
    @ApiModelProperty(value = "收货人姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "坐标,格式:(经度,纬度)精确到小数点后 6 位")
    private String coordinate;
    @ApiModelProperty(value = "详细地址信息")
    private String address;
    @ApiModelProperty(value = "门牌号")
    private String houseNum;
    @ApiModelProperty(value = "地址类型 0 公司, 1 住宅, 2 学校,3 其他")
    private String type;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "上次使用时间")
    private String lastUseTime;
    @ApiModelProperty(value = "是否默认")
    private String isDefault;

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(String lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public String getUserAddId() {
        return userAddId;
    }

    public void setUserAddId(String userAddId) {
        this.userAddId = userAddId == null ? null : userAddId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}