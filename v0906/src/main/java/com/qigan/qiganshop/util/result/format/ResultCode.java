package com.qigan.qiganshop.util.result.format;

public enum ResultCode {
	
	AUTH("401","无权限"),

    SUCCESS("200", "操作成功"),

    FAIL("40x", "操作失败"),
    
    FOUND("302", "重定向"),

    ERROR("500","系统错误");
    
    private String res_code;
    private String message;
    private ResultCode(String res_code, String message){
        this.res_code = res_code;
        this.message = message;
    }

    public String res_code() {
        return res_code;
    }

    public String message() {
        return message;
    }



}
