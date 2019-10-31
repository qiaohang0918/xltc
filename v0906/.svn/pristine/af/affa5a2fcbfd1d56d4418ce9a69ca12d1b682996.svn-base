package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 订单商品 oneToMore
 *
 * @author wanghao
 */
public interface OrderItemMapper {

    /**
     * 添加订单商品
     *
     * @param record
     * @return
     */
    int insert(OrderItem record);

    /**
     * 查商品
     *
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(String orderId);


    @Update("update tb_order_item  set isQuestionRefund = #{status} where orderItemId in ( ${orderItemIds} )")
    int updateOrderItemStatus(@Param("orderItemIds") String itemsIds, @Param("status")String status);

    @Select("select * from tb_order_item where orderItemId in ( ${itemsIdsConstructed} )")
    List<OrderItem> findSkuIds(@Param("itemsIdsConstructed") String itemsIds);
}