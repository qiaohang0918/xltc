package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbDeposit;
import com.qigan.qiganshop.pojo.TbDepositExample;
import java.util.List;
import java.util.Map;

import com.qigan.qiganshop.util.result.XltcResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbDepositMapper {
    int countByExample(TbDepositExample example);

    int deleteByExample(TbDepositExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(TbDeposit record);

    int insertSelective(TbDeposit record);

    List<TbDeposit> selectByExample(TbDepositExample example);

    TbDeposit selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TbDeposit record, @Param("example") TbDepositExample example);

    int updateByExample(@Param("record") TbDeposit record, @Param("example") TbDepositExample example);

    int updateByPrimaryKeySelective(TbDeposit record);

    int updateByPrimaryKey(TbDeposit record);

    @Select({"select * from tb_deposit where 1=1 ${conditions}" ,
            ""})
    List<Map<String,Object>>   findDepositRecordByConditions(@Param("conditions") String conditions);
}