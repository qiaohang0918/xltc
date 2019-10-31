package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.List;

/**
 * sku服务层
 *
 * @author wanghao
 */


public interface ItemService {
    /**
     * 增加sku
     *
     * @param item
     * @return
     */


    Integer add(Item item);

    /**
     * 批量删除sku
     *
     * @param ids
     * @return
     */

    Integer delete(String[] ids);

    /**
     * 修改
     *
     * @param itemList
     * @return
     */

    Integer update(List<Item> itemList);

    /**
     * 修改
     *
     * @param item
     * @return
     */

    Integer update(Item item);

    /**
     * 查询单个sku
     *
     * @param itemId
     * @return
     */


    Item findOne(String itemId);

    /**
     * 条件查询,分页查询,查询所有,三合一
     *
     * @param item
     * @param page
     * @param size
     * @return
     */


    PageResult findByItem(Item item, Integer page, Integer size);

    /**
     * 查询所有
     *
     * @param item
     * @return
     */


    PageResult findByItem(Item item);

    /**
     * 根据条件进行删除sku
     *
     * @param spuId
     */

    Integer deleteByItem(String spuId);

    /**
     * 批量修改商品状态
     *
     * @param itemIds
     * @param status
     * @return
     */
    Integer updateStatus(String[] itemIds, String status);
    /**
     * 批量修改商品启用状态
     *
     * @param itemIds
     * @param status
     * @return
     */
    Integer updateEnable(String[] itemIds, String status);

    /**
     * 通过 spu 商品代码和 sku 商品代码查询对应的 sku 信息
     *
     * @param goodsCode
     * @param itemCode
     * @return
     */
    List<Item> findByCode(String goodsCode, String itemCode);

    /**
     * 根据上级 ID 查询 sku 集合,同时绑定库存
     *
     * @param goodsId
     * @return
     */
    List<Item> findBySpuId(String goodsId);
    /**
     * 根据上级 ID 查询 sku 集合,不关联库存
     *
     * @param goodsId
     * @return
     */
    List<Item> findBySpuIdNotStock(String goodsId);

    Goods getGoodsByItemId(String skuId,String userId,String coordinate);

    Integer updateSaleCount(String itemId,Integer count);
}
