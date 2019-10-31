package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Goods;

import java.util.List;

public interface SearchService {
    List<String> getHotBotRank();

    List<String> getHotBotOrderNumber();

    Goods findOneSearch(String id);
}
