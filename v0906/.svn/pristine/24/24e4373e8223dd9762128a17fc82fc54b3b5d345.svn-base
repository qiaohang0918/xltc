package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Scope;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.List;

/**
 * 规格服务层
 */
public interface ScopeService {
    /**
     * 返回全部列表
     *
     * @return
     */
    public List<Scope> findAll();

    /**
     * 增加
     */
    public Integer add(Scope scope);

    /**
     * 修改
     */
    public Integer update(Scope scope);

    /**
     * 根据ID获取实体
     *
     * @param scopeId
     * @return
     */
    public Scope findOne(String scopeId);

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
    public PageResult findPage(Scope scope, Integer pageNum, Integer pageSize);

    /**
     * 添加仓库配送范围时,检测当前区域是否存在重合现象,同一个仓库不做处理
     *
     * @param wareHouseId
     * @param apexs
     * @return
     */
    Boolean findCoincide (String wareHouseId, String apexs,String city);

    /**
     * 检测同一仓库是否重名
     * @param wareHouseId
     * @param name
     * @return
     */
    Boolean checkNameExits(String wareHouseId, String name);


    boolean checkIn(String coordinate);
}
