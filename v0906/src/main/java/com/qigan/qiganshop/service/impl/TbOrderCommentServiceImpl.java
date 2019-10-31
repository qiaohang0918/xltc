/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.domain.TbOrderComment;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.TbOrderCommentMapper;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.TbOrderCommentModel;
import com.qigan.qiganshop.service.TbOrderCommentService;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class TbOrderCommentServiceImpl implements TbOrderCommentService {
	
	@Autowired
	private TbOrderCommentMapper mapper;

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.TbOrderCommentService#save(com.qigan.qiganshop.domain.TbOrderComment)
	 */
	@Override
	public void save(TbOrderComment model) {
		// TODO Auto-generated method stub
		if(model == null)
			throw XltcRuntimeException.throwable("评论异常，请稍后再试");
		
		TbOrderComment comment = this.findByOrder(model.getOrderid());
		if(comment != null)
			throw XltcRuntimeException.throwable("抱歉，您已评论过了");
		mapper.insertSelective(model);
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.TbOrderCommentService#findAll(com.qigan.qiganshop.pojo.CommonPage)
	 */
	@Override
	public PageResult findAll(CommonPage page) {
		// TODO Auto-generated method stub
		page.startPageHelper();
		PageInfo<?> pageInfo = new PageInfo<>(mapper.selectByExample(null));
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.TbOrderCommentService#findReport()
	 */
	@Override
	public PageResult findReport(TbOrderCommentModel model) {
		// TODO Auto-generated method stub
		if(model == null)
			throw XltcRuntimeException.throwable("参数不全，请检查参数");
		model.startPageHelper();
		PageInfo<?> pageInfo = new PageInfo<>(mapper.selectReport(model));
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	/* (non-Javadoc)
	 * @see com.qigan.qiganshop.service.TbOrderCommentService#findByOrder(java.lang.String)
	 */
	@Override
	public TbOrderComment findByOrder(String orderId) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(orderId))
			throw XltcRuntimeException.throwable("系统异常，请稍后再试");
		return mapper.selectByOrderId(orderId);
	}

}
