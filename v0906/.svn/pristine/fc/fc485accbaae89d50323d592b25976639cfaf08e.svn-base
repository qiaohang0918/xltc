package com.qigan.qiganshop.util.pay.wxpay;

public class Refund {

    private String appid; //公众号id
    private String mch_id; //微信支付分配的商户号
    private String nonce_str; //随机字符串
    private String sign; //签名
    private String body; //商品描述
    private String out_trade_no; //商户订单号
    private int total_fee; //标价金额
    private String spbill_create_ip; //终端ip
    private String notify_url; //通知地址
    private String trade_type; //交易类型 JSAPI

    private int refund_fee;
    private String out_refund_no;

    public int getRefund_fee() {
        return refund_fee;
    }
    public void setRefund_fee(int refund_fee) {
        this.refund_fee = refund_fee;
    }
    public String getOut_refund_no() {
        return out_refund_no;
    }
    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMch_id() {
        return mch_id;
    }
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
    public String getNonce_str() {
        return nonce_str;
    }
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public int getTotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public String getNotify_url() {
        return notify_url;
    }
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
    public String getTrade_type() {
        return trade_type;
    }
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    @Override
    public String toString() {
        return "Pay [appid=" + appid + ", mch_id=" + mch_id + ", nonce_str=" + nonce_str + ", sign=" + sign + ", body="
                + body + ", out_trade_no=" + out_trade_no + ", total_fee=" + total_fee + ", spbill_create_ip="
                + spbill_create_ip + ", notify_url=" + notify_url + ", trade_type=" + trade_type
                + "]";
    }
    public Refund(String appid, String mch_id, String nonce_str, String sign, String body, String out_trade_no,
               int total_fee, String spbill_create_ip, String notify_url, String trade_type) {

        this.appid = appid;
        this.mch_id = mch_id;
        this.nonce_str = nonce_str;
        this.sign = sign;
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.notify_url = notify_url;
        this.trade_type = trade_type;
    }
    public Refund() {

    }
}
