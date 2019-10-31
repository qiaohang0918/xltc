package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.game.ScoreSetting;
import com.qigan.qiganshop.pojo.game.SourceType;

import java.util.List;

public interface ScoreSettingService {

    void save(SourceType sourceType, double money, int score) throws Exception;

    List<ScoreSetting> getScoreSettings();

}
