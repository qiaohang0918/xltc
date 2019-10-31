package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbRefundRecord;
import com.qigan.qiganshop.pojo.TbRefundRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbRefundRecordMapper {
    int countByExample(TbRefundRecordExample example);

    int deleteByExample(TbRefundRecordExample example);

    int deleteByPrimaryKey(String refundrecordid);

    int insert(TbRefundRecord record);

    int insertSelective(TbRefundRecord record);

    List<TbRefundRecord> selectByExample(TbRefundRecordExample example);

    TbRefundRecord selectByPrimaryKey(String refundrecordid);

    int updateByExampleSelective(@Param("record") TbRefundRecord record, @Param("example") TbRefundRecordExample example);

    int updateByExample(@Param("record") TbRefundRecord record, @Param("example") TbRefundRecordExample example);

    int updateByPrimaryKeySelective(TbRefundRecord record);

    int updateByPrimaryKey(TbRefundRecord record);

    @Select({"select a.*,b.dealDateTime,b.payTime,c.userId,c.nickName,c.phone,b.payMoney from tb_refund_record a " ,
            "LEFT JOIN tb_order b on a.orderId=b.orderId " ,
            "left join tb_user c on b.userId = c.userId " ,
            "where 1=1 ${conditionsConcat}"
    })
    List<TbRefundRecord> queryBalanceRefundRecord(@Param("conditionsConcat") String conditionsConcat);
}