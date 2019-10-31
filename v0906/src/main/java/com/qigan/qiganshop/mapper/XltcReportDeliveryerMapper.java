/**
 * 
 */
package com.qigan.qiganshop.mapper;

import java.util.List;
import java.util.Map;

import com.qigan.qiganshop.pojo.CountDelivery;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import com.qigan.qiganshop.pojo.XltcReportDeliveryerModel;

/**
 * @author wyy
 *
 */
public interface XltcReportDeliveryerMapper {
	
	@Insert({
		"insert into report_deliveryer(" ,
		"user_id_, ",
		"user_name_, ",
		"user_procd_count_, ",
		"order_num) values (",
		"#{model.userId}, ",
		"#{model.userName}, ",
		"#{model.userProcdNum}, ",
		"#{model.orderNum}) ",
	})
	void insert(@Param("model") XltcReportDeliveryerModel model);


	@Results(value={
		    @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
		    @Result(column="user_id_", property="userId", jdbcType=JdbcType.VARCHAR),
		    @Result(column="user_name_", property="userName", jdbcType=JdbcType.VARCHAR),
		    @Result(column="user_procd_count_", property="userProcdNum", jdbcType=JdbcType.VARCHAR),
		    @Result(column="order_num", property="orderNum", jdbcType=JdbcType.VARCHAR),
		    @Result(column="create_time_", property="createDate", jdbcType=JdbcType.VARCHAR)
	})
	@SelectProvider(method = "selectAll", type = XltcReportDeliveryerMapperProvider.class)
	List<XltcReportDeliveryerModel> selectAll(XltcReportDeliveryerModel model);
	
	@Results(value={
			@Result(column="user_name_", property="userName", jdbcType=JdbcType.VARCHAR),
			@Result(column="user_id_", property="userId", jdbcType=JdbcType.VARCHAR),
			@Result(column="user_procd_count_", property="userProcdNum", jdbcType=JdbcType.VARCHAR)
	})
	@SelectProvider(method = "selectReportByUserId", type = XltcReportDeliveryerMapperProvider.class)
	List<XltcReportDeliveryerModel> selectReportByUserId(XltcReportDeliveryerModel model);


	@Select({"select  sum(b.user_procd_count_) sum,a.status,b.user_id_,b.user_name_ from tb_order a " ,
			"LEFT JOIN report_deliveryer b on a.orderId=b.order_num " ,
			"where b.user_id_ = #{deliveryId} " ,
			" ${conditions} " ,
			"group by a.status "})
	List<Map> countDelivery(@Param("deliveryId") String userId, @Param("conditions") String conditions);
}
