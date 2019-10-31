package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Coupon;
import com.qigan.qiganshop.pojo.DeliveryDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CouponMapper {

    /**
     * 删除
     *
     * @param couponId
     * @return
     */
    int delete(@Param("couponId") String couponId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(Coupon record);

    /**
     * 条件查询
     *
     * @param coupon
     * @param page
     * @param size
     * @return
     */
    List<Coupon> findPage(
            @Param("record") Coupon coupon,
            @Param("page") Integer page,
            @Param("size") Integer size);

    /**
     * 查询单个
     *
     * @param couponId
     * @return
     */
    Coupon findOne(String couponId);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int update(Coupon record);


    @Select("select * from tb_coupon where couponId in ( ${couponIds} )")
    List<Coupon> findSome(@Param("couponIds") String couponIds);
    
    @Select({
    	"select couponId, couponName, type, fullMoney, reduceMoney, enable ",
    	"from tb_coupon",
    	"where enable = #{status}"
    })
    List<Coupon> findCouponByStatus(String status);
    
    @Select({
    	"select couponId, couponName, type, fullMoney, reduceMoney, enable ",
    	"from tb_coupon",
    	"where type = #{type} and enable = '1'"
    })
    List<Coupon> findCouponByType(String type);
    
    @Select({
    	"select count(0) from tb_coupon t ",
    	"right join tb_user_coupon y on y.couponId = t.couponId"
    })
    Integer countUserCoupon();

    @Select({"select a.orderId, " ,
            "case when a.status = '8' then '已评价' " ,
                    "when a.status = '4' then '已签收' " ,
                    " when a.status = '902' then '补差价' " ,
                    "  end as status, " ,
                    "  a.payMoney, " ,
                    "a.dealDateTime,a.payMoney,a.receiverAddress,b.endTime,c.name,c.deliveryerId,b.outboundTime  from tb_order a " ,
                    "left join tb_deliver_order b on a.orderId =b.orderId " ,
                    "left join tb_deliveryer c on b.deliverId = c.deliveryerId " ,
                    "where a.dealDatetime > '2019-10-01' " ,
                    "and a.`status` in ('4','8','1') " ,
                    "and a.`status` is not NULL " ,
                    "and b.endTime is not null " ,
                    "group by a.orderId" ,
                    "" ,
            ""})
    List<DeliveryDetails> exportExcel();

    List<DeliveryDetails> exportExcelExporeOrder();

    List<DeliveryDetails> exportExcelExporeOrderByTime(@Param("conditions") String conditions);
}