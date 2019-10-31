/**
 * 
 */
package com.qigan.qiganshop.service;

import java.util.List;

import com.qigan.qiganshop.domain.TbLocalCategory;
import com.qigan.qiganshop.pojo.Category;

/**
 * @author wyy
 *
 */
public interface TbLocalCategoryService {
	
	List<?> findAllLocalCate();
	
	List<?> findAllLocalCateByEnable(String enable);
	
	List<Category> findAll2Category(String enable);
	
	TbLocalCategory findOne(Long id);
	
	Category findOneById(String id);
	
	TbLocalCategory findOneByEnable(Long id, String enable);
	
	void save(TbLocalCategory model);
	
	void update(TbLocalCategory model);
	
	void delete(Long... ids);
	
	void enable(String enable, Long... ids);
	
	void sort(int sort, Long id);
	
	List<?> findAllCate();
	
	

}
