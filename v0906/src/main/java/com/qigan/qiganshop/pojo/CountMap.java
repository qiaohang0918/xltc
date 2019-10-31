package com.qigan.qiganshop.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-27 15:28
 */
@ToString
public class CountMap {
       
       
    private String userId;
       
       
    private String status;
       
       
    private Integer number;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
