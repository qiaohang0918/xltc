package com.qigan.qiganshop.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author starlord
 */
public class Feedback implements Serializable {

    private Integer feedbackId;
    private String content;
    private String userId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public Feedback() {
    }

    public Feedback(String content, String userId) {
        this.content = content;
        this.userId = userId;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
