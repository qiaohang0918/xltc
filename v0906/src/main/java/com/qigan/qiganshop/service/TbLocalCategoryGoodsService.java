/**
 * 
 */
package com.qigan.qiganshop.service;

import java.util.List;

import com.qigan.qiganshop.domain.TbLocalGoods;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * @author wyy
 *
 */
public interface TbLocalCategoryGoodsService {
	
	List<?> findAllByLocalCateId(Long localCateId);
	
	List<?> findAllByLocalCateId(Long localCateId, String enable);
	
	PageResult findAll(CommonPage page);
	
	PageResult findAll(CommonPage page, Long localCateId);
	
	PageResult findGoods(Long localCateId, String conni, CommonPage page);
	
	void save(List<TbLocalGoods> models);
	
	void update(List<TbLocalGoods> models);
	
	void delete(List<TbLocalGoods> models);
}
