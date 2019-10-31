package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Balance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BalanceMapper {
    /**
     * 增加
     *
     * @param balance
     * @return
     */
    int insert(Balance balance);


    /**
     * 查询卡消费列表
     *
     * @param userId
     * @return
     */
    List<Balance> findPage(
            @Param("beginTime") String begin,
            @Param("endTime") String endTime,
            @Param("userId") String userId,
            @Param("page") Integer page,
            @Param("size") Integer size);

    /**
     * 查询单个消费信息
     *
     * @param balanceId
     * @return
     */
    Balance findOne(String balanceId);

    /**
     * 根据用户 ID 查询最后一条交易记录
     *
     * @param userId
     * @return
     */
    Balance findByUserId(String userId);
}