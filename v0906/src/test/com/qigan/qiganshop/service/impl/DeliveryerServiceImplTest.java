package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.pojo.Deliveryer;
import com.qigan.qiganshop.service.DeliveryerService;
import com.qigan.qiganshop.util.result.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-03 16:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class DeliveryerServiceImplTest {
    @Autowired
    private DeliveryerService service;

    @Test
    public void add() {
        Deliveryer deliveryer = new Deliveryer();
        deliveryer.setName("李四");
        deliveryer.setPhoneNum("18888888888");
        deliveryer.setPasswd("123");
        service.add(deliveryer);
    }

    @Test
    public void update() {
        Deliveryer deliveryer = new Deliveryer();
        deliveryer.setDeliveryerId(10000001);
        deliveryer.setName("张三1");
        deliveryer.setPasswd("123");

        service.update(deliveryer);
    }

    @Test
    public void findOne() {
        Deliveryer one = service.findOne(10000001);
        System.err.println(one.getPhoneNum());
    }

    @Test
    public void findPage() {
        PageResult page = service.findPage(null, 1, 1);
        System.err.println(page.getTotal());
        System.err.println(page.getRows().size());
    }
}