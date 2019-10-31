package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbSpecialUnit;
import com.qigan.qiganshop.pojo.TbSpecialUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbSpecialUnitMapper {
    int countByExample(TbSpecialUnitExample example);

    int deleteByExample(TbSpecialUnitExample example);

    int deleteByPrimaryKey(String unitId);

    int insert(TbSpecialUnit record);

    int insertSelective(TbSpecialUnit record);

    List<TbSpecialUnit> selectByExample(TbSpecialUnitExample example);

    TbSpecialUnit selectByPrimaryKey(String unitId);

    int updateByExampleSelective(@Param("record") TbSpecialUnit record, @Param("example") TbSpecialUnitExample example);

    int updateByExample(@Param("record") TbSpecialUnit record, @Param("example") TbSpecialUnitExample example);

    int updateByPrimaryKeySelective(TbSpecialUnit record);

    int updateByPrimaryKey(TbSpecialUnit record);

    @Select({"select " ,
            "b.sort, " ,
            "b.enable, " ,
            "b.create_time as createTime, " ,
            "b.unit_details as unitDetails, " ,
            "b.unit_id as unitId, " ,
            "c.topic_id as topicId, " ,
            "c.special_id as specialId, " ,
            "c.topic_name as topicName, " ,
            "b.create_time as createTime, " ,
            "b.unit_like as unitLike, " ,
            "b.unit_owner_name as unitOwnerName, " ,
            "b.unit_owner_no as unitOwnerNo, " ,
            "b.unit_owner_picture as unitOwnerPicture, " ,
            "b.unit_picture as unitPicture, " ,
            "b.unit_stored as unitStored, " ,
            "b.unit_sub_notice as unitSubNotice, " ,
            "b.unit_title as unitTitle, " ,
            "b.update_time as updateTime " ,
            "from tb_special_concat a " ,
            "left join  tb_special_unit b on a.unit_id = b.unit_id " ,
            "left join  tb_special_topic c on c.topic_id = a.topic_id " ,
            "where 1=1 " ,
            " ${conditions} " ,
            "order by b.sort desc  " })
    List<TbSpecialUnit> findUnitByCondition(@Param("conditions") String conditions);

    @Select({"select " ,
            "b.sort, " ,
            "b.enable, " ,
            "b.create_time as createTime, " ,
            "b.unit_details as unitDetails, " ,
            "b.unit_id as unitId, " ,
            "c.topic_id as topicId, " ,
            "c.special_id as specialId, " ,
            "c.topic_name as topicName, " ,
            "b.create_time as createTime, " ,
            "b.unit_like as unitLike, " ,
            "b.unit_owner_name as unitOwnerName, " ,
            "b.unit_owner_no as unitOwnerNo, " ,
            "b.unit_owner_picture as unitOwnerPicture, " ,
            "b.unit_picture as unitPicture, " ,
            "b.unit_stored as unitStored, " ,
            "b.unit_sub_notice as unitSubNotice, " ,
            "b.unit_title as unitTitle, " ,
            "b.update_time as updateTime " ,
            "from tb_special_concat a " ,
            "left join  tb_special_unit b on a.unit_id = b.unit_id " ,
            "left join  tb_special_topic c on c.topic_id = a.topic_id " ,
            "where 1=1 " ,
            " ${conditions} " ,
            "order by b.sort asc  " })
    List<TbSpecialUnit> findUnitByConditionForApp(@Param("conditions") String conditions);
}