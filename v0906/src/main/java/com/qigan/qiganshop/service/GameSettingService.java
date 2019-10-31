package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.game.RedeemType;
import com.qigan.qiganshop.pojo.game.Setting;

import java.util.List;

public interface GameSettingService {

    void save(RedeemType redeemType, String name, String describe, int points) throws Exception;

    List<Setting> getSettings();
}
