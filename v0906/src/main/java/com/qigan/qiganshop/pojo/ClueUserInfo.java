package com.qigan.qiganshop.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/26 17:34
 * @Modified By:
 */
public class ClueUserInfo implements Serializable {

    @Excel(name = "扩展用户的数量")
    private String explandRegisters;
    @Excel(name = "手机")
    private String phone;
    @Excel(name = "用户名")
    private String nickName;
    @Excel(name = "账户金额")
    private String balance;

    public ClueUserInfo(String explandRegisters, String phone, String nickName, String balance) {
        this.explandRegisters = explandRegisters;
        this.phone = phone;
        this.nickName = nickName;
        this.balance = balance;
    }

    public ClueUserInfo() {
    }

    public String getExplandRegisters() {
        return explandRegisters;
    }

    public void setExplandRegisters(String explandRegisters) {
        this.explandRegisters = explandRegisters;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
