package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.pojo.UserCart;
import com.qigan.qiganshop.service.UserCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-10 15:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")

public class UserCartServerImplTest {
    @Autowired
    private UserCartService service;

    @Test
    public void add() {
        UserCart userCart = new UserCart();
        userCart.setCartId("123");
        userCart.setSkuId("123223");
        userCart.setNum(3454352);
        service.add(userCart);
    }

    @Test
    public void delete() {
        service.delete("123","123223");
    }

    @Test
    public void delete1() {
        service.delete("123");
    }

    @Test
    public void update() {
        UserCart userCart = new UserCart();
        userCart.setCartId("123");
        userCart.setSkuId("123223");
        userCart.setNum(1);
       service.update(userCart);
    }

    @Test
    public void findOne() {
        UserCart one = service.findOne("123", "12323");
        System.err.println(one.toString());
    }

    @Test
    public void findByCateId() {
        List<UserCart> byCateId = service.findByCateId("123");
        for (UserCart userCart : byCateId) {
            System.err.println(userCart.toString());
        }
    }
}