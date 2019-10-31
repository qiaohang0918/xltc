/**
 * 
 */
package com.qigan.qiganshop.util.sms;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.pojo.SmsSendRequest;
import com.qigan.qiganshop.pojo.SmsSendResponse;

/**
 * @author wyy
 *
 */
@Component
public class ChuangLanSms {
	
	@Value("${cl.send.url}")
    private String sendUrl;

    @Value("${cl.code.name}")
    private String codeName;
    
    @Value("${cl.code.password}")
    private String codePassword;
    
    @Value("${cl.inform.name}")
    private String infoName;
    
    @Value("${cl.inform.password}")
    private String infoPassword;
    
    @Value("${cl.register}")
    private String register;
    
    @Value("${cl.retrieve}")
    private String retrieve;
    
    @Value("${cl.login}")
    private String login;
    
    @Value("${cl.inform.deliveryer}")
    private String deliveryer;
    
    private static final String REPORT = "true";
    
	public boolean sendCode(String phone, String code, String template){
		String message = "";
		switch (template) {
		case "register":
			message = String.format(register, code);
			break;
		case "retrieve":
			message = String.format(retrieve, code);
			break;
		case "login":
			message = String.format(login, code);
			break;
		default:
			break;
		}
		if(StringUtils.isBlank(message))
			throw XltcRuntimeException.throwable("短信类型不存在");
		return this.sendMessage(phone, message, infoName, infoPassword);
	}

	public boolean sendDeliveryer(String phone, String order, String deliveryerName, String dphone) {
		String message = String.format(deliveryer, order, deliveryerName, dphone);
		if(StringUtils.isBlank(message))
			throw XltcRuntimeException.throwable("短信类型不存在");
		return this.sendMessage(phone, message, infoName, infoPassword);
	}
	
	private boolean sendMessage(String phone, String message, String account, String password) {
		SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, message, phone, REPORT);
		String requestJson = JSON.toJSONString(smsSingleRequest);
		String response = ChuangLanSmsUtil.sendSmsByPost(sendUrl, requestJson);
		if(StringUtils.isBlank(response))
			throw XltcRuntimeException.throwable("短信发送失败");
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		if(smsSingleResponse == null)
			throw XltcRuntimeException.throwable("短信发送失败");
		String errorMsg = smsSingleResponse.getErrorMsg();
		return StringUtils.isBlank(errorMsg);
	}
	
}
