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
public enum BannerModuleType {
	
	Banner("banner", "1", BannerType.getNotAlertBanner()), 
	
	AD("广告", "2", BannerType.getNotAlertBanner()), 
	
	Activity("活动", "3", BannerType.getNotAlertBanner()), 
	
	Alert("优惠券弹窗", "4", BannerType.getAlertBanner()),
	
	AlertAds("广告弹窗", "5", BannerType.getAlertBanner()), 
	
	Index("app首页图片", "6", BannerType.getAlertBanner());
	
	public String text;
	
	public String value;
	
	public BannerType[] type;
	
	private BannerModuleType(String text, String value, BannerType... type){
		this.text = text;
		this.value = value;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}

	public BannerType[] getType() {
		return type;
	}

	public static List<?> selectAllBannerModelTypeByList() {
		List<Map<?, ?>> result = new ArrayList<>();
		for (BannerModuleType type : BannerModuleType.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", type.getText());
			map.put("value", type.getValue());
			result.add(map);
		}
		return result;
	}
	
	public static Map<?, ?> selectAllBannerModelTypeByMap() {
		Map<String, Object> map = new HashMap<>();
		for (BannerModuleType type : BannerModuleType.values()) {
			map.put(type.getValue(), type.getText());
		}
		return map;
	}
	
}
