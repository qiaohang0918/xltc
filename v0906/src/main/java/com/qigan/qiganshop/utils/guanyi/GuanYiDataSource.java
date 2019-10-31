/**
 * 
 */
package com.qigan.qiganshop.utils.guanyi;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.util.guanyierp.GetSign;
import com.qigan.qiganshop.util.guanyierp.SendPost;
import com.qigan.qiganshop.utils.json.JsonUtils;

/**
 * @author wyy
 *
 */
@Component
public class GuanYiDataSource {

	private static final int DEF_PAGE = 1;

	private static final int DEF_SIZE = 10;

	@Autowired
	private SendPost post;

	@Autowired
	private GetSign getSign;

	@Value("${guanyierpUrl}")
	private String url;

	@Value("${appkey}")
	private String appkey;

	@Value("${sessionkey}")
	private String sessionkey;

	@Value("${secret}")
	private String secret;

	private enum GuanYi {
		items("code"), warehouse(""), stock("item_code");

		private String value;

		private GuanYi(String value) {
			this.value = value;
		}

		private String getValue() {
			return value;
		}

		public static LinkedHashMap<String, Object> caseParam(String key, String value) {
			if (StringUtils.isBlank(key) && StringUtils.isBlank(value))
				return null;
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			for (GuanYi guanyi : GuanYi.values()) {
				if (key.indexOf(guanyi.name()) != -1) {
					map.put(guanyi.getValue(), value);
					break;
				}
			}
			return map;

		}
	}

	public <T> T get(String methodUrl, Integer page, Integer size, String code, Class<T> classz, Map<String, Object> param) {
		page = page == null ? DEF_PAGE : page;
		size = size == null ? DEF_SIZE : size;
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("appkey", appkey);
		map.put("sessionkey", sessionkey);
		map.put("method", methodUrl);
		map.put("page_no", page);
		map.put("page_size", size);
		
		if (StringUtils.isNotBlank(code))
			map.putAll(GuanYi.caseParam(methodUrl, code));
		
		if(param != null) 
			map.putAll(param);
		
		String json = JsonUtils.writeValueAsString(map);
		String sign = getSign.sign(json, secret);
		map.put("sign", sign);
		String data = JsonUtils.writeValueAsString(map);
		String result = post.sendPost(url, data);
		return JSON.parseObject(result, classz);

	}

	public <T> T get(String methodUrl, String code, Class<T> classz, Map<String, Object> param) {
		return this.get(methodUrl, null, null, code, classz, param);
	}

}
