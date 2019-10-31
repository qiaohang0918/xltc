/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.service.XltcActivityService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;

/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/xltc/activity")
public class XltcActivityController {
	
	@Autowired
	private XltcActivityService service;
	
	@Autowired
	private JsonResult jr;
	
	@RequestMapping("/findAll.do")
	public JsonResult list(){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.findAll());
	}
	
	@RequestMapping("/insert.do")
	public JsonResult insert(String status, String... goodsId){
		service.insert(status, goodsId);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code());
	}
	
	@RequestMapping("/update.do")
	public JsonResult update(String status, Long... ids){
		service.update(status, ids);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code());
	}
	
	@RequestMapping("/delete.do")
	public JsonResult update(Long... ids){
		service.delete(ids);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code());
	}
	

}
