/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.domain.TbLocalGoods;
import com.qigan.qiganshop.domain.TbLocalGoodsExample;
import com.qigan.qiganshop.domain.TbLocalGoodsKey;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.TbLocalGoodsMapper;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.service.TbLocalCategoryGoodsService;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class TbLocalCategoryGoodsServiceImpl implements TbLocalCategoryGoodsService {
	
	@Autowired
	private TbLocalGoodsMapper mapper;

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.TbLocalCategoryGoodsService#findAllByLocalCateId(java.lang.Long)
	 */
	@Override
	public List<?> findAllByLocalCateId(Long localCateId) {
		// TODO Auto-generated method stub
		TbLocalGoodsExample example = new TbLocalGoodsExample();
		example.createCriteria().andCategoryIdEqualTo(localCateId);
		return mapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.TbLocalCategoryGoodsService#save(java.util.List)
	 */
	@Override
	public void save(List<TbLocalGoods> models) {
		// TODO Auto-generated method stub
		for (TbLocalGoods model : models) {
			mapper.insertSelective(model);
		}
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.TbLocalCategoryGoodsService#update(java.util.List)
	 */
	@Override
	public void update(List<TbLocalGoods> models) {
		// TODO Auto-generated method stub
		for (TbLocalGoods model : models) {
			mapper.updateByPrimaryKeySelective(model);
			
		}
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.TbLocalCategoryGoodsService#delete(java.util.List)
	 */
	@Override
	public void delete(List<TbLocalGoods> models) {
		// TODO Auto-generated method stub
		for (TbLocalGoods model : models) {
			TbLocalGoodsKey key = new TbLocalGoodsKey(model.getCategoryId(), model.getTbGoodsid());
			mapper.deleteByPrimaryKey(key);
		}
	}

	@Override
	public PageResult findAll(CommonPage page) {
		// TODO Auto-generated method stub
		page.startPageHelper();
		PageInfo<?> pageInfo = new PageInfo<>(mapper.selectByExample(null));
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	@Override
	public PageResult findAll(CommonPage page, Long localCateId) {
		// TODO Auto-generated method stub
		TbLocalGoodsExample example = new TbLocalGoodsExample();
		example.createCriteria().andCategoryIdEqualTo(localCateId);
		page.startPageHelper();
		PageInfo<?> pageInfo = new PageInfo<>(mapper.selectByExample(example));
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	@Override
	public List<?> findAllByLocalCateId(Long localCateId, String enable) {
		// TODO Auto-generated method stub
		enable = StringUtils.isBlank(enable) ? "1" : enable;
		TbLocalGoodsExample example = new TbLocalGoodsExample();
		example.createCriteria().andCategoryIdEqualTo(localCateId).andGoodsEnableEqualTo(enable);
		return mapper.selectByExample(example);
	}

	@Override
	public PageResult findGoods(Long localCateId, String conni, CommonPage page) {
		// TODO Auto-generated method stub
		if(localCateId == null) 
			throw XltcRuntimeException.throwable("参数不完整");
		page.startPageHelper();
		PageInfo<?> pageInfo = new PageInfo<>(mapper.selectGoods(localCateId));
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
		
	}

}
