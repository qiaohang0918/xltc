/**
 * 
 */
package com.qigan.qiganshop.constant;

/**
 * @author wyy
 *
 */
public class RedisConstant {
	
	public static final String USER = "USER:";
	
	public static final String USER_ADDR_ALL = "USER:ADDR:ALL:";
	
	public static final String USER_ADDR_ONE = "USER:ADDR:ONE:";
	
	public static final String USER_COUPON = "USER_COUPON:";
	
	public static final String HOME_BANANER = "HOME:BANANER:";
	
	public static final String HOME_CATE = "HOME:CATE:";
	
	public static final String CONI = "CONI:";
	
	public static final String ADDR_CONI = "ADDR:CONI:";
	
	public static final String ORDER_PAY = "ORDER:PAY";

	//商品，仓库，库存操作同步锁
	public static  final  String ASYNCRO_STEP = "ASYNCRO_STEP";
	//限购商品 smembers
	public static  final  String LIMITED_CODES = "LIMITED_CODES";
	//限购商品（宽泛） smembers
	public static  final  String LIMITED_CODES_CASUE = "LIMITED_CODES_CASUE";

	//限购商品用户购买记录 hash
	public static  final  String LIMITED_CODE_USER = "LIMITED_CODE_USER";
	
	public static  final  String ORDER_CANCEL = "ORDER_CANCEL:";

	//同步部分库存锁 string
	public static  final  String ASYNC_STOCK_LOCK = "ASYNC_STOCK_LOCK";

	//线索用户优惠卷  hashkey(CLUE_USER_COUPONS)   --->  field(couponId)  value(useableDays)
	public static  final  String CLUE_USER_COUPONS = "CLUE_USER_COUPONS";

	//源用户福利优惠卷 haskKey(ORGIN_USER_COUPONS)  ---》field(couponId)  value(useableDays)
	public static final String ORGIN_USER_COUPONS = "ORGIN_USER_COUPONS";

	//充值福利优惠卷 haskKey(DEPOSIT_COUPONS)  ---》field(couponId)  value(useableDays)
	public static final String DEPOSIT_COUPONS = "DEPOSIT_COUPONS";

	//源用户邀请福利金额（元【1.21】）  stringKey
	public static  final  String CLUE_USER_AMOUNT = "CLUE_USER_AMOUNT";

	//源用户邀请福利金额预添加记录（元【1.21】）  hashkey(ORGIN_USER_WISHES_AMOUNT)   ----->  field(newUserId)  value(wishesAmount)
	public static  final  String ORGIN_USER_WISHES_AMOUNT = "ORGIN_USER_WISHES_AMOUNT";

	//源用户邀请福利优惠卷  预下发记录  hashkey(ORGIN_USER_WISHES_COUPONS) ----->  field(newUserId)  value(wishesAmount)
	public static  final  String ORGIN_USER_WISHES_COUPONS = "ORGIN_USER_WISHES_COUPONS";

	//预售商品code  hashkey    field(code)  value( level@2019-09-18_00:00:00 )
	public static  final String PRESELL_GOODS_CODE = "PRESELL_GOODS_CODE";

	//充值订单 stringkey  out_trade_no -->  token & orderid
	//public static  final  String DEPOSIT_ORDER = "DEPOSIT_ORDER";

	//新人首单
	//新用户首单
	public static final String NEW_USER_FIRST_ORDER = "NEW_USER_FIRST_ORDER";
	
	public static final String NEW_MOBILE = "NEW_MOBILE"; 
	
	public static final String OPEN_BANNER = "OPEN_BANNER:";
	
	public static final String CLOSE_BANNER = "CLOSE_BANNER:";
	
	public static final String USER_ALERT_FLAG = "USER_ALERT_FLAG";//用户弹窗hash key

	//预售单推送日志 hashkey (PRESELL_ORDER_PUSH_PERSON) --> (yyyy-MM-dd HH:mm:ss : pushInfo)
	//retaintion thirty days
	public static final String PRESELL_ORDER_PUSH_PERSON = "PRESELL_ORDER_PUSH_PERSON";

	//预售单推送日志 hashkey (PRESELL_ORDER_PUSH_AUTO) --> (yyyy-MM-dd HH:mm:ss : pushInfo)
	//retaintion thirty days
	public static final String PRESELL_ORDER_PUSH_AUTO = "PRESELL_ORDER_PUSH_AUTO";


}
