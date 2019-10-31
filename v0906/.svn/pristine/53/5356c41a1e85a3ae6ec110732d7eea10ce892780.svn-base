package com.qigan.qiganshop.service;

import java.util.List;
import java.util.Map;

import com.qigan.qiganshop.pojo.ClueUserInfo;
import com.qigan.qiganshop.pojo.Order;
import com.qigan.qiganshop.pojo.OrderItem;
import com.qigan.qiganshop.pojo.group.GroupOrder;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;

/**
 * create by qh
 */
public interface XltcOrderService {
    /**
     * 提交订单
     *
     * @param appOrder
     * @return
     */
    Map<String,Object> submit(GroupOrder appOrder);

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
    Map<String, Object> payInfo(String out_trade_no, String payType, String orderId);

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
     * 推送到管易
     * @param OrderId
     * @return
     */
    String pullToGuanYi(String OrderId);

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
    Integer updateComment(String userPhone, String orderId, Integer comment, Integer orderLevel, String context);


    /**
     * 下单第三环节
     * @param map
     * @return
     */
    public JsonResult  tailSubmit(Map<String,Object> map);
    
    public JsonResult optGuanYiOrder(String orderId, Order one);

    boolean lockOrderWaitingPay(String orderId);

    JsonResult findSuccessPay(Map<String, String> conditionalMap);

    List<Order> findByOrderForExport(Order order, String beginTime, String endTime, Integer page, Integer rows, String rule);

    List<ClueUserInfo> selectCLueUserInfo();
    
    PageResult findOrderList(Order order);

    JsonResult optPreSellOrder(String orderId, Order one);
}
