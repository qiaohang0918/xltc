/**
 * 
 */
package com.qigan.qiganshop.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import com.qigan.qiganshop.enums.XLtcChanners;
import com.qigan.qiganshop.redis.event.KeyExpiredListener;

/**
 * @author WYY
 *
 */
@Configuration
public class RedisSubscribeListener {
	
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory){
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new KeyExpiredListener(), new PatternTopic(XLtcChanners.keyexpired.getValue()));
//        container.addMessageListener(new MyChannelTest(), new PatternTopic(myChanner.name()));
        return container;
	}

}
