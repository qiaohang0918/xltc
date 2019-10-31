package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Scope;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 配送范围表的 DAO 层
 *
 * @author wanghao
 */

public interface ScopeMapper {

    /**
     * 增加
     *
     * @param scope
     * @return
     */
    int insert(Scope scope);


    /**
     * 删除
     *
     * @param rangeId
     * @return
     */
    int delete(String rangeId);


    /**
     * 修改
     *
     * @param scope
     * @return
     */
    int update(@Param("record") Scope scope);

    /**
     * 查询所有或者查询单个
     * 当查询所有时,传递 null
     * 当查询单个时,传递 specification
     *
     * @return
     */
    List<Scope> findByScope(@Param("record") Scope scope, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 查询单个规格
     *
     * @param rangeId
     * @return
     */
    Scope findOne(@Param("rangeId") String rangeId);

    /**
     * 查询其他仓库的数据
     *
     * @param wareHouseId
     * @return
     */
    List<Scope> findOtherWarehouse(@Param("wareHouseId") String wareHouseId, @Param("city") String city);

}