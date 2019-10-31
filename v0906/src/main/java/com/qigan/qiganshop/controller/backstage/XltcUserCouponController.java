/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.UserCoupon;
import com.qigan.qiganshop.service.UserCouponService;
import com.qigan.qiganshop.util.result.XltcResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;

/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/user/coupan")
public class XltcUserCouponController {
	
	@Autowired
	UserCouponService service;
	
	@Autowired
	JsonResult jr;
	
	@RequestMapping("/findOne.do")
	public JsonResult findOne(String userCouponId){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.findOne(userCouponId));
	}
	
	@RequestMapping("/findByUserId.do")
	public JsonResult findByUserIdAndCouponId(String token, String couponId){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.findByUserIdAndCouponId(service.checkToken(token), couponId));
	}
	
	@RequestMapping("/findAll.do")
	public JsonResult findAll(CommonPage page, String token){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.findAll(token, page));
	}
	
	@RequestMapping("/findByconditions.do")
	public JsonResult findAll(CommonPage page, String token, String type){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.findByConditions(page, token, type));
	}
	
	@RequestMapping("/findPage.do")
	public JsonResult findByUserIdAndCouponId(CommonPage page, String token){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.findPage(service.checkToken(token), page.getPage(), page.getSize()));
	}
	
	@RequestMapping("/add.do")
	public JsonResult add(@RequestBody List<UserCoupon> userCoupon){
		for (UserCoupon coupon : userCoupon) {
			service.add(coupon, true);
		}
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功");
	}
	
	@RequestMapping("/update.do")
	public JsonResult update(@RequestBody UserCoupon userCoupon){
		service.update(userCoupon);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功");
	}
	
	@RequestMapping("/delete.do")
	public JsonResult update(String... userCouponIds){
		service.delete(userCouponIds);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功");
	}
	
	@RequestMapping("/isShow.do")
	public JsonResult isShow(String token){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.isShowReceive(token));
	}
	
	@RequestMapping("/findList.do")
	public JsonResult findCanReceiveAndCanUse(String token, String mobileId){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.findCanReceiveAndCanUse(token, mobileId));
	}
	
	@RequestMapping("/report.do")
	public XltcResult findReport(@RequestBody UserCoupon coupon){
		return XltcResult.ok(service.findReport(coupon));
	}
	
	@RequestMapping("/findUserCanUse.do")
	public XltcResult findUserCanUse(String token, Double fullMoney){
		return XltcResult.ok(service.findUserCanUse(token, fullMoney));
	}
	
	@RequestMapping("/findPieData.do")
	public XltcResult findPieData(String start, String end){
		return XltcResult.ok(service.findCouponPieData(start, end));
	}
	
	@RequestMapping("/findAllData.do")
	public XltcResult findAllData(Goods goods, String couponId, String type){
		return XltcResult.ok(service.findAllData(goods, couponId, type));
	}
	
	@RequestMapping("/findAllCoupon.do")
	public XltcResult findAllCoupon(String token, Double fullMoney, String mobileId, String... codes){
		return XltcResult.ok(service.findAllCoupon(token, fullMoney, mobileId, codes));
	}
	
	@RequestMapping("/findAllCoupon4App.do")
	public XltcResult findAllCoupon4App(String token, String goodsId, String mobileId, String index) {
		return XltcResult.ok(service.findAllCoupon4App(token, goodsId, mobileId, index));
	}
	
	@RequestMapping("/sendCoupon2User.do")
	public XltcResult sendCoupon2User(String no, String[] userIds, String... couponIds){
		service.sendCoupon2User(no, userIds, couponIds);
		return XltcResult.ok();
	}
	
	@RequestMapping("/chenUserCanSend.do")
	public XltcResult chenUserCanSend(String userPhone){
		return XltcResult.ok(service.chenUserCanSend(userPhone));
	}
	
	@RequestMapping("/findSendCouponAll.do")
	public XltcResult findSendCouponAll(@RequestBody UserCoupon coupon){
		return XltcResult.ok(service.findSendCouponAll(coupon));
	}
	
}
