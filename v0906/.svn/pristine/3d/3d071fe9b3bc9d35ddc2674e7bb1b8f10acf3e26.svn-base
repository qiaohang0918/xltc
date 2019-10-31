package com.qigan.qiganshop.service;

import java.util.List;
import java.util.Map;

import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Coupon;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.UserCoupon;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * 用户优惠券服务层
 *
 * @author wanghao
 */
public interface UserCouponService {

	/**
	 * 增加
	 */
	public Integer add(UserCoupon userCoupon, boolean flag);

	/**
	 * 修改
	 */
	public Integer update(UserCoupon userCoupon);

	/**
	 * 根据ID获取实体
	 *
	 * @param userCouponId
	 * @return
	 */
	public UserCoupon findOne(String userCouponId);

	/**
	 * 根据userId和优惠券ID获取实体
	 *
	 * @param couponId
	 * @param userid
	 * @return
	 */
	public UserCoupon findByUserIdAndCouponId(String userid, String couponId);

	/**
	 * 分页
	 *
	 * @param pageNum
	 *            当前页 码
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	public List<UserCoupon> findPage(String userId, Integer pageNum, Integer pageSize);

	public List<?> findAll(String token, CommonPage page);

	/**
	 * 查询可用优惠券
	 *
	 * @param userId
	 * @param total
	 * @return
	 */
	List<Coupon> findCanUse(String userId, double total);
	
	List<?> findUserCanUse(String token, Double total);

	/**
	 * 下单时修改优惠券状态
	 * 
	 * @param userCoupon
	 */
	Integer updateByOrder(UserCoupon userCoupon);

	void delete(String... userCouponIds);

	List<?> findByConditions(CommonPage page, String token, String type);

	void recoverUserCoupon(String orderId);

	void expireUserCoupon(String userCouponId);
	
	boolean isShowReceive(String token);
	
	List<?> findCanReceiveAndCanUse(String token, String mobileId);
	
	String checkToken(String token);
	
	PageResult findReport(UserCoupon coupon);
	
	List<?> findCouponPieData(String start, String end);
	
	List<?> findAllCoupon(String token, Double fullMoney, String mobileId, String... codes);
	
	Map<?, ?> findAllData(Goods goods, String couponId, String type);
	
	List<?> findAllCoupon4App(String token, String goodsId, String mobileId, String index);
	
	void sendCoupon2User(String no, String[] userIds, String... couponIds);
	
	AppUser chenUserCanSend(String userPhone);
	
	PageResult findSendCouponAll(UserCoupon coupon);
}
