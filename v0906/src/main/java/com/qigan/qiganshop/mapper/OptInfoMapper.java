package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.OptInfo;
import com.qigan.qiganshop.pojo.OptInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptInfoMapper {
    int countByExample(OptInfoExample example);

    int deleteByExample(OptInfoExample example);

    int deleteByPrimaryKey(String optid);

    int insert(OptInfo record);

    int insertSelective(OptInfo record);

    List<OptInfo> selectByExample(OptInfoExample example);

    OptInfo selectByPrimaryKey(String optid);

    int updateByExampleSelective(@Param("record") OptInfo record, @Param("example") OptInfoExample example);

    int updateByExample(@Param("record") OptInfo record, @Param("example") OptInfoExample example);

    int updateByPrimaryKeySelective(OptInfo record);

    int updateByPrimaryKey(OptInfo record);
}