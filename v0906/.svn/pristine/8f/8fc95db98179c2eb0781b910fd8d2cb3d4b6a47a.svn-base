package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Item;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 规格表的 DAO 层
 *
 * @author wanghao
 */
public interface ItemMapper {

    /**
     * 增加
     *
     * @param item
     * @return
     */
    int insert(Item item);


    /**
     * 删除
     *
     * @param itemId
     * @return
     */
    Integer delete(String itemId);


    /**
     * 修改
     *
     * @param goods
     * @return
     *//*
    int update(Goods goods);

    */

    /**
     * 查询所有或者查询单个
     * 当查询所有时,传递 null
     * 当查询单个时,传递 specification
     *
     * @return
     */
    List<Item> findByItem(@Param("record") Item item, @Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * 查询单个规格
     *
     * @param itemId
     * @return
     */
    Item findOne(@Param("itemId") String itemId);

    /**
     * 根据上级 ID 删除 item
     *
     * @param spuId
     * @return
     */
    Integer deleteBySPUId(@Param("spuId") String spuId);

    /**
     * 修改
     *
     * @param item
     * @return
     */
    Integer update(@Param("record") Item item);

    /**
     * 通过 spu 商品代码和 sku 商品代码查询对应的 sku 信息
     *
     * @param goodsCode
     * @param itemCode
     * @return
     */
    List<Item> findByCode(@Param("goodsCode") String goodsCode, @Param("itemCode") String itemCode);

    /**
     * 通过 SPUId 查询 sku,包含库存
     *
     * @param spuId
     * @return
     */
    List<Item> findBySpuId(String spuId);
    /**
     * 通过 SPUId 查询 sku,包含库存
     *
     * @param spuId
     * @return
     */
    List<Item> findBySpuIdNotStock(String spuId);

    /**
     * 商品售出后,增加销售数量
     *
     * @param itemId
     * @param count
     * @return
     */
    Integer updateSaleCount(@Param("itemId") String itemId, @Param("count") Integer count);

    /**
     * 增加已销售数量
     *
     * @param goodsId
     * @param count
     * @return
     */
    
    @Update("delete from tb_item")
    void truncate();
    
    @Insert({
    	"<script> ",
    	"insert into tb_item (itemId, spuId, code,name, note, weight,",
    	"volume, salesPrice, costPrice,stockStatusCode, stockNum, barCode,",
    	"originArea, del, isVipItem,VIPrice, praise, salesNum,",
    	" info, shareNum, activePrice, status, unit) values ",
    	"<foreach collection='list' item='record' index='index' separator=','> ",
    	"(#{record.itemId,jdbcType=VARCHAR}, #{record.spuId,jdbcType=VARCHAR}, #{record.code,jdbcType=VARCHAR},",
    	"#{record.name,jdbcType=VARCHAR}, #{record.note,jdbcType=VARCHAR}, #{record.weight,jdbcType=DOUBLE},",
    	"#{record.volume,jdbcType=VARCHAR}, #{record.salesPrice,jdbcType=DECIMAL}, #{record.costPrice,jdbcType=DECIMAL},",
    	"#{record.stockStatusCode,jdbcType=VARCHAR}, #{record.stockNum,jdbcType=INTEGER}, #{record.barCode,jdbcType=VARCHAR},",
    	"#{record.originArea,jdbcType=VARCHAR}, #{record.del,jdbcType=VARCHAR}, #{record.isVipItem,jdbcType=VARCHAR},",
    	"#{record.vIPrice,jdbcType=DECIMAL}, #{record.praise,jdbcType=VARCHAR}, #{record.salesNum,jdbcType=INTEGER},",
    	"#{record.info,jdbcType=VARCHAR}, #{record.shareNum,jdbcType=INTEGER}, #{record.activePrice,jdbcType=DECIMAL},",
    	" #{record.status,jdbcType=VARCHAR}, #{record.unit,jdbcType=VARCHAR}) ",
    	"</foreach>",
    	"  ON DUPLICATE KEY UPDATE ",
    	"itemId = VALUES(itemId),",
    	"name =  VALUES(name), ",
    	"salesPrice =  VALUES(salesPrice),",
    	"costPrice =  VALUES(costPrice),",
    	"salesPrice =  VALUES(salesPrice),",
    	"VIPrice =  VALUES(VIPrice), ",
    	"code =  VALUES(code) ",
    	"</script> "
    })
    void insertByGY(@Param("list") List<Item> list);
    
    List<Item> findItemByCode(@Param("goodsCode") String goodsId, @Param("itemCode") String spuId);
}