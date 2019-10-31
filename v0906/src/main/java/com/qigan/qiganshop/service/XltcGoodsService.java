/**
 * 
 */
package com.qigan.qiganshop.service;

/**
 * @author wyy
 *
 */
public interface XltcGoodsService {
	
	void syncGoodsAll();
	
	void syncGoodsByCode(String... codes);

}
