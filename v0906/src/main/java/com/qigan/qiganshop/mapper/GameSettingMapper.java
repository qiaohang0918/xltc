package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.game.RedeemType;
import com.qigan.qiganshop.pojo.game.Setting;

import java.util.List;

/**
 * @author starlord
 */
public interface GameSettingMapper {

    int update(Setting setting);

    List<Setting> getGameSettings();

    Setting getGameSetting(RedeemType redeemType);
}
