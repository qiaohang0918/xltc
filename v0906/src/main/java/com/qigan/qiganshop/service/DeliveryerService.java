package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Coupon;
import com.qigan.qiganshop.pojo.Deliveryer;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.ArrayList;

/**
 * 配送员服务层
 *
 * @author wanghao
 * @time 2019-05-05 14:43
 */
public interface DeliveryerService {
    /**
     * 增加快递员
     *
     * @param deliveryer
     * @return
     */
    Integer add(Deliveryer deliveryer);

    /**
     * 修改配送员信息
     *
     * @param deliveryer
     * @return
     */
    Integer update(Deliveryer deliveryer);

    /**
     * 查询单个配送员
     *
     * @param deliveryerId
     * @return
     */
    Deliveryer findOne(Integer deliveryerId);

    /**
     * 查询全部,分页查询 条件查询
     *
     * @param deliveryer
     * @param page
     * @param size
     * @return
     */
    PageResult findPage(Deliveryer deliveryer, Integer page, Integer size);

    /**
     * 修改配送员状态
     *
     * @param deliveryerId
     * @param flag
     * @return
     */
    Integer updateEnable(Integer deliveryerId, boolean flag);

    /**
     * 简单登录接口
     *
     * @param deliveryerId
     * @param passwd
     * @return
     */
    Deliveryer login(Integer deliveryerId, String passwd);

}
