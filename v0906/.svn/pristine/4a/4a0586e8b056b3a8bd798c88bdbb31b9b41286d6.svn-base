/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qigan.qiganshop.domain.TbDictModel;
import com.qigan.qiganshop.domain.TbDictModelExample;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.TbDictModelMapper;
import com.qigan.qiganshop.service.XltcDictService;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class XltcDictServiceImpl implements XltcDictService {
	
	@Autowired
	private TbDictModelMapper mapper;

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDictService#insert(com.qigan.qiganshop.domain.TbDictModel)
	 */
	@Override
	public void insert(TbDictModel model) {
		// TODO Auto-generated method stub
		mapper.insertSelective(model);
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDictService#update(com.qigan.qiganshop.domain.TbDictModel)
	 */
	@Override
	public void update(TbDictModel model) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(model);
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDictService#delete(java.lang.String[])
	 */
	@Override
	public void delete(Long... ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			mapper.deleteByPrimaryKey(id);
		}
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDictService#selectAll()
	 */
	@Override
	public List<?> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDictService#selectByExample(com.qigan.qiganshop.domain.TbDictModel)
	 */
	@Override
	public List<?> selectByExample(TbDictModel model) {
		// TODO Auto-generated method stub
		if(model == null)
			throw XltcRuntimeException.throwable("type为空");
		
		TbDictModelExample example = new TbDictModelExample();
		example.createCriteria().andDictTypeEqualTo(model.getDictType());
		return mapper.selectByExample(example);
	}

}
