/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.pojo.XltcReportDeliveryerModel;
import com.qigan.qiganshop.service.XltcReportDeleryerService;
import com.qigan.qiganshop.util.result.XltcResult;

/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/manages")
public class XltcReportDeliveryerController {
	
	@Autowired
	private XltcReportDeleryerService service;
	
	@RequestMapping("/list.do")
	public XltcResult selectAll(XltcReportDeliveryerModel model){
		return  XltcResult.ok(service.selectAll(model));
	}

	/**
	 * 后台订单配送员的配送情况
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/report.do")
	public XltcResult selectReportByUserId(XltcReportDeliveryerModel model){
		return XltcResult.ok(service.selectReportByUserId(model));
	}

}
