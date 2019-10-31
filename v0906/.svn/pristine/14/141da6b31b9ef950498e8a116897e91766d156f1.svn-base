/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.TbOrderCommentModel;
import com.qigan.qiganshop.service.TbOrderCommentService;
import com.qigan.qiganshop.util.result.XltcResult;

/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/order/comment")
public class TbOrderCommentController {
	
	@Autowired
	private TbOrderCommentService service;
	
	@RequestMapping(value = "/findPageAll.do", method = RequestMethod.POST)
	public XltcResult findAll(CommonPage page){
		return XltcResult.ok(service.findAll(page));
	}
	
	@RequestMapping(value = "/findByOrder.do", method = RequestMethod.POST)
	public XltcResult findByOrder(String orderId){
		return XltcResult.ok(service.findByOrder(orderId));
	}
	
	@RequestMapping(value = "/findReport.do", method = RequestMethod.POST)
	public XltcResult findReport(TbOrderCommentModel model){
		return XltcResult.ok(service.findReport(model));
	}
	
	@RequestMapping("/test.do")
	public Object test(@ModelAttribute Map<String, String> param){
		return param;
	}
	
	

}
