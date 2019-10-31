/**
 * 
 */
package com.qigan.qiganshop.controller.frontend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.annocation.XltcLogRecord;
import com.qigan.qiganshop.pojo.group.GroupOrder;
import com.qigan.qiganshop.service.XltcOrderServiceConcat;

/**
 * @author wyy
 *
 */
@RequestMapping("/test/ttt")
@RestController
public class TestController {
	
	@Autowired
	XltcOrderServiceConcat service;
	

	@RequestMapping("/order.do")
	public void test(){
		
	}
	
	@RequestMapping("/order1.do")
	@XltcLogRecord
	public String test(String status, String orderId, String no, String... ids){
		return status + orderId + no + ids.length;
		
	}
	
}
