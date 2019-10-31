package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.TbActivityUnionGoods;
import com.qigan.qiganshop.pojo.TbActivityUnionGoodsExample;
import java.util.List;

import com.qigan.qiganshop.pojo.XltcGoodsModel;
import com.qigan.qiganshop.util.result.XltcResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbActivityUnionGoodsMapper {
    int countByExample(TbActivityUnionGoodsExample example);

    int deleteByExample(TbActivityUnionGoodsExample example);

    int deleteByPrimaryKey(String unionGoodsId);

    int insert(TbActivityUnionGoods record);

    int insertSelective(TbActivityUnionGoods record);

    List<TbActivityUnionGoods> selectByExample(TbActivityUnionGoodsExample example);

    TbActivityUnionGoods selectByPrimaryKey(String unionGoodsId);

    int updateByExampleSelective(@Param("record") TbActivityUnionGoods record, @Param("example") TbActivityUnionGoodsExample example);

    int updateByExample(@Param("record") TbActivityUnionGoods record, @Param("example") TbActivityUnionGoodsExample example);

    int updateByPrimaryKeySelective(TbActivityUnionGoods record);

    int updateByPrimaryKey(TbActivityUnionGoods record);

    List<XltcGoodsModel> findUnionGoods(@Param("unionId") String unionId, @Param("picHeader") String picHeader, @Param("wareId") String wareId);
}