package com.qigan.qiganshop.pojo.game;

public enum RedeemType {
    FEED("饲料"),WATER("水"),CLEAN("清洁"),VACCIN("疫苗"),LAY_EGGS("下蛋");
    String name;

    RedeemType(String name) {
        this.name = name;
    }
}
