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
import com.qigan.qiganshop.domain.TbPushRecord;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.MessageMapper;
import com.qigan.qiganshop.mapper.TbPushRecordMapper;
import com.qigan.qiganshop.pojo.JiGuangSendModel;
import com.qigan.qiganshop.service.XltcMessageService;
import com.qigan.qiganshop.util.jiguang.JiGuangSend;
import com.qigan.qiganshop.util.result.PageResult;

import cn.jpush.api.push.PushResult;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class XltcMessageServiceImpl implements XltcMessageService {

	@Autowired
	JiGuangSend jiguangSend;

	@Autowired
	MessageMapper mapper;

	@Autowired
	TbPushRecordMapper pushMapper;

	private static final String SEND_ERR_MESSAGE = "推送失败，请稍后重试";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qigan.qiganshop.service.XltcMessageService#send2JiGuang(java.lang.
	 * String)
	 */
	@Override
	public void send2JiGuang(String message, String title, String pushNo, String msgType) {
		// TODO Auto-generated method stub
		this.send2JiGuang(message, title, pushNo, msgType, new String[] {});
	}

	private void sendJiGuangMessage(String message, String title, String pushNo, String msgType, String... userIds) {
		if(StringUtils.isAllBlank(message, title, pushNo))
			throw XltcRuntimeException.throwable("推送信息不完整，请检查参数");
		
		int count = userIds.length;
		String type = "2";
		if (userIds.length == 0) {
			count = this.send(message, title, pushNo, null, msgType);
			type = "1";
		} else {
			for (String userId : userIds) {
				this.send(message, title, pushNo, userId, msgType);
			}
		}
		TbPushRecord record = new TbPushRecord();
		record.setPushMessage(message);
		record.setPushTitle(title);
		record.setPushNum(count);
		record.setPushNo(pushNo);
		record.setPushType(type);
		pushMapper.insertSelective(record);

	}

	private int send(String message, String title, String pushNo, String userId, String type) {
		JiGuangSendModel model = new JiGuangSendModel(userId, message, title);
		PushResult push = jiguangSend.send(model);
		if (push == null)
			throw XltcRuntimeException.throwable(SEND_ERR_MESSAGE);

		if (push.getResponseCode() != 200)
			throw XltcRuntimeException.throwable(SEND_ERR_MESSAGE);
		
		int count = 0;
		if(StringUtils.isNotBlank(userId)){
			
		}else{
			count = mapper.insertJGMessage(message, title, type);
		}
		return count;
	}

	@Override
	public PageResult selectPushAll(TbPushRecord model) {
		// TODO Auto-generated method stub
		model.startPageHelper();
		List<?> list = pushMapper.selectByCodition(model);
		PageInfo<?> pageResult = new PageInfo<>(list);
		return new PageResult(pageResult.getTotal(), list);
	}

	@Override
	public void send2JiGuang(String message, String title, String pushNo, String msgType, String... userIds) {
		// TODO Auto-generated method stub
		this.sendJiGuangMessage(message, title, pushNo, msgType, userIds);
	}

}
