package com.qigan.qiganshop.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 配送员实体
 *
 * @author wanghao
 */
@ApiModel(value = "Deliveryer", description = "配送员实体")
public class Deliveryer {

    @ApiModelProperty(value = "工号(系统自动生成)")
       
       
    private Integer deliveryerId;
    @ApiModelProperty(value = "员工姓名")
       
       
    private String name;
    @ApiModelProperty(value = "密码")
       
       
    @JsonIgnore
    private String passwd;
    @ApiModelProperty(value = "手机号")
       
       
    private String phoneNum;
    @ApiModelProperty(value = "入职日期 (系统自动生成)")
       
       
    private String createTime;
    @ApiModelProperty(value = "是否启用 0 禁用 1 启用")
       
       
    private String enable;

    public Integer getDeliveryerId() {
        return deliveryerId;
    }

    public void setDeliveryerId(Integer deliveryerId) {
        this.deliveryerId = deliveryerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}