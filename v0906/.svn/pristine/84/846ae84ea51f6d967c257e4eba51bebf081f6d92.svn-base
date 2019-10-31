package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.TbPresellOrder;
import com.qigan.qiganshop.util.result.XltcResult;

import java.util.List;
import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/5 16:21
 * @Modified By:
 */
public interface XltcPreSellOrderService {

    int insert(TbPresellOrder presellOrder);

    TbPresellOrder findByOrderId(String orderId);

    XltcResult findPreSellOrder(Map<String, String> parms);

    void update(TbPresellOrder preSellOrder);

    List<TbPresellOrder> findCurrentSuitPushOrders();

    int updateCurrentOrderStatusIsPushed(String id, String orderId);

    XltcResult findPreSellOrderDetails(String orderId);

    XltcResult reportEveryLevelData();

    XltcResult reportEveryLevelsGoodsData(int page, int size);
}
