package com.qigan.qiganshop.util.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qigan.qiganshop.util.guanyierp.SendGet;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-23 09:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class LoggerAspectTest {
    @Autowired
    private SendGet get;

    @Test
    public void test() {
        String ip = "0.0.0.0";
        String path = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        String inputline = "";
        String info = "";
        try {
            URL url;
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10 * 1000);
            conn.setRequestMethod("GET");
            InputStreamReader inStream = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader buffer = new BufferedReader(inStream);
            while ((inputline = buffer.readLine()) != null) {
                info += inputline;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonob = JSONObject.parseObject((JSONObject.parseObject(info).getString("data")));
        String country = StringEscapeUtils.escapeSql(jsonob.getString("country"));
        String city = StringEscapeUtils.escapeSql(jsonob.getString("city"));
        String isp = StringEscapeUtils.escapeSql(jsonob.getString("isp"));
        String region = StringEscapeUtils.escapeSql(jsonob.getString("region"));

        System.err.println(isp+":"+ip+" "+country+" "+region+" "+city);
    }


}