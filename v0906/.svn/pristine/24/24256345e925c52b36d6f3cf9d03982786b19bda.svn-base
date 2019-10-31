package com.qigan.qiganshop.pojo.group;

import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.Label;
import com.qigan.qiganshop.pojo.XltcGoodsModel;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class GroupLabel extends Label {

	public GroupLabel(){
		
	}
	
    public GroupLabel(Label label, List<Goods> goodsList) {
        super(label.getLabelId(), label.getLabelName(), label.getCateId(), label.getSort());
        this.goodsList = goodsList;
    }
    
    public GroupLabel(Label label, List<XltcGoodsModel> goodsList, String a) {
    	super(label.getLabelId(), label.getLabelName(), label.getCateId(), label.getSort());
    	this.xlGoodsList = goodsList;
    }
    
    private List<Goods> goodsList;
    
    private List<XltcGoodsModel> xlGoodsList;
    
    public List<XltcGoodsModel> getXlGoodsList() {
		return xlGoodsList;
	}

	public void setXlGoodsList(List<XltcGoodsModel> xlGoodsList) {
		this.xlGoodsList = xlGoodsList;
	}

	public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
