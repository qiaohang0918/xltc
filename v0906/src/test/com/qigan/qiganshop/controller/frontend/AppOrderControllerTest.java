package com.qigan.qiganshop.controller.frontend;

import com.qigan.qiganshop.util.result.format.JsonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-23 15:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")

public class AppOrderControllerTest {

    @Autowired
    private AppOrderController controller;

}