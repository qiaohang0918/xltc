package com.qigan.qiganshop.service;

public interface XltcOptInfoService {
    /**
     * 记录管理员操作日志
     * @param no
     * @param name
     * @return
     */
    int recordOptInfo(String no,String name,String inter, String Func);
}
