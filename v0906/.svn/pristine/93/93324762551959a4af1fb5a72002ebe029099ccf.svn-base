package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Collect;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CollectMapper {

    int insertCollect(@Param("userId") String userId, @Param("itemId") String itemId);

    List<Collect> selectCollectList(String userId);

    int delectCollect(@Param("userId") String userId, @Param("itemId") String itemId);

    Collect selectCollectByUseridAndItemid(@Param("userId") String userId, @Param("itemId") String itemId);

    Item selectItemByItemid(String itemId);

    Goods selectGoodsByGoodsid(String spuId);
}
