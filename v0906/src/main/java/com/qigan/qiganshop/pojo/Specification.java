package com.qigan.qiganshop.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品规格表
 *
 * @author wanghao
 */
public class Specification implements Serializable {
    /**
     * 规格 ID
     */
    private String specId;
    /**
     * 规格名称
     */
    private String specName;
    /**
     * 规格详情
     */
    private List<SpecOption> optionList = new ArrayList<SpecOption>();

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }


    public List<SpecOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SpecOption> optionList) {
        this.optionList = optionList;
    }
}
