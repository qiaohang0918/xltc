package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Category;
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
 * @time 2019-06-28 15:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class CategoryServiceTest {

    @Autowired
    private CategoryService service;

    @Test
    public void findAllByCateId() {
        Category category = service.findAllByCateId("4545495cb3044c1cbf71d238bfe55150", "116.865782,36.666315");
        System.err.println(category.getLabelList().get(0).getGoodsList().size());
    }
}