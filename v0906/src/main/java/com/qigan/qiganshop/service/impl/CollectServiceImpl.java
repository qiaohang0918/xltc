package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.CollectMapper;
import com.qigan.qiganshop.pojo.Collect;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper mapper;

    @Override
    public int insertCollect(String userId, String itemId) {
        return mapper.insertCollect(userId,itemId);
    }

    @Override
    public List<Collect> selectCollectList(String userId) {
        return mapper.selectCollectList(userId);
    }

    @Override
    public int delectCollect(String userId,String itemId) {
        return mapper.delectCollect(userId,itemId);
    }

    @Override
    public Collect selectCollectByUseridAndItemid(String userId, String itemId) {
        return mapper.selectCollectByUseridAndItemid(userId,itemId);
    }

    @Override
    public Goods selectGoodsByItemid(String itemId) {
        Item item = mapper.selectItemByItemid(itemId);
        Goods goods = mapper.selectGoodsByGoodsid(item.getSpuId());
        List list = new ArrayList();
        list.add(item);
        goods.setItems(list);
        return goods;
    }
}
