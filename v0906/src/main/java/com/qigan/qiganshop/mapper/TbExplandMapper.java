package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbExpland;
import com.qigan.qiganshop.pojo.TbExplandExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TbExplandMapper {
    int countByExample(TbExplandExample example);

    int deleteByExample(TbExplandExample example);

    int deleteByPrimaryKey(String explanduserid);

    int insert(TbExpland record);

    int insertSelective(TbExpland record);

    List<TbExpland> selectByExample(TbExplandExample example);

    TbExpland selectByPrimaryKey(String explanduserid);

    int updateByExampleSelective(@Param("record") TbExpland record, @Param("example") TbExplandExample example);

    int updateByExample(@Param("record") TbExpland record, @Param("example") TbExplandExample example);

    int updateByPrimaryKeySelective(TbExpland record);

    int updateByPrimaryKey(TbExpland record);

    @Update("update  tb_expland set explandRegisters = explandRegisters + 1 where explandUserId = #{orginUserId} ")
    void addHisRegistersCount(@Param("orginUserId") String orginUserId);

    @Select("select clueUserId from tb_clue_user where userId = #{userId} and clueType = 'qudao' " )
    List<String> findCurrentUsersExplandUserIds(@Param("userId") String searchValue);

    @Select({"select a.phone ,b.* from tb_user a" ,
            "right join tb_expland b " ,
            "on a.userId= b.explandUserId "})
    List<Map> findExplandUsersScore();


    @Select({"select a.phone,b.explandUserId as explanduserid , b.explandregisters as explandregisters  from tb_user a right join " ,
            "(select a.explandUserId,IFNULL(b.explandRegisters,0) as explandRegisters from tb_expland a left join  " ,
            "(select userId , count(0) as explandRegisters from tb_clue_user where 1=1  ${timeCondition} " ,
            "and clueType = 'qudao' group by userId ) b on a.explandUserId = b.userId) b on a.userId =b.explandUserId" ,
            "where 1=1  ${phoneContion}"})
    List<TbExpland> exportExcelFindExplandUsers(@Param("phoneContion") String phoneContion, @Param("timeCondition")String timeCondition);


    @Select({"select IFNULL(count(0),0) from tb_user where userId in ( " ,
            "select clueUserId from tb_clue_user where userId = #{explanduserid} and clueType='qudao' ${timeCondition} ) " ,
            "and orderNum > 0 "})
    int findHisExplandUserWhichHavingOrdered(@Param("explanduserid") String explanduserid , @Param("timeCondition") String timeCondition);
}