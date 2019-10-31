package com.qigan.qiganshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.qigan.qiganshop.controller.utils.AlipayController;
import com.qigan.qiganshop.controller.utils.WxpayController;
import com.qigan.qiganshop.mapper.OrderMapper;
import com.qigan.qiganshop.pojo.*;
import com.qigan.qiganshop.pojo.group.GroupOrder;
import com.qigan.qiganshop.pojo.synchronization.*;
import com.qigan.qiganshop.service.*;
import com.qigan.qiganshop.service.synchronization.SynchGuanYiOrderService;
import com.qigan.qiganshop.service.synchronization.SynchGuanYiUserService;
import com.qigan.qiganshop.util.baidumap.BaiDuMapService;
import com.qigan.qiganshop.util.maputil.MapUtil;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.picture.PicUtil;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * 订单
 *
 * @author wanghao
 * @time 2019-05-04 18:05
 */
@SuppressWarnings("all")
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BaiDuMapService mapService;
    @Autowired
    private JsonResult jr;
    @Autowired
    private UserAddrService userAddrService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AppUserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private StockService stockService;
    @Autowired
    private PayService payService;
    @Autowired
    private SynchGuanYiUserService guanYiUserService;
    @Autowired
    private SynchGuanYiOrderService guanYiOrderService;
    @Autowired
    private PicUtil picUtil;
    @Autowired
    private CouponService couponService;
    @Autowired
    private UserCartService userCartService;
    @Autowired
    private AlipayController alipayController;
    @Autowired
    private WxpayController wxpayController;
    @Autowired
    private InfoService infoService;


    @Value("${shopCode}")
    private String shopCode;
    @Value("${expressCode}")
    private String expressCode;


    /**
     * 提交订单
     * <p>
     * 确定用户收货地址
     * 锁定库存数量
     * 保存订单信息
     *
     * @param groupOrder
     * @return 用户提交的信息中, 包括:
     * 1,token 用于查询用户
     * 2,userAddrId 用于查询用户收货地址
     * 3,userChooseTime 用户选择的配送和时间
     * 4,Map<String, Integer> items 商品集合,包含个数
     * 5,coupons 优惠券集合
     * 6,invoice 发票
     * <p>
     * 需求分析:
     * 用户提交订单,
     * 1,查询用户信息
     * 2,查询用户收货地址
     * 3,记录用户的配送时间
     * 4,判断用户选择的商品是否能够购买,并生成 orderItem
     * 5,将可以购买的商品生成订单
     * 6,添加优惠券 优惠信息
     * 7,如果用户可填写了发票,同时保存发票信息
     * 8,本地锁定库存,状态设置为待支付状态
     */
    @Override
    public JsonResult submit(GroupOrder groupOrder) {

        Order order = new Order();
        // 〇 确定订单编号 规则: 时间戳+毫秒值+自增长(3位)
        String lastId = this.findMax();
        int end = 0;
        if (StringNotNull.check(lastId)) {

            end = Integer.parseInt(lastId.substring(lastId.length() - 3));
        }
        if (end >= 999) {
            end = 1;
        } else {
            end++;
        }
        order.setOrderId(new SimpleDateFormat("yyyyMMdd").format(new Date()) + System.currentTimeMillis() + String.format("%03d", end));
        //① 查询用户信息
        String token = groupOrder.getToken();

        AppUser user = userService.getAppUserByToken(token);
        if (user == null) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "登录信息已失效!");
        }
        // 属性复制
        order.setUserId(user.getUserId());
        // 用户信息有效
        //####################################################################################################################################################
        // ② 判断用户收货地址
        UserAddress userAddress = userAddrService.findOne(groupOrder.getUserAddrId());
        if (userAddress == null) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "收货地址无效!");
        }
        // 用户信息有效
        // 收货地址有效 将收货信息复制到订单
        // 用户收货地址 ID
        order.setUserAddrId(userAddress.getUserAddId());
        // 收货人姓名
        order.setReceiverName(userAddress.getName());
        // 收货人手机号
        order.setReceiverMobile(userAddress.getPhone());
        // 收货地址
        order.setReceiverAddress(userAddress.getAddress() + userAddress.getHouseNum());
        // 省
        order.setReceiverProvince(userAddress.getProvince());
        // 市
        order.setReceiverCity(userAddress.getCity());
        // 区(根据经纬度查询)
        String district = mapService.getDistrict(userAddress.getCoordinate());
        if (StringNotNull.check(district)) {
            order.setReceiverDistrict(district);
        } else {
            order.setReceiverDistrict("");
        }
        //####################################################################################################################################################
        // ③ 记录用户的配送时间
        String userChooseTime = groupOrder.getUserChooseTime();
        if (!StringNotNull.check(userChooseTime)) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "配送时间不能为空!");
        } else {
            String regEx = "([0-9]|0[0-9]|1[0-9]|2[0-3]):[0][0]-([0-9]|0[0-9]|1[0-9]|2[0-3]):[0][0]";

            if ("1小时内".equals(userChooseTime)) {
                Calendar cal = Calendar.getInstance();
                // 当前时间
                String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
                // 一小时后
                cal.add(Calendar.HOUR_OF_DAY, +1);
                String late = new SimpleDateFormat("HH:mm:ss").format(cal.getTime());
                order.setUserChooseTime(now + "-" + late);
            } else if ("2小时内".equals(userChooseTime)) {
                Calendar cal = Calendar.getInstance();
                // 当前时间
                String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
                // 两小时后
                cal.add(Calendar.HOUR_OF_DAY, +2);
                String late = new SimpleDateFormat("HH:mm:ss").format(cal.getTime());
                order.setUserChooseTime(now + "-" + late);
            } else if (compile(regEx).matcher(userChooseTime.replace("今天:", "")).find()) {
                // 如果传入时间符合正则表达式 ==> 17:00-18:00 即滑动选项中选中的选项
                String[] split = userChooseTime.split("-");
                Calendar cal = Calendar.getInstance();
                // 当前时间
                String today = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
                order.setUserChooseTime(today + split[0] + ":00-" + split[1] + ":00");
            } else if (userChooseTime.startsWith("明天") && compile(regEx).matcher(userChooseTime.replace("明天:", "")).find()) {
                // 如果传入时间符合正则表达式 ==> 17:00-18:00 即滑动选项中选中的选项
                String[] split = userChooseTime.split("-");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_YEAR, +1);
                // 当前时间
                String tomorrow = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
                order.setUserChooseTime(tomorrow + split[0] + ":00-" + split[1] + ":00");
            } else {
                return jr.jsonResultData(ResultCode.FAIL.res_code(), "配送时间异常,仅可以传输以下内容[1小时内,2小时内,今天:17:00-18:00,明天:17:00-18:00]!");
            }
        }
        //####################################################################################################################################################
        // ④ 检查用户选择的商品是否可以购买(直接调用购物车判断接口)
        Cart cart = new Cart();
        cart.setCoordinate(userAddress.getCoordinate());
        cart.setItems(groupOrder.getItems());
        cart.setCoupons(groupOrder.getCoupons());
        cart.setToken(groupOrder.getToken());
        JsonResult settlement = cartService.settlement(cart);
        // 判断完信息后,检查是否满足订单条件
        if (!"200".equals(settlement.getRes_code())) {
            return settlement;
        }
        ResultCart resultCart = (ResultCart) settlement.getData();
        if (resultCart.getSellOut().size() > 0) {
            // 有失效商品
            settlement.setMessage("订单中存在失效商品,请先清除!");
            return settlement;
        }
        // 可以购买了,属性复制
        // 订单金额(未优惠金额)订单原价
        order.setMoney(resultCart.getTotalPrice());
        // 优惠金额(优惠金额) 优惠券金额
        order.setCouponMoney(Math.abs(resultCart.getCoupon()));
        // 配送费
        order.setPeisongfei(resultCart.getPeisongfei() * 1.0);
        // 实付
        order.setPayMoney(resultCart.getSum());
//####################################################################################################################################################
        //⑤ 生成订单商品
        List<Goods> canBuy = resultCart.getCanBuy();
        List<OrderItem> byItems = new ArrayList<>();
        for (Goods goods : canBuy) {


            // 根据 SKU ID 查询商品,准备插入订单商品表
            Item one = goods.getItems().get(0);
            if (one != null) {
                OrderItem orderItem = new OrderItem();
                // 订单商品 ID
                orderItem.setOrderItemId(order.getOrderId() + one.getItemId());
                // 订单 ID
                orderItem.setOrderId(order.getOrderId());
                // 用户 ID
                orderItem.setUserId(user.getUserId());
                //SPU ID
                orderItem.setGoodsId(one.getSpuId());
                //SKU ID
                orderItem.setItemId(one.getItemId());
                // 商品名称

                if (goods == null) {
                    return jr.jsonResultData(ResultCode.FAIL.res_code(), "该商品已下架,请重新选择!");
                }
                orderItem.setGoodsName(goods.getName());
                // 规格名称
                orderItem.setItemName(one.getName());
                // 购买时价格
                orderItem.setByPrice(one.getSalesPrice() * 1.0);
                // 图片地址
                orderItem.setPicUrls(goods.getPicUrls().split(",")[0]);
                //数量
                orderItem.setCount(goods.getUserChooseNum());
                // 计量单位
                orderItem.setUnit(one.getUnit());
                // 小计
                orderItem.setSubTotal(goods.getUserChooseNum() * 1.0 * one.getSalesPrice());
                byItems.add(orderItem);

            }
        }


        //####################################################################################################################################################
        // ⑥ 添加优惠券
        List<UserCoupon> userCoupons = new ArrayList<>();
        List<Coupon> coupons = groupOrder.getCoupons();
        if (coupons != null && coupons.size() > 0) {
            for (Coupon coupon : coupons) {
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setIsUse("1");
                userCoupon.setCouponId(coupon.getCouponId());
                userCoupon.setUserId(user.getUserId());
                userCoupon.setOrderId(order.getOrderId());
                userCoupons.add(userCoupon);
            }
        }
        //####################################################################################################################################################
        // ⑦ 添加发票
        Invoice invoice = groupOrder.getInvoice();
        if (invoice != null) {
            invoice.setInvoiceId(System.currentTimeMillis() + new Random().nextInt(100) * 1000000);
            invoice.setInvoiceAmount(order.getPayMoney());
            StringBuilder detail = new StringBuilder();

            for (OrderItem byItem : byItems) {
                detail.append(byItem.getGoodsName()).append(" \t");
                detail.append(byItem.getItemName()).append("  \t");
                detail.append(byItem.getUnit() == null ? "1件" : "byItem.getUnit()" + "*" + byItem.getCount() + " \t");
                detail.append("￥").append(byItem.getSubTotal()).append("  \t");
                detail.append("\n");
            }
            detail.append("总计:" + order.getMoney()).append("\n");
            detail.append("优惠:" + order.getCouponMoney()).append("\n");
            detail.append("运费:" + order.getPeisongfei()).append("\n");
            detail.append("合计:" + order.getPayMoney()).append("\n");
            invoice.setInvoiceContent(detail.toString());
            invoice.setOrderId(order.getOrderId());
        }
        //##############################################################################################
        //⑧ 其他项
        // 下单时间
        order.setDealDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 商铺代码(前期默认写死)
        order.setShopCode(shopCode);
        // 物流公司代码
        order.setExpressCode(expressCode);
        // 订单类型
        order.setOrderTypeCode("Sales");
        // 仓库 ID
        order.setWarehouseCode(resultCart.getWarehouseCode());
        // 买家留言
        order.setBuyerMemo(groupOrder.getBuyerMemo());

        //**********************************************************************************************
        // 锁定库存
        for (OrderItem byItem : byItems) {
            Stock stock = stockService.findByItemIdAndWareHouseId(byItem.getItemId(), resultCart.getWarehouseCode());
            if (stock != null) {
                Stock update = new Stock();
                update.setStockId(stock.getStockId());
                update.setGoodsId(byItem.getGoodsId());
                update.setItemId(byItem.getItemId());
                update.setWarehouseId(stock.getWarehouseId());
                update.setStockNum(stock.getStockNum());
                // 锁定数
                update.setLockNum(stock.getLockNum() + byItem.getCount());
                // 可销售数
                update.setSalableNum(stock.getStockNum() - update.getLockNum());
                stockService.update(update);
            } else {
                return jr.jsonResultData(ResultCode.FAIL.res_code(), "锁定库存失败!");
            }
        }

        //**********************************************************************************************
        /**##############################################################################################
         * 插入数据库相关
         */
        // 订单没有问题,设置订单状态为待支付
        order.setStatus("0");
        int add = orderMapper.add(order);
        // 插入订单商品
        for (OrderItem byItem : byItems) {
            int add1 = orderItemService.add(byItem);
        }
        // 修改优惠券使用信息
        for (UserCoupon userCoupon : userCoupons) {
            Integer integer = userCouponService.updateByOrder(userCoupon);
        }
        // 增加发票信息
        if (invoice != null) {
            invoiceService.add(invoice);
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("orderId", order.getOrderId());
        result.put("payMoney", order.getPayMoney());
        Info info = new Info();
        info.setOrderId(order.getOrderId());
        info.setUserId(user.getUserId());
        info.setInfo("订单创建成功!等待支付");
        infoService.add(info);

        //*********************************删除购物车中已购买商品的记录****************************************************
        if(byItems.size()>0){
            int deleteCartResult=0;
            List<String> collect = byItems.stream().map(x -> {
                return x.getItemId();
            }).collect(Collectors.toList());
            for (OrderItem byItem : byItems) {
                deleteCartResult=cartService.deleteCurrentOrderItemsFromCart(groupOrder.getToken(),collect);
            }
            if(deleteCartResult<=0)
                return jr.jsonResultData(ResultCode.FAIL.res_code(), "清除购物车失败，请重新下单！");
        }
        else
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "未选中需要下单的商品！");

        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "订单添加成功!等待支付", result);
    }


    /**
     * 增加
     *
     * @param order
     * @return
     */
    @Override
    public int add(Order order) {
        return orderMapper.add(order);
    }

    /**
     * 修改
     *
     * @param order
     * @return
     */
    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }


    /**
     * 三合一接口
     *
     * @param order
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Order> findPage(Order order, Integer page, Integer size, String rule) {
        List<Order> result;
        if (page != null && size != null) {

            result = orderMapper.findPage(order, (page - 1) * size, size, rule);
        } else {

            result = orderMapper.findPage(order, null, null, rule);
        }
        return result;
    }

    /**
     * 查询最大的排序值
     *
     * @return
     */
    @Override
    public String findMax() {
        return orderMapper.findLastId();
    }

    /**
     * 查询支付信息,并修改订单状态
     *
     * @param orderId
     * @return
     */
    @Override
    public JsonResult payInfo(String out_trade_no, String payType, String orderId) {
        // 检查支付信息,如果存在,继续,不存在,拉取支付信息
        Pay pay = payService.selectByOrderId(orderId);
        if (pay == null) {
            if ("zhifubao".equals(payType)) {
                JSONObject select = alipayController.select(out_trade_no);
                if (select != null && "TRADE_SUCCESS".equals(select.getString("trade_status"))) {
                    // 支付成功,继续写入
                    Pay pay1 = new Pay();
                    pay1.setOutPayCode(out_trade_no);
                    pay1.setPayCode(select.getString("trade_no"));
                    pay1.setOrderId(orderId);
                    pay1.setPayTypeCode(payType);
                    pay1.setPayment(select.getString("total_amount"));
                    pay1.setPaytime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    payService.insertPay(pay1);
                    pay = payService.selectByOrderId(orderId);
                }


            } else if ("weixin".equals(payType)) {
                Map orderquery = wxpayController.orderquery(out_trade_no);

                if (orderquery != null && "SUCCESS".equals(orderquery.get("trade_state"))) {
                    // 支付成功,继续写入
                    Pay pay1 = new Pay();
                    pay1.setOutPayCode(out_trade_no);
                    pay1.setPayCode(orderquery.get("transaction_id").toString());
                    pay1.setOrderId(orderId);
                    pay1.setPayTypeCode(payType);
                    pay1.setPayment(Double.parseDouble(orderquery.get("total_fee").toString()) / 100 + "");
                    pay1.setPaytime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    payService.insertPay(pay1);
                    pay = payService.selectByOrderId(orderId);
                }

            } else {
                // 问题订单
                this.updateStatus(orderId, "10");
                Info info = new Info();
                info.setOrderId(orderId);
                info.setInfo("支付失败!订单信息不存在或订单不是待支付状态!");
                infoService.add(info);
                return jr.jsonResultData(ResultCode.FAIL.res_code(), "支付失败!订单信息不存在或订单不是待支付状态!");
            }

        }

        Order one = this.findOne(orderId);

        if ((!"1".equals(one.getStatus()) && !"0".equals(one.getStatus())) || one == null || pay == null) {
            // 问题订单
            this.updateStatus(orderId, "10");
            Info info = new Info();
            info.setOrderId(orderId);
            info.setUserId(one.getUserId());
            info.setInfo("支付失败!订单信息不存在或订单不是待支付状态!");
            infoService.add(info);
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "支付失败!订单信息不存在或订单不是待支付状态!");

        }

        if (pay != null) {
            // 能查询到支付信息
        	if(!"1".equals(one.getStatus())) {
        		one.setPayCode(pay.getPayCode());
        		one.setPayTime(pay.getPaytime());
        		// 支付成功
        		one.setStatus("1");
        		orderMapper.update(one);
        	}
            // 减库存
            List<OrderItem> list = orderItemService.findByOrderId(orderId);
            // 锁定库存 = 原锁定库存 - 本订单中销售数量
            // 所有库存 = 原所有库存 - 本订单中销售数量
            // 可销售库存 = 现所有库存 - 锁定数量
            list.forEach(orderItem -> {
                Stock stock = stockService.findByItemIdAndWareHouseId(orderItem.getItemId(), one.getWarehouseCode());
                stock.setWarehouseId(one.getWarehouseCode());
                stock.setLockNum(stock.getLockNum() - orderItem.getCount());
                stock.setStockNum(stock.getStockNum() - orderItem.getCount());
                stock.setSalableNum(stock.getStockNum() - stock.getLockNum());
                stockService.update(stock);
                // 增加已销售数量
                itemService.updateSaleCount(orderItem.getItemId(), orderItem.getCount());
                // 清空购物车
                userCartService.delete(one.getUserId(), orderItem.getItemId());

            });
            // 管易加订单
            String result = this.pullToGuanYi(orderId);
            if ("ok".equals(result)) {
            	if(!"1".equals(one.getStatus())) {
            		one.setStatus("1");
            		orderMapper.update(one);
            	}
                // 用户增加下单次和消费金额
                userService.addDeal(one.getUserId(), one.getPayMoney());
                Info info = new Info();
                info.setOrderId(orderId);
                info.setUserId(one.getUserId());
                info.setInfo("订单支付成功!");
                infoService.add(info);
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "支付成功!");
            } else {
                this.updateStatus(orderId, "10");
                Info info = new Info();
                info.setOrderId(orderId);
                info.setUserId(one.getUserId());
                info.setInfo("支付成功,但" + result + ",请联系客服处理!");
                infoService.add(info);
                return jr.jsonResultData(ResultCode.FAIL.res_code(), "支付成功,但" + result + ",请联系客服处理!");
            }

        }
        this.updateStatus(orderId, "10");
        Info info = new Info();
        info.setOrderId(orderId);
        info.setUserId(one.getUserId());
        info.setInfo("支付失败!订单信息不存在!");
        infoService.add(info);
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "支付失败!订单信息不存在!");
    }

    @Override
    public List<Order> findExpired(Integer time) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, time * -1);
        String late = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowTime.getTime());
        return orderMapper.findExpired(late);
    }

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     * @return
     */
    @Override
    public int updateStatus(String orderId, String status) {
        if (StringNotNull.check(orderId) && StringNotNull.check(status)) {

            return orderMapper.updateStatus(orderId, status);
        }
        return 0;
    }

    // 推送到管易云
    private String pullToGuanYi(String OrderId) {
        /**
         * 开发思想:
         * ①,获取用户信息(检查是否存在该会员)
         * ②,填充订单基本信息
         * ③,添加用户收货地址信息
         * ④,获取订单商品信息
         * ⑤,获取支付信息
         * ⑥,获取发票信息
         * ⑦,新增
         */
        Order order = orderMapper.findOne(OrderId);
        if (order == null) {
            return "订单不存在";
        }
        //①,获取用户信息
        GuanYiOrder guanYiOrder = new GuanYiOrder();
        GuanYiUser guanYiUser = guanYiUserService.getGuanYiUser(order.getUserId());
        String userId = order.getUserId();
        if (guanYiUser == null) {
            //添加用户
            GuanYiUser add = new GuanYiUser();
            add.setCode(userId);
            add.setName(userService.getAppUserById(userId).getNickName());
            add.setShop_code(order.getShopCode());
            // 获取用户收货地址列表
            List<UserAddress> rows = userAddrService.findPage(userId, null, null).getRows();
            ArrayList<GuanYiUser.Address> receive_infos = new ArrayList<>();
            for (UserAddress row : rows) {
                GuanYiUser.Address address = new GuanYiUser.Address();
                address.setName(row.getUserAddId());
                address.setReceiver(row.getName());
                address.setTelephone(row.getPhone());
                address.setAddress(row.getAddress() + row.getHouseNum());
                receive_infos.add(address);
            }
            add.setReceive_infos(receive_infos);
            // 添加用户
            guanYiUserService.add(add);
        }
        // ② 复制订单信息
        // 添加用户信息
        guanYiOrder.setVip_code(userId);
        // 添加店铺信息
        guanYiOrder.setShop_code(order.getShopCode());
        // 平台单号
        guanYiOrder.setPlatform_code(order.getOrderId());
        // 仓库代码
        guanYiOrder.setWarehouse_code(order.getWarehouseCode());
        //物流公司
        guanYiOrder.setExpress_code(order.getExpressCode());
        // 拍单时间
        guanYiOrder.setDeal_datetime(order.getDealDatetime());
        //订单类型
        guanYiOrder.setOrder_type_code(order.getOrderTypeCode());
        //买家留言
        guanYiOrder.setBuyer_memo("配送时间" + order.getUserChooseTime() + "买家留言" + order.getBuyerMemo());
        //③ 复制收货信息
        // 收货人
        guanYiOrder.setReceiver_name(order.getReceiverName());
        // 手机号
        guanYiOrder.setReceiver_phone(order.getReceiverMobile());
        //省
        guanYiOrder.setReceiver_province(order.getReceiverProvince());
        //市
        guanYiOrder.setReceiver_city(order.getReceiverCity());
        //区
        guanYiOrder.setReceiver_district(order.getReceiverDistrict());
        //收货地址
        guanYiOrder.setReceiver_address(order.getReceiverAddress());
        // ④ 复制商品明细
        // 商品明细
        List<OrderItem> byOrderId = orderItemService.findByOrderId(OrderId);
        List<GuanYiItem> guanYiItems = new ArrayList<>();
        for (OrderItem orderItem : byOrderId) {
            GuanYiItem guanYiItem = new GuanYiItem();
            //商品代码
            Goods one = goodsService.findOne(orderItem.getGoodsId());
            guanYiItem.setItem_code(one != null ? one.getCode() : "");
            //sku_code
            Item item = itemService.findOne(orderItem.getItemId());
            guanYiItem.setSku_code(item != null ? item.getCode() : "");
            // 实际单价
            guanYiItem.setPrice(orderItem.getByPrice() + "");
            // 数量
            guanYiItem.setQty(orderItem.getCount());
            // 退款状态
            guanYiItem.setRefund(0);
            guanYiItems.add(guanYiItem);
        }
        guanYiOrder.setDetails(guanYiItems);
        // ⑤ 复制交易信息
        // 支付状态
        Pay pay = payService.selectByOrderId(OrderId);
        if (pay == null) {
            return "未查询到有效的支付信息";
        }
        GuanYiPayment guanYiPayment = new GuanYiPayment();
        // 支付方式
        guanYiPayment.setPay_type_code(pay.getPayTypeCode());
        // 支付金额
        guanYiPayment.setPayment(Double.valueOf(pay.getPayment()));
        // 支付时间
        try {
            guanYiPayment.setPaytime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pay.getPaytime()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "支付时间转换异常";
        }
        // 交易号
        guanYiPayment.setPay_code(pay.getPayCode());
        ArrayList<GuanYiPayment> guanYiPayments = new ArrayList<>();
        guanYiPayments.add(guanYiPayment);
        guanYiOrder.setPayments(guanYiPayments);
        guanYiOrder.setShop_code(order.getShopCode());
        // ⑥ 发票信息
        Invoice invoice = invoiceService.findByOrderId(OrderId);
        if (invoice != null) {
            ArrayList<GuanYiInvoice> invoices = new ArrayList<GuanYiInvoice>();
            GuanYiInvoice guanYiInvoice = new GuanYiInvoice();
            // 发票种类
            guanYiInvoice.setInvoice_type(invoice.getInvoiceType());
            //发票抬头类型
            guanYiInvoice.setInvoice_title_type(invoice.getInvoiceTitleType());
            //发票类型
            guanYiInvoice.setInvoice_type_name(invoice.getInvoiceTypeName());
            //发票抬头
            guanYiInvoice.setInvoice_title(invoice.getInvoiceTitle());
            //发票内容
            guanYiInvoice.setInvoice_content(invoice.getInvoiceContent());
            //纳税人识别号
            guanYiInvoice.setInvoice_tex_payer_number(invoice.getInvoiceTexPayerNumber());
            //手机号
            guanYiInvoice.setInvoice_phone(invoice.getInvoicePhone());
            //发票金额
            guanYiInvoice.setInvoice_amount(invoice.getInvoiceAmount());
            invoices.add(guanYiInvoice);
            guanYiOrder.setInvoices(invoices);
        }
        boolean add = guanYiOrderService.add(guanYiOrder);
        if (add) {
            Info info = new Info();
            info.setOrderId(order.getOrderId());
            info.setUserId(order.getUserId());
            info.setInfo("订单已推送至配送系统,等待出仓!");
            infoService.add(info);
            return "ok";
        }
        return "订单提交失败!";

    }

    /**
     * 用户根据状态查询订单信息
     *
     * @param userId
     * @param status
     * @return
     */
    @Override
    public JsonResult findByStatus(String userId, String status, Integer page, Integer rows) {
        Order order = new Order();
        if (StringNotNull.check(userId)) {

            order.setUserId(userId);
        }
        if (!"-1".equals(status)) {
            order.setStatus(status);
        }
        ArrayList<Map> result = new ArrayList<>();
        List<Order> orderList = null;
        if (page != null && rows != null) {

            orderList = this.findPage(order, page, rows, "DESC");
        } else {
            orderList = this.findPage(order, null, null, "DESC");
        }
        for (Order one : orderList) {

            Map<String, Object> map = findOrderItemAndPayInfo(one);
            result.add(map);
        }
        PageResult pageResult = new PageResult(this.findPage(order, null, null, "DESC").size(), result);
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), pageResult);
    }

    private Map<String, Object> findOrderItemAndPayInfo(Order one) {
        Map<String, Object> map = MapUtil.objectToMap(NotNull.checkNull(one));
        List<OrderItem> byOrderId = orderItemService.findByOrderId(one.getOrderId());
        ArrayList<String> picList = new ArrayList<>();
        for (OrderItem orderItem : byOrderId) {
            picList.add(picUtil.addOneUrlHead(orderItem.getPicUrls()));
        }
        map.put("picList", picList);
        Pay pay = payService.selectByOrderId(one.getOrderId());
        map.put("pay", NotNull.checkNull(pay));
        return map;
    }

    /**
     * 分页查询订单信息
     *
     * @param order
     * @param beginTime
     * @param endTime
     * @param page
     * @param rows
     * @return
     */
    @Override
    public JsonResult findByOrder(Order order, String beginTime, String endTime, Integer page, Integer rows, String rule) {
        if (!StringNotNull.check(beginTime) || !StringNotNull.check(endTime)) {
            beginTime = null;
            endTime = null;
        }
        ArrayList<Order> orders = new ArrayList<>();
        if (page != null && rows != null) {

            orders = orderMapper.findByOrder(order, beginTime, endTime, (page - 1) * rows, rows, rule);
        } else {

            orders = orderMapper.findByOrder(order, beginTime, endTime, null, null, rule);
        }
        PageResult pageResult = new PageResult(orderMapper.findByOrder(order, beginTime, endTime, null, null, rule).size(), orders);
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), pageResult);
    }


    /**
     * 查询单个订单
     *
     * @param orderId
     * @return
     */
    @Override
    public Order findOne(String orderId) {
        return orderMapper.findOne(orderId);
    }

    /**
     * 查询单个订单完整信息
     *
     * @param orderId
     * @return
     */
    @Override
    public Map findOneFull(String orderId) {
        // 订单基本信息
        Order one = orderMapper.findOne(orderId);
        if (one == null) {
            return null;
        }
        // 支付信息
        Pay pay = payService.selectByOrderId(orderId);
        // 商品详情
        List<OrderItem> byOrderId = orderItemService.findByOrderId(orderId);
        for (OrderItem orderItem : byOrderId) {
            orderItem.setPicUrls(picUtil.addOneUrlHead(orderItem.getPicUrls()));
        }
        Map<String, Object> orderMap = MapUtil.objectToMap(NotNull.checkNull(one));
        orderMap.put("pay", pay);
        orderMap.put("orderItemList", byOrderId);
        return orderMap;
    }


    /**
     * 查询单个订单
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> findorderItems(String orderId) {
        // 查询订单的基本信息
        Order one = this.findOne(orderId);
        if (one == null) {
            return new ArrayList<>();
        }
        List<OrderItem> byOrderId = orderItemService.findByOrderId(orderId);
        List<OrderItem> result = new ArrayList<>();
        for (OrderItem orderItem : byOrderId) {
            orderItem.setPicUrls(picUtil.addOneUrlHead(orderItem.getPicUrls()));
            result.add(orderItem);
        }

        return result;
    }

    /**
     * 统计数据
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public Map Statistics(String beginTime, String endTime) {
        if (!StringNotNull.check(beginTime) || !StringNotNull.check(endTime)) {
            beginTime = null;
            endTime = null;
        }
        HashMap<String, Integer> statistics = new HashMap<>();
        Integer complete = orderMapper.findEnd(beginTime, endTime);
        Integer halfway = orderMapper.findHalfway(beginTime, endTime);
        Integer question = orderMapper.findQuestion(beginTime, endTime);
        statistics.put("complete", complete);
        statistics.put("halfway", halfway);
        statistics.put("question", question);
        return statistics;
    }

    /**
     * app 统计数据
     *
     * @param userId
     * @return
     */
    @Override
    public Map getCount(String userId) {
        List<CountMap> byUserId = orderMapper.findByUserId(userId);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("waitPay", 0);
        map.put("waitSend", 0);
        map.put("sending", 0);
        map.put("waitAssess", 0);
        map.put("question", 0);
        for (CountMap countMap : byUserId) {
            String status = countMap.getStatus();
            if ("0".equals(status)) {
                map.put("waitPay", countMap.getNumber());
            } else if ("1".equals(status)) {
                map.put("waitSend", countMap.getNumber());
            } else if ("3".equals(status)) {
                map.put("sending", countMap.getNumber());
            } else if ("4".equals(status)) {
                map.put("waitAssess", countMap.getNumber());
            } else if ("9".equals(status)) {
                map.put("question", countMap.getNumber());
            } /*else if ("9".equals(status)) {
                map.put("question", map.get("question") + countMap.getNumber());
            }*/
        }
        return map;
    }

    /**
     * 更新评论
     *
     * @param orderId
     * @param comment
     * @return
     */
    @Override
    public Integer updateComment(String orderId, Integer comment) {

        Integer updateComment = orderMapper.updateComment(orderId, comment);
        if (updateComment > 0) {
            Info info = new Info();
            info.setOrderId(orderId);
            info.setInfo("订单已评价");
            infoService.add(info);
        }
        return updateComment;
    }

    @Override
    public int updatePayedByOrderId(String orderId) {
        return orderMapper.updatePayedByOrderId(orderId);
    }

    public static void main(String[] args) {
    	LocalDateTime now = LocalDateTime.now().minusMinutes(15);
    	System.err.println(now.toString().replace("T", " "));

	}

}
