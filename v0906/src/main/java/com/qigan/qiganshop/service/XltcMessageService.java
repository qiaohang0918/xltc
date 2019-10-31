/**
 * 
 */
package com.qigan.qiganshop.service;

import com.qigan.qiganshop.domain.TbPushRecord;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * @author wyy
 *
 */
public interface XltcMessageService {
	
	void send2JiGuang(String message, String title, String pushNo, String msgType);
	
	void send2JiGuang(String message, String title, String pushNo, String msgType, String... userIds);
	
	PageResult selectPushAll(TbPushRecord model);

}
