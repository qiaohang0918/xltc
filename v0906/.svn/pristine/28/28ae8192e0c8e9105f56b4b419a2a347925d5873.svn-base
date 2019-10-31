package com.qigan.qiganshop.service;


import com.qigan.qiganshop.pojo.Collect;
import com.qigan.qiganshop.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface CollectService {

    int insertCollect(String userId, String itemId);

    List<Collect> selectCollectList(String itemId);

    int delectCollect(String userId,String itemId);

    Collect selectCollectByUseridAndItemid(String userId, String itemId);

    Goods selectGoodsByItemid(String itemId);
}
