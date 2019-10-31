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
public enum BannerType {
	
	Goods("商品明细", "1"),
	
	WebActUrl("web活动页面", "2"),
	
	Url("url", "3"),
	
	GoodsType("商品分类", "4"), 
	
	AlertBigImg("弹窗大图", "7"),
	
	AlertClose("弹窗关闭", "8"),
	
	AlertSmall("弹窗小图", "9");
	
	public String text;
	
	public String value;
	
	private BannerType(String text, String value){
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}
	
	public static BannerType[] getNotAlertBanner(){
		return new BannerType[]{Goods, WebActUrl, Url, GoodsType};
	}
	
	public static BannerType[] getAlertBanner(){
		return new BannerType[]{AlertBigImg, AlertClose, AlertSmall};
	}
	
	public static List<?> selectAllBannerTypeByList() {
		List<Map<?, ?>> result = new ArrayList<>();
		for (BannerType type : BannerType.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", type.getText());
			map.put("value", type.getValue());
			result.add(map);
		}
		return result;
	}
	
	public static Map<?, ?> selectAllBannerTypeByMap() {
		Map<String, Object> map = new HashMap<>();
		for (BannerType type : BannerType.values()) {
			map.put(type.getValue(), type.getText());
		}
		return map;
	}
	
}
