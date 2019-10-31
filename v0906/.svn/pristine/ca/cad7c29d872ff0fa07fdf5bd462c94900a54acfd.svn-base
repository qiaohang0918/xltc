/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.domain.TbPushRecord;
import com.qigan.qiganshop.service.XltcMessageService;
import com.qigan.qiganshop.util.result.XltcResult;

/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/xltc/send")
public class XltcMessageController {
	
	@Autowired
	XltcMessageService service;
	
	@RequestMapping(value = "/jiguang.do", method = RequestMethod.POST)
	public XltcResult send2JiGuang(String message, String title, String pushNo, String msgType){
		service.send2JiGuang(message, title, pushNo, msgType);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/jiguangByUsers.do", method = RequestMethod.POST)
	public XltcResult send2JiGuangByUsers(String message, String title, String pushNo, String msgType, String... userIds){
		service.send2JiGuang(message, title, pushNo, msgType, userIds);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
	public XltcResult findAll(TbPushRecord model){
		return XltcResult.ok(service.selectPushAll(model));
	}
	
	
	
	

}
