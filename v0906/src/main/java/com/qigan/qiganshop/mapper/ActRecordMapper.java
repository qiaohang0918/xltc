package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.ActRecord;
import com.qigan.qiganshop.pojo.ActRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ActRecordMapper {
    int countByExample(ActRecordExample example);

    int deleteByExample(ActRecordExample example);

    int deleteByPrimaryKey(String actrecordid);

    int insert(ActRecord record);

    int insertSelective(ActRecord record);

    List<ActRecord> selectByExample(ActRecordExample example);

    ActRecord selectByPrimaryKey(String actrecordid);

    int updateByExampleSelective(@Param("record") ActRecord record, @Param("example") ActRecordExample example);

    int updateByExample(@Param("record") ActRecord record, @Param("example") ActRecordExample example);

    int updateByPrimaryKeySelective(ActRecord record);

    int updateByPrimaryKey(ActRecord record);

    @Update("update tb_act_record set endTime= #{endTime} where code in ( ${codes} ) and endTime is null")
    int deleteByCode(@Param("codes") String needUpdatesCodes, @Param("endTime")String endTime);

}