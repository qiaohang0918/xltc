package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.pojo.XltcGoodsModel;
import com.qigan.qiganshop.pojo.XltcGoodsPojo;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.XltcResult;
import com.qigan.qiganshop.util.result.format.JsonResult;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")

/**
 * 规格服务层
 */
public interface GoodsService {
    /**
     * 返回全部列表
     *
     * @return
     */
    public List<Goods> findAll();

    /**
     * 增加
     */
    public Integer add(Goods goods);

    /**
     * 修改
     */
    public Integer update(Goods goods);

    /**
     * 根据ID获取实体
     *
     * @param userId
     * @param goodsId
     * @return
     */
    Goods findOne(String token, String goodsId);

    Goods findOne(String goodsId);
    
    Goods findOnes(String goodsId, String continate);

    /**
     * 根据ID获取实体
     *
     * @param goodsId
     * @return
     */
    public Goods findOneGoods(String goodsId);


    public void searchGoodsPreSellInfo(Goods goods);

    public void searchGoodsOtherInfo(Goods goods);

    /**
     * 批量删除
     *
     * @param ids
     */
    public Integer delete(String[] ids);

    /**
     * 分页查询,需要传递坐标,前端使用
     *
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @return
     */
    public JsonResult findPage(Goods goods, String coordinate, Integer pageNum, Integer pageSize);

    /**
     * 批量修改商品状态
     *
     * @param goodsIds
     * @param status
     * @return
     */
    Integer updateStatus(String[] goodsIds, String status);

    /**
     * 批量修改商品启用状态
     *
     * @param goodsIds
     * @param status
     * @return
     */
    Integer updateEnable(String[] goodsIds, String status);

    /**
     * 随机查询商品
     *
     * @return
     */
    List<Goods> findByRand(String coordinate);

    /**
     * 普通分页查询,不涉及库存,配送范围 后端专用
     *
     * @param goods
     * @return
     */
    PageResult findPageGoods(Goods goods, Integer page, Integer size);

    /**
     * 根据标签查询商品
     *
     * @param categoryId
     * @param labelId
     * @return
     */
    PageResult findUnionGoods(String categoryId, String labelId, CommonPage page);

    /**
     * 前端 查询商品
     *
     * @param categoryId
     * @param coordinate
     * @param labelId
     * @return
     */
    PageResult findUnionGoods(String categoryId, String coordinate, String labelId, Integer page, Integer size);

    /**
     * 根据分类类型以及分类 ID 查询商品
     *
     * @param cateId
     * @param location
     * @return
     */
    List<Goods> findByLocation(String cateId, String location);


    JsonResult getWarehouseId(String coordinate);

    /**
     * 根据 spuId 和坐标查询商品
     *
     * @param item
     * @param coordinate
     * @return
     */
    Goods findOneByCoordinate(Item item, String coordinate);
    
    List<XltcGoodsModel> goodsByIds();
    
    void deleteGoods(String token, String... goodsId);


    /**
     * 搜索商品
     * @param word
     * @param coordinate
     * @return
     */
    List<XltcGoodsModel> searchGoodsByWordsAndWareHouse(String keyWord, String wareHouseId);
    
    
    List<?> findGoodsByPlatform(String goodsId);

    String findCodeByGoodsId(String goodsId);

    List<Goods> findGoodsByCodes(String searchCodes);
    
    PageResult findAllGoods(XltcGoodsPojo goods);

    String findGoodsIdByCode(String code);

    String findNameByCode(String code);

    XltcResult findGoodsByConditions(Map<String,String> parms);

    List<String> findGoodsIdsByCodes(String codesSqlFormat);

    String checkUserLimitedRecord(String userId, String goodsId);
}
