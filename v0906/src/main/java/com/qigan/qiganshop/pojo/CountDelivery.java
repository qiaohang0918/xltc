package com.qigan.qiganshop.pojo;

import java.io.Serializable;

public class CountDelivery implements Serializable {

    //合计
    private String totalDelivery="0";

    //完成递送
    private String finishDelivery="0";

    //问题递送
    private String questionDelivery="0";

    //其他问题递送
    private String otherUnknowDelivery="0";

    //正在配送的订单
    private String onSending="0";


    public CountDelivery(String totalDelivery, String finishDelivery, String questionDelivery,String onSending,Object pleaseIgnore) {
        this.totalDelivery = totalDelivery;
        this.finishDelivery = finishDelivery;
        this.questionDelivery = questionDelivery;
        this.onSending = onSending;
    }


    public CountDelivery(String totalDelivery, String finishDelivery, String questionDelivery) {
        this.totalDelivery = totalDelivery;
        this.finishDelivery = finishDelivery;
        this.questionDelivery = questionDelivery;
    }


    public String getOnSending() {
        return onSending;
    }

    public void setOnSending(String onSending) {
        this.onSending = onSending;
    }

    @Override
    public String toString() {
        return "CountDelivery{" +
                "totalDelivery='" + totalDelivery + '\'' +
                ", finishDelivery='" + finishDelivery + '\'' +
                ", questionDelivery='" + questionDelivery + '\'' +
                ", otherUnknowDelivery='" + otherUnknowDelivery + '\'' +
                ", onSending='" + onSending + '\'' +
                '}';
    }

    public String getTotalDelivery() {
        return totalDelivery;
    }

    public void setTotalDelivery(String totalDelivery) {
        this.totalDelivery = totalDelivery;
    }

    public String getFinishDelivery() {
        return finishDelivery;
    }

    public void setFinishDelivery(String finishDelivery) {
        this.finishDelivery = finishDelivery;
    }

    public String getQuestionDelivery() {
        return questionDelivery;
    }

    public void setQuestionDelivery(String questionDelivery) {
        this.questionDelivery = questionDelivery;
    }

    public String getOtherUnknowDelivery() {
        return otherUnknowDelivery;
    }

    public void setOtherUnknowDelivery(String otherUnknowDelivery) {
        this.otherUnknowDelivery = otherUnknowDelivery;
    }

    public CountDelivery(String totalDelivery, String finishDelivery, String questionDelivery, String otherUnknowDelivery) {
        this.totalDelivery = totalDelivery;
        this.finishDelivery = finishDelivery;
        this.questionDelivery = questionDelivery;
        this.otherUnknowDelivery = otherUnknowDelivery;
    }

    public CountDelivery() {
    }
}
