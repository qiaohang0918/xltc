package com.qigan.qiganshop.util.quartz;

import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.pojo.Info;
import com.qigan.qiganshop.pojo.Order;
import com.qigan.qiganshop.pojo.OrderItem;
import com.qigan.qiganshop.pojo.synchronization.ResultStock;
import com.qigan.qiganshop.pojo.synchronization.ResultStock.StocksBean;
import com.qigan.qiganshop.service.*;
import com.qigan.qiganshop.util.Redis.RedisLock;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.utils.guanyi.GuanYiDataSource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 定时执行
 *
 * @author wanghao
 * @time 2019-05-15 11:38
 */
@Component(value = "orderQuartzJob")
public class OrderQuartzJob {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private StockService stockService;
	@Autowired
	private InfoService infoService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private JedisUtil jedisUtil;

	@Autowired
	private GuanYiStockService gyStockService;

	@Autowired
	UserCouponService couponService;

	public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static final int EX = 4;

	public synchronized void removeLockStock() {
		RedisLock lock = new RedisLock("freedLock");
		try {
			lock.lock();
			freedLock(3);
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
//		if (integer == 1) {
//			// System.err.println("成功删除超时未支付订单,当前时间:" + new
//			// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//		} else {
//			// System.err.println("删除失败!");
//		}
	}

	public Integer freedLock(Integer time) {
		List<Order> list = orderService.findExpired(time);
		if (list == null || list.size() == 0) {
			return 1;
		}

		for (Order order : list) {
			try {
				if ("2".equals(order.getWaitingPay()))
					continue;

				if ("1".equals(order.getWaitingPay()) && payTimeOut(order.getDealDatetime(), null))
					continue;

				// boolean flag = true;
				// if("1".equals(order.getWaitingPay())){
				// flag = closeService.closePayByOrder(order.getOrderId());
				// if(!flag)
				// continue;
				// }

				int total = 0;
				int allItemCount = 0;
				List<OrderItem> orderItems = orderItemService.findByOrderId(order.getOrderId());
				if (orderItems == null || orderItems.size() == 0) {
					continue;
				}
				allItemCount += orderItems.size();
				for (OrderItem orderItem : orderItems) {
					// 取消订单时，查询是否是受限商品，如果是则解除限制
					// 商品code
					String code = goodsService.findCodeByGoodsId(orderItem.getGoodsId());
					Set<String> limitedCodes = findLimitedCodes();
					if (limitedCodes.contains(code)) {
						// 删除该用户的限购
						String userId = orderItem.getUserId();
						jedisUtil.removeElementFromHash(RedisConstant.LIMITED_CODE_USER, code + ":" + userId);
					}

					ResultStock stock = gyStockService.findGoodsStock(orderItem.getCode(), order.getWarehouseCode());
					Integer to = 0;
					if (stock != null && stock.getStocks() != null && stock.getStocks().size() > 0) {
						StocksBean bean = stock.getStocks().get(0); // 一个仓只能有一个商品code
						to = bean.getSalable_qty();
						// orderItem.setCount(bean.getQty() -
						// bean.getSalable_qty());
					}

					total += stockService.cancelLock(orderItem, order.getWarehouseCode(), to);

				}
				// 更新订单状态
				Info info = new Info();
				info.setOrderId(order.getOrderId());
				info.setUserId(order.getUserId());
				info.setInfo("支付失败!订单信息不存在或订单不是待支付状态!");
				infoService.add(info);
				orderService.updateStatus(order.getOrderId(), "2");
				couponService.recoverUserCoupon(order.getOrderId());
			} catch (Exception e) {
				// TODO: handle exception
				jedisUtil.setToHash(RedisConstant.ORDER_CANCEL, order.getOrderId(), "1");
			}
		}

		return 1;
	}

	/**
	 * 查询redis中的限购商品
	 * 
	 * @return
	 */
	public Set<String> findLimitedCodes() {
		Jedis jedis = jedisUtil.getConn();
		try {
			Set<String> codes = jedis.smembers(RedisConstant.LIMITED_CODES);
			return codes;
		} finally {
			jedis.close();
		}
	}

	private static boolean payTimeOut(String createTime, Long ex) {
		ex = ex == null ? EX : ex;
		LocalDateTime ldt = LocalDateTime.parse(createTime, DF);
		long minutes = ChronoUnit.MINUTES.between(ldt, LocalDateTime.now());
		if (minutes <= ex)
			return true;

		return false;
	}

	public static void main(String[] args) {
		System.err.println(payTimeOut("2019-10-11 08:29:33", null));
	}

}
