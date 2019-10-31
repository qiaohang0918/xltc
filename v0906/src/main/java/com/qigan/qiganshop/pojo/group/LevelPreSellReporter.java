package com.qigan.qiganshop.pojo.group;

import java.io.Serializable;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/9 15:59
 * @Modified By:
 */
//分期预售报表
public class LevelPreSellReporter implements Serializable {

    //订单总和
    private String sumOrder = "0";

    //销量总和(实际发售)
    private String realSellOut = "0";

    //销售额度总和
    private String realSellMoney = "0.00";

    //其他订单状态商品数总和（退款订单商品总和）
    private String questionGoodsCount = "0";

    //预售商品code
    private String preSellGoodsCode = null;

    //批次
    private String level;

    public String getPreSellGoodsCode() {
        return preSellGoodsCode;
    }

    public void setPreSellGoodsCode(String preSellGoodsCode) {
        this.preSellGoodsCode = preSellGoodsCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(String sumOrder) {
        this.sumOrder = sumOrder;
    }

    public String getRealSellOut() {
        return realSellOut;
    }

    public void setRealSellOut(String realSellOut) {
        this.realSellOut = realSellOut;
    }

    public String getRealSellMoney() {
        return realSellMoney;
    }

    public void setRealSellMoney(String realSellMoney) {
        this.realSellMoney = realSellMoney;
    }

    public String getQuestionGoodsCount() {
        return questionGoodsCount;
    }

    public void setQuestionGoodsCount(String questionGoodsCount) {
        this.questionGoodsCount = questionGoodsCount;
    }
}
