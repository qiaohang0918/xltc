package com.qigan.qiganshop.service;


import com.qigan.qiganshop.pojo.DeliveryOrder;
import com.qigan.qiganshop.pojo.Goods;

import java.util.List;
import java.util.Map;

/**
 * 配送员关联订单
 *
 * @author wanghao
 */
public interface DeliveryOrderService {
    /**
     * 领取订单
     *
     * @param deliveryerId
     * @param orderId
     * @return
     */
    String receive(Integer deliveryerId, String orderId, Integer num);

    /**
     * 正常签收商品
     *
     * @param deliveryId
     * @param orderId
     * @param signer
     * @return
     */
    boolean arrived(Integer deliveryId, String orderId, String signer);

    /**
     * 根据员工编号查询订单
     *
     * @param deliveryId    配送员 ID
     * @param deliverStatus 配送状态
     * @return
     */
    List<Goods> findByDeliveryId(Integer deliveryId, String deliverStatus);

    /**
     * 查询配送中订单
     *
     * @param deliveryerId
     * @return
     */
    List<Map> receiving(Integer deliveryerId);

    /**
     * 三合一
     *
     * @param deliveryOrder
     * @param page
     * @param size
     * @return
     */
    List<DeliveryOrder> findPage(DeliveryOrder deliveryOrder, Integer page, Integer size);

    /**
     * 问题订单
     *
     * @param deliveryerId
     * @param orderId
     * @param problem
     * @return
     */
    boolean question(Integer deliveryerId, String orderId, String problem);

    /**
     * 其他状态的问题订单
     * @param deliveryerId
     * @param orderId
     * @param flag
     * @return
     */
    boolean otherStatus(Integer deliveryerId, String orderId, String flag);
}