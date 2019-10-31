package com.qigan.qiganshop.util.quartz;

import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.constant.TimeFormat;
import com.qigan.qiganshop.myutils.SqlConstructUtils;
import com.qigan.qiganshop.pojo.Order;
import com.qigan.qiganshop.pojo.TbPresellOrder;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/23 16:20
 * @Modified By:
 */
@Component
@EnableScheduling
public class LimitedGoodsCacheCheckTask {

    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private GoodsService goodsService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void checkCacheReailyShioNShipTwo(){
        //缓存中待检查的限购记录
        Set<String> limitedRecords = new HashSet<>();
        Jedis conn = jedisUtil.getConn();
        Map<String, String> map = conn.hgetAll(RedisConstant.LIMITED_CODE_USER);
        if(map!=null && map.size()>0){
            //只检查value = 1 的数据( value = 1 标识未经检测过 )
            Set<Map.Entry<String, String>> set = map.entrySet().stream().filter(x -> {
                return "1".equals(x.getValue());
            }).collect(Collectors.toSet());
            if(set!=null && set.size()>0){
                for (Map.Entry<String, String> entry : set) {
                    limitedRecords.add(entry.getKey());
                }
            }
        }
        Set<String> smembers = conn.smembers(RedisConstant.LIMITED_CODES);
        conn.close();
        if(smembers!=null && smembers.size()>0){
            if(limitedRecords!=null && limitedRecords.size()>0) {
                for (String code : smembers) {
                    //查询商品id
                    String goodsId = goodsService.findGoodsIdByCode(code);
                    //过滤当前商品code的限购记录
                    List<String> collect = limitedRecords.stream().filter(x -> {
                        return x.startsWith(code);
                    }).collect(Collectors.toList());
                    if(!SqlConstructUtils.nullList(collect)){
                        //存在该限购商品的限购记录，校验真实性！
                        List<String> userIds = collect.stream().map(x -> {
                            return x.split(":")[1];
                        }).collect(Collectors.toList());
                        //遍历已存在的用户购买记录，查询真实购买状况
                        for (String userId : userIds) {
                            //查询该用户对于该商品的真实购买记录
                            String havingOrder = goodsService.checkUserLimitedRecord(userId,goodsId);
                            if(!StringNotNull.check(havingOrder)){
                                //不存在真实订单记录，删除缓存了限购
                                jedisUtil.removeElementFromHash(RedisConstant.LIMITED_CODE_USER,code + ":" + userId);
                            }else {
                                //通过检测，修改cache value = 2
                                jedisUtil.setToHash(RedisConstant.LIMITED_CODE_USER,code + ":" + userId,"2");
                            }
                        }
                    }
                }
            }
        }
        jedisUtil.set("CacheChecked",TimeFormat.nomalFormat.format(new Date()));
        System.out.println("检测cache limited recordd , time:" + TimeFormat.nomalFormat.format(new Date()));
    }
}
