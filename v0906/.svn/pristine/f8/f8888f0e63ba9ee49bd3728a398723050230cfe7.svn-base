package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.DeliveryOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 配送员订单关联表
 *
 * @author wanghao
 */
public interface DeliveryOrderMapper {


    int insert(DeliveryOrder record);


    DeliveryOrder findOne(String deliverOrderId);


    int update(DeliveryOrder record);

    List<DeliveryOrder> findPage(@Param("record") DeliveryOrder record, @Param("page") Integer page, @Param("size") Integer size);

}