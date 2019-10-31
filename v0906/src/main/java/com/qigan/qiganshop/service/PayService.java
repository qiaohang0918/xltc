package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Pay;

public interface PayService {

    int insertPay(Pay pay);

    Pay selectByOutpaycode(String out_trade_no);

    Pay selectByOrderId(String orderId);

    void updateMember(String token) throws Exception;

    void updateBalance(String token,Double total);
}
