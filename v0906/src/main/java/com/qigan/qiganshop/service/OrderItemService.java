package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.OrderItem;
import com.qigan.qiganshop.pojo.Pay;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghao
 */
public interface OrderItemService {

    int add(OrderItem orderItem);

    /**
     * 根据订单ID查询商品
     *
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(String orderId);

    int updateOrderItemStatus(String itemsIds, String s);

    @Select("select itemId from tb_order_item where orderItemId in ( ${itemsIds} )")
    List<OrderItem> findSkuIds(@Param("itemsIds") String itemsIds);
}
