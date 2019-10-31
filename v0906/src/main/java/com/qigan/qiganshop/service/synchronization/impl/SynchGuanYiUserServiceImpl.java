package com.qigan.qiganshop.service.synchronization.impl;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.pojo.synchronization.GuanYiUser;
import com.qigan.qiganshop.pojo.synchronization.ResultGuanYiUser;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.synchronization.SynchGuanYiUserService;
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

public class SynchGuanYiUserServiceImpl implements SynchGuanYiUserService {
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
    @Value("${userAddMethod}")
    private String userAddMethod;
    @Value("${userGetMethod}")
    private String userGetMethod;


    @Override
    public int add(GuanYiUser guanYiUser) {
        String userJson = JSON.toJSONString(guanYiUser);
        Map hashMap = JSON.parseObject(userJson);

        hashMap.put("appkey", appKey);
        hashMap.put("sessionkey", sessionKey);
        hashMap.put("method", userAddMethod);
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
        System.err.println(data.toString());
        String result = post.sendPost(url, data);
        System.err.println(result);
        return 0;
    }

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public GuanYiUser getGuanYiUser(String userId) {

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("appkey", appKey);
        map.put("sessionkey", sessionKey);
        map.put("method", userGetMethod);
        map.put("code",userId);
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
        ResultGuanYiUser resultGuanYiUser = JSON.parseObject(result, ResultGuanYiUser.class);
        if(resultGuanYiUser.getTotal()!= 1){
            return null;
        }
        GuanYiUser guanYiUser = new GuanYiUser();
        ResultGuanYiUser.VipsBean vipsBean = resultGuanYiUser.getVips().get(0);
        guanYiUser.setCode(vipsBean.getCode());
        guanYiUser.setName(vipsBean.getName());
        return guanYiUser;

    }


}
