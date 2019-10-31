package com.qigan.qiganshop.util.result;

/**
 * 文件上传返回信息的通用类
 *
 * @author haoha
 */
public class VideoResponse {
    private String videoname;
    private String videourl;
    private String status;

    public VideoResponse(String videoname, String videourl, String status) {

        this.videoname = videoname;
        this.videourl = videourl;
        this.status = status;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
