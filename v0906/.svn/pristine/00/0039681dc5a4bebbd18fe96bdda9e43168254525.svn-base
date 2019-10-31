package com.qigan.qiganshop.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 配送员订单关联表
 *
 * @author wanghao
 */
@ApiModel(value = "配送员订单关联表实体")
@RequiredArgsConstructor()
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DeliveryOrder implements Serializable {

    @NonNull
       
       
    @ApiModelProperty(value = "关联 ID")
    private String deliverOrderId;

       
       
    @ApiModelProperty(value = "配送员 ID")

    private Integer deliverId;
       
       
    @ApiModelProperty(value = "订单ID")
    private String orderId;

       
       
    @ApiModelProperty(value = "订单状态 ")
    private String status;

       
       
    @ApiModelProperty(value = "配送结果 0 已完成 1,拒收 2,地址错误 3,问题")
    private String deliverStatus;

       
       
    @ApiModelProperty(value = "签收人")
    private String signer;

       
       
    @ApiModelProperty(value = "创建时间(领取订单)")
    private String createTime;

       
       
    @ApiModelProperty(value = "出库时间(开始配送)")
    private String outboundTime;

       
       
    @ApiModelProperty(value = "送达时间(已签收订单)")
    private String endTime;

       
       
    @ApiModelProperty(value = "问题描述")
    private String problem;

       
       
    @ApiModelProperty(value = "用户选择的配送时间")
    private String deliveryTime;
       
       
    @ApiModelProperty(value = "是否超时")
    private String isTimeout;

    public String getDeliverOrderId() {
        return deliverOrderId;
    }

    public void setDeliverOrderId(String deliverOrderId) {
        this.deliverOrderId = deliverOrderId;
    }

    public Integer getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOutboundTime() {
        return outboundTime;
    }

    public void setOutboundTime(String outboundTime) {
        this.outboundTime = outboundTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getIsTimeout() {
        return isTimeout;
    }

    public void setIsTimeout(String isTimeout) {
        this.isTimeout = isTimeout;
    }
}