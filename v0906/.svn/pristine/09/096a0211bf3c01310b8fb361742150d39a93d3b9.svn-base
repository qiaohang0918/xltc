/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.XltcActivityMapper;
import com.qigan.qiganshop.pojo.XltcActivityModel;
import com.qigan.qiganshop.service.XltcActivityService;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class XltcActivityServiceImpl implements XltcActivityService{

	@Autowired
	XltcActivityMapper actMapper;
	
	@Override
	public void insert(String status, String... goodsId) {
		// TODO Auto-generated method stub
		if(goodsId == null)
			throw XltcRuntimeException.throwable("商品id为空");
		List<XltcActivityModel> list = new ArrayList<>();
		for (String string : goodsId) {
			XltcActivityModel activity = new XltcActivityModel();
			activity.setGoodsId(string);
			activity.setStatus(status);
			list.add(activity);
		}
		actMapper.insert(list);
	}

	@Override
	public void update(String status, Long... ids) {
		// TODO Auto-generated method stub
		if(ids == null)
			throw XltcRuntimeException.throwable("商品id为空");
		for (Long id : ids) {
			actMapper.delete(status, id);
		}
	}

	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		return actMapper.findAll();
	}

	@Override
	public void delete(Long... ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			actMapper.deleteById(id);
		}
	}

	@Override
	public List<?> findGoodsId() {
		// TODO Auto-generated method stub
		return actMapper.findGoodsId();
	}

}
