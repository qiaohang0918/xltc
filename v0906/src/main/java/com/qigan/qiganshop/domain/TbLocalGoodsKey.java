package com.qigan.qiganshop.domain;

public class TbLocalGoodsKey {
	
    public TbLocalGoodsKey() {
	}

	public TbLocalGoodsKey(Long categoryId, String tbGoodsid) {
		super();
		this.categoryId = categoryId;
		this.tbGoodsid = tbGoodsid;
	}

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_local_goods.category_id
     *
     * @mbg.generated
     */
    private Long categoryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_local_goods.tb_goodsId
     *
     * @mbg.generated
     */
    private String tbGoodsid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_local_goods.category_id
     *
     * @return the value of tb_local_goods.category_id
     *
     * @mbg.generated
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_local_goods.category_id
     *
     * @param categoryId the value for tb_local_goods.category_id
     *
     * @mbg.generated
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_local_goods.tb_goodsId
     *
     * @return the value of tb_local_goods.tb_goodsId
     *
     * @mbg.generated
     */
    public String getTbGoodsid() {
        return tbGoodsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_local_goods.tb_goodsId
     *
     * @param tbGoodsid the value for tb_local_goods.tb_goodsId
     *
     * @mbg.generated
     */
    public void setTbGoodsid(String tbGoodsid) {
        this.tbGoodsid = tbGoodsid == null ? null : tbGoodsid.trim();
    }
}