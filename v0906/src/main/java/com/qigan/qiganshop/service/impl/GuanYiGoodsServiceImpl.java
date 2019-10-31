/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.pojo.synchronization.ResultGoods;
import com.qigan.qiganshop.service.GuanYiGoodsService;
import com.qigan.qiganshop.util.guanyierp.GetSign;
import com.qigan.qiganshop.util.guanyierp.SendPost;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class GuanYiGoodsServiceImpl implements GuanYiGoodsService {

	private static final int DEF_PAGE = 1;

	private static final int DEF_SIZE = 1;

	@Value("${guanyierpUrl}")
	private String url;

	@Value("${appkey}")
	private String appkey;

	@Value("${sessionkey}")
	private String sessionkey;

	@Value("${secret}")
	private String secret;

	@Value("${itemsMethod}")
	private String itemsMethod;

	@Autowired
	private GetSign getSign;
	
	@Autowired
    private SendPost post;

	@Override
	public ResultGoods findGuanYiGoods(String code) {
		// TODO Auto-generated method stub
		return this.findGuanYiGoods(null, null, code);
	}

	@Override
	public ResultGoods findGuanYiGoods(Integer page, Integer size, String code) {
		// TODO Auto-generated method stub
		page = page == null ? DEF_PAGE : page;
		size = size == null ? DEF_SIZE : size;
		return findPageGoods(page, size, code);
	}

	private ResultGoods findPageGoods(Integer page, Integer size, String code) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("appkey", appkey);
		map.put("sessionkey", sessionkey);
		map.put("method", itemsMethod);
		map.put("page_no", page);
		map.put("page_size", size);
		if(StringUtils.isNotBlank(code)) 
			map.put("code", code);
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
		ResultGoods resultGoods = JSON.parseObject(result, ResultGoods.class);
		return resultGoods;

	}

}
