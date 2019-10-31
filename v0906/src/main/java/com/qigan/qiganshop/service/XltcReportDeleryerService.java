/**
 * 
 */
package com.qigan.qiganshop.service;

import java.util.List;

import com.qigan.qiganshop.pojo.XltcReportDeliveryerModel;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * @author wyy
 *
 */
public interface XltcReportDeleryerService {
	
	void insert(XltcReportDeliveryerModel model);

	PageResult selectAll(XltcReportDeliveryerModel model);
	
	List<?> selectReportByUserId(XltcReportDeliveryerModel model);
	
}
