package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.SpecOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格表的 DAO 层
 *
 * @author wanghao
 */
public interface SpecOptionMapper {

    /**
     * 增加
     *
     * @param option
     * @return
     */
    int insert(@Param("record") SpecOption option);


    /**
     * 删除
     *
     * @param optionId
     * @return
     */
    int delete(String optionId);

    /**
     * 批量删除
     *
     * @param specId
     * @return
     */
    int deleteBySpecId(String specId);


    /**
     * 修改
     *
     * @param option
     * @return
     */
    int update(@Param("record") SpecOption option);

    /**
     * 查询所有或者查询单个
     * 当查询所有时,传递 null
     * 当查询单个时,传递 specification
     *
     * @return
     */
    List<SpecOption> findBySpecOption(@Param("record") SpecOption option);

    /**
     * 查询单个规格详情
     *
     * @param optionId
     * @return
     */
    SpecOption findOne(@Param("optionId") String optionId);


}