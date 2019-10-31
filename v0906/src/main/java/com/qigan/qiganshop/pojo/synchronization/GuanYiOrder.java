package com.qigan.qiganshop.pojo.synchronization;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 管易云订单实体
 *
 * @author wanghao
 * @time 2019-05-04 01:43
 */
public class GuanYiOrder {
    @ApiModelProperty(value = "店铺代码")
    private String shop_code;
    @ApiModelProperty(value = "会员代码")
    private String vip_code;
    @ApiModelProperty(value = "平台单号")
    private String platform_code;
    @ApiModelProperty(value = "仓库代码")
    private String warehouse_code;
    @ApiModelProperty(value = "物流公司")
    private String express_code;
    @ApiModelProperty(value = "拍单时间")
    private String deal_datetime;
    @ApiModelProperty(value = "订单类型 Sales-销售订单,Return-换货订单,Charge-费用订单,Delivery-补发货订单,Invoice-补发票订单")
    private String order_type_code;
    @ApiModelProperty(value = "买家留言")
    private String buyer_memo;
    @ApiModelProperty(value = "商品明细")
    private List<GuanYiItem> details;
    @ApiModelProperty(value = "收货人")
    private String receiver_name;
    @ApiModelProperty(value = "固定电话电话与手机号二选一必填")
    private String receiver_phone;
    @ApiModelProperty(value = "手机号码电话与手机号二选一必填")
    private String receiver_mobile;
    @ApiModelProperty(value = "省名称汉字匹配ERP地址库")
    private String receiver_province;
    @ApiModelProperty(value = "市名称汉字匹配ERP地址库")
    private String receiver_city;
    @ApiModelProperty(value = "区名称汉字匹配ERP地址库")
    private String receiver_district;
    @ApiModelProperty(value = "收货地址")
    private String receiver_address;
    @ApiModelProperty(value = "发票")
    private List<GuanYiInvoice> invoices;
    @ApiModelProperty(value = "支付方式")
    private List payments;
    @ApiModelProperty(value = "二次备注")
    private String seller_memo_late;
    @ApiModelProperty(value = "附加信息")
    private String extend_memo;

    public String getShop_code() {
        return shop_code;
    }

    public String getSeller_memo_late() {
        return seller_memo_late;
    }

    public void setSeller_memo_late(String seller_memo_late) {
        this.seller_memo_late = seller_memo_late;
    }

    public String getExtend_memo() {
        return extend_memo;
    }

    public void setExtend_memo(String extend_memo) {
        this.extend_memo = extend_memo;
    }

    public void
    setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public String getVip_code() {
        return vip_code;
    }

    public void setVip_code(String vip_code) {
        this.vip_code = vip_code;
    }

    public String getWarehouse_code() {
        return warehouse_code;
    }

    public void setWarehouse_code(String warehouse_code) {
        this.warehouse_code = warehouse_code;
    }

    public String getExpress_code() {
        return express_code;
    }

    public void setExpress_code(String express_code) {
        this.express_code = express_code;
    }

    public String getDeal_datetime() {
        return deal_datetime;
    }

    public void setDeal_datetime(String deal_datetime) {
        this.deal_datetime = deal_datetime;
    }

    public String getOrder_type_code() {
        return order_type_code;
    }

    public void setOrder_type_code(String order_type_code) {
        this.order_type_code = order_type_code;
    }

    public List<GuanYiItem> getDetails() {
        return details;
    }

    public void setDetails(List<GuanYiItem> details) {
        this.details = details;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_province() {
        return receiver_province;
    }

    public void setReceiver_province(String receiver_province) {
        this.receiver_province = receiver_province;
    }

    public String getReceiver_city() {
        return receiver_city;
    }

    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }

    public String getReceiver_district() {
        return receiver_district;
    }

    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public List<GuanYiInvoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<GuanYiInvoice> invoices) {
        this.invoices = invoices;
    }

    public List getPayments() {
        return payments;
    }

    public void setPayments(List payments) {
        this.payments = payments;
    }

    public String getPlatform_code() {
        return platform_code;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
    }

    public String getBuyer_memo() {
        return buyer_memo;
    }

    public void setBuyer_memo(String buyer_memo) {
        this.buyer_memo = buyer_memo;
    }
}
