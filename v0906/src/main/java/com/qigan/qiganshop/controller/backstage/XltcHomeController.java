/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.service.XltcHomeService;
import com.qigan.qiganshop.util.result.XltcResult;

/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/home")
public class XltcHomeController {

	@Autowired
	XltcHomeService service;
	
	@RequestMapping("/list.do")
	public XltcResult home(CommonPage page){
		return XltcResult.ok(service.home(page));
	}

	@RequestMapping("/listIOS.do")
	public XltcResult homeIOS(CommonPage page){
		return XltcResult.ok(service.homeIOS(page));
	}
	
	@RequestMapping("/isShowCoupon.do")
	public XltcResult isShowCoupon(String token){
		return XltcResult.ok(service.isShowCoupon(token));
	}
	
	@RequestMapping("/showAlertAds.do")
	public XltcResult isShowAlertAds(String coni){
		return XltcResult.ok(service.isShowAlertAds(coni));
	}
	
	@RequestMapping("/isShowAlert.do")
	public XltcResult isShowAlert(String token){
		return XltcResult.ok(service.isShowAlert(token));
	}
	
	@RequestMapping("/indexImg.do")
	public XltcResult indexImg(String coordinate){
		return XltcResult.ok(service.indexImg(coordinate));
	}
	
}
