package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class TbClueActivity implements Serializable {
    private String clueActivityId;

    //app展示图片url
    private String pictureUrl;

    private String enable;

    private String createTime;

    private String description;

    private String money;

    private String updateTime;

    //分享模板图片url
    private String templatePicture;

    //商品详情页入口(小图)
    private String smallPicture;

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture;
    }

    public String getTemplatePicture() {
        return templatePicture;
    }

    public void setTemplatePicture(String templatePicture) {
        this.templatePicture = templatePicture;
    }

    private static final long serialVersionUID = 1L;

    public String getClueActivityId() {
        return clueActivityId;
    }

    public void setClueActivityId(String clueActivityId) {
        this.clueActivityId = clueActivityId == null ? null : clueActivityId.trim();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clueActivityId=").append(clueActivityId);
        sb.append(", pictureUrl=").append(pictureUrl);
        sb.append(", enable=").append(enable);
        sb.append(", createTime=").append(createTime);
        sb.append(", description=").append(description);
        sb.append(", money=").append(money);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}