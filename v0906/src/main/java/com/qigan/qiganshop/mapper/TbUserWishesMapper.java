package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbUserWishes;
import com.qigan.qiganshop.pojo.TbUserWishesExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbUserWishesMapper {
    int countByExample(TbUserWishesExample example);

    int deleteByExample(TbUserWishesExample example);

    int deleteByPrimaryKey(String wishesid);

    int insert(TbUserWishes record);

    int insertSelective(TbUserWishes record);

    List<TbUserWishes> selectByExample(TbUserWishesExample example);

    TbUserWishes selectByPrimaryKey(String wishesid);

    int updateByExampleSelective(@Param("record") TbUserWishes record, @Param("example") TbUserWishesExample example);

    int updateByExample(@Param("record") TbUserWishes record, @Param("example") TbUserWishesExample example);

    int updateByPrimaryKeySelective(TbUserWishes record);

    int updateByPrimaryKey(TbUserWishes record);

    List<Map> selectByExampleWith_Name_Phone(TbUserWishesExample example);
}