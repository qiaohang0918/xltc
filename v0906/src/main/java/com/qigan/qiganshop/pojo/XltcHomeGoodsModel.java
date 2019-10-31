/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.util.List;

/**
 * @author wyy
 *
 */
public class XltcHomeGoodsModel {
	
	private List<?> headTypes;
	
	private List<?> centers;
	
	private List<?> bottoms;

	public List<?> getHeadTypes() {
		return headTypes;
	}

	public void setHeadTypes(List<?> headTypes) {
		this.headTypes = headTypes;
	}

	public List<?> getCenters() {
		return centers;
	}

	public void setCenters(List<?> centers) {
		this.centers = centers;
	}

	public List<?> getBottoms() {
		return bottoms;
	}

	public void setBottoms(List<?> bottoms) {
		this.bottoms = bottoms;
	}

	@Override
	public String toString() {
		return "XltcHomeGoodsModel [headTypes=" + headTypes + ", centers=" + centers + ", bottoms=" + bottoms + "]";
	}
	
}
