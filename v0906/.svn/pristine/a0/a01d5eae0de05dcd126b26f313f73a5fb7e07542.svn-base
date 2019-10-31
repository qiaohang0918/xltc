package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.VersionMapper;
import com.qigan.qiganshop.pojo.Version;
import com.qigan.qiganshop.service.VersionService;
import com.qigan.qiganshop.util.picture.PicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-25 17:08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VersionServiceImpl implements VersionService {

    @Autowired
    private VersionMapper mapper;
    @Autowired
    private PicUtil picUtil;

    /**
     * 添加版本
     *
     * @param versionCode
     * @param appUrl
     * @return
     */
    @Override
    public Integer add(String versionCode, String appUrl, String type) {
        Version version = new Version();
        version.setVersionCode(versionCode);
        version.setApkUrl(picUtil.deleteUrlHead(appUrl));
        version.setTypeCode(type);
        version.setVersionId(UUID.randomUUID().toString());
        return mapper.add(version);
    }

    /**
     * 根据类型查询包列表
     *
     * @param type
     * @return
     */
    @Override
    public List<Version> get(String type) {
        List<Version> versions = mapper.get(type);
        for (Version version : versions) {
            version.setApkUrl(picUtil.addOneUrlHead(version.getApkUrl()));
        }
        return versions;
    }

    /**
     * 获取最新的安装包信息
     *
     * @param type
     * @return
     */
    @Override
    public Version getMax(String type) {
        Version max = mapper.getMax(type);
        if (max != null) {
            max.setApkUrl(picUtil.addOneUrlHead(max.getApkUrl()).replace(",", ""));
        }
        return max;
    }
}
