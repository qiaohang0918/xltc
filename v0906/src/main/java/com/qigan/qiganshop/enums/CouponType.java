/**
 * 
 */
package com.qigan.qiganshop.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.qigan.qiganshop.exception.XltcRuntimeException;

/**
 * @author wyy
 *
 */
public enum CouponType {
	
	AppointCoupon("满减优惠券", "1", CouponType.ELIMINATE, true),
	
	EggCoupon("鸡蛋券", "3", CouponType.OTHER, false),
	
	FreeCoupon("自定义日期优惠券", "4", CouponType.ELIMINATE, true), 
	
	DeliveryCoupon("运费券", "2", CouponType.OTHER, false), 
	
	TypeCoupon("品类优惠券", "6", CouponType.CONTAIN_TYPE, true), 
	
	SimpleGoodsCoupon("单品优惠券", "7", CouponType.CONTAIN_SIMPLE_GOODS, true), 
	
	AcitivityCoupon("福利优惠券", "5", CouponType.ELIMINATE, true),
	
	NewUserFirstOrder("新人首单券", "8", CouponType.ELIMINATE, true), 
	
	ForSend("推送专属优惠券", "9", CouponType.ELIMINATE, true);
	
	public String text;
	
	public String value;
	
	public String index;
	
	public boolean show;
	
	public static final String ELIMINATE = "eliminate";
	
	public static final String CONTAIN_TYPE = "contain_type";
	
	public static final String CONTAIN_SIMPLE_GOODS = "contain_simple_goods";
	
	public static final String CONTAIN_FIRST_ORDER = "contain_first_order";
	
	public static final String OTHER = "other";
	
	private CouponType(String text, String value, String index, boolean show){
		this.text = text;
		this.value = value;
		this.index = index;
		this.show = show;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}
	
	public String getIndex() {
		return index;
	}
	
	public boolean getShow() {
		return show;
	}
	
	public static List<?> findAll(){
		List<Map<?, ?>> result = new ArrayList<>();
		for (CouponType type : CouponType.values()) {
			Map<String, String> map = new HashMap<>();
			map.put("name", type.name());
			map.put("text", type.getText());
			map.put("value", type.getValue());
			result.add(map);
		}
		return result;
	}
	
	public static Map<?, ?> findAllByMap(){
		Map<String, Object> list = new HashMap<>();
		for (CouponType type : CouponType.values()) {
			Map<String, String> map = new HashMap<>();
			map.put("name", type.name());
			map.put("text", type.getText());
			map.put("value", type.getValue());
			map.put("x", type.getIndex());
			list.put(type.getValue(), map);
		}
		return list;
	}
	
	public static boolean couponCheck(String type, String index){
		for (CouponType coupontype : CouponType.values()) {
			if(coupontype.getValue().equals(type) && coupontype.getIndex().equals(index))
				return true;
		}
		return false;
	}
	
	public static boolean couponCheck(String type, String index1, String index2, String index3){
		return couponCheck(type, index1) | couponCheck(type, index2) | couponCheck(type, index3);
	}
	
	public static boolean couponCheck(String type, String... indexs){
		boolean flag = false;
		for (String index : indexs) {
			flag |= couponCheck(type, index);
		}
		return flag;
	}
	
	public static void checkNewUserCoupon(String value, String mobile, String type){
		if(checkNewUserCouponCanUse(value, mobile, type)){
			throw XltcRuntimeException.throwable("抱歉:不符合新人首单券领取规则");
		}
	}
	
	public static boolean checkNewUserCouponCanUse(String value, String mobile, String type){
		if(CouponType.NewUserFirstOrder.getValue().equals(type) && (StringUtils.isBlank(value) || StringUtils.isNotBlank(mobile)))
			return true;
		
		return false;
	}
	
	public static boolean checkNewUserCouponCanUse(String value,  String type){
		if(CouponType.NewUserFirstOrder.getValue().equals(type) && StringUtils.isBlank(value))//首单券且已经下单了 则需移除优惠券
			return true;
		
		return false;
	}
	
	public static List<String> showCouponList(boolean isShow){
		List<String> result = new ArrayList<>();
		for (CouponType coupontype : CouponType.values()) {
			if(coupontype.getShow() == isShow){
				result.add(coupontype.getValue());
			}
		}
		return result;
	}
	
	public static List<String> showCouponList4App(boolean isShow){
		List<String> result = new ArrayList<>();
		for (CouponType coupontype : CouponType.values()) {
			if(coupontype.getShow() == isShow){
				if(coupontype.getValue().equals(CouponType.AcitivityCoupon.getValue()) || coupontype.getValue().equals(CouponType.ForSend.getValue()))
					continue;
				result.add(coupontype.getValue());
			}
		}
		return result;
	}
	
}
