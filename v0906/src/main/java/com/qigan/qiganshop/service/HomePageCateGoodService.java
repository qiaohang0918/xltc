package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Category;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.HomepageCateGoods;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.List;

/**
 * 首页分类服务层
 *
 * @author wanghao
 */
public interface HomePageCateGoodService {


    /**
     * 增加
     *
     * @param cateId
     * @param goodsIds
     * @return
     */
    Integer add(String cateId, String[] goodsIds);


    /**
     * 根据ID获取实体
     *
     * @param cateId
     * @return
     */
    List<HomepageCateGoods> findByCateId(String cateId);

    /**
     * 批量删除
     *
     * @param cateId
     * @param goodsIds
     * @return
     */
    Integer delete(String cateId, String[] goodsIds);

    /**
     * 更新商品排序
     *
     * @param cateId
     * @param goodsId1
     * @param goodsId2
     * @return
     */
    Integer updateSort(String cateId, String goodsId1, String goodsId2);
    
    Integer updateGoodsSort(String homepageCateId, String goodsId, Integer sort);

    /**
     * 查询单个
     *
     * @param cateId
     * @param goodsId
     * @return
     */
    HomepageCateGoods findOne(String cateId, String goodsId);

    /**
     * 修改
     *
     * @param one
     * @return
     */
    int update(HomepageCateGoods one);

    /**
     * 根据首页分类查询商品
     *
     * @param cateId
     * @return
     */
    List<Goods> findGoodsByCateId(String cateId, String coni, CommonPage page);

    /**
     * 释放关联商品
     * @param categoryId
     * @param statusStrList
     * @return
     */
    boolean releaseUnionGoods(String categoryId, String statusStrList);
}
