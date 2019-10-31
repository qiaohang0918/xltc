package com.qigan.qiganshop.pojo.game;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author starlord
 */
public class Setting {
    private RedeemType redeemType;
    private String name;
    private String describe;
    private int points;
    private Date createTime;
    @JSONField(format="yyyy-MM-dd hh:mm:ss")
    private Date modifiedTime;

    private Setting() {
    }

    public Setting(RedeemType redeemType, String name, String describe, int points) {
        this.redeemType = redeemType;
        this.name = name;
        this.describe = describe;
        this.points = points;
    }

    public RedeemType getRedeemType() {
        return redeemType;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public int getPoints() {
        return points;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }
}
