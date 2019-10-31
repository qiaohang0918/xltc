package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Pay;

public interface PayMapper {

    int insertPay(Pay pay);

    Pay selectByOutpaycode(String out_trade_no);

    Pay selectByOrderId(String orderId);
}
