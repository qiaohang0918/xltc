/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.util.List;

/**
 * @author Admin
 *
 */
public class XltcPageResult {

	public int nowPage;

	public int totalPage;

	public long totalData;

	public List<?> result_list;

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getResult_list() {
		return result_list;
	}

	public void setResult_list(List<?> result_list) {
		this.result_list = result_list;
	}

	public long getTotalData() {
		return totalData;
	}

	public void setTotalData(long totalData) {
		this.totalData = totalData;
	}

	@Override
	public String toString() {
		return "XltcPageResult [nowPage=" + nowPage + ", totalPage=" + totalPage + ", totalData=" + totalData
				+ ", result_list=" + result_list + "]";
	}
	
}
