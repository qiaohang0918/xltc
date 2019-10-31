package com.qigan.qiganshop.util.Redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * Created by 乔航 on 2019/7/8.
 */
@Component
public class RedisUtil implements EmbeddedValueResolverAware , InitializingBean {
    //redis服务地址
    public static  String REDIS_IP = null;
    //jedis配置
    public static JedisPoolConfig jedisPoolConfig=null;
    //jedisPool连接池
    public static  JedisPool jedisPool=null;

    /**
     * 获取jedis链接
     * @return
     */
    public static Jedis  getConnection(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     *关闭jedsi链接
     */
    public static void closeJedisConnection(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }


    /**
     * 初始化配置和连接池
     * @param stringValueResolver
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        if(REDIS_IP == null) {
            REDIS_IP = stringValueResolver.resolveStringValue("${redis.host}");
            System.out.println("RedisUtil ----> StringValueResolver working .....");
        }
    }


    //bean initlization processer!
    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(3000);
        jedisPoolConfig.setMaxIdle(50);
        jedisPool = new JedisPool(jedisPoolConfig,REDIS_IP);
        System.out.println("RedisUtil post processer ---->  JedisPool Initlizationing .....");
    }

    public static String getRedisIp() {
        return REDIS_IP;
    }

    public static void setRedisIp(String redisIp) {
        REDIS_IP = redisIp;
    }

    public static JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public static void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        RedisUtil.jedisPoolConfig = jedisPoolConfig;
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    public static void setJedisPool(JedisPool jedisPool) {
        RedisUtil.jedisPool = jedisPool;
    }
}
