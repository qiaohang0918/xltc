package com.qigan.qiganshop.controller.frontend;

import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@SuppressWarnings("all")
@RestController
@RequestMapping("/app/time")
@Api(tags = {"APP配送时间接口"})
public class TimeController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private AppUserService service;
    @Value("${endTime}")
    private Integer endTime;
    
    

    @RequestMapping("getTime.do")
    @ApiOperation(
            value = "获取配送时间列表",
            notes = "获取配送时间列表,传递 token,判断用户是否为VIP 用户,  \n" +
                    "VIP用户 特权 一小时内送达, 非 VIP 用户两小时内送达  \n" +
                    "测试阶段,VIP token传递 1,非 VIP token传递 2",
            httpMethod = "POST")
    public JsonResult getTime(String token) {
//        AppUser appUserByToken = service.getAppUserByToken(token);

        /**
         * 今天
         */

        Date date = new Date();
        //主要是将格式设置为HH
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Integer hour = Integer.valueOf(formatter.format(date));
        List<String> todaylist = new ArrayList<>();

        List<String> tomorrowlist = new ArrayList<>();


        if ("1".equals("1")) {

            if (hour < 9) {
                for (int i = 9; i < endTime; i++) {
                    todaylist.add(i + ":00-" + (i + 1) + ":00");
                }
            } else if (hour > 8 && hour < endTime) {

                todaylist.add("1小时内");
                for (int i = hour + 1; i < endTime; i++) {
                    todaylist.add(i + ":00-" + (i + 1) + ":00");
                }
            }
            for (int i = 9; i < endTime; i++) {
                tomorrowlist.add(i + ":00-" + (i + 1) + ":00");
            }
        }/* else if ("0".equals(appUserByToken.getMember())) {
            if (hour < 8) {
                for (int i = 8; (i + 2) <= endTime; i++) {
                    todaylist.add(i + ":00-" + (i + 2) + ":00");
                }
            } else if (hour > 7 && hour < endTime - 1) {

                todaylist.add("2小时内");
                for (int i = hour + 1; (i + 2) <= endTime; i++) {
                    todaylist.add(i + ":00-" + (i + 2) + ":00");
                }
            }
            for (int i = 8; (i + 2) <= endTime; i++) {
                tomorrowlist.add(i + ":00-" + (i + 2) + ":00");
            }
        }*/ else {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "token 不符合测试阶段要求", new HashMap<>());
        }

        HashMap<String, List> map = new HashMap<>();
        map.put("today", todaylist);
        map.put("tomorrow", tomorrowlist);
        
        LocalDateTime now = LocalDateTime.now();
        if(now.getHour() == 20 &&  now.getMinute() > 30){
        	map.put("today", new ArrayList<>());
        }
        
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), map);
    }


}
