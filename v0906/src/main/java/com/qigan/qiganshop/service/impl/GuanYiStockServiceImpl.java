/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qigan.qiganshop.pojo.synchronization.ResultStock;
import com.qigan.qiganshop.service.GuanYiStockService;
import com.qigan.qiganshop.utils.guanyi.GuanYiDataSource;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class GuanYiStockServiceImpl implements GuanYiStockService {

	@Autowired
	GuanYiDataSource guanyi;

	@Value("${stockMethod}")
	private String stockMethod;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qigan.qiganshop.service.GuanYiStockService#findGoodsStock(java.lang.
	 * String)
	 */
	@Override
	public ResultStock findGoodsStock(String code, String warehouseCode) {
		// TODO Auto-generated method stub
		return this.findGoodsStock(null, null, code, warehouseCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qigan.qiganshop.service.GuanYiStockService#findGoodsStock(java.lang.
	 * Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public ResultStock findGoodsStock(Integer page, Integer size, String code, String warehouseCode) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(warehouseCode)) 
			map.put("warehouse_code", warehouseCode);
		return guanyi.get(stockMethod, page, size, code, ResultStock.class, map);
	}

}
