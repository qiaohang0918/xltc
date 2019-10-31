package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.OrderItemMapper;
import com.qigan.qiganshop.pojo.OrderItem;
import com.qigan.qiganshop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-12 20:22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public int add(OrderItem orderItem) {
        if(orderItem.getOrderItemId()!= null && orderItem.getOrderId()!= null){
           return orderItemMapper.insert(orderItem);
        }
        return 0;
    }

    /**
     * 根据订单ID查询商品
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> findByOrderId(String orderId) {
        return orderItemMapper.findByOrderId(orderId);
    }

    @Override
    public int updateOrderItemStatus(String itemsIds, String status) {
        return orderItemMapper.updateOrderItemStatus(itemsIds,status);
    }

    @Override
    public List<OrderItem> findSkuIds(String itemsIds) {
        return orderItemMapper.findSkuIds(itemsIds);
    }
}
