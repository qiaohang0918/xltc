package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.constant.TimeFormat;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.myutils.SqlConstructUtils;
import com.qigan.qiganshop.pojo.Order;
import com.qigan.qiganshop.pojo.TbPresellOrder;
import com.qigan.qiganshop.service.XltcOrderService;
import com.qigan.qiganshop.service.XltcPreSellOrderService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/5 17:52
 * @Modified By:
 */
@RestController
@RequestMapping("/preSellOrder")
public class XltcPreSellOrderController {

    @Autowired
    private XltcPreSellOrderService preSellOrderService;
    @Autowired
    private XltcOrderService xltcOrderService;
    @Autowired
    private JedisUtil jedisUtil;
    @Value("${presell.order.push.info.retation.days}")
    private String PRESELL_ORDER_PUSH_INFO_RERETATION_DAYS;

    /**
     * 按条件查询预售订单( 状态 / 预售时间  / 手机号 / .....  )
     * @param parms
     * @return
     */
    @RequestMapping(value = "/findPreSellOrder.do",method = RequestMethod.POST)
    public XltcResult findPreSellOrder(@RequestBody Map<String,String> parms){
        return preSellOrderService.findPreSellOrder(parms);
    }

    /**
     * 查看预售订单详情
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/findPreSellOrderDetails.do",method = RequestMethod.GET)
    public XltcResult findPreSellOrderDetails(@RequestParam String orderId){
        if(!StringNotNull.check(orderId))
            return XltcResult.error("订单id必传！");
        return preSellOrderService.findPreSellOrderDetails(orderId);
    }


    /**
     * 推送订单到管易
     * @param orderIds
     * @return
     */
    @RequestMapping(value = "/pushPreSellOrderToGuanyi.do",method = RequestMethod.POST)
    public XltcResult pushPreSellOrderToGuanyi(@RequestParam List<String> orderIds,String no){
        if(SqlConstructUtils.nullList(orderIds)) {
            return XltcResult.error("请选择要推送的订单id");
        }
        //本次试图推送的订单总数
        int total = orderIds.size();
        //本次成功推送的订单数量
        int successNum = 0;
        //本次推送失败的订单数
        int failNum = 0;
        //失败的订单Id
        List<String> fileList = new ArrayList<>();
        try {
            for (String orderId : orderIds) {
                //查询是否已经推送过了
                TbPresellOrder preSellOrder = preSellOrderService.findByOrderId(orderId);
                if(preSellOrder==null)
                    return XltcResult.error("预售订单：["+orderId+"]不存在!本次成功推送的订单数："+successNum+", 失败的订单数："+failNum+", 未推送的订单数："+(total-successNum-failNum)+", 失败的订单id："+fileList);
                if("1".equals(preSellOrder.getIspushed())) {
                    return XltcResult.error("预售订单：["+orderId+"]已经推送过了！本次成功推送的订单数："+successNum+",失败的订单数："+failNum+", 未推送的订单数："+(total-successNum-failNum)+", 失败的订单id："+fileList);
                }
                //查询订单
                Order order = xltcOrderService.findOne(orderId);
                if(!"1".equals(order.getStatus())){
                    return XltcResult.error("订单：["+orderId+"]不是支付状态(1)！本次成功推送的订单数："+successNum+", 失败的订单数："+failNum+", 未推送的订单数："+(total-successNum-failNum)+", 失败的订单id："+fileList);
                }
                //推送订单
                String result = xltcOrderService.pullToGuanYi(orderId);
                if(!"ok".equalsIgnoreCase(result)){
                    fileList.add(orderId);
                    failNum++;
                }else {
                    //成功，更新订单的isPushed
                    TbPresellOrder sellOrder = new TbPresellOrder();
                    sellOrder.setPresellorderid(orderId);
                    sellOrder.setIspushed("1");
                    preSellOrderService.update(sellOrder);
                    successNum++;
                }
            }
            return  XltcResult.ok("本次成功推送的订单数："+successNum+", 失败的订单数："+failNum+", 未推送的订单数："+(total-successNum-failNum)+", 失败的订单id："+fileList);
        }catch (Exception e){
            throw XltcRuntimeException.throwable("系统繁忙,稍后重试");
        }finally {
            //删除三十天前日志记录
            this.deletePushPersonInfo();
            //插入当前操作记录
            jedisUtil.setToHash(RedisConstant.PRESELL_ORDER_PUSH_PERSON, TimeFormat.nomalFormat.format(new Date()),"no:"+no+"  total:"+total+"  success:"+successNum+"  fail:"+failNum+"  noPush:"+(total-successNum-failNum)+"  fileList:"+fileList.toString());
        }
    }

    /**
     * retaintion thirty days (push info)
     * @return
     */
    public boolean deletePushPersonInfo(){
        long days = 30;
        if(StringNotNull.check(PRESELL_ORDER_PUSH_INFO_RERETATION_DAYS)){
            try {
                days = Long.parseLong(PRESELL_ORDER_PUSH_INFO_RERETATION_DAYS);
            }catch (Exception e){

            }
        }
        Set<String> elements = jedisUtil.getElementsFromHash(RedisConstant.PRESELL_ORDER_PUSH_PERSON);
        if(elements!=null && elements.size()>0){
            LocalDateTime now = LocalDateTime.now();
            for (String element : elements) {
                LocalDateTime parse = LocalDateTime.parse(element, TimeFormat.normalDateTimeFormater);
                if(parse.plusDays(days).isBefore(now)){
                    //存储超过30天，删除
                    jedisUtil.removeElementFromHash(RedisConstant.PRESELL_ORDER_PUSH_PERSON,element);
                }
            }
        }
        return true;
    }

    /**
     * 查询所有批次预售结果报表
     * @return
     */
//    @RequestMapping(value = "/reportEveryLevelData.do",method = RequestMethod.GET)
    public XltcResult reportEveryLevelData(){
         XltcResult xltcResult = preSellOrderService.reportEveryLevelData();
         return  xltcResult;
    }


    /**
     * 查询所有批次及其下商品预售结果报表
     * @return
     */
//    @RequestMapping(value = "/reportEveryLevelsGoodsData.do",method = RequestMethod.GET)
    public XltcResult reportEveryLevelsGoodsData(@RequestParam(value = "page" , defaultValue = "1") int page,
                                                 @RequestParam(value = "size" , defaultValue = "10") int size){
        XltcResult xltcResult = preSellOrderService.reportEveryLevelsGoodsData(page,size);
        return xltcResult;
    }




}
