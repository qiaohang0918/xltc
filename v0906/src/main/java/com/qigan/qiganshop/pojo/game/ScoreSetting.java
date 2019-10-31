package com.qigan.qiganshop.pojo.game;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author starlord
 */
public class ScoreSetting {

    private SourceType sourceType;
    private double money;
    private int score;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date modifiedTime;

    private ScoreSetting(){

    }
    public ScoreSetting(SourceType sourceType, double money, int score) {
        this.sourceType = sourceType;
        this.money = money;
        this.score = score;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public double getMoney() {
        return money;
    }

    public int getScore() {
        return score;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }
}
