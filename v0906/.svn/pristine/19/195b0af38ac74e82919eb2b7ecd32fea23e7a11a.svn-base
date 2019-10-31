package com.qigan.qiganshop.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qigan.qiganshop.pojo.UserAddress;

public interface UserAddressMapper {
    /**
     * 增加
     *
     * @param userAddress
     * @return
     */
    int insert(@Param("record") UserAddress userAddress);


    /**
     * 删除
     *
     * @param userAddress
     * @return
     */
    int delete(@Param("record") UserAddress userAddress);


    /**
     * 修改
     *
     * @param userAddress
     * @return
     */
    int update(@Param("record") UserAddress userAddress);

    /**
     * 查询所有或者查询单个
     * 当查询所有时,传递 null
     * 当查询单个时,传递 specification
     *
     * @param userId
     * @return
     */
    List<UserAddress> findByUserId(@Param("userId") String userId, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 查询单个规格详情
     *
     * @param userAddId
     * @return
     */
    UserAddress findOne(@Param("userAddId") String userAddId);

    /**
     * 将用户的收货地址全部设置为非默认
     *
     * @param userId
     * @return
     */
    Integer updateIsDefault(String userId);
    
    @Select({
    	"select userAddId, userId, name, phone, province, ",
    	"city, houseNum,coordinate, address, type, createTime,lastUseTime,isDefault ",
    	"from tb_userAddress ",
    	"where userId = #{userId} ", 
    	"order by isDefault desc, lastUseTime desc ", 
    })
    List<UserAddress> findById(String userId);
    
}