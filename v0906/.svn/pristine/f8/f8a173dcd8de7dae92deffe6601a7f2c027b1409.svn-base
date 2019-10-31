package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbPresellGoods;
import com.qigan.qiganshop.pojo.TbPresellGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface TbPresellGoodsMapper {
    int countByExample(TbPresellGoodsExample example);

    int deleteByExample(TbPresellGoodsExample example);

    int deleteByPrimaryKey(String preSellId);

    int insert(TbPresellGoods record);

    int insertSelective(TbPresellGoods record);

    List<TbPresellGoods> selectByExample(TbPresellGoodsExample example);

    TbPresellGoods selectByPrimaryKey(String preSellId);

    int updateByExampleSelective(@Param("record") TbPresellGoods record, @Param("example") TbPresellGoodsExample example);

    int updateByExample(@Param("record") TbPresellGoods record, @Param("example") TbPresellGoodsExample example);

    int updateByPrimaryKeySelective(TbPresellGoods record);

    int updateByPrimaryKey(TbPresellGoods record);

    @Update("update tb_presell_goods set sellNum = sellNum + #{count} where preSellGoodsCode = #{code} and level = #{level} and enabled = '1' ")
    int addCurrentPreSelllGoodsSellNum(@Param("code") String code, @Param("level")String level, @Param("count")String count);
}