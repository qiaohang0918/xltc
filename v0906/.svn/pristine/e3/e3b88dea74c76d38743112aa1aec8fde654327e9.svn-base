package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbActivityUnion;
import com.qigan.qiganshop.pojo.TbActivityUnionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbActivityUnionMapper {
    int countByExample(TbActivityUnionExample example);

    int deleteByExample(TbActivityUnionExample example);

    int deleteByPrimaryKey(String unionId);

    int insert(TbActivityUnion record);

    int insertSelective(TbActivityUnion record);

    List<TbActivityUnion> selectByExample(TbActivityUnionExample example);

    TbActivityUnion selectByPrimaryKey(String unionId);

    int updateByExampleSelective(@Param("record") TbActivityUnion record, @Param("example") TbActivityUnionExample example);

    int updateByExample(@Param("record") TbActivityUnion record, @Param("example") TbActivityUnionExample example);

    int updateByPrimaryKeySelective(TbActivityUnion record);

    int updateByPrimaryKey(TbActivityUnion record);

    @Select({"select a.categoryCode,a.categoryName,a.goodsId,a.code as 'key',a.name as label from tb_goods a " ,
            "left join tb_stock b on a.goodsId = b.goodsId  " ,
            "where 1=1 " ,
            " ${conditions} "})
    List<Map<String, Object>> selectDisUnionGoodsInfo(@Param("conditions") String conditions);

    @Select("select goods_code from tb_activity_union_goods where union_id = #{union_id} ")
    List<String> selectUnionedGoodsInfo(@Param("union_id") String union_id);
}