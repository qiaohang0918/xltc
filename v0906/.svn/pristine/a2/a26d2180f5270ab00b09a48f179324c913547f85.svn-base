package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Cart;
import com.qigan.qiganshop.pojo.Label;
import com.qigan.qiganshop.pojo.Order;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;

import java.util.List;

/**
 * sku服务层
 *
 * @author wanghao
 */
public interface CartService {
    /**
     * 检查购物车
     * @param coordinate
     * @param itemIds
     * @return
     */
    JsonResult check(String coordinate, String itemIds);

    /**
     * 检查订单
     * @param cart
     * @return
     */
    JsonResult settlement(Cart cart);


    /**
     * 删除购物车记录(by userId and goodsItemId)
     * @param userId
     * @param
     * @return
     */
    int deleteCurrentOrderItemsFromCart(String userId,List<String> itemIds);


}
