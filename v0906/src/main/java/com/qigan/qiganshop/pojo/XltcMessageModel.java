/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.util.List;

/**
 * @author wyy
 *
 */
public class XltcMessageModel {
	
	private String messageName;
	
	private String messageType;
	
	private List<Message> messages;

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
}
