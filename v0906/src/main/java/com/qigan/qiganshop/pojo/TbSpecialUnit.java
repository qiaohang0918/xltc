package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class TbSpecialUnit implements Serializable {
    private String unitId;

    private String unitPicture;

    private String unitTitle;

    private String unitSubNotice;

    private String unitOwnerPicture;

    private String unitOwnerNo;

    private String unitOwnerName;

    private String unitLike;

    private String unitStored;

    private String createTime;

    private String updateTime;

    private String enable;

    private String unitDetails;

    private String sort;

    private String topicId;
    private String specialId;
    private String topicName;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getSpecialId() {
        return specialId;
    }

    public void setSpecialId(String specialId) {
        this.specialId = specialId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    private static final long serialVersionUID = 1L;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getUnitPicture() {
        return unitPicture;
    }

    public void setUnitPicture(String unitPicture) {
        this.unitPicture = unitPicture == null ? null : unitPicture.trim();
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle == null ? null : unitTitle.trim();
    }

    public String getUnitSubNotice() {
        return unitSubNotice;
    }

    public void setUnitSubNotice(String unitSubNotice) {
        this.unitSubNotice = unitSubNotice == null ? null : unitSubNotice.trim();
    }

    public String getUnitOwnerPicture() {
        return unitOwnerPicture;
    }

    public void setUnitOwnerPicture(String unitOwnerPicture) {
        this.unitOwnerPicture = unitOwnerPicture == null ? null : unitOwnerPicture.trim();
    }

    public String getUnitOwnerNo() {
        return unitOwnerNo;
    }

    public void setUnitOwnerNo(String unitOwnerNo) {
        this.unitOwnerNo = unitOwnerNo == null ? null : unitOwnerNo.trim();
    }

    public String getUnitOwnerName() {
        return unitOwnerName;
    }

    public void setUnitOwnerName(String unitOwnerName) {
        this.unitOwnerName = unitOwnerName == null ? null : unitOwnerName.trim();
    }

    public String getUnitLike() {
        return unitLike;
    }

    public void setUnitLike(String unitLike) {
        this.unitLike = unitLike == null ? null : unitLike.trim();
    }

    public String getUnitStored() {
        return unitStored;
    }

    public void setUnitStored(String unitStored) {
        this.unitStored = unitStored == null ? null : unitStored.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getUnitDetails() {
        return unitDetails;
    }

    public void setUnitDetails(String unitDetails) {
        this.unitDetails = unitDetails == null ? null : unitDetails.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", unitId=").append(unitId);
        sb.append(", unitPicture=").append(unitPicture);
        sb.append(", unitTitle=").append(unitTitle);
        sb.append(", unitSubNotice=").append(unitSubNotice);
        sb.append(", unitOwnerPicture=").append(unitOwnerPicture);
        sb.append(", unitOwnerNo=").append(unitOwnerNo);
        sb.append(", unitOwnerName=").append(unitOwnerName);
        sb.append(", unitLike=").append(unitLike);
        sb.append(", unitStored=").append(unitStored);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", enable=").append(enable);
        sb.append(", unitDetails=").append(unitDetails);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}