/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.math.BigDecimal;

/**
 * @author wyy
 *
 */
public class XltcReportDeliveryerModel {
	
	private Long id;
	
	//配送员姓名
	private String userName;
	
	//配送员id
	private String userId;
	
	//配送员真实产量
	private BigDecimal userProcdNum;
	
	//接单时间
	private String createDate;
	
	private String startDate;
	
	private String endDate;
	
	//派送订单号
	private String orderNum;

	//订单详情
	private Order order;

	//递送计数
	private CountDelivery countDelivery=new CountDelivery();

	public CountDelivery getCountDelivery() {
		return countDelivery;
	}

	public void setCountDelivery(CountDelivery countDelivery) {
		this.countDelivery = countDelivery;
	}

	private int page=0;

	private int rows=10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getUserProcdNum() {
		return userProcdNum;
	}

	public void setUserProcdNum(BigDecimal userProcdNum) {
		this.userProcdNum = userProcdNum;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "XltcReportDeliveryerModel [id=" + id + ", userName=" + userName + ", userId=" + userId
				+ ", userProcdNum=" + userProcdNum + ", createDate=" + createDate + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", orderNum=" + orderNum + "]";
	}
	
}
