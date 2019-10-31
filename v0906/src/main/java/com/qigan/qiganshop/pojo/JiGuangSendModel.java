/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.util.Map;

/**
 * @author wyy
 *
 */
public class JiGuangSendModel {
	
	private String alias;
	
	private String alert;
	
	private String content;
	
	private Map<String, String> extras;
	
	public JiGuangSendModel() {
		
	}
	
	public JiGuangSendModel(String alias, String alert, String content) {
		this.alias = alias;
		this.alert = alert;
		this.content = content;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}

	@Override
	public String toString() {
		return "JiGuangSendModel [alias=" + alias + ", alert=" + alert + ", content=" + content + ", extras=" + extras
				+ "]";
	}

}
