package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class TbSpecialConcat implements Serializable {
    private String concatId;

    private String topicId;

    private String unitId;

    private static final long serialVersionUID = 1L;

    public String getConcatId() {
        return concatId;
    }

    public void setConcatId(String concatId) {
        this.concatId = concatId == null ? null : concatId.trim();
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", concatId=").append(concatId);
        sb.append(", topicId=").append(topicId);
        sb.append(", unitId=").append(unitId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}