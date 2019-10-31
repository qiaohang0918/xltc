/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.domain.TbLocalGoods;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.service.TbLocalCategoryGoodsService;
import com.qigan.qiganshop.util.result.XltcResult;

/**
 * @author wyy
 *
 */
@RestController
@RequestMapping("/local/goods")
public class TbLocalCategoryGoodsController {
	
	@Autowired
	private TbLocalCategoryGoodsService service;
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public XltcResult save(@RequestBody List<TbLocalGoods> models){
		service.save(models);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public XltcResult update(@RequestBody List<TbLocalGoods> models){
		service.update(models);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public XltcResult delete(@RequestBody List<TbLocalGoods> models){
		service.delete(models);
		return XltcResult.ok();
	}
	
	@RequestMapping(value = "/findAllByLocalCateId.do", method = RequestMethod.POST)
	public XltcResult findAllByLocalCateId(Long localCateId){
		return XltcResult.ok(service.findAllByLocalCateId(localCateId));
	}
	
	@RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
	public XltcResult findAll(CommonPage page){
		return XltcResult.ok(service.findAll(page));
	}
	
	@RequestMapping(value = "/findAllByPage.do", method = RequestMethod.POST)
	public XltcResult findAll(CommonPage page, Long localCateId){
		return XltcResult.ok(service.findAll(page, localCateId));
	}
	
	@RequestMapping(value = "/findGoods.do", method = RequestMethod.POST)
	public XltcResult findGoodsByLocalCate(Long localCateId, String cooni, CommonPage page){
		return XltcResult.ok(service.findGoods(localCateId, cooni, page));
	}

}
