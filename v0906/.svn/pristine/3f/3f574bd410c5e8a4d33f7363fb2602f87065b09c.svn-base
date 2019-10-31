package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.UserCart;
import com.qigan.qiganshop.util.result.format.JsonResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户购物车服务层
 *
 * @author wanghao
 */
public interface UserCartService {
    /**
     * 增加
     *
     * @param userCart
     * @return
     */
    int add(UserCart userCart);

    /**
     * 删除用户自己的购物车
     *
     * @param cartId
     * @return
     */
    int delete(String cartId);

    /**
     * 删除单个
     *
     * @param cartId
     * @param skuId
     * @return
     */
    int delete(String cartId, String skuId);

    /**
     * 更新购物车
     *
     * @param userCart
     * @return
     */
    int update(UserCart userCart);

    /**
     * 查询购物车中单个商品信息
     *
     * @param cartId
     * @param skuId
     * @return
     */
    UserCart findOne(String cartId, String skuId);

    /**
     * 查询某个用户的购物车完整信息
     *
     * @param cartId
     * @return
     */
    List<UserCart> findByCateId(String cartId);

    /**
     * APP 端增加一个或多个商品到购物车
     *
     * @param appUser
     * @param items
     * @return
     */
    Integer addOneGoods(AppUser appUser, String cd, Map<String, Integer> items);

    /**
     * 保存订单信息
     * @param appUser
     * @param items
     * @return
     */
    boolean saveCart(AppUser appUser, Map<String, Integer> items);

    /**
     * 更新购物车数据
     * @param userId
     * @param items
     * @return
     */
    boolean updateCart(String userId, Map<String, Integer> items);


    /**
     * 查询当前仓库库存
     * @param userId
     * @param wareHouseId
     * @param items
     * @return
     */
    boolean updateCart(String userId,String wareHouseId, Map<String, Integer> items);

    /**
     * 删除购物车中的商品
     * @param userId
     * @param skuIds
     * @return
     */
    boolean deleteCart(String userId, String[] skuIds);

    /**
     * 获取用户的购物车信息
     * @param userId
     * @return
     */
    Map getCart(String userId,String coordinate);
}
