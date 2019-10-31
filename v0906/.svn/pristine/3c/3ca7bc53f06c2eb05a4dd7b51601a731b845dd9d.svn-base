package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.CountMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-27 15:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class OrderMapperTest {
    @Autowired
    private  OrderMapper mapper;

    @Test
    public void findByUserId() {
        List<CountMap> byUserId = mapper.findByUserId("0da8c18a7bd311e9a221441ea146053a");
        for (CountMap countMap : byUserId) {
            System.err.println(countMap.toString());
        }
    }
}