/**
 * 
 */
package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.synchronization.ResultStock;

/**
 * @author wyy
 *
 */
public interface GuanYiStockService {

	ResultStock findGoodsStock(String code, String warehouseCode);

	ResultStock findGoodsStock(Integer page, Integer size, String code, String warehouseCode);

}
