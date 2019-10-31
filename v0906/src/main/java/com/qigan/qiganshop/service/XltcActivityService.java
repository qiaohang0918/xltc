/**
 * 
 */
package com.qigan.qiganshop.service;

import java.util.List;

/**
 * @author wyy
 *
 */
public interface XltcActivityService {
	
	void insert(String status, String... goodsId);
	
	void update(String status, Long... ids);
	
	void delete(Long... ids);
	
	List<?> findAll();
	
	List<?> findGoodsId();
}
