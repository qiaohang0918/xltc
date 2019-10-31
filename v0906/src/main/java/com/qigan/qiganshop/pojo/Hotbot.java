package com.qigan.qiganshop.pojo;

/**
 * 热门搜索实体类
 */
public class Hotbot {

    private int HotId;
    private String word;
    private int number;
    private String rank;
    private String createTime;

    public int getHotId() {
        return HotId;
    }

    public void setHotId(int hotId) {
        HotId = hotId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
