package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.util.guanyierp.SendGet;
import com.qigan.qiganshop.util.result.PageResult;
import lombok.val;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@SuppressWarnings("all")

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-04-29 17:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class GoodsServiceImplTest {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SendGet get;

    @Test
    public void add() {

       /* String time = "今天:18:00-19:00";

        time.replace("今天:", "");
        System.err.println(time);*/

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, +1);
        // 当前时间
        String tomorrow = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        System.err.println(tomorrow);

    }

    @Test
    public void time() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println(
                format.format(new Date())
        );
    }

    @Test
    public void notNull() {

        String[] elements = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String s = "";

        for (int i1 = 9; i1 >= 0; i1--) {
            s += elements[new Random().nextInt(36)];
        }
        //IZG7YV5CFN
        String response = get.sendGet("https://jmhd8.com/" + s);
        String err = "\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"initial-scale=1.0, maximum-scale=1.0, user-scalable=no\" />\n" +
                "    <title>错误信息</title>\n" +
                "    <style>\n" +
                "        .wrapper{padding-top:36px;text-align:center}\n" +
                "        .error{\n" +
                "            width:100px;\n" +
                "            height:100px;\n" +
                "            margin:0 auto;\n" +
                "            border-radius:50%;\n" +
                "            background:#CB1201;\n" +
                "            line-height:100px;\n" +
                "            text-align:center;\n" +
                "            color:#ffffff;\n" +
                "            font-size:80px;\n" +
                "        }\n" +
                "        h2{font-size:25px;margin:10px auto;}\n" +
                "        p{font-size:16px;margin:10px auto;}\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"wrapper\">\n" +
                "        <div class=\"error\">!</div>\n" +
                "        <p>&#x62B1;&#x6B49;&#xFF0C;&#x7801;&#x65E0;&#x6548;</p>\n" +
                "    </div>\n" +
                "  \n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
        if (err.equals(response)) {
            System.err.println("err");
        } else {
            System.err.println("ok");
            System.err.println(s);
        }
    }

    @Test
    public void test() {
        String s = get.sendGet("http://ip.taobao.com/service/getIpInfo.php?ip=58.59.18.101");
        System.err.println(s);
    }

    @Test
    public void aaa() {
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=58.59.18.101";
        String cityName = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                System.err.println(strResult);
                /*try {
                    JSONObject jsonResult = JSON.parseObject(strResult);
                    System.out.println(JSON.toJSONString(jsonResult, true));
                    cityName = jsonResult.getString("address");
                    System.out.println(JSON.toJSONString(jsonResult, true));
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(cityName);
    }

    @Test
    public void test1() {
        Goods goods = new Goods();
        goods.setWarehouseId("XCZC001");
        goods.setCategoryCode("4545495cb3044c1cbf71d238bfe55150");
        PageResult pageGoods = goodsService.findPageGoods(goods, null, null);
        System.err.println(pageGoods.getTotal());
    }

}