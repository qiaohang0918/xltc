package com.qigan.qiganshop.mapper;

import java.util.List;
import java.util.Map;

import com.qigan.qiganshop.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qigan.qiganshop.pojo.Stock;
import com.qigan.qiganshop.pojo.XltcGoodsDetailsModel;

/**
 * 库存 DAO
 *
 * @author wanghao
 */

public interface StockMapper {
    /**
     * 删除
     *
     * @param stockId
     * @return
     */
    int delete(String stockId);

    /**
     * 增加
     *
     * @param record
     * @return
     */
    int insert(Stock record);

    /**
     * 分页查询,条件查询,查询全部
     *
     * @param stock
     * @param page
     * @param rows
     * @return
     */
    List<Stock> findPage(@Param("record") Stock stock, @Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * 查询单个
     *
     * @param stockId
     * @return
     */
    Stock findOne(@Param("stockId") String stockId);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int update(@Param("record") Stock record);
    
    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updates(@Param("list") List<Stock> list);

    /**
     * 根据商品 Id 和仓库 Id 查询某商品在某仓库中是否存在
     *
     * @param itemId
     * @param warehouseId
     * @return
     */
    Stock checkExits(@Param("itemId") String itemId, @Param("warehouseId") String warehouseId);

    /**
     * 查询某仓库中所有库存大于 0 的商品
     *
     * @param wareHouseId
     * @return
     */
    List<Stock> findStockNum(@Param("wareHouseId") String wareHouseId, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 查询某仓库中所有库存大于 0 的商品
     *
     * @param wareHouseId
     * @return
     */
    List<Stock> findStock(@Param("wareHouseId") String wareHouseId, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 根据 itemId 和 仓库 Id 查询库存
     *
     * @param s
     * @param warehouseId
     * @return
     */
    Stock findByItemIdAndWareHouseId(@Param("itemId") String s, @Param("warehouseId") String warehouseId);

    /**
     * 解锁库存
     *
     * @param warehouseCode
     * @param itemId
     * @param count
     * @return
     */
    int cancelLock(
            @Param("warehouseCode") String warehouseCode,
            @Param("itemId") String itemId,
            @Param("countNum") Integer count,
    		@Param("saleNum") Integer saleNum);
    
    @Select({
    	"select t.warehouseId as warehouseId, x.name as warehouseName, ",
    	"t.stockNum as stockNumber, t.lockNum as lockNumber, ",
    	"t.salableNum as salesNumber ",
    	"from tb_stock t ",
    	"left join tb_warehouse x on t.warehouseId = x.warehouseId ",
    	"where t.goodsId = #{goodsId} "
    })
    List<XltcGoodsDetailsModel> findStockById(String goodsId);
    
    @Update({
    	"update tb_stock t",
    	"left join (",
    	"select goodsId, code from tb_goods ",
    	") r on t.goodsId = r.goodsId",
    	"set t.stockNum = #{map.stockNum},",
//    	"t.lockNum = #{map.lockNum},",
    	"t.salableNum = #{map.salableNum} ",
    	"where t.warehouseId = #{map.wareId} ",
    	"and t.lockNum = 0 ",
    	"and r.code = #{map.code}"
    })
    int updateStockByCode(@Param("map") Map<String, Object> map);
    
    @Select({
    	"select t.lockNum from tb_stock t ",
    	"left join tb_goods x on t.goodsId = x.goodsId",
    	"where x.code = #{code} and t.warehouseId = #{wareId} and t.lockNum = 0 "
    })
    Integer selectByCode(@Param("wareId") String wareId, @Param("code") String code);
    
    
    int insertOrUpdate(List<Stock> list);

    @Select({
            "select a.* from tb_stock a ",
            "left join tb_goods b on a.goodsId = b.goodsId",
            "where b.code = #{code} and a.warehouseId = #{wareId} "
    })
    Stock findStockByCodeAndWarehouseId(@Param("wareId") String wareId, @Param("code")String code);

    @Select({
            "select a.goodsId,b.itemId from tb_goods a ",
            "LEFT JOIN tb_item b on a.goodsId=b.spuId",
            " where a.code = #{code} "
    })
    Map<String, String> findSpuSkuId(@Param("code") String code);
    
    @Select({
    	"select x.lockNum from tb_goods t",
    	"left join tb_stock x on t.goodsId = x.goodsId",
    	"where t.code = #{code} and x.warehouseId= #{wareId}",
    })
    Integer findByItemCodeAndWareHouseId(@Param("code") String code, @Param("wareId") String wareId);

    @Select("select * from tb_stock where warehouseId = #{warehouseCode} and itemId in ( ${skuIdsConstructed} ) and isDel ='0' ")
    List<Stock> findEnabledStockByItemIdsAndWarehouseCode(@Param("skuIdsConstructed") String skuIdsConstructed, @Param("warehouseCode") String warehouseCode);

    @Update({"update tb_stock set stockNum = stockNum + #{orderItem.count} ,salableNum = salableNum + #{orderItem.count} " ,
            " where itemId = #{orderItem.itemId}  and warehouseId = #{warehouseCode} "})
    Integer increQuestionsRefundOrderItemsStock(@Param("orderItem") OrderItem orderItem, @Param("warehouseCode") String warehouseCode);
}