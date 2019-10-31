package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Specification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格表的 DAO 层
 *
 * @author wanghao
 */
public interface SpecMapper {

    /**
     * 增加
     *
     * @param specification
     * @return
     */
    int insert(@Param("record") Specification specification);


    /**
     * 删除
     *
     * @param specId
     * @return
     */
    int delete(String specId);


    /**
     * 修改
     *
     * @param specification
     * @return
     */
    int update(Specification specification);

    /**
     * 查询所有或者查询单个
     * 当查询所有时,传递 null
     * 当查询单个时,传递 specification
     *
     * @return
     */
    List<Specification> findBySpecification(@Param("record") Specification specification);

    /**
     * 查询单个规格
     *
     * @param specId
     * @return
     */
    Specification findOne(@Param("specId") String specId);


}