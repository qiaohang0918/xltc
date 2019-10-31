package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbClueUser;
import com.qigan.qiganshop.pojo.TbClueUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbClueUserMapper {
    int countByExample(TbClueUserExample example);

    int deleteByExample(TbClueUserExample example);

    int deleteByPrimaryKey(String clueid);

    int insert(TbClueUser record);

    int insertSelective(TbClueUser record);

    List<TbClueUser> selectByExample(TbClueUserExample example);

    TbClueUser selectByPrimaryKey(String clueid);

    int updateByExampleSelective(@Param("record") TbClueUser record, @Param("example") TbClueUserExample example);

    int updateByExample(@Param("record") TbClueUser record, @Param("example") TbClueUserExample example);

    int updateByPrimaryKeySelective(TbClueUser record);

    int updateByPrimaryKey(TbClueUser record);
}