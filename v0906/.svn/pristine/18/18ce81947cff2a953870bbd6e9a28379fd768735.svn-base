/**
 * 
 */
package com.qigan.qiganshop.ding;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.qigan.qiganshop.utils.json.JsonUtils;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class XltcSendDingMessageImpl implements XltcSendDingMessage{
	
	private RestTemplate restTemplate;
	
	public XltcSendDingMessageImpl(){
		restTemplate = new RestTemplate();
	}

	@Override
	public void send() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(Object object) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		Map<String, Object> param = new HashMap<>();
		param.put("msgtype", "text");
		Map<String, Object> text = new HashMap<>();
		text.put("content", object);
		param.put("text", text);
		HttpEntity<String> requestEntity = new HttpEntity<String>(JsonUtils.writeValueAsString(param), headers);
		ResponseEntity<String> s =  restTemplate.postForEntity("https://oapi.dingtalk.com/robot/send?access_token=dc7b336036ccc68970957085d12c190576022e28197ccd112668391a73712502", requestEntity, String.class);
		System.err.println(s.getBody());
	}
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		Map<String, Object> param = new HashMap<>();
		param.put("msgtype", "text");
		Map<String, String> text = new HashMap<>();
		text.put("content", "我只是一个测试2");
		Map<String, Object> ats = new HashMap<>();
		String[] strArray = new String[]{"15562255322"};
		ats.put("atMobiles", strArray);
		param.put("text", text);
		param.put("at", ats);
		HttpEntity<String> requestEntity = new HttpEntity<String>(JsonUtils.writeValueAsString(param), headers);
		ResponseEntity<String> s =  restTemplate.postForEntity("https://oapi.dingtalk.com/robot/send?access_token=dc7b336036ccc68970957085d12c190576022e28197ccd112668391a73712502", requestEntity, String.class);
		System.err.println(s.getBody());
	}

}
