											package com.qigan.qiganshop.service;

import com.qigan.qiganshop.enums.CouponType;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Coupon;
import com.qigan.qiganshop.pojo.CouponDetailsModel;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.List;

/**
 * 优惠券服务层
 *
 * @author wanghao
 * @time 2019-05-05 14:43
 */
public interface CouponService {
    /**
     * 新增优惠券
     *
     * @param coupon
     * @return
     */
    int add(Coupon coupon);

    /**
     * 删除优惠券
     *
     * @param ids
     * @return
     */
    int delete(String[] ids);

    /**
     * 更新优惠券
     *
     * @param coupon
     * @return
     */
    int update(Coupon coupon);
    /**
     * 更新优惠券
     *
     * @param couponIds
     * @param flag
     * @return
     */
    int updateEnable(String[] couponIds, String flag);

    /**
     * 查询单个
     *
     * @param couponId
     * @return
     */
    Coupon findOne(String couponId);

    /**
     * 查询集合
     *
     * @param coupon
     * @param page
     * @param rows
     * @return
     */
    PageResult findPage(Coupon coupon, Integer page, Integer rows);

    List<Coupon> findCouponByIds(String couponIds);
    
    void saveCouponDetails(CouponDetailsModel model);
    
    PageResult selectCouponDetails(String couponId, CommonPage page);
    
    void deleteDetails(String couponId, String type, String...codes);
    
    List<Coupon> findNewFirstCoupon(CouponType type);
    
    void deleteCoupon(String... ids);
}
