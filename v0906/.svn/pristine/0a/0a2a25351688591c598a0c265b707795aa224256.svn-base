package com.qigan.qiganshop.util.access;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.controller.frontend.AppCartController;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {

    @Resource
    private JedisPool jedisPool;

    private Jedis getResource(){
        return jedisPool.getResource();
    }



    public Long setToHash(String KEY,String key,String value){
        Jedis jedis = getResource();
        try {
            Long hset = jedis.hset(KEY, key, value);
            return hset;
        }finally {
            jedis.close();
        }
    }

    public  Long setToHashWithJsonStr(String KEY, String concatKey, AppCartController.Params params){
        Jedis jedis = getResource();
        try {
            String paramJsonStr = JSON.toJSONString(params);
            Long hset = jedis.hset(KEY, concatKey, paramJsonStr);
            return hset;
        }finally {
            jedis.close();
        }
    }

    public Set<String> getMembersFromSet(String key){
        Jedis jedis = getResource();
        try {
            Set<String> smembers = jedis.smembers(key);
            return smembers;
        }finally {
            jedis.close();
        }
    }


    public  String getFromHash(String KEY, String concatKey){
        Jedis jedis = getResource();
        try {
            String jsonStr= jedis.hget(KEY, concatKey);
            return jsonStr;
        }finally {
            jedis.close();
        }
    }


    public Set<String> getElementsFromHash(String KEY){
        Jedis jedis = getResource();
        try {
            Set<String> hkeys = jedis.hkeys(KEY);
            return hkeys;
        }finally {
            jedis.close();
        }
    }
    
    public Jedis getConn(){
    	return getResource();
    }

    public Long removeElementFromHash(String KEY,String key){
        Jedis jedis = getResource();
        try {
            Long hdel = jedis.hdel(KEY, key);
            return hdel;
        }finally {
            jedis.close();
        }
    }
   public Long createNo(){
	   Jedis jedis =  getResource();
	   return jedis.incr("orderNoIncr");
   }


    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis =  getResource();
        try {
            jedis.set(key,value);
            return value;
        } finally {
            jedis.close();
        }
    }


    public Long del(String... keys) {
        Jedis jedis =  getResource();
        try {
            Long del = jedis.del(keys);
            return del;
        } finally {
            jedis.close();
        }
    }

    public Long del(String key) {
        Jedis jedis =  getResource();
        try {
            Long del = jedis.del(key);
            return del;
        } finally {
            jedis.close();
        }
    }

    public void set(String key, String value) {
        Jedis jedis = getResource();
        try {
            jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }
    
    public void set(String key, String value, String nx, String ex, Long time) {
    	Jedis jedis = getResource();
    	try {
    		jedis.set(key, value, nx, ex, time);
    	} finally {
    		jedis.close();
    	}
    }

    public void expire(byte[] key, int i) {
        Jedis jedis =  getResource();
        try {
            jedis.expire(key,i);
        } finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis =  getResource();
        try {
           return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public String get(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis =  getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiro_session_prefix) {
        Jedis jedis =  getResource();
        try {
            return jedis.keys((shiro_session_prefix+"*").getBytes());
        } finally {
            jedis.close();
        }
    }


}

