/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.domain.TbDeliverInfoModel;
import com.qigan.qiganshop.service.XltcDeliverInfoService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;

/**
 * @author wyy
 *
 */
@RequestMapping("/deliver/info")
@RestController
public class XltcDeliverInfoController {
	
	@Autowired
	private JsonResult jr;
	
	@Autowired
	private XltcDeliverInfoService service;
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public JsonResult insert(@ModelAttribute TbDeliverInfoModel model){
		service.insert(model);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code());
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public JsonResult update(TbDeliverInfoModel model){
		service.update(model);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code());
	}
	
	@RequestMapping("/delete.do")
	public JsonResult delete(Long... ids){
		service.delete(ids);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code());
	}
	
	@RequestMapping("/listAll.do")
	public JsonResult listAll(){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.selectAll());
	}
	
	@RequestMapping("/list.do")
	public JsonResult list(TbDeliverInfoModel model){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.selectByExample(model));
	}

}
