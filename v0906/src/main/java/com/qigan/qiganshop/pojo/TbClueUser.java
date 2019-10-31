package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class TbClueUser implements Serializable {
    private String clueid;

    private String userid;

    private String clueuserid;

    private String registtime;

    private String cluetype;

    private String explandtype;

    private static final long serialVersionUID = 1L;

    public String getClueid() {
        return clueid;
    }

    public void setClueid(String clueid) {
        this.clueid = clueid == null ? null : clueid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getClueuserid() {
        return clueuserid;
    }

    public void setClueuserid(String clueuserid) {
        this.clueuserid = clueuserid == null ? null : clueuserid.trim();
    }

    public String getRegisttime() {
        return registtime;
    }

    public void setRegisttime(String registtime) {
        this.registtime = registtime == null ? null : registtime.trim();
    }

    public String getCluetype() {
        return cluetype;
    }

    public void setCluetype(String cluetype) {
        this.cluetype = cluetype == null ? null : cluetype.trim();
    }

    public String getExplandtype() {
        return explandtype;
    }

    public void setExplandtype(String explandtype) {
        this.explandtype = explandtype == null ? null : explandtype.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clueid=").append(clueid);
        sb.append(", userid=").append(userid);
        sb.append(", clueuserid=").append(clueuserid);
        sb.append(", registtime=").append(registtime);
        sb.append(", cluetype=").append(cluetype);
        sb.append(", explandtype=").append(explandtype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}