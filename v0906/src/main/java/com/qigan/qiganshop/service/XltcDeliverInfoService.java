/**
 * 
 */
package com.qigan.qiganshop.service;

import java.util.List;

import com.qigan.qiganshop.domain.TbDeliverInfoModel;

/**
 * @author wyy
 *
 */
public interface XltcDeliverInfoService {

	void insert(TbDeliverInfoModel model);

	void update(TbDeliverInfoModel model);

	void delete(Long... ids);

	List<?> selectAll();

	List<?> selectByExample(TbDeliverInfoModel model);

}
