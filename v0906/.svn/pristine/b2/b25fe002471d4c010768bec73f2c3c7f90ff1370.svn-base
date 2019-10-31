package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbClueActivity;
import com.qigan.qiganshop.pojo.TbClueActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface TbClueActivityMapper {
    int countByExample(TbClueActivityExample example);

    int deleteByExample(TbClueActivityExample example);

    int deleteByPrimaryKey(String clueActivityId);

    int insert(TbClueActivity record);

    int insertSelective(TbClueActivity record);

    List<TbClueActivity> selectByExample(TbClueActivityExample example);

    TbClueActivity selectByPrimaryKey(String clueActivityId);

    int updateByExampleSelective(@Param("record") TbClueActivity record, @Param("example") TbClueActivityExample example);

    int updateByExample(@Param("record") TbClueActivity record, @Param("example") TbClueActivityExample example);

    int updateByPrimaryKeySelective(TbClueActivity record);

    int updateByPrimaryKey(TbClueActivity record);

    @Update("update tb_clue_activity set enable = #{status} ")
    Integer setAllActivityStatus(@Param("status") String status);
}