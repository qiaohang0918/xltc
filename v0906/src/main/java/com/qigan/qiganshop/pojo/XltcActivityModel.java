/**
 * 
 */
package com.qigan.qiganshop.pojo;

/**
 * @author wyy
 *
 */
public class XltcActivityModel {
	
	private Long id;
	
	private String goodsId;
	
	private String status;
	
	private String createDate;
	
	private String updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "XltcActivityModel [id=" + id + ", goodsId=" + goodsId + ", status=" + status + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
	
}
