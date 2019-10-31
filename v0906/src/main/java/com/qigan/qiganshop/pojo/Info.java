package com.qigan.qiganshop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "com.qigan.qiganshop.pojo.Info")
   
   
@ToString
@TableName(value = "tb_info")
public class Info implements Serializable {
    public static final String COL_INFOID = "infoId";
    /**
     * 订单信息Id
     */
    @TableId(value = "infoId", type = IdType.AUTO)
    @ApiModelProperty(value = "订单信息Id")
    private String infoId;

    /**
     * 订单信息
     */
    @TableField(value = "info")
    @ApiModelProperty(value = "订单信息")
    private String info;

    /**
     * 订单ID
     */
    @TableField(value = "orderId")
    @ApiModelProperty(value = "订单ID")
    private String orderId;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 用户Id
     */
    @TableField(value = "userId")
    @ApiModelProperty(value = "用户Id")
    private String userId;

    private static final long serialVersionUID = 1L;

    public static final String COL_INFO = "info";

    public static final String COL_ORDERID = "orderId";

    public static final String COL_CREATETIME = "createTime";

    public static final String COL_USERID = "userId";


    public static String getColInfoid() {
        return COL_INFOID;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getColInfo() {
        return COL_INFO;
    }

    public static String getColOrderid() {
        return COL_ORDERID;
    }

    public static String getColCreatetime() {
        return COL_CREATETIME;
    }

    public static String getColUserid() {
        return COL_USERID;
    }
}