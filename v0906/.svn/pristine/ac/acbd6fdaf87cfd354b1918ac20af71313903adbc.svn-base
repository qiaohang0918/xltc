package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.service.RefundService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-07-01 20:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class RefundServiceImplTest {

    @Autowired
    private RefundService refundService;

    @Test
    public void refund() {
        refundService.wxRefund("20190724110657r17Y4", "3.60");
    }

    @Test
    public void Alirefund() {
        refundService.aliRefund("20190703185206Q31S7", "0.02");
    }


}