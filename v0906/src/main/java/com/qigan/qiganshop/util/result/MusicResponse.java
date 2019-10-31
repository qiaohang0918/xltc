package com.qigan.qiganshop.util.result;

/**
 * 文件上传返回信息的通用类
 *
 * @author haoha
 */
public class MusicResponse {
    private String musicid;
    private String musicname;
    private String musicurl;
    private String status;

    public MusicResponse(String musicid, String musicname, String musicurl, String status) {
        this.musicid = musicid;
        this.musicname = musicname;
        this.musicurl = musicurl;
        this.status = status;
    }

    public MusicResponse() {
    }

    public String getMusicid() {
        return musicid;
    }

    public void setMusicid(String musicid) {
        this.musicid = musicid;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getMusicurl() {
        return musicurl;
    }

    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
