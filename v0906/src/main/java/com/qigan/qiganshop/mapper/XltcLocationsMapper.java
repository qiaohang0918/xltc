package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.XltcLocations;
import com.qigan.qiganshop.pojo.XltcLocationsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XltcLocationsMapper {
    int countByExample(XltcLocationsExample example);

    int deleteByExample(XltcLocationsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XltcLocations record);

    int insertSelective(XltcLocations record);

    List<XltcLocations> selectByExample(XltcLocationsExample example);

    XltcLocations selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XltcLocations record, @Param("example") XltcLocationsExample example);

    int updateByExample(@Param("record") XltcLocations record, @Param("example") XltcLocationsExample example);

    int updateByPrimaryKeySelective(XltcLocations record);

    int updateByPrimaryKey(XltcLocations record);
}