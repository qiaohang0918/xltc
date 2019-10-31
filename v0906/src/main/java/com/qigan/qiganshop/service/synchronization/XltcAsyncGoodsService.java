package com.qigan.qiganshop.service.synchronization;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/8/27 10:01
 * @Modified By:
 */
public interface XltcAsyncGoodsService  {

    void syncGoodsAll(String start_date,String end_date);
}
