/**
 * 
 */
package com.qigan.qiganshop.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wyy
 *
 */
public enum OrderStatus {
	
	OrderNotPay("未支付", "0"), 
	
	OrderFinishPay("已支付", "1"),
	
	OrderCancel("已取消", "2"),
	
	OrderDelv("配送中", "3"),
	
	OrderDelved("已签收","4"),
	
	OrderAddrsError("地址错误","5"),
	
	OrderRefuse("拒收", "6"),
	
	OrderProblem("其他问题", "7"),
	
	OrderEvaluate("已评价", "8"),
	
	OrdeRrefund("已退款", "9"),
	
	OrderPayException("支付异常", "10"),
	
	OrdeRrefundALittle("部分退款", "901"),
	
	OrdeRrefundDiff("已退差价", "902"),
	
	OrderPreSalesRrefund("预售单退款", "903");
	
	private String text;
	
	private String value;
	
	public static final String TEXT = "text";
	
	public static final String STATUS = "status";
	
	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}

	private OrderStatus(String text, String value){
		this.text = text;
		this.value = value;
	}
	
	public static Map<?, ?> getOrderStatus(String status){
		Map<String, String> result = new HashMap<>();
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if(orderStatus.value.equals(status)){
				result.put(TEXT, orderStatus.text);
				result.put(STATUS, orderStatus.value);
				return result;
			}
		}
		return null;
	}
	
	public static List<?> getOrderStatusAll(){
		List<Map<?, ?>> list = new ArrayList<>();
		for (OrderStatus orderStatus : OrderStatus.values()) {
			Map<String, String> result = new HashMap<>();
				result.put(TEXT, orderStatus.text);
				result.put(STATUS, orderStatus.value);
				list.add(result);
		}
		return list;
	}
	
	public static Map<?, ?> getOrderStatusAllMap(){
		Map<String, Object> list = new HashMap<>();
		for (OrderStatus orderStatus : OrderStatus.values()) {
			list.put(orderStatus.value, orderStatus.text);
		}
		return list;
	}
	
	public static String getStatusName(String status){
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if(orderStatus.getValue().equals(status))
				return orderStatus.getText();
		}
		return null;
	}
	
	
}
