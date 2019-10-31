/**
 * 
 */
package com.qigan.qiganshop.redis.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author WYY
 *
 */
@Service
public abstract class RedisSubscribeListenerAbstract implements MessageListener{

	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		redisChannelHandle(message, pattern);
	}
	
	public abstract void redisChannelHandle(Message message, byte[] pattern);

}
