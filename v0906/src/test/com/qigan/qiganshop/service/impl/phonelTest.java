package com.qigan.qiganshop.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.service.AppUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-11 12:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class phonelTest {
    @Autowired
    private AppUserService service;
    private static final Logger logger = LoggerFactory.getLogger(phonelTest.class);
    private static final String formatSpecifier = "^1[3|4|5|7|8|9][0-9]{9}$";
    //正则模板：编译
    private static Pattern fsPattern = Pattern.compile(formatSpecifier);

    @Test
    public void findPage() {
        List<AppUser> page = service.findPage(null, null, null);
        for (AppUser appUser : page) {

            if (!StringUtils.isEmpty(appUser.getPhone())) {
                this.checkPhoneNumberBelongs(appUser.getPhone());
            }
        }
    }

    public Map<String, Object> checkPhoneNumberBelongs(String phoneNumber) {
        logger.info("begin to check phoneNumber={} belongs to  the ground", phoneNumber);
        Map<String, Object> map = new HashMap<>();
        //校验是否为手机号码
        if (phoneNumber.length() != 11 || !fsPattern.matcher(phoneNumber).matches()) {
            return map;
        }

        StringBuffer url = new StringBuffer();
        url.append("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=");
        url.append(phoneNumber);
        url.append("&co=&resource_id=6004&t=1498214330640&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110206964374771341681_1498213853507&_=1498213853531");

        URL openUrl = null;
        URLConnection conn = null;
        BufferedReader br = null;
        String inputLine = null;

        try {
            openUrl = new URL(url.toString());
            conn = openUrl.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            StringBuffer sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }

            List<String> asList = Arrays.asList(String.valueOf(sb).split(","));
            System.err.print("手机号:"+phoneNumber);
            for (String string : asList) {
                if (string.contains("city") || string.contains("prov")|| string.contains("type")) {
                    String[] split = string.replaceAll(" ", "").replaceAll("\"", "").split(":");


                    System.err.print(string);
                    continue;
                }
            }
            System.err.println();

        } catch (Exception e) {
            logger.error("PhoneNumberBelongsUtil类中checkPhoneNumberBelongs方法:param ={}, error：{}", phoneNumber, e);
            return map;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                logger.error("close io exception error message:{}", e);
            }
        }
        return map;
    }
}