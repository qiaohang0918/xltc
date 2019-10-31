package com.qigan.qiganshop.pojo.synchronization;

import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

/**
 * 管易云会员
 *
 * @author wanghao
 * @time 2019-04-28 14:11
 */
public class GuanYiUser {
    @ApiModelProperty(value = "代码 是")
    private String code;
    @ApiModelProperty(value = "名称 是")
    private String name;
    @ApiModelProperty(value = "所属店铺 是")
    private String shop_code;
    @ApiModelProperty(value = "收货信息列表")
    private List<Address> receive_infos;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public List<Address> getReceive_infos() {
        return receive_infos;
    }

    public void setReceive_infos(List<Address> receive_infos) {
        this.receive_infos = receive_infos;
    }

    public static class Address {
        @ApiModelProperty(value = "名称")
        private String name;
        @ApiModelProperty(value = "收货人")
        private String receiver;
        @ApiModelProperty(value = "收货电话")
        private String telephone;
        @ApiModelProperty(value = "收货手机")
        private String mobile;
        @ApiModelProperty(value = "收货地区")
        private String area;
        @ApiModelProperty(value = "收货详细地址")
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "name='" + name + '\'' +
                    ", receiver='" + receiver + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", area='" + area + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GuanYiUser{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", shop_code='" + shop_code + '\'' +
                ", receive_infos=" + receive_infos +
                '}';
    }
}
