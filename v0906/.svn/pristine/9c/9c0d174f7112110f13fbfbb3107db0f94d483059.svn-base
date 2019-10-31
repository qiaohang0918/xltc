package com.qigan.qiganshop.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.controller.utils.WxpayController;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.TbRefundRecordMapper;
import com.qigan.qiganshop.myutils.SqlConstructUtils;
import com.qigan.qiganshop.pojo.*;
import com.qigan.qiganshop.service.*;
import com.qigan.qiganshop.service.synchronization.SynchGuanYiOrderService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.pay.PayUtil;
import com.qigan.qiganshop.util.pay.wxpay.Httpsrequest;
import com.qigan.qiganshop.util.pay.wxpay.Refund;
import com.qigan.qiganshop.util.pay.wxpay.Sign;
import com.qigan.qiganshop.util.pay.wxpay.Xstreamutil;
import com.qigan.qiganshop.util.result.XltcResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 退款
 *
 * @author wanghao
 * @time 2019-07-01 10:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RefundServiceImpl implements RefundService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private PayService payService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private StockService stockService;
    @Autowired
    private SynchGuanYiOrderService synchGuanYiOrderService;
    @Autowired
    private WxpayController wxpayController;
    @Autowired
    private TbRefundRecordMapper refundRecordMapper;
    @Autowired
    private XltcPreSellGoodsService preSellGoodsService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private JedisUtil jedisUtil;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 阿里云参数

    @Value("${serverUrl}")
    private String serverUrl;
    @Value("${appId}")
    private String appId;
    @Value("${app_private_key}")
    private String app_private_key;
    @Value("${format}")
    private String format;
    @Value("${charset}")
    private String charset;
    @Value("${alipay_public_key}")
    private String alipay_public_key;
    @Value("${sign_type}")
    private String sign_type;


    // 微信参数

    @Value("${appid}")
    private String appid;
    @Value("${mch_id}")
    private String mch_id;
    @Value("${Key}")
    private String Key;

    /**
     * 退款接口
     * app 用户在商品未出仓之前进行退货
     * <p>
     * 需求分析:
     * 用户在 APP 下单商品,此时订单状态为支付成功状态,状态码 == 1
     * 如果用户发起退款流程,应当首先检测订单状态是否为 1
     * 该订单状态不为 1,直接返回 error,结束方法
     * 如果订单状态符合状态
     * 下一步查询订单的付款信息,并获取付款方式
     * 如果查不到支付信息,直接返回
     * 如果能查到支付信息,且支付方式匹配
     * 直接跳转至对应退款方法
     * <p>
     * 每个退款方式都会调用各自的退款方式和还原库存
     * 接下来调用向管易云发送取消订单的通知
     *
     * @param orderId
     * @param userId
     * @return
     */
    @Override
    public String refund(String orderId, String userId) {

        Order order = orderService.findOne(orderId);
        if (!"1".equals(order.getStatus())
                && !"5".equals(order.getStatus())
                && !"6".equals(order.getStatus())
                && !"7".equals(order.getStatus())) {
            return "订单当前状态不能完成退款";
        }

        Pay pay = payService.selectByOrderId(orderId);
        if (pay != null) {
            boolean flag = this.cancelGuanYiOrder(order.getOrderId(), userId, Double.parseDouble(pay.getPayment()));
            if ("balance".equals(pay.getPayTypeCode())) {
                if (flag) {
                    ResponseEntity responseEntity = this.balanceRefund(order, pay);
                    if (responseEntity.getStatusCode().is2xxSuccessful()) {
                        this.reductionStock(order);
                        return "退款成功!";
                    }
                }
                return "退款失败!";


            } else if ("zhifubao".equals(pay.getPayTypeCode())) {
                if (flag) {
                    ResponseEntity responseEntity = this.aliRefund(pay.getOutPayCode(), pay.getPayment());
                    if (responseEntity.getStatusCode().is2xxSuccessful()) {
                        this.reductionStock(order);
                        return "退款成功!";
                    }
                }
                return "退款失败!";


            } else if ("weixin".equals(pay.getPayTypeCode())) {
                if (flag) {
                    ResponseEntity responseEntity = this.wxRefund(pay.getOutPayCode(), pay.getPayment());
                    if (responseEntity.getStatusCode().is2xxSuccessful()) {
                        this.reductionStock(order);
                        return "退款成功!";
                    }
                }
                return "退款失败!";


            } else {
                return "支付方式不匹配";
            }
        } else {
            return "支付信息不存在";
        }

    }

    @Override
    public String refundButNotCheck(String orderId, String userId){
        Order order = orderService.findOne(orderId);
        if (!"1".equals(order.getStatus())
                && !"5".equals(order.getStatus())
                && !"6".equals(order.getStatus())
                && !"7".equals(order.getStatus())) {
            return "订单当前状态不能完成退款";
        }

        Pay pay = payService.selectByOrderId(orderId);
        if (pay != null) {
            if ("balance".equals(pay.getPayTypeCode())) {
                ResponseEntity responseEntity = this.balanceRefund(order, pay);
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    this.reductionStock(order);
                    return "退款成功!";
                }
                return "退款失败!";

            } else if ("zhifubao".equals(pay.getPayTypeCode())) {
                ResponseEntity responseEntity = this.aliRefund(pay.getOutPayCode(), pay.getPayment());
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    this.reductionStock(order);

                    return "退款成功!";
                }
                return "退款失败!";

            } else if ("weixin".equals(pay.getPayTypeCode())) {
                ResponseEntity responseEntity = this.wxRefund(pay.getOutPayCode(), pay.getPayment());
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    this.reductionStock(order);

                    return "退款成功!";
                }
                return "退款失败!";
            } else {
                return "支付方式不匹配";
            }
        } else {
            return "支付信息不存在";
        }
    }

    /**
     * WEB 退款接口
     * web 操作订单退款
     *
     * @param orderId
     * @return
     */
    @Override
    public String webRefund(String orderId) {
        Order one = orderService.findOne(orderId);
        if (one != null) {
            String refund = this.refund(orderId, one.getUserId());
            Info info = new Info();
            info.setOrderId(orderId);
            info.setUserId(one.getUserId());
            info.setInfo("订单尝试退款:"+refund);
            infoService.add(info);
            return refund;
        }

        return "订单信息不存在";
    }

    /**
     * 余额退款
     * 退还余额需要修改订单状态为已退款
     * 通过订单里面的 userId 获取用户信息
     * 将用户的余额退还为消费前余额 (即当前余额 + 订单实付金额)
     * 余额修改成功,修改库存
     * 余额退还失败,返回前端
     *
     * @param order
     * @param pay
     * @return
     */
    private ResponseEntity balanceRefund(Order order, Pay pay) {
        ResponseEntity responseEntity = reductionStock(order);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            orderService.updateStatus(order.getOrderId(), "9");
            AppUser user = appUserService.getAppUserById(order.getUserId());
            Double userBalance = user.getBalance() == null ? 0 : user.getBalance();
            user.setBalance(userBalance + Double.parseDouble(pay.getPayment()));
            AppUser update = new AppUser();
            update.setUserId(user.getUserId());
            update.setBalance(user.getBalance());
            int i = appUserService.updateAppUser(update);
            if (i > 0) {
                return new ResponseEntity("库存还原成功!", HttpStatus.OK);
            }
        }
        return new ResponseEntity("用户余额退还失败!", HttpStatus.NOT_FOUND);
    }

    /**
     * 还原库存
     * 退款完成,需要将用户购买商品时库存的减少数量再进行还原
     * 获取该订单用户购买的所有商品
     * 并根据订单 商品 ID 和 仓库 ID 查询对应的库存记录
     * 查询到该库存记录,将总数量进行添加,然后可销售数用 总数减掉 锁定数
     * 进行修改
     * 修改订单状态
     *
     * @param order
     * @return
     */
    private ResponseEntity reductionStock(Order order) {
        try {
            List<OrderItem> byOrderId = orderItemService.findByOrderId(order.getOrderId());
            for (OrderItem orderItem : byOrderId) {
                Stock stock = stockService.findByItemIdAndWareHouseId(orderItem.getItemId(), order.getWarehouseCode());
                stock.setStockNum(stock.getStockNum() + orderItem.getCount());
                stock.setSalableNum(stock.getStockNum() - stock.getLockNum());
                stockService.update(stock);
            }
            orderService.updateStatus(order.getOrderId(), "9");
            return new ResponseEntity(new JsonResult("200", "库存还原成功!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new JsonResult("500", "库存还原失败!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 管易取消订单
     *
     * @return
     */
    private boolean cancelGuanYiOrder(String orderId, String vipCode, Double amount) {
        return synchGuanYiOrderService.refundAdd(orderId, vipCode, amount);
    }

    /**
     * 支付宝退款
     *
     * @return
     */
    @Override
    public ResponseEntity aliRefund(String out_trade_no, String refund_amount) {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId,
                app_private_key, format, charset, alipay_public_key, sign_type);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        request.setBizContent("{" +
                " \"out_trade_no\":\"" + out_trade_no + "\"," +
                " \"out_request_no\":\"" + out_trade_no + System.currentTimeMillis() + "\"," +
                " \"refund_amount\":\"" + refund_amount + "\"" +
                "  }");
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("操作成功");
                return new ResponseEntity(new JsonResult(ResultCode.SUCCESS.res_code(),"退款成功"), HttpStatus.OK);
            } else {
                System.out.println("操作失败");
                return new ResponseEntity(new JsonResult(ResultCode.FAIL.res_code(),"退款失败"), HttpStatus.NOT_FOUND);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return new ResponseEntity(new JsonResult(ResultCode.ERROR.res_code()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * out_trade_no 订单号  ，refund_fee 退款金额
     *
     * @param out_trade_no
     * @return
     */
    @Override
    public ResponseEntity wxRefund(String out_trade_no, String amount) {
        try {
            Map orderquery = wxpayController.orderquery(out_trade_no);
            int total_fee = Integer.parseInt(orderquery.get("total_fee").toString());
//            int refund_fee = (int) (Double.parseDouble(amount) * 100);
            
            BigDecimal total = new BigDecimal(amount);
            BigDecimal total_temp = new BigDecimal(100);
            int refund_fee = total.multiply(total_temp).intValue();

            String nonce_str = PayUtil.getRandomString(20);
            String out_refund_no = PayUtil.getRandomString(20);

            SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
            parameters.put("appid", appid);
            parameters.put("mch_id", mch_id);
            parameters.put("nonce_str", nonce_str);
            parameters.put("out_trade_no", out_trade_no);
            parameters.put("out_refund_no", out_refund_no);
            parameters.put("total_fee", total_fee);
            parameters.put("refund_fee", refund_fee);

            String sign = Sign.createSign(parameters, Key);
            Refund pay = new Refund();
            pay.setAppid(appid);
            pay.setMch_id(mch_id);
            pay.setNonce_str(nonce_str);
            pay.setSign(sign);
            pay.setOut_trade_no(out_trade_no);
            pay.setTotal_fee(total_fee);
            pay.setRefund_fee(refund_fee);
            pay.setOut_refund_no(out_refund_no);

            Xstreamutil.xstream.alias("xml", Refund.class);

            String xml = Xstreamutil.xstream.toXML(pay).replaceAll("__", "_");
            System.out.println(xml);
            String requestUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
            String respxml = null;
            String result_code = null;

            respxml = Httpsrequest.doRefund(requestUrl, xml);

            Document doc = DocumentHelper.parseText(respxml);
            Element root = doc.getRootElement();
            List<Element> elementList = root.elements();
            for (int i = 0; i < elementList.size(); i++) {
                if (elementList.get(i).getName().equals("result_code")) {
                    result_code = elementList.get(i).getTextTrim();
                }
            }
            if ("SUCCESS".equals(result_code)) {
                return new ResponseEntity(new JsonResult(ResultCode.SUCCESS.res_code(), "退款成功"), HttpStatus.OK);
            }
            return new ResponseEntity(new JsonResult(ResultCode.FAIL.res_code(), "退款失败"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new JsonResult(ResultCode.ERROR.res_code()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 问题订单退款
     * @param orderId
     * @param orderItemIds
     * @param status
     * @param money
     * @return
     */
    @Override
    public XltcResult questionOrderRefund(String orderId, List<String> orderItemIds, String status, String money) {
        Order order = orderService.findOne(orderId);
        //校验订单
        if(order==null)
            return XltcResult.error("订单不存在！");
        //校验订单是否可退/补差价
        if (!"1".equals(order.getStatus())
                && !"4".equals(order.getStatus())
                && !"5".equals(order.getStatus())
                && !"6".equals(order.getStatus())
                && !"7".equals(order.getStatus())) {
            return XltcResult.error("只有状态为[ 1/4/5/6/7 ]的订单才能完成退款或者补差价！");
        }
        //校验是否是预售单退款，该订单是否是预售单预售单
        if("903".equalsIgnoreCase(status)){
            if(!"preSell".equalsIgnoreCase(order.getTag()))
                return XltcResult.error("该订单不是预售单，退款状态不能传入903！");
        }
        //补差价
        if(StringNotNull.check(money)){
            try {
                Double.parseDouble(money);
            }catch (Exception e){
                return XltcResult.error("差价格式不正确，请确保是数字！");
            }
            if("902".equalsIgnoreCase(status)){
                //补差价。插入info记录
                Info info = new Info();
                info.setOrderId(orderId);
                info.setUserId(order.getUserId());
                info.setInfo("已补差价"+money+"元");
                Integer add = infoService.add(info);
                //修改订单状态
                int i = orderService.updateStatus(orderId, "902");
                if(add.intValue()>0 && i>0)
                    return  XltcResult.ok();
                else
                    throw XltcRuntimeException.throwable("补差价记录失败！");
            }
        }
        //校验选择的订单明细
        if(SqlConstructUtils.nullList(orderItemIds))
            return XltcResult.error("退款未选择订单明细！");
        //查询真实订单明细
        List<OrderItem> items = orderService.findorderItems(orderId);
        if(SqlConstructUtils.nullList(items))
            return XltcResult.error("退款失败，该订单不存在明细信息，刷新重试");
        //退款---->>>>>>>>>>>>>>>>>>>>>>>>>
        String itemsIdsConstructed = SqlConstructUtils.constructListToStringsOnIn(orderItemIds, null);
        //1.回库存--》1.查询订单商品skuid---》2.查询库存是否存在已停用--->2.回库
        List<OrderItem> orderItems =  orderItemService.findSkuIds(itemsIdsConstructed);
        List<String> skuIds = orderItems.stream().map(x -> {
            return x.getItemId();
        }).collect(Collectors.toList());
        if(SqlConstructUtils.nullList(skuIds))
            return XltcResult.error();
        String skuIdsConstructed = SqlConstructUtils.constructListToStringsOnIn(skuIds, null);
        List<Stock> enableStocks = stockService.findEnabledStockByItemIdsAndWarehouseCode(skuIdsConstructed,order.getWarehouseCode());
        if(enableStocks.size()<orderItemIds.size()){
            return XltcResult.error("无法回库，存在已经停用的库存！");
        }else {
            Set<String> limitedGoodsCode = this.findLimitedGoodsCode();
            //回库
            for (OrderItem orderItem : orderItems) {
                stockService.increQuestionsRefundOrderItemsStock(orderItem,order.getWarehouseCode());
                Item item = itemService.findOne(orderItem.getItemId());
                //判断是否是限购商品，取消限购购买记录
                if(limitedGoodsCode!=null){
                    if(limitedGoodsCode.contains(item.getCode())){
                        //该商品时预售商品，解除限购记录
                        jedisUtil.removeElementFromHash(RedisConstant.LIMITED_CODE_USER,item.getCode() + ":" + order.getUserId());
                    }
                }
                //判断是否是预售商品订单，减 售卖数量
                if("preSell".equals(order.getTag())){
                    //校验传入的订单状态(equals 903)
                    if(status.equals("903"))
                        status = "903";
                    String level = order.getLevel();
                    //回退本期预售商品的售卖量
                    String count = "-" + String.valueOf(orderItem.getCount());
                    preSellGoodsService.addCurrentPreSelllGoodsSellNum(item.getCode(),level,count);
                }

            }
        }
        //2.修改订单明细的退款标识
        int j =orderItemService.updateOrderItemStatus(itemsIdsConstructed,"1");
        //3.修改order状态
        int update = orderService.updateStatus(orderId, status);
        //4.添加info日志
        Info info = new Info();
        info.setOrderId(orderId);
        info.setUserId(order.getUserId());
        if("9".equalsIgnoreCase(status))
            info.setInfo("已完全部退款！");
        else if("901".equalsIgnoreCase(status))
            info.setInfo("已完成部分退款！");
        else if("903".equalsIgnoreCase(status)) {
            info.setInfo("已完成预售订单退款[" + money + "]元！");
        }else {
            info.setInfo("已完成订单退款(不明退款类型),退款传入状态:["+status+"]！");
        }
        Integer add = infoService.add(info);
        return XltcResult.ok();
    }

    /**
     * 查询限购商品code
     * @return
     */
    public Set<String> findLimitedGoodsCode(){
        Set<String> elements = jedisUtil.getMembersFromSet(RedisConstant.LIMITED_CODES);
        if(elements==null || elements.size()<=0)
            return null;
        else
            return elements;
    }

    @Override
    public XltcResult onlyRefundBalance(TbRefundRecord tbRefundRecord) {
        //判断是否是余额退款，目前只放行余额退款
        if(!"balance".equalsIgnoreCase(tbRefundRecord.getRefundtype()))
            return XltcResult.error("抱歉，该接口只开放余额退款:[ refundtype : balance ]!");
        Order order = orderService.findOne(tbRefundRecord.getOrderid());
        if(order==null)
            return XltcResult.error("订单不存在！");
        String userId = order.getUserId();
        //退余额
        int i = appUserService.onlyRefundBalance(userId,tbRefundRecord.getMoney());
        if(i<=0)
            return XltcResult.error("退款失败，联系管理员！");
        //插入退款记录
        tbRefundRecord.setOpttime(dateFormat.format(new Date()));
        tbRefundRecord.setRefundrecordid(UUID.randomUUID().toString().replace("-",""));
        int j = refundRecordMapper.insert(tbRefundRecord);
        if(j>0)
            return XltcResult.ok();
        else
            throw XltcRuntimeException.throwable("退款失败，联系管理员！");
    }

    @Override
    public PageInfo<TbRefundRecord> queryBalanceRefundRecord(String startTime, String endTime, String no, int page, int size) {
        StringBuffer buffer = new StringBuffer(" ");
        if(StringNotNull.check(startTime)){
            buffer.append(" and a.optTime >= '"+startTime+"' ");
        }
        if(StringNotNull.check(endTime)){
            buffer.append(" and a.optTime <= '"+endTime+"' ");
        }
        if(StringNotNull.check(no)){
            buffer.append(" and a.no = '"+no+"' ");
        }
        buffer.append(" order by a.optTime desc ");
        PageHelper.startPage(page,size);
        List<TbRefundRecord> records = refundRecordMapper.queryBalanceRefundRecord(buffer.toString());
        PageInfo<TbRefundRecord> info = new PageInfo<>(records);
        return info;
    }

    @Override
    public List<TbRefundRecord> queryBalanceRefundRecordForExportExcel(String startTime, String endTime, String no) {
        StringBuffer buffer = new StringBuffer(" ");
        if(StringNotNull.check(startTime)){
            buffer.append(" and a.optTime >= '"+startTime+"' ");
        }
        if(StringNotNull.check(endTime)){
            buffer.append(" and a.optTime <= '"+endTime+"' ");
        }
        if(StringNotNull.check(no)){
            buffer.append(" and a.no = '"+no+"' ");
        }
        buffer.append(" order by a.optTime desc ");
        List<TbRefundRecord> records = refundRecordMapper.queryBalanceRefundRecord(buffer.toString());
        return records;
    }


}
