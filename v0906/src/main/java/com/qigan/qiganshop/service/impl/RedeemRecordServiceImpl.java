package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.GameSettingMapper;
import com.qigan.qiganshop.mapper.RedeemRecordMapper;
import com.qigan.qiganshop.pojo.game.RedeemRecord;
import com.qigan.qiganshop.pojo.game.RedeemType;
import com.qigan.qiganshop.pojo.game.Setting;
import com.qigan.qiganshop.service.RedeemRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedeemRecordServiceImpl implements RedeemRecordService {

    @Autowired
    private RedeemRecordMapper redeemRecordMapper;

    @Autowired
    private GameSettingMapper gameSettingMapper;

    @Override
    public void addRedeemRecord(RedeemType redeemType, String userId) {
        final Setting setting = gameSettingMapper.getGameSetting(redeemType);
        if (setting != null) {
            redeemRecordMapper.insert(new RedeemRecord(redeemType, userId, setting.getPoints()));
        } else {
            
        }
    }
}
