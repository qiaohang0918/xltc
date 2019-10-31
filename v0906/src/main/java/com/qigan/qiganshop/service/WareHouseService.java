package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Specification;
import com.qigan.qiganshop.pojo.Stock;
import com.qigan.qiganshop.pojo.Warehouse;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.List;

/**
 * 规格服务层
 */
public interface WareHouseService {

    /**
     * 增加
     */
    public Integer add(Warehouse warehouse);

    /**
     * 修改
     */
    public Integer update(Warehouse warehouse);

    /**
     * 根据ID获取实体
     *
     * @param warehouseId
     * @return
     */
    public Warehouse findOne(String warehouseId);

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
    public PageResult findPage(Warehouse warehouse, Integer pageNum, Integer pageSize);


}
