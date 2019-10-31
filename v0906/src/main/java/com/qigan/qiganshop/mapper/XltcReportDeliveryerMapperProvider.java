/**
 * 
 */
package com.qigan.qiganshop.mapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.qigan.qiganshop.pojo.XltcReportDeliveryerModel;

/**
 * @author wyy
 *
 */
public class XltcReportDeliveryerMapperProvider {
	
	public String selectAll(XltcReportDeliveryerModel model){
		SQL sql = new SQL();
		sql.SELECT("id, user_id_, user_name_");
		sql.SELECT("user_procd_count_, date_format(create_time_, '%Y-%m-%d %T') as create_time_,order_num");
		sql.FROM("report_deliveryer");
		if(model == null){
			sql.ORDER_BY("create_time_ desc");
			return sql.toString();
		}
		if(StringUtils.isNotBlank(model.getUserName())){
			sql.WHERE("user_name_ like '%" + model.getUserName() +"%'");
		}
		if(StringUtils.isNotBlank(model.getOrderNum())){
			sql.WHERE("order_num = '" + model.getOrderNum() + "'");
		}
		if(StringUtils.isNotBlank(model.getUserId())){
			sql.WHERE("user_id_ = '" + model.getUserId() + "'");
		}
		if(StringUtils.isNotBlank(model.getStartDate()) && StringUtils.isNotBlank(model.getEndDate())){
			sql.WHERE("date_format(create_time_, '%Y-%m-%d %T') between '" + model.getStartDate() + "' and '" + model.getEndDate() + "'" );
		}
		if(model.getUserProcdNum() != null){
			sql.WHERE("user_procd_count_ <> 0");
		}
		sql.ORDER_BY("create_time_ desc");
		return sql.toString();
	}
	
	public String selectReportByUserId(XltcReportDeliveryerModel model){
		SQL sql = new SQL();
		sql.SELECT("user_id_, user_name_, sum(user_procd_count_) as user_procd_count_ ");
		sql.FROM("report_deliveryer");
		if(model == null){
			sql.GROUP_BY("user_id_");
			return sql.toString();
		}
		if(StringUtils.isNotBlank(model.getUserName())){
			sql.WHERE("user_name_ like '%" + model.getUserName() +"%'");
		}
		if(StringUtils.isNotBlank(model.getUserId())){
			sql.WHERE("user_id_ = '" + model.getUserId() + "'");
		}
		if(StringUtils.isNotBlank(model.getStartDate()) && StringUtils.isNotBlank(model.getEndDate())){
			sql.WHERE("date_format(create_time_, '%Y-%m-%d %T') between '" + model.getStartDate() + "' and '" + model.getEndDate() + "'" );
		}
		sql.GROUP_BY("user_id_");
		return sql.toString();
	}
	
}
