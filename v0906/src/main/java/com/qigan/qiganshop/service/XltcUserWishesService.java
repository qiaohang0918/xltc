package com.qigan.qiganshop.service;

import com.qigan.qiganshop.util.result.XltcResult;

import java.util.List;
import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/18 11:53
 * @Modified By:
 */
public interface XltcUserWishesService {

    /**
     * 查询当前用户的福利信息
     * @param token
     * @return
     */
    public XltcResult getCurrentUserWishes(String token);

    /**
     * 查询所有用户的福利下发情况
     * @param parms
     * @return
     */
    public XltcResult findAllUsersWishesByConditions(Map<String,Object> parms);
}
