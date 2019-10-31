package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Deliveryer;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface DeliveryerMapper {

    /**
     * 删除
     *
     * @param deliveryerId
     * @return
     */
    int delete(Integer deliveryerId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(Deliveryer record);

    /**
     * 查询单个
     *
     * @param deliveryerId
     * @return
     */
    Deliveryer findOne(Integer deliveryerId);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int update(Deliveryer record);

    /**
     * 三合一
     *
     * @param deliveryer
     * @param page
     * @param size
     * @return
     */
    ArrayList<Deliveryer> findPage(@Param("record") Deliveryer deliveryer, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 获取最大的员工 Id
     *
     * @return
     */
    Integer getMaxId();

    /**
     * 查询总记录数
     *
     * @return
     */
    int getCount();

    /**
     * 根据手机号码查询是否存在
     * @return
     */
    Integer getByPhone(String phoneNum);
}