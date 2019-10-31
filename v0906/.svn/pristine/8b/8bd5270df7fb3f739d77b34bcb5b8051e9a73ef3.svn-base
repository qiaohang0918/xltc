/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.domain.TbLocalCategory;
import com.qigan.qiganshop.service.TbLocalCategoryService;
import com.qigan.qiganshop.util.result.XltcResult;


/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/local/cate")
public class TbLocalCategoryController {
	
	@Autowired
	private TbLocalCategoryService service;
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public XltcResult save(TbLocalCategory model){
		service.save(model);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public XltcResult update(TbLocalCategory model){
		service.update(model);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public XltcResult delete(Long... ids){
		service.delete(ids);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/enable.do", method = RequestMethod.POST)
	public XltcResult enable(String enable, Long... ids){
		service.enable(enable, ids);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/sort.do", method = RequestMethod.POST)
	public XltcResult sort(int sort, Long id){
		service.sort(sort, id);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
	public XltcResult findAllLocalCate(){
		return XltcResult.ok(service.findAllLocalCate());
	}
	
	@RequestMapping(value = "/findAllByEnable.do", method = RequestMethod.POST)
	public XltcResult findAllLocalCateByEnable(String enable){
		return XltcResult.ok(service.findAllLocalCateByEnable(enable));
	}
	
	@RequestMapping(value = "/findOne.do", method = RequestMethod.POST)
	public XltcResult findOne(Long id){
		return XltcResult.ok(service.findOne(id));
	}
	
	@RequestMapping(value = "/findOneByEnable.do", method = RequestMethod.POST)
	public XltcResult findOneByEnable(Long id, String enable){
		return XltcResult.ok(service.findOneByEnable(id, enable));
	}
	
	@RequestMapping(value = "/findAllCate.do", method = RequestMethod.POST)
	public XltcResult findAllCate(){
		return XltcResult.ok(service.findAllCate());
	}
	
	
	
}
