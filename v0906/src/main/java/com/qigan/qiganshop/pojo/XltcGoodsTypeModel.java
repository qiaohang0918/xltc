/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.util.List;

/**
 * @author wyy
 *
 */
public class XltcGoodsTypeModel {
	
	private String typeId;
	
	private String typeName;
	
	private String typeImage;
	
	private List<XltcGoodsModel> children;
	
	private List<XltcGoodsLabelModel> lables;

	public XltcGoodsTypeModel() {
		
	}
	
	public XltcGoodsTypeModel(String typeId, String typeName, String typeImage) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeImage = typeImage;
	}
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<XltcGoodsModel> getChildren() {
		return children;
	}

	public void setChildren(List<XltcGoodsModel> children) {
		this.children = children;
	}

	public List<XltcGoodsLabelModel> getLables() {
		return lables;
	}

	public void setLables(List<XltcGoodsLabelModel> lables) {
		this.lables = lables;
	}

	public String getTypeImage() {
		return typeImage;
	}

	public void setTypeImage(String typeImage) {
		this.typeImage = typeImage;
	}

	@Override
	public String toString() {
		return "XltcGoodsTypeModel [typeId=" + typeId + ", typeName=" + typeName + ", typeImage=" + typeImage
				+ ", children=" + children + ", lables=" + lables + "]";
	}
	
}
