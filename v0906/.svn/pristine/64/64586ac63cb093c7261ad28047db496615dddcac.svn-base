package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.SearchMapper;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper mapper;
    @Autowired
    private GoodsService goodsService;

    @Override
    public List<String> getHotBotRank() {
        return mapper.getHotBotRank();
    }

    @Override
    public List<String> getHotBotOrderNumber() {
        return mapper.getHotBotOrderNumber();
    }

    @Override
    public Goods findOneSearch(String id) {
        //return mapper.findOneSearch(id);
        return goodsService.findOne(id);
    }


}