package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.pojo.HomepageCateGoods;
import com.qigan.qiganshop.service.HomePageCateGoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-24 17:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class HomePageCateGoodServiceImplTest {
    @Autowired
    private HomePageCateGoodService service;

    @Test
    public void add() {
        String[] Arr = {"1", "2", "3", "4", "6"};
        service.add("12345", Arr);
    }

    @Test
    public void findByCateId() {
        List<HomepageCateGoods> byCateId = service.findByCateId("12345");
        System.err.println(byCateId.size());
    }

    @Test
    public void delete() {
        String[] Arr = {"1", "2", "3", "4", "6","5"};
        service.delete("12345", Arr);
    }

    @Test
    public void updateSort() {
        Integer integer = service.updateSort("12345", "1", "2");
        System.err.println(integer);

    }

    @Test
    public void findOne() {

    }

    @Test
    public void update() {

    }
}