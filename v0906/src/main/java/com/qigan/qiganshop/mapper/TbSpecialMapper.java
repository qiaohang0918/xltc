package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbSpecial;
import com.qigan.qiganshop.pojo.TbSpecialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface TbSpecialMapper {
    int countByExample(TbSpecialExample example);

    int deleteByExample(TbSpecialExample example);

    int deleteByPrimaryKey(String specialId);

    int insert(TbSpecial record);

    int insertSelective(TbSpecial record);

    List<TbSpecial> selectByExample(TbSpecialExample example);

    TbSpecial selectByPrimaryKey(String specialId);

    int updateByExampleSelective(@Param("record") TbSpecial record, @Param("example") TbSpecialExample example);

    int updateByExample(@Param("record") TbSpecial record, @Param("example") TbSpecialExample example);

    int updateByPrimaryKeySelective(TbSpecial record);

    int updateByPrimaryKey(TbSpecial record);

    @Update("update tb_special set is_hot = #{isHot} ")
    int changeAllSpecialsIsHot(@Param("isHot") String isHot);
}