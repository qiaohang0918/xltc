package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class TbActivityMain implements Serializable {
    private String activityId;

    private String activityName;

    private String mainPicture;

    private String activityStatus;

    private String actactivitySort;

    private String createTime;

    private String activitySkipUrl;

    private String topPicture;

    private String bottomPicture;

    private String smallPicture;

    private String background;

    private static final long serialVersionUID = 1L;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture == null ? null : mainPicture.trim();
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus == null ? null : activityStatus.trim();
    }

    public String getActactivitySort() {
        return actactivitySort;
    }

    public void setActactivitySort(String actactivitySort) {
        this.actactivitySort = actactivitySort == null ? null : actactivitySort.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getActivitySkipUrl() {
        return activitySkipUrl;
    }

    public void setActivitySkipUrl(String activitySkipUrl) {
        this.activitySkipUrl = activitySkipUrl == null ? null : activitySkipUrl.trim();
    }

    public String getTopPicture() {
        return topPicture;
    }

    public void setTopPicture(String topPicture) {
        this.topPicture = topPicture == null ? null : topPicture.trim();
    }

    public String getBottomPicture() {
        return bottomPicture;
    }

    public void setBottomPicture(String bottomPicture) {
        this.bottomPicture = bottomPicture == null ? null : bottomPicture.trim();
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture == null ? null : smallPicture.trim();
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background == null ? null : background.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityId=").append(activityId);
        sb.append(", activityName=").append(activityName);
        sb.append(", mainPicture=").append(mainPicture);
        sb.append(", activityStatus=").append(activityStatus);
        sb.append(", actactivitySort=").append(actactivitySort);
        sb.append(", createTime=").append(createTime);
        sb.append(", activitySkipUrl=").append(activitySkipUrl);
        sb.append(", topPicture=").append(topPicture);
        sb.append(", bottomPicture=").append(bottomPicture);
        sb.append(", smallPicture=").append(smallPicture);
        sb.append(", background=").append(background);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}