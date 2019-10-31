package com.qigan.qiganshop.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

/**
 * @author starlord
 */
public class Manager {

    private Integer managerId;
    private String no;
    private String name;
    private String phone;
    private boolean enabled = false;
    private String password;
    private String authRefund;

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", authRefund='" + authRefund + '\'' +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthRefund() {
        return authRefund;
    }

    public void setAuthRefund(String authRefund) {
        this.authRefund = authRefund;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Manager(Integer managerId, String no, String name, String phone, boolean enabled, String password, String authRefund, Date createTime, Date modifiedTime) {
        this.managerId = managerId;
        this.no = no;
        this.name = name;
        this.phone = phone;
        this.enabled = enabled;
        this.password = password;
        this.authRefund = authRefund;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date modifiedTime;

    private Manager() {
    }

    public Manager(Integer managerId, String no, String name, String phone) {
        this.managerId = managerId;
        this.no = no;
        this.name = name;
        this.phone = phone;
        this.password = DigestUtils.md5Hex("123456");
    }

    public Manager(Integer managerId, boolean enabled) {
        this.managerId = managerId;
        this.enabled = enabled;
    }

    public Manager(Integer managerId, String password) {
        this.managerId = managerId;
        this.password = password;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }
}
