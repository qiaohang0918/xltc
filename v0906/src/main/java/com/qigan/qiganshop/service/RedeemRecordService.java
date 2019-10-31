package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.game.RedeemType;

/**
 * @author starlord
 */
public interface RedeemRecordService {

    void addRedeemRecord(RedeemType redeemType, String userId);
}
