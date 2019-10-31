package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Label;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.XltcResult;

import java.util.List;

/**
 * sku服务层
 *
 * @author wanghao
 */
public interface LabelService {
    /**
     * 增加sku
     *
     * @param label
     * @return
     */
    Integer add(List<Label> label);

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
     * @param labelList
     * @return
     */
    Integer update(List<Label> labelList);

    /**
     * 查询单个sku
     *
     * @param labelId
     * @return
     */
    Label findOne(String labelId);

    /**
     * 条件查询,分页查询,查询所有,三合一
     *
     * @param label
     * @return
     */
    PageResult findByLabel(Label label);


    /**
     * 根据条件进行删除标签
     *
     * @param cateId
     */
    Integer deleteByCateId(String cateId);

    /**
     * 商品与标签 关联或取消关联
     *
     * @param labelId
     * @param goodsIds
     * @param flag
     * @return
     */
    Integer unionGoods(String labelId, String goodsIds, String flag);

    /**
     * 修改排序
     * @param labelId1
     * @param labelId2
     * @return
     */
    Integer updateSort(String labelId1, String labelId2);
    
    List<?> findCatesByCate(String cateId);
    
    List<?> findPageByLabel(String lId, String coni, CommonPage page);
    
    PageResult findPageAllByLabelId(String lId, String coni, CommonPage page);
    
    Integer updateGoodsSort(String labelId, String goodsId, Integer sort);
    
    XltcResult findUnitGoodsByCateId(String cateId, CommonPage page);
}
