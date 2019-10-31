package com.qigan.qiganshop.service.synchronization.impl;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.pojo.synchronization.GuanYiOrder;
import com.qigan.qiganshop.pojo.synchronization.ResultRefund;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.synchronization.SynchGuanYiOrderService;
import com.qigan.qiganshop.util.guanyierp.GetSign;
import com.qigan.qiganshop.util.guanyierp.SendPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wanghao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SynchGuanYiOrderServiceImpl implements SynchGuanYiOrderService {

    @Autowired
    private SendPost post;
    @Autowired
    private GoodsService service;
    @Autowired
    private GetSign getSign;

    @Value("${guanyierpUrl}")
    private String url;
    @Value("${appkey}")
    private String appKey;
    @Value("${sessionkey}")
    private String sessionKey;
    @Value("${secret}")
    private String secret;
    @Value("${tradeAddMethod}")
    private String tradeAddMethod;
    @Value("${refundUpdateMethod}")
    private String refundUpdateMethod;

    @Override
    public boolean add(GuanYiOrder guanYiOrder) {
        String userJson = JSON.toJSONString(guanYiOrder);
        Map hashMap = JSON.parseObject(userJson);

        hashMap.put("appkey", appKey);
        hashMap.put("sessionkey", sessionKey);
        hashMap.put("method", tradeAddMethod);
        /**
         * 将请求参数转换为 json 格式的字符串,进行加密处理,获取授权
         */
        String json = JSON.toJSONString(hashMap);
        /**
         * 获取授权
         */
        String sign = getSign.sign(json, secret);
        hashMap.put("sign", sign);
        String data = JSON.toJSONString(hashMap);
        String result = post.sendPost(url, data);
        Map resultMap = JSON.parseObject(result);
        System.err.println(resultMap);
        return (Boolean) resultMap.get("success");
    }
    
    public static void main(String[] args) {
    	Object a = null;
    	boolean s = (Boolean) a;
		System.err.println(s);
		
	}

    /**
     * 发送退款
     *
     * @param vipCode 会员
     * @return
     */
    @Override
    public boolean refundAdd(String orderId, String vipCode, Double amount) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("appkey", appKey);
        map.put("sessionkey", sessionKey);
        map.put("method", refundUpdateMethod);
        map.put("tid", orderId);
        map.put("oid", orderId);
        map.put("refund_state", "1");
        /**
         * 将请求参数转换为 json 格式的字符串,进行加密处理,获取授权
         */
        String json = JSON.toJSONString(map);
        /**
         * 获取授权
         */
        String sign = getSign.sign(json, secret);
        map.put("sign", sign);
        String data = JSON.toJSONString(map);
        String result = post.sendPost(url, data);
        System.err.println(result);
        ResultRefund refund = JSON.parseObject(result, ResultRefund.class);
        //return resultGoods;
        return refund.isSuccess();
    }

}
