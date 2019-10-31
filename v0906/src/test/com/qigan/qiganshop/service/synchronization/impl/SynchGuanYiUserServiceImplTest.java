package com.qigan.qiganshop.service.synchronization.impl;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.pojo.synchronization.*;
import com.qigan.qiganshop.service.synchronization.SynchGuanYiUserService;
import com.qigan.qiganshop.util.guanyierp.GetSign;
import com.qigan.qiganshop.util.guanyierp.SendPost;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-02 18:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class SynchGuanYiUserServiceImplTest {
    @Autowired
    private SynchGuanYiUserService service;
    @Value("${appkey}")
    private String appKey;
    @Value("${sessionkey}")
    private String sessionKey;
    @Value("${secret}")
    private String secret;
    @Value("${tradeAddMethod}")
    private String tradeAddMethod;
    @Autowired
    private GetSign getSign;
    @Autowired
    private SendPost post;
    @Value("${guanyierpUrl}")
    private String url;

    @Test
    public void getGuanYiUser() {

        System.err.println(service.getGuanYiUser("123").toString());
    }

    @Test
    public void addGuanYiUser() {

        GuanYiUser guanYiUser = new GuanYiUser();
        guanYiUser.setCode("45645");
        guanYiUser.setName("123");
        guanYiUser.setShop_code("0001");
        GuanYiUser.Address address = new GuanYiUser.Address();
        address.setName("111");
        address.setReceiver("张三");
        address.setTelephone("18888888888");
        address.setAddress("哈哈哈哈");
        List<GuanYiUser.Address> addresses = new ArrayList<>();
        addresses.add(address);
        guanYiUser.setReceive_infos(addresses);
        service.add(guanYiUser);
    }

    @Test
    public void newTestOrder() {
        GuanYiOrder guanYiOrder = new GuanYiOrder();
        guanYiOrder.setShop_code("0001");
        guanYiOrder.setVip_code("123");
        guanYiOrder.setWarehouse_code("0001");
        guanYiOrder.setExpress_code("YTO");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        guanYiOrder.setDeal_datetime(format.format(new Date()));
        guanYiOrder.setOrder_type_code("Sales");
        guanYiOrder.setReceiver_name("爱打篮球唱歌跳舞的蔡徐坤");
        guanYiOrder.setReceiver_phone("18888888888");
        guanYiOrder.setReceiver_province("山东省");
        guanYiOrder.setReceiver_city("济南市");
        guanYiOrder.setReceiver_district("");
        guanYiOrder.setReceiver_address("善信大厦");
        ArrayList<GuanYiItem> guanYiItems = new ArrayList<>();
        GuanYiItem guanYiItem = new GuanYiItem();
        guanYiItem.setItem_code("20000");
        guanYiItem.setOid(UUID.randomUUID().toString().replace("-", ""));
        guanYiItem.setPrice("10");
        guanYiItem.setQty(1900);
        guanYiItems.add(guanYiItem);
        GuanYiItem guanYiItem1 = new GuanYiItem();
        guanYiItem1.setItem_code("20001");
        guanYiItem1.setOid(UUID.randomUUID().toString().replace("-", ""));
        guanYiItem1.setPrice("10");
        guanYiItem1.setQty(1900);
        guanYiItems.add(guanYiItem1);
        guanYiOrder.setDetails(guanYiItems);
        GuanYiInvoice guanYiInvoice = new GuanYiInvoice();
        guanYiInvoice.setInvoice_type(1l);
        guanYiInvoice.setInvoice_title_type(1);
        guanYiInvoice.setInvoice_type_name(2);
        guanYiInvoice.setInvoice_title("测试对接");
        guanYiInvoice.setInvoice_content("一大堆商品");
        guanYiInvoice.setInvoice_amount(0.01);
        ArrayList<GuanYiInvoice> guanYiInvoices = new ArrayList<>();
        guanYiInvoices.add(guanYiInvoice);
        guanYiOrder.setInvoices(guanYiInvoices);
        String userJson = JSON.toJSONString(guanYiOrder);
        HashMap<String, Object> pay = new HashMap<>();
        pay.put("pay_type_code", "zhifubao");//支付方式
        pay.put("payment", "0.01");// 金额
        pay.put("paytime", System.currentTimeMillis());//时间
        pay.put("pay_code", "2019050822001453801044746829");//流水号
        ArrayList arrayList = new ArrayList();
        arrayList.add(pay);
        Map hashMap = JSON.parseObject(userJson);
        hashMap.put("payments", arrayList);
        hashMap.put("appkey", appKey);
        hashMap.put("sessionkey", sessionKey);
        hashMap.put("method", tradeAddMethod);
        hashMap.put("platform_code", System.currentTimeMillis());
        String json = JSON.toJSONString(hashMap);
        /**
         * 获取授权
         */
        String sign = getSign.sign(json, secret);
        hashMap.put("sign", sign);
        String data = JSON.toJSONString(hashMap);
        System.err.println(data.toString());
        String result = post.sendPost(url, data);

        System.err.println(result);
    }

    @Test
    public void getArea() {


        Map hashMap = new HashMap<>();
        hashMap.put("appkey", appKey);
        hashMap.put("sessionkey", sessionKey);
        hashMap.put("method", "gy.erp.trade.refund.update");
        hashMap.put("tid", "201907021562051368092042");
        hashMap.put("oid", "201907021562051368092 042");
        hashMap.put("refund_state", "1");
        //hashMap.put("id","123050682525");
        //hashMap.put("page_size", "100");
        //hashMap.put("shop_code", "0001");

        String json = JSON.toJSONString(hashMap);
        /**
         * 获取授权
         */
        String sign = getSign.sign(json, secret);
        hashMap.put("sign", sign);
        String data = JSON.toJSONString(hashMap);
        //System.err.println(data.toString());
        String result = post.sendPost(url, data);
        System.err.println(result);
    }

    @Test
    public void timeTest() throws Exception{
        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-05-09 17:35:02").getTime();
        System.err.println(time);

    }
}