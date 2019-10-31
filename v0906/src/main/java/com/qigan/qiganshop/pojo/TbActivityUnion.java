package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class TbActivityUnion implements Serializable {
    private String unionId;

    private String activityId;

    private String unionSort;

    private String unionName;

    private String unionPicture;

    private String unionSkipUrl;

    private static final long serialVersionUID = 1L;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getUnionSort() {
        return unionSort;
    }

    public void setUnionSort(String unionSort) {
        this.unionSort = unionSort == null ? null : unionSort.trim();
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName == null ? null : unionName.trim();
    }

    public String getUnionPicture() {
        return unionPicture;
    }

    public void setUnionPicture(String unionPicture) {
        this.unionPicture = unionPicture == null ? null : unionPicture.trim();
    }

    public String getUnionSkipUrl() {
        return unionSkipUrl;
    }

    public void setUnionSkipUrl(String unionSkipUrl) {
        this.unionSkipUrl = unionSkipUrl == null ? null : unionSkipUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", unionId=").append(unionId);
        sb.append(", activityId=").append(activityId);
        sb.append(", unionSort=").append(unionSort);
        sb.append(", unionName=").append(unionName);
        sb.append(", unionPicture=").append(unionPicture);
        sb.append(", unionSkipUrl=").append(unionSkipUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}