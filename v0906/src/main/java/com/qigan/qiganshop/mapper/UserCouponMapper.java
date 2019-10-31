package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Coupon;
import com.qigan.qiganshop.pojo.UserCoupon;
import com.qigan.qiganshop.pojo.XltcCouponModel;
import com.qigan.qiganshop.pojo.XltcCouponPieModel;
import com.qigan.qiganshop.pojo.XltcUserCouponModel;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserCouponMapper {

    int delete(String userCouponId);

    int insert(UserCoupon record);

    UserCoupon findOne(String userCouponId);

    int update(UserCoupon record);

    List<UserCoupon> findPage(@Param("userId") String userId,
                              @Param("page") Integer page,
                              @Param("size") Integer size);

    /**
     * 查询可用优惠券
     *
     * @param userId
     * @param total
     * @return
     */
    List<Coupon> findCanUse(@Param("userId") String userId,
                            @Param("total") Double total);

    /**
     * 根据用户 ID 和 优惠券 Id 查询用户是否拥有该优惠券
     *
     * @param userId
     * @param couponId
     * @return
     */
    List<UserCoupon> findByUserIdAndCouponId(@Param("userId") String userId, @Param("couponId") String couponId);

    /**
     * 下单时修改优惠券状态
     *
     * @param userCoupon
     */
    Integer updateByOrder(UserCoupon userCoupon);
    
    @Select({
    	"select t.couponId, t.userCouponId, t.isUse,t.startTime,t.timeOut,",
    	"t.disableTime,x.type,x.begin,x.end,x.fullMoney,x.reduceMoney,x.couponName ",
    	"from tb_user_coupon t",
    	"left join tb_coupon x on x.couponId = t.couponId",
    	"where userId = #{userId}"
    })
    List<XltcUserCouponModel> findAll(String userId);
    
    @Select({
    	"select t.couponId, t.userCouponId, t.isUse,t.startTime,t.timeOut,",
    	"t.disableTime,x.type,x.begin,x.end,x.fullMoney,x.reduceMoney,x.couponName  ",
    	"from tb_user_coupon t",
    	"left join tb_coupon x on x.couponId = t.couponId",
    	"where t.userId = #{userId} and t.timeOut is not null and t.isUse <> '1' and  x.enable = '1' ",
    	"order by x.type"
    })
    List<XltcUserCouponModel> findExpire(String userId);
    
    @Select({
    	"select t.couponId, t.userCouponId, t.isUse,t.startTime,t.timeOut,",
    	"t.disableTime,x.type,x.begin,x.end,x.fullMoney,x.reduceMoney,x.couponName, ",
    	"t.userId, 1 as fullMoneyIsUse",
    	"from tb_user_coupon t",
    	"left join tb_coupon x on x.couponId = t.couponId",
    	"where t.userId = #{userId} and t.isUse = #{type} and t.timeOut is null and x.enable = '1'",
    	"order by x.type"
    })
    List<XltcUserCouponModel> findAllByType(@Param("userId") String userId, @Param("type") String type);
    
    @Update("update tb_user_coupon set isUse = '0', orderId= null, useTime = null where orderId = #{orderId}")
    void recoverCoupon(String orderId);
    
    @Update("update tb_user_coupon set timeOut = '1' where userCouponId = #{userCouponId}")
    void expireUserCoupon(String userCouponId);
    
    @Select({
    	"<script>",
    	"select * from (",
    	"select x.couponId,x.couponName,x.type,x.fullMoney,x.reduceMoney,t.userId,",
    	"x.begin,x.end,x.enable, if(t.userCouponId is null, 0, 1) as isShow, ",
    	"t.disableTime, t.startTime, t.isUse, t.timeOut",
    	"from tb_user_coupon t",
    	"right join ( ",
    	"SELECT couponId,couponName,type,fullMoney,reduceMoney,begin,end,enable  FROM tb_coupon WHERE enable = '1' and",
    	"(NOW() BETWEEN DATE_FORMAT(begin, '%Y-%m-%d %T') AND DATE_FORMAT(end, '%Y-%m-%d %T') or begin is null)",
    	"<if test='list.size() > 0'>",
    	"and ",
    	"type in (",
    	"<foreach collection='list' item='id' index='index' separator=','> ",
        "#{id}",
        "</foreach>",
        ")",
    	"</if>",
    	")",
    	"x on t.couponId = x.couponId ",
    	"and t.userId = #{userId}) r ",
    	"where r.enable= '1' and (r.isUse = '0' or r.isUse is null) and r.timeOut is null",
    	"order by r.type",
    	"</script>"
    })
    List<XltcUserCouponModel> findCouponByUserId(@Param("userId") String userId, @Param("list") List<String> list);
    
    List<XltcCouponModel> findReport(@Param("record") UserCoupon coupon);
    
    List<XltcCouponModel> findSendReport(@Param("record") UserCoupon coupon);
    
    @Select({
    	"select t.couponId, t.userCouponId, t.isUse,t.startTime,t.timeOut,",
    	"t.disableTime,x.type,x.begin,x.end,x.fullMoney,x.reduceMoney,x.couponName, ",
    	"t.userId",
    	"from tb_user_coupon t",
    	"left join tb_coupon x on x.couponId = t.couponId",
    	"where t.userId = #{userId} and t.isUse = '0' and t.timeOut is null and x.fullMoney <= #{total} and x.enable= '1' ",
    	"order by x.type"
    })
    List<XltcUserCouponModel> findUserCanUse(@Param("userId") String userId, @Param("total") double total);
    
    @Select({
    	"select t.couponId, t.userCouponId, t.isUse,t.startTime,t.timeOut,",
    	"t.disableTime,x.type,x.begin,x.end,x.fullMoney,x.reduceMoney,x.couponName, ",
    	"t.userId, if(x.fullMoney <= #{total}, 1, 0) as fullMoneyIsUse",
    	"from tb_user_coupon t",
    	"left join tb_coupon x on x.couponId = t.couponId",
    	"where t.userId = #{userId} and t.isUse = '0' and t.timeOut is null and x.enable= '1' ",
    	"order by x.type"
    })
    List<XltcUserCouponModel> findAllCouponByUserId(@Param("userId") String userId, @Param("total") double total);
    
    @Select({
    	"select",
        "userCouponId,userId,couponId,timeOut,isUse,orderId,disableTime,startTime,useTime,createTime ",
        "from tb_user_coupon",
        "where userId = #{userId} and couponId = #{couponId} and isUse = '0' and timeOut is null "
    })
    List<UserCoupon> findUserCoupons(@Param("userId") String userId, @Param("couponId") String couponId);
    
    @Select({
    	"<script>",
    	"select couponName, r.* from tb_coupon y ",
    	"right join(",
    	"select t.couponId, t.total, x.unused, (t.total - x.unused) as used from (",
    	"select  couponId, count(0) total from tb_user_coupon ",
    	"<if test='start != null and end != null'>",
    	"where startTime between #{start} and #{end}",
    	"</if>",
    	"group by couponId) t",
    	"left join (select count(0) unUsed, couponId",
    	"from tb_user_coupon ",
    	"where isUse = '0' ",
    	"<if test='start != null and end != null'>",
    	"and startTime between #{start} and #{end}",
    	"</if>",
    	"group by couponId) x on x.couponId = t.couponId) r",
    	"on r.couponId = y.couponId",
    	"</script>"
    })
    List<XltcCouponPieModel> findAllPie(@Param("start") String start, @Param("end") String end);

    @Select({"select SUM(money) sumTotalMoney , sum(payMoney) sumPayMoney from tb_user_coupon a " ,
			"LEFT JOIN tb_order b on a.orderId = b.orderId " ,
			"where a.couponId = #{couponId} " ,
			"and a.isUse =1  ${userTimeConditions} "
    })
	Map<String, Double> sumCurrentConponEffact(@Param("couponId") String couponId,@Param("userTimeConditions") String userTimeConditions);
}