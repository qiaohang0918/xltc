package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.HomepageCate;
import com.qigan.qiganshop.pojo.Specification;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;

import java.util.List;

/**
 * 首页分类服务层
 *
 * @author wanghao
 */
public interface HomePageCateService {
    /**
     * 返回全部列表
     *
     * @return
     */
    public List<HomepageCate> findAll();

    /**
     * 增加
     */
    public Integer add(HomepageCate cate);

    /**
     * 修改
     */
    public Integer update(HomepageCate cate);

    /**
     * 根据ID获取实体
     *
     * @param cateId
     * @return
     */
    public HomepageCate findOne(String cateId);

    /**
     * 批量删除
     *
     * @param ids
     */
    public Integer delete(String[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(HomepageCate cate, Integer pageNum, Integer pageSize);

    /**
     * 更改排序
     *
     * @param cateId1
     * @param cateId2
     * @return
     */
    Integer updateSort(String cateId1, String cateId2);

    /**
     * 首页横向滑动
     *
     * @param coordinate
     * @return
     */
    JsonResult findHomePageCenter(String coordinate);

    /**
     * 分类关联商品
     *
     * @param one
     * @param goodsIds
     * @param flag
     * @return
     */
    Integer unionGoods(HomepageCate one, String[] goodsIds, String flag);
}
