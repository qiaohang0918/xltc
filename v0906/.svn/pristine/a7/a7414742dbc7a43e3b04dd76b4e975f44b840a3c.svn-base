package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.HomepageCateGoods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 首页分类关联商品中间表 dao
 *
 * @author wanghao
 */
public interface HomepageCateGoodsMapper {
    /**
     * 删除
     *
     * @param cateId
     * @param goodsId
     * @return
     */
    int delete(@Param("cateId") String cateId, @Param("goodsId") String goodsId);

    /**
     * 增加
     *
     * @param record
     * @return
     */
    int add(HomepageCateGoods record);

    /**
     * 根据分类查询
     *
     * @param cateId
     * @return
     */
    List<HomepageCateGoods> findByCateId(String cateId);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    Integer update(@Param("record") HomepageCateGoods record);

    /**
     * 查询单个
     *
     * @param cateId
     * @param goodsId
     * @return
     */
    HomepageCateGoods findOne(@Param("cateId") String cateId, @Param("goodsId") String goodsId);
    
    @Update({
    	"update tb_homepageCate_goods",
    	"set sort = #{sort}",
    	"where homepageCateId = #{cateId}",
        "and goodsId = #{goodsId} "
    })
    int updateGoodsSort(@Param("cateId") String cateId, @Param("goodsId") String goodsId, @Param("sort") Integer sort);

    /**
     * 释放goods
     * @param categoryId
     * @param statusStrList
     * @return
     */
    @Delete("delete from tb_homepagecate_goods where homepageCateId = #{cateId} and goodsId in ( ${goodsIds} )")
    int releaseUnionGoods(@Param("cateId") String categoryId, @Param("goodsIds")String statusStrList);
    
}