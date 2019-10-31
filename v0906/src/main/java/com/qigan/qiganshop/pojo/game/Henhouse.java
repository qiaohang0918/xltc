package com.qigan.qiganshop.pojo.game;

import java.util.Date;

public class Henhouse {
    private String userId;
    private int eggs;
    private Date modifiedTime;

    private Henhouse() {
    }

    public Henhouse(String userId, int num) {
        this.userId = userId;
        this.eggs = this.eggs + num;
    }

    public String getUserId() {
        return userId;
    }

    public int getEggs() {
        return eggs;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }
}
