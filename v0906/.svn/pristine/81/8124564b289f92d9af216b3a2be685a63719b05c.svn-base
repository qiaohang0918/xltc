package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.service.AppUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-11 12:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class AppUserServiceImplTest {
    @Autowired
    private AppUserService service;

    @Test
    public void findPage() {
        String phone = "1355555555";
        for (int i = 0; i < 6; i++) {AppUser appUser = new AppUser();
            appUser.setUserId(UUID.randomUUID().toString().replace("-",""));
            appUser.setPhone(phone+(i+1));
            appUser.setPassword(DigestUtils.md5Hex("123456"));
            appUser.setPaypassword(DigestUtils.md5Hex("123456"));
            service.addAppUser(appUser);

        }


    }
}