package com.qigan.qiganshop.service.synchronization;

import com.qigan.qiganshop.pojo.synchronization.GuanYiUser;
import com.qigan.qiganshop.pojo.synchronization.ResultGuanYiUser;

import java.util.List;

/**
 * 管易云会员用户
 * @author wanghao
 */
public interface SynchGuanYiUserService {

    int add(GuanYiUser guanYiUser);

    GuanYiUser getGuanYiUser(String userId);
}
