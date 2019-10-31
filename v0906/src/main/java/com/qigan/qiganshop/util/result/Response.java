package com.qigan.qiganshop.util.result;

/**
 * 文件上传返回信息的通用类
 *
 * @author haoha
 */
public class Response {
    private String name;
    private String url;
    private String status;

    public Response(String name, String url, String status) {
        this.name = name;
        this.url = url;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}