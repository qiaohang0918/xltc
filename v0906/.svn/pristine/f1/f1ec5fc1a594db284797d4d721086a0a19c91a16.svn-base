package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbPresellOrder;
import com.qigan.qiganshop.pojo.TbPresellOrderExample;
import java.util.List;
import java.util.Map;

import com.qigan.qiganshop.pojo.group.LevelPreSellReporter;
import com.qigan.qiganshop.util.result.XltcResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbPresellOrderMapper {
    int countByExample(TbPresellOrderExample example);

    int deleteByExample(TbPresellOrderExample example);

    int deleteByPrimaryKey(String presellorderid);

    int insert(TbPresellOrder record);

    int insertSelective(TbPresellOrder record);

    List<TbPresellOrder> selectByExample(TbPresellOrderExample example);

    TbPresellOrder selectByPrimaryKey(String presellorderid);

    int updateByExampleSelective(@Param("record") TbPresellOrder record, @Param("example") TbPresellOrderExample example);

    int updateByExample(@Param("record") TbPresellOrder record, @Param("example") TbPresellOrderExample example);

    int updateByPrimaryKeySelective(TbPresellOrder record);

    int updateByPrimaryKey(TbPresellOrder record);

    @Select({"select c.phone,d.preSendTime,a.*,b.* from tb_presell_order a " ,
            "left join tb_order b on a.preSellOrderId = b.orderId " ,
            "left join tb_user c on b.userId = c.userId" ,
            "left join tb_presell_goods d on a.code = d.preSellGoodsCode " ,
            "where 1=1 and a.level = d.level  ${conditions} " ,
            "order by b.dealDatetime desc"})
    List<Map<String, Object>> findPreSellOrder(@Param("conditions") String conditions);

    @Select({"select a.* from tb_presell_order a " ,
            "left join tb_order b on a.preSellOrderId = b.orderId " ,
            "where a.isPushed = #{isPushed} " ,
            "and a.preSellTime <= #{preSellTime} " ,
            "and b.status = #{status} "})
    List<TbPresellOrder> findCurrentSuitPushOrders(@Param("preSellTime") String preSellTime, @Param("isPushed") String isPushed, @Param("status") String status);

    @Select({"select c.phone,c.nickName,a.receiverAddress,a.payMoney,a.money,a.status,b.* from tb_order a " ,
            "left join tb_order_item b on a.orderId = b.orderId " ,
            "left join tb_user c on c.userId = b.userId " ,
            "where a.orderId = #{orderId} "})
    List<Map<String,Object>> findPreSellOrderDetails(@Param("orderId") String orderId);

    @Select({"select a.tepStatus,a.level,count(0) as orderNums, " ,
            "sum(a.but_count) as sumCount,sum(a.payMoney) as sumPayMoney  from ( " ,
            " select b.payMoney,b.money, if(b.status in ('1','3','4','8','902') ,'sellOut','goBack') as tepStatus , a.* " ,
            "from tb_presell_order a left join tb_order b on a.preSellOrderId = b.orderId) a " ,
            "group by a.tepStatus,a.level " })
    List<Map<String, Object>> reportEveryLevelData();

    @Select("select DISTINCT level from tb_presell_goods order by level desc")
    List<String> findAllLevels();

    @Select("SELECT preSellGoodsCode,level FROM tb_presell_goods where level = #{level} ")
    List<LevelPreSellReporter> findCurrentLevelPreSellGoods(@Param("level") String level);

    @Select({"select a.code,b.tepStatus,IFNULL(sum(b.payMoney),0.00) as sumPayMoney,count(0) as orderCount,sum(a.but_count) as buyCount from tb_presell_order a " ,
            "left join (select *,if(`status` in ('1','3','4','8','902') , 'sellOut','goBack') as tepStatus " ,
            " from tb_order where tag = 'preSell' and level = #{level} ) b on a.preSellOrderId = b.orderId " ,
            " where a.`level` = #{level} " ,
            "group by a.code , b.tepStatus "})
    List<Map<String,Object>> selectCurrentLevelsCodesEffect(@Param("level") String level);
}