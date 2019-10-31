package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Version;

import java.util.List;

/**
 * 版本服务层
 */
public interface VersionService {

    /**
     * 添加版本
     *
     * @param versionCode
     * @param appUrl
     * @return
     */
    Integer add(String versionCode, String appUrl,String type);

    /**
     * 根据类型查询包列表
     *
     * @param type
     * @return
     */
    List<Version> get(String type);

    /**
     * 获取最新的安装包信息
     *
     * @param type
     * @return
     */
    Version getMax(String type);
}
