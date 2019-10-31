/**
 * 
 */
package com.qigan.qiganshop.service;

import java.util.List;

import com.qigan.qiganshop.domain.TbDictModel;

/**
 * @author wyy
 *
 */
public interface XltcDictService {
	
	void insert(TbDictModel model);
	
	void update(TbDictModel model);
	
	void delete(Long... ids);
	
	List<?> selectAll();
	
	List<?> selectByExample(TbDictModel model);

}
