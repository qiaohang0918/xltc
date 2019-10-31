package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.GameSettingMapper;
import com.qigan.qiganshop.pojo.game.RedeemType;
import com.qigan.qiganshop.pojo.game.Setting;
import com.qigan.qiganshop.service.GameSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSettingServiceImpl implements GameSettingService {

    @Autowired
    private GameSettingMapper gameSettingMapper;

    @Override
    public void save(RedeemType redeemType, String name, String describe, int points) throws Exception {
        int result = -1;
        try {
            result = gameSettingMapper.update(new Setting(redeemType, name, describe, points));
        } catch (Exception e) {
            throw new Exception("操作失败");
        } finally {
            if (result != 1) {
                throw new Exception("操作失败");
            }
        }
    }

    @Override
    public List<Setting> getSettings() {

        return gameSettingMapper.getGameSettings();
    }
}
