package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.InfoMapper;
import com.qigan.qiganshop.pojo.Info;
import com.qigan.qiganshop.service.InfoService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-07-13 16:49
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoMapper mapper;

    @Override
    public Integer add(Info info) {
        info.setInfoId(System.currentTimeMillis() + RandomStringUtils.random(3, false, true));
        return mapper.add(info);
    }
    
    @Override
    public List<Info> findPage(String orderId) {
        return mapper.findPage(orderId);
    }
}


