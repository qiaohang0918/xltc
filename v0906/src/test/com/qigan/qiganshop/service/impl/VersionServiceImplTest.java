package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.pojo.Version;
import com.qigan.qiganshop.service.VersionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-25 17:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class VersionServiceImplTest {

    @Autowired
    private VersionService service;

    @Test
    public void add() {
        service.add("V1.0", "http://58.59.18.101:81/hahha2", "1");
    }

    @Test
    public void get() {
        List<Version> versions = service.get("1");
        System.err.println(versions.size());
    }

    @Test
    public void getMax() {
        Version max = service.getMax("1");
        System.err.println(max.getApkUrl());
    }
}