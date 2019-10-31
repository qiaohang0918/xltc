/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qigan.qiganshop.domain.TbDeliverInfoModel;
import com.qigan.qiganshop.domain.TbDeliverInfoModelExample;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.TbDeliverInfoModelMapper;
import com.qigan.qiganshop.service.DeliveryOrderService;
import com.qigan.qiganshop.service.XltcDeliverInfoService;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class XltcDeliverInfoServiceImpl implements XltcDeliverInfoService {

	@Autowired
	private TbDeliverInfoModelMapper mapper;
	
	@Autowired
	private DeliveryOrderService diliverService;
	
	private static final String OTHER = "other";
	
	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDeliverInfoService#insert(com.qigan.qiganshop.domain.TbDeliverInfoModel)
	 */
	
	@Override
	public void insert(TbDeliverInfoModel model) {
		// TODO Auto-generated method stub
		if(model == null){
			throw XltcRuntimeException.throwable("信息为空，请联系管理员");
		}
		mapper.insertSelective(model);
		String orderIds[] = model.getOrderId().split(",");
		for (String id : orderIds) {
			changeOrderStatus(id, model.getDictType(), model.getUserId(), model.getContent());
		}
	}
	
	private void changeOrderStatus(String id, String type, String userId, String content){
		
		
		
		switch (type) {
		case OTHER:
			diliverService.question(Integer.valueOf(userId), id, content);
			break;

		default:
			diliverService.otherStatus(Integer.valueOf(userId), id, type);
			break;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDeliverInfoService#update(com.qigan.qiganshop.domain.TbDeliverInfoModel)
	 */
	@Override
	public void update(TbDeliverInfoModel model) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(model);
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDeliverInfoService#delete(java.lang.Long[])
	 */
	@Override
	public void delete(Long... ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			mapper.deleteByPrimaryKey(id);
		}
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDeliverInfoService#selectAll()
	 */
	@Override
	public List<?> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.XltcDeliverInfoService#selectByExample(com.qigan.qiganshop.domain.TbDeliverInfoModel)
	 */
	@Override
	public List<?> selectByExample(TbDeliverInfoModel model) {
		// TODO Auto-generated method stub
		if(model == null)
			return this.selectAll();
		TbDeliverInfoModelExample example = new TbDeliverInfoModelExample();
		example.createCriteria().andUserIdEqualTo(model.getUserId())
						.andOrderIdEqualTo(model.getOrderId());
		return mapper.selectByExample(example);
	}

}
