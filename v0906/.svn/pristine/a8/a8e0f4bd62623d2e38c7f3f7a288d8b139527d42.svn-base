package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.UserCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCartMapper {

    int delete(String cartId);

    int deleteOne(@Param("cartId") String cartId, @Param("skuId") String skuId);

    int insert(UserCart record);

    int update(UserCart record);

    UserCart findOne(@Param("cartId") String cartId, @Param("skuId") String skuId);

    List<UserCart> findByCartId(String cartId);
    
    int deleteCurrentOrderItemsFromCart(@Param("cartId") String cartId, @Param("itemIds")List<String> itemId);
}