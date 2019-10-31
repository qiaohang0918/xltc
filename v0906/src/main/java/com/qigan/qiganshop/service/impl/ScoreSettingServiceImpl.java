package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.ScoreSettingMapper;
import com.qigan.qiganshop.pojo.game.ScoreSetting;
import com.qigan.qiganshop.pojo.game.SourceType;
import com.qigan.qiganshop.service.ScoreSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreSettingServiceImpl implements ScoreSettingService {

    @Autowired
    private ScoreSettingMapper scoreSettingMapper;
    @Override
    public void save(SourceType sourceType, double money, int score) throws Exception {
        scoreSettingMapper.update(new ScoreSetting(sourceType,  money,  score));
    }

    @Override
    public List<ScoreSetting> getScoreSettings() {
        return scoreSettingMapper.getScoreSettings();
    }
}
