package com.qigan.qiganshop.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.qigan.qiganshop.mapper.ItemMapper;
import com.qigan.qiganshop.pojo.Collect;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.service.CollectService;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.ItemService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author wanghao
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CollectService collectService;

    /**
     * 增加sku
     *
     * @param item
     * @return
     */

    @Override
    public Integer add(Item item) {
        item.setItemId(item.getItemId().trim());
        if (item.getItemId() == null || "".equals(item.getItemId())) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            item.setItemId(uuid);
        }
        return itemMapper.insert(item);
    }

    /**
     * 批量删除sku
     *
     * @param ids
     * @return
     */

    @Override
    public Integer delete(String[] ids) {
        Integer count = 0;
        for (String id : ids) {
            count += itemMapper.delete(id);
        }
        return count;
    }

    /**
     * 修改
     *
     * @param itemList
     * @return
     */


    @Override
    public Integer update(List<Item> itemList) {
        Integer count = 0;
        for (Item item : itemList) {
            this.delete(new String[]{item.getItemId()});
            count += this.add(item);
        }
        return count;
    }

    /**
     * 修改
     *
     * @param item
     * @return
     */


    @Override
    public Integer update(Item item) {
        return itemMapper.update(item);
    }

    /**
     * 查询单个sku
     *
     * @param itemId
     * @return
     */


    @Override
    public Item findOne(String itemId) {
        return itemMapper.findOne(itemId);
    }

    /**
     * 条件查询,分页查询,查询所有,三合一
     *
     * @param item
     * @param pageNum
     * @param sizeNum
     * @return
     */


    @Override
    public PageResult findByItem(Item item, Integer pageNum, Integer sizeNum) {

        List<Item> list = null;
        if (pageNum != null && sizeNum != null) {

            list = itemMapper.findByItem(item, (pageNum - 1) * sizeNum, sizeNum);
        } else {
            list = itemMapper.findByItem(item, null, null);

        }
        return new PageResult(itemMapper.findByItem(item, null, null).size(),
                (List<Item>) NotNull.checkListNull(list));
    }

    /**
     * 查询所有
     *
     * @param item
     * @return
     */


    @Override
    public PageResult findByItem(Item item) {
        List<Item> itemList = itemMapper.findByItem(item, null, null);
        return new PageResult(itemList.size(), (List<Item>) NotNull.checkListNull(itemList));
    }

    /**
     * 根据条件进行删除sku
     *
     * @param spuId
     */

    @Override
    public Integer deleteByItem(String spuId) {


        return itemMapper.deleteBySPUId(spuId);
    }

    /**
     * 批量修改商品状态
     *
     * @param itemIds
     * @param status
     * @return
     */
    @Override
    public Integer updateStatus(String[] itemIds, String status) {
        Integer count = 0;
        for (String itemId : itemIds) {
            Item item = new Item();
            item.setItemId(itemId);
            item.setStatus(status);
            count += this.update(item);
        }
        return count;
    }

    /**
     * 批量修改商品启用状态
     *
     * @param itemIds
     * @param status
     * @return
     */
    @Override
    public Integer updateEnable(String[] itemIds, String status) {
        Integer count = 0;
        for (String itemId : itemIds) {
            Item item = new Item();
            item.setItemId(itemId);
            item.setDel(status);
            count += this.update(item);
        }
        return count;
    }


    /**
     * 通过 spu 商品代码和 sku 商品代码查询对应的 sku 信息
     *
     * @param goodsCode
     * @param itemCode
     * @return
     */
    @Override
    public List<Item> findByCode(String goodsCode, String itemCode) {
        return (List<Item>) NotNull.checkListNull(itemMapper.findByCode(goodsCode, itemCode));
    }

    /**
     * 根据上级 ID 查询 sku 集合,同时绑定库存
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<Item> findBySpuId(String goodsId) {
        return (List<Item>) NotNull.checkListNull(itemMapper.findBySpuId(goodsId));
    }
    /**
     * 根据上级 ID 查询 sku 集合,不关联库存
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<Item> findBySpuIdNotStock(String goodsId) {
        return (List<Item>) NotNull.checkListNull(itemMapper.findBySpuIdNotStock(goodsId));
    }

    @Override
    public Goods getGoodsByItemId(String skuId, String userId, String coordinate) {
        Item one = this.findOne(skuId);
        if (one == null) {
            return null;
        }
        Goods goods = goodsService.findOneByCoordinate(one, coordinate);
        if (goods == null) {
            return null;
        }
        Collect collect = collectService.selectCollectByUseridAndItemid(userId, one.getItemId());

        if (collect == null || userId == null) {
            // 未关联
            one.setCollection("no");
        } else {
            //关联
            one.setCollection("yes");
        }
        goods.getItems().add(one);


        return goods;
    }

    @Override
    public Integer updateSaleCount(String itemId, Integer count) {
        if (!StringUtils.isEmpty(itemId) && count > 0) {
            return itemMapper.updateSaleCount(itemId, count);
        }
        return 0;

    }


}
