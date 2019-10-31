package com.qigan.qiganshop.controller.backstage;


import com.qigan.qiganshop.service.XltcOrderService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

/**
 * create by qh
 * 管理未推送订单
 */
@RestController
@RequestMapping("/publisher")
@CrossOrigin
public class XltcPushOderController {

    @Resource
    JedisUtil jedisUtil;

    @Resource
    XltcOrderService orderService;


    /**
     * 获取未推送订单信息
     * @return
     */
    @RequestMapping(value = "/getUnPublishOrders.do",method = RequestMethod.GET)
    public XltcResult getUnPublishOrders(){
        Set<String> elements = jedisUtil.getElementsFromHash("pub_err");
        return XltcResult.ok(elements);
    }

    /**
     * 手动推送订单
     * @param unPublishOrderIds
     * @return
     */
    @RequestMapping(value = "/publishOrdersByPerson.do",method = RequestMethod.POST)
    public  XltcResult  publishOrdersByPerson(@RequestBody Set<String> unPublishOrderIds){
        int succNum=0;
        StringBuffer buffer = new StringBuffer("[ ");
        if(unPublishOrderIds!=null && unPublishOrderIds.size()>0){
            for (String orderId : unPublishOrderIds) {
                String result = orderService.pullToGuanYi(orderId);
                if("ok".equals(result.trim())){
                    buffer.append(orderId).append(",");
                    ++succNum;
                }
            }
        }
        String pushSuccessOrderIds = buffer.replace(buffer.length() - 1, buffer.length(), "").append(" ]").toString();
        return XltcResult.ok("本次推送成功的订单数："+succNum+"，详细信息："+pushSuccessOrderIds);
    }

    /**
     * 自动检测未推送订单并尝试推送
     * @return
     */
    @RequestMapping(value = "/autoPublishOrdersByXXL_JOB.do",method = RequestMethod.POST)
    public  XltcResult autoPublishOrdersByPerson(){
        int succNum=0;
        StringBuffer buffer = new StringBuffer("[ ");
        Object data = this.getUnPublishOrders().getData();
        Set<String> orders = null;
        if(data!=null ){
           orders = (Set<String>)data;
        }
        if(orders!=null && orders.size()>0){
            for (String orderId : orders) {
                String result = orderService.pullToGuanYi(orderId);
                if("ok".equals(result.trim())){
                    buffer.append(orderId).append(",");
                    ++succNum;
                }
            }
        }
        //System.out.println("本次推送成功的订单数："+succNum);
        //return "所有未推送的订单数："+orders.size()+",本次推送成功的订单数："+succNum;
        String pushSuccessOrderIds = buffer.replace(buffer.length() - 1, buffer.length(), "").append(" ]").toString();
        return XltcResult.ok("所有未推送的订单数："+orders.size()+",本次推送成功的订单数："+succNum+",详细信息："+pushSuccessOrderIds);
    }



}
