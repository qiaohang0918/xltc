package com.qigan.qiganshop.service;

import java.util.List;

import com.qigan.qiganshop.pojo.Category;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.XltcGoodsTypeModel;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * 规格服务层
 */
public interface CategoryService {
    /**
     * 返回全部列表
     *
     * @return
     */
    public List<Category> findAll();

    /**
     * 增加
     */
    public Category add(Category category);

    /**
     * 修改
     */
    public Integer update(Category category);

    /**
     * 根据ID获取实体
     *
     * @param cateId
     * @return
     */
    public Category findOne(String cateId);

    /**
     * 批量删除
     *
     * @param ids
     */
    public Integer delete(String[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @param category 分类
     * @return PageResult
     */
    public PageResult findPage(Category category, Integer pageNum, Integer pageSize);

    /**
     * 修改所有的分类的排序
     *
     * @param cateId1
     * @param cateId2
     * @return
     */

    Integer updateSort(String cateId1, String cateId2);

    /**
     * 根据名字精确查询
     *
     * @param category_name
     * @return
     */
    Category findByName(String category_name);

    /**
     * 查询分类,同时查询标签及其下属商品
     *
     * @param cateId
     * @return
     */
    Category findByCate(String cateId);

    /**
     * 根据分类 ID 和标签 ID 查询关联的商品
     *
     * @param cateId
     * @param labelId
     * @return
     */
    List<Goods> findOneLabel(String cateId, String labelId);

    /**
     * 查询分类下的所有狗东西
     *
     * @param cateId
     * @param coordinate
     * @return
     */
    Category findAllByCateId(String cateId, String coordinate, CommonPage page);
    
    XltcGoodsTypeModel findGoodsByCateId(String cateId, String coordinate);
    
    void updateImage(String cateId, String imageUrl);
     
    List<?> findRandByCateId(String cateId, String coordinate);
    
}
