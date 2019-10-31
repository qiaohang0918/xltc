package com.qigan.qiganshop.util.baidumap;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.pojo.ClueUserInfo;
import com.qigan.qiganshop.pojo.baidumap.BaiduMapResult;
import com.qigan.qiganshop.pojo.synchronization.ResultGoods;
import com.qigan.qiganshop.util.guanyierp.SendGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-04-23 16:48
 */
@Service
public class BaiDuMapService {
    /**
     * 根据经纬度获取城市名称,发送 get 请求,转成 json,获取城市名并返回
     */
    @Autowired
    private SendGet get;
    final static String begin = "http://api.map.baidu.com/geocoder/v2/?location=";
    final static String end = "&output=json&pois=1&latest_admin=1&ak=FdjYcvL4QfOLbbbvA9gsGBZtaN0mGeKC";

    public String getCity(String coordinate) {
        String[] split = coordinate.split(",");
        if(split.length!= 2){
            return null;

        }
        //经度
        String lng = split[0];
        //纬度
        String lat = split[1];
        String url = begin + lat + "," + lng + end;
        String s = get.sendGet(url);
        BaiduMapResult mapResult = JSON.parseObject(s, BaiduMapResult.class);
        return mapResult.getResult().getAddressComponent().getCity();
    }
    public String getDistrict(String coordinate) {
        String[] split = coordinate.split(",");
        //经度
        String lng = split[0];
        //纬度
        String lat = split[1];
        String url = begin + lat + "," + lng + end;
        String s = get.sendGet(url);
        BaiduMapResult mapResult = JSON.parseObject(s, BaiduMapResult.class);
        return mapResult.getResult().getAddressComponent().getDistrict();
    }


}
