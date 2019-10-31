package com.qigan.qiganshop.util.result.format;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 通用返回实体
 *
 * @author wanghao
 */
@Component
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JsonResult implements Serializable {


    private String res_code;
    private String message;
    private String isVip="0";
    private String total;
    private Object data;

    public JsonResult(String res_code, String message, String total, Object data) {
        this.res_code = res_code;
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private JsonResult() {
    }

    public JsonResult(String res_code, String message, Object data) {
        this.res_code = res_code;
        this.message = message;
        this.data = data;
    }

    public JsonResult(String res_code, String message) {
        this.res_code = res_code;
        this.message = message;
    }

    public JsonResult(Object data) {
        this.res_code = ResultCode.SUCCESS.res_code();
        this.message = ResultCode.SUCCESS.message();
        this.data = data;
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public JsonResult jsonResultData(String res_code) {
        this.setRes_code(res_code);
        if (ResultCode.AUTH.res_code().equals(res_code)) {
            this.setMessage(ResultCode.AUTH.message());
        } else if (ResultCode.SUCCESS.res_code().equals(res_code)) {
            this.setMessage(ResultCode.SUCCESS.message());
        } else if (ResultCode.FAIL.res_code().equals(res_code)) {
            this.setMessage(ResultCode.FAIL.message());
        } else if (ResultCode.ERROR.res_code().equals(res_code)) {
            this.setMessage(ResultCode.ERROR.message());
        }
        this.setData(map);
        return this;
    }

    public JsonResult jsonResultData(String res_code, String message) {
        this.setRes_code(res_code);
        this.setMessage(message);
        this.setData(map);
        return this;
    }

    public JsonResult jsonResultData(String res_code, String message, Object object) {
        this.setRes_code(res_code);
        this.setMessage(message);
        this.setData(object);
        return this;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("res_code", res_code);
            json.put("message", message);
            json.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }


}
