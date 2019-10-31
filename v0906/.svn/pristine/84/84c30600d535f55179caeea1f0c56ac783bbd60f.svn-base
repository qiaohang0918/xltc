package com.qigan.qiganshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.LabelGoods;
import com.qigan.qiganshop.pojo.XltcGoodsModel;

/**
 * 标签商品关联表
 *
 * @author wanghao
 */
public interface LabelGoodsMapper {
    /**
     * 删除
     *
     * @param record
     * @return
     */
    int delete(LabelGoods record);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(LabelGoods record);

    /**
     * 查询单个关联情况,主要用于查重
     *
     * @param labelGoods
     * @return
     */
    List<LabelGoods> findOne(LabelGoods labelGoods);

    /**
     * 根据标签 ID 查询商品
     *
     * @param cateId
     * @param labelId
     * @return
     */
    List<Goods> findUnionGoods(@Param("cateId") String cateId, @Param("labelId") String labelId);
    
    
    
    @Select({
    	"select rx.createDate, rx.goodsId, rx.name as goodsName, rx.simpleName, rx.categoryCode, rx.VIPrice , ",
    	"rx.salesPrice, rx.costPrice,r.salableNum, ",
    	"concat(#{host}, SUBSTRING_INDEX(rx.picUrls, ',', 1)) as imageUrl",
    	"from(",
    	"select t.goodsId, x.salableNum , t.sort from tb_label_goods t",
    	"left join tb_stock x on x.goodsId = t.goodsId",
    	"where t.labelId = #{lId} and x.warehouseId = #{wareId} and x.salableNum > 0) r",
    	"left join tb_goods rx on rx.goodsId = r.goodsId",
    	"where rx.del = '0' and rx.status = '2'",
    	"order by r.sort asc"
    })
    List<XltcGoodsModel> findGoodsByLabel(@Param("lId") String lId, @Param("wareId") String wareId, @Param("host") String host);
    
    @Select({
    	"select ",
    	"rx.createDate, rx.goodsId, rx.name as goodsName, rx.simpleName, rx.categoryCode, rx.VIPrice , ",
    	"rx.salesPrice, rx.costPrice, x.sort, rx.code as goodsCode, t.labelId  ",
    	"from tb_label t",
    	"left join tb_label_goods x on t.labelId = x.labelId",
    	"left join tb_goods rx on rx.goodsId = x.goodsId",
    	"where x.labelId = #{labelId}",
    	"and rx.del = '0' and rx.status = '2'",
    	"order by x.sort asc "
    })
    List<XltcGoodsModel> findUnitGoodsByLabelId(String labelId);
    
    @Select({
    	"select r.name as goodsName, r.simpleName, ",
    	"r.code as goodsCode, r.salesPrice, r.goodsId",
    	"from tb_label_goods t",
    	"right join (",
    	"select * from tb_goods where categoryCode = #{cateId} ",
    	"and status = '2' and del = '0') r on r.goodsId = t.goodsId",
    	"where t.labelGoodsId is  null"
    })
    List<XltcGoodsModel> findUnitGoodsByCateId(String cateId);
    
    
}