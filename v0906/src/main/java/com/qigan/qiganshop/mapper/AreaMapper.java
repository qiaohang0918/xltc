package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaMapper {

    /**
     * 增加
     *
     * @param area
     * @return
     */
    int insert(Area area);


    /**
     * 删除
     *
     * @param areaId
     * @return
     */
    int delete(String areaId);


    /**
     * 修改
     *
     * @param area
     * @return
     */
    int update(@Param("record") Area area);

    /**
     * 查询所有或者查询单个
     * 当查询所有时,传递 null
     * 当查询单个时,传递 area
     *
     * @return
     */
    List<Area> findByArea(@Param("record") Area area);

    /**
     * 查询单个规格
     *
     * @param areaId
     * @return
     */
    Area findOne(@Param("areaId") String areaId);
}