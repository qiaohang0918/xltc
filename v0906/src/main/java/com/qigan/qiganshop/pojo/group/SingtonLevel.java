package com.qigan.qiganshop.pojo.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/10 11:52
 * @Modified By:
 */
public class SingtonLevel implements Serializable {

    private String level;

    private List<LevelPreSellReporter> reporterList = new ArrayList<>();

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<LevelPreSellReporter> getReporterList() {
        return reporterList;
    }

    public void setReporterList(List<LevelPreSellReporter> reporterList) {
        this.reporterList = reporterList;
    }
}
