/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.enums.OrderStatus;
import com.qigan.qiganshop.util.result.XltcResult;

/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/xltc/basic")
public class XltcBasicController {
	
	@RequestMapping("/orderStatus.do")
	public XltcResult selectOrderStatus(){
		return XltcResult.ok(OrderStatus.getOrderStatusAll());
	}
	
	@RequestMapping("/orderStatusMap.do")
	public XltcResult selectOrderStatusMap(){
		return XltcResult.ok(OrderStatus.getOrderStatusAllMap());
	}

}
