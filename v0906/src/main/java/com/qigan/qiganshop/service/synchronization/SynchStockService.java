package com.qigan.qiganshop.service.synchronization;

/**
 * @author wanghao
 */
public interface SynchStockService {
    /**
     * 同步获取所有商品库存
     *
     * @return
     */
    String updateAllStock();

    String updateAllStock(String start_date,String end_date,String warehouseId);
}
