package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.AppUser;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/11 8:00
 * @Modified By:
 */
public interface XltcClueUserService {
    boolean insertRecord(String orginUserId, String clueType, String couponOrAmount, String newUserId);

    boolean givenOrginUserWishes(String orginUserId, String couponOrAmount, String newUserId);

    boolean checkFirstOrderGivenWishes(String orderId);
}
