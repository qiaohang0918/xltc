package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.TbDeposit;
import com.qigan.qiganshop.util.result.XltcResult;

import java.util.List;
import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/8 11:23
 * @Modified By:
 */
public interface XltcDepositService {
    int insert(TbDeposit tbDeposit);

    List<TbDeposit> findByOutTradeNum(String out_trade_no);

    int updateByPrimaryKeySelective(TbDeposit deposit);

    XltcResult findDepositRecordByConditions(Map<String, String> parms);
}
