package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Balance;
import com.qigan.qiganshop.pojo.Banner;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.List;

/**
 * 余额明细服务层
 */
public interface BalanceService {

    /**
     * 增加明细
     *
     * @param balance
     * @return
     */
    int insert(Balance balance,String token);

    /**
     * 查询单个订单
     *
     * @param balanceId
     * @return
     */
    Balance findOne(String balanceId);

    /**
     * 查询消费记录
     *
     * @param beginTime
     * @param endTime
     * @param token
     * @return
     */
    PageResult findPage(String beginTime, String endTime, String token,Integer page,Integer rows);
}
