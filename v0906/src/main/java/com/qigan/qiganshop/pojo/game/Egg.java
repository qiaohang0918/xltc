package com.qigan.qiganshop.pojo.game;

import java.util.Date;

public class Egg {
    private String userId;
    private int stock;
    private Date modifiedTime;

    private Egg() {
    }

    public Egg(String userId, int stock) {
        this.userId = userId;
        this.stock = stock;
    }

    public String getUserId() {
        return userId;
    }

    public int getStock() {
        return stock;
    }
}
