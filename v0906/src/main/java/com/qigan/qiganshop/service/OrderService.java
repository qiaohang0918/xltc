package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Order;
import com.qigan.qiganshop.pojo.OrderItem;
import com.qigan.qiganshop.pojo.group.GroupOrder;
import com.qigan.qiganshop.util.result.format.JsonResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单服务层
 *
 * @author wanghao
 * @time 2019-05-04 22:14
 */
public interface OrderService {
    /**
     * 提交订单
     *
     * @param appOrder
     * @return
     */
    JsonResult submit(GroupOrder appOrder);

    /**
     * 增加
     *
     * @param order
     * @return
     */
    int add(Order order);

    /**
     * 修改
     *
     * @param order
     * @return
     */
    int update(Order order);

    /**
     * 查询单个订单
     *
     * @param orderId
     * @return
     */
    Order findOne(String orderId);

    /**
     * 三合一接口
     *
     * @param order
     * @param page
     * @param size
     * @return
     */
    List<Order> findPage(Order order, Integer page, Integer size, String rule);

    /**
     * 查询最大的排序值
     *
     * @return
     */
    String findMax();

    /**
     * 查询支付信息,并修改订单状态
     *
     * @param orderId
     * @return
     */
    JsonResult payInfo(String out_trade_no, String payType, String orderId);

    /**
     * 查询 15 分钟之前的订单
     *
     * @param time
     * @return
     */
    List<Order> findExpired(Integer time);

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param s
     * @return
     */
    int updateStatus(String orderId, String s);

    /**
     * 用户根据状态查询订单信息
     *
     * @param userId
     * @param status
     * @param page
     * @param rows
     * @return
     */
    JsonResult findByStatus(String userId, String status, Integer page, Integer rows);

    /**
     * 分页查询订单信息
     *
     * @param order
     * @param beginTime
     * @param endTime
     * @param page
     * @param rows
     * @return
     */
    JsonResult findByOrder(Order order, String beginTime, String endTime, Integer page, Integer rows, String rule);

    /**
     * 查询完整订单信息
     *
     * @param orderId
     * @return
     */
    Map findOneFull(String orderId);

    /**
     * 查询订单的所有信息
     *
     * @param orderId
     * @return
     */
    List<OrderItem> findorderItems(String orderId);

    /**
     * 统计数据
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    Map Statistics(String beginTime, String endTime);

    /**
     * app 统计数据
     *
     * @param userId
     * @return
     */
    Map getCount(String userId);

    /**
     * 更新评论
     *
     * @param orderId
     * @param comment
     * @return
     */
    Integer updateComment(String orderId, Integer comment);

    int updatePayedByOrderId(String ss);
}
