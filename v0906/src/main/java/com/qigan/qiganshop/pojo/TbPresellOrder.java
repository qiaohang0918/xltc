package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class TbPresellOrder implements Serializable {
    private String presellorderid;

    private String preselltime;

    private String createtime;

    private String ispushed;

    //预售批次
    private String level;

    //本币订单购买的预售商品code
    private String code;

    //购买数量
    private String butCount;

    private static final long serialVersionUID = 1L;

    public String getPresellorderid() {
        return presellorderid;
    }

    public void setPresellorderid(String presellorderid) {
        this.presellorderid = presellorderid == null ? null : presellorderid.trim();
    }

    public String getPreselltime() {
        return preselltime;
    }

    public void setPreselltime(String preselltime) {
        this.preselltime = preselltime == null ? null : preselltime.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getIspushed() {
        return ispushed;
    }

    public void setIspushed(String ispushed) {
        this.ispushed = ispushed == null ? null : ispushed.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getButCount() {
        return butCount;
    }

    public void setButCount(String butCount) {
        this.butCount = butCount == null ? null : butCount.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", presellorderid=").append(presellorderid);
        sb.append(", preselltime=").append(preselltime);
        sb.append(", createtime=").append(createtime);
        sb.append(", ispushed=").append(ispushed);
        sb.append(", level=").append(level);
        sb.append(", code=").append(code);
        sb.append(", butCount=").append(butCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}