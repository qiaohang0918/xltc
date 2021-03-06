package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Category;
import com.qigan.qiganshop.pojo.CouponVueModel;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 规格表的 DAO 层
 *
 * @author wanghao
 */
public interface CategoryMapper {

    /**
     * 增加
     *
     * @param category
     * @return
     */
    int insert(@Param("record") Category category);


    /**
     * 删除
     *
     * @param cateId
     * @return
     */
    int delete(@Param("cateId") String cateId);


    /**
     * 修改
     *
     * @param category
     * @return
     */
    int update(@Param("record") Category category);

    /**
     * 查询所有或者查询单个
     * 当查询所有时,传递 null
     * 当查询单个时,传递 specification
     *
     * @param category
     * @param page
     * @param rows
     * @return
     */
    List<Category> findByCategory(@Param("record") Category category, @Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * 查询单个规格
     *
     * @param cateId
     * @return
     */
    Category findOne(@Param("cateId") String cateId);

    /**
     * 根据名字精确查询
     *
     * @param
     * @return
     */
    Category findByName(String cateName);
    
    @Update("delete from tb_category")
    void truncate();
    
    @Insert({
    	"<script> ",
    	"insert into tb_category (cateId,cateName,sort,enable) values ",
    	"<foreach collection='list' item='record' index='index' separator=','> ",
    	"(#{record.cateId,jdbcType=VARCHAR},",
        "#{record.cateName,jdbcType=VARCHAR},",
    	"#{index},",
    	"#{record.enable,jdbcType=VARCHAR})",
    	"</foreach>",
    	"  ON DUPLICATE KEY UPDATE ",
    	"cateId = VALUES(cateId),",
    	"cateName =  VALUES(cateName)",
    	"</script> "
    })
    void insertByGY(@Param("list") List<Category> cates);
    
    @Update({
    	"update tb_category set cateImage = #{url} ",
    	"where cateId = #{cateId}"
    })
    void updateCateImage(@Param("cateId") String cateId, @Param("url") String url);

    @Select({
    	"select cateId as 'key', cateName as label from tb_category ",
    	"where enable = '1'"
    })
    List<CouponVueModel> findCateId4Vue();
    
}