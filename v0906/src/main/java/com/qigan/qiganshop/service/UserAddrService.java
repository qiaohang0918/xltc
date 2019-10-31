package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.UserAddress;
import com.qigan.qiganshop.pojo.XltcPageResult;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * 用户收货地址服务层
 */
public interface UserAddrService {

    /**
     * 增加
     */
    public Integer add(UserAddress userAddress);

    /**
     * 修改
     */
    public Integer update(UserAddress userAddress);

    /**
     * 根据ID获取实体
     *
     * @param userAddId
     * @return
     */
    public UserAddress findOne(String userAddId);

    /**
     * 批量删除
     *
     * @param userAddress
     */
    public Integer delete(UserAddress userAddress);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(String userId, Integer pageNum, Integer pageSize);
    
    public XltcPageResult findPage(CommonPage page);


}
