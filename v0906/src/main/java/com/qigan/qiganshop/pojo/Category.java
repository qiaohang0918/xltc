package com.qigan.qiganshop.pojo;

import com.qigan.qiganshop.pojo.group.GroupLabel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 商品分类信息
 *
 * @author wanghao
 */
public class Category implements Serializable {
    @ApiModelProperty(value = "分类 Id")
    private String cateId;
    @ApiModelProperty(value = "分类名称")
    private String cateName;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "标签列表")
    private List<GroupLabel> labelList;
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;
    private String cateImage;
    private List<?> labels;
    private boolean changeInterface;
    private boolean hasChild;
    
    public List<?> getLabels() {
		return labels;
	}

	public void setLabels(List<?> labels) {
		this.labels = labels;
	}

	public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public List<GroupLabel> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<GroupLabel> labelList) {
        this.labelList = labelList;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

	public String getCateImage() {
		return cateImage;
	}

	public void setCateImage(String cateImage) {
		this.cateImage = cateImage;
	}

	public boolean isChangeInterface() {
		return changeInterface;
	}

	public void setChangeInterface(boolean changeInterface) {
		this.changeInterface = changeInterface;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
    
}
