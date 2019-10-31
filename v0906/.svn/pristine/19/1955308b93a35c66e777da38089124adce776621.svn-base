/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.domain.TbDictModel;
import com.qigan.qiganshop.service.XltcDictService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;

/**
 * @author wyy
 *
 */
@RequestMapping("/dict")
@RestController
public class XltcDictController {
	
	@Autowired
	private JsonResult jr;
	
	@Autowired
	private XltcDictService service;
	
	@RequestMapping("/insert.do")
	public JsonResult insert(TbDictModel model){
		service.insert(model);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code());
	}
	
	@RequestMapping("/update.do")
	public JsonResult update(TbDictModel model){
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
	public JsonResult list(TbDictModel model){
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", service.selectByExample(model));
	}

}
