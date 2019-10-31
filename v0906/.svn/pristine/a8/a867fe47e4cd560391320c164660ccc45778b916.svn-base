package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbSpecialConcat;
import com.qigan.qiganshop.pojo.TbSpecialConcatExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbSpecialConcatMapper {
    int countByExample(TbSpecialConcatExample example);

    int deleteByExample(TbSpecialConcatExample example);

    int deleteByPrimaryKey(String concatId);

    int insert(TbSpecialConcat record);

    int insertSelective(TbSpecialConcat record);

    List<TbSpecialConcat> selectByExample(TbSpecialConcatExample example);

    TbSpecialConcat selectByPrimaryKey(String concatId);

    int updateByExampleSelective(@Param("record") TbSpecialConcat record, @Param("example") TbSpecialConcatExample example);

    int updateByExample(@Param("record") TbSpecialConcat record, @Param("example") TbSpecialConcatExample example);

    int updateByPrimaryKeySelective(TbSpecialConcat record);

    int updateByPrimaryKey(TbSpecialConcat record);

    @Select({"select c.* from tb_special_unit a " ,
            "left join tb_special_concat b on a.unit_id = b.unit_id " ,
            "left join tb_special_topic c on b.topic_id = c.topic_id " ,
            "where a.unit_id = #{unitId} " ,
            "and c.enable = '1' "})
    List<Map<String, String>> selectUnitsCasecadeTopic(@Param("unitId") String unitId);
}