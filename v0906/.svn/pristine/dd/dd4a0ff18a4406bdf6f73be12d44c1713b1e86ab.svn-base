package com.qigan.qiganshop.util.jiguang;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qigan.qiganshop.pojo.JiGuangSendModel;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 
 */

/**
 * @author wyy
 *
 */
@Component
public class JiGuangSend {
	
	@Value("${jiguang.masterSecret}")
	private String masterSecret;

	@Value("${jiguang.appkey}")
	private String appKey;
	
	public PushResult send(JiGuangSendModel model) {
		if(model == null)
			return null;
		Audience audience = StringUtils.isNotBlank(model.getAlias()) ? Audience.alias(model.getAlias()) : Audience.all(); 
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
	    PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(audience)
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(model.getAlert())
                                .addExtras(model.getExtras())
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                        		.setAlert(model.getAlert())
                        		.addExtras(model.getExtras())
                               .build())
                        .build())
                 .setMessage(Message.content(model.getContent()))
                 .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                 .build();

	    try {
	        return jpushClient.sendPush(payload);
	    } catch (Exception e) {
	        // Connection error, should retry later
	    	return new PushResult();
	    }    
	}

}
