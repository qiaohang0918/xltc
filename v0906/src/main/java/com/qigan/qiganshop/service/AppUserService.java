package com.qigan.qiganshop.service;


import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.util.result.format.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AppUserService {

    AppUser getAppUserByToken(String token);

    AppUser getAppUserById(String id);

    AppUser findAppUserByPhone(String phone);


    int addAppUser(AppUser user);

    int updateAppUser(AppUser appUser);

    int updateAppUserToken(AppUser appUser);

    /**
     * 用户上传头像
     *
     * @param token
     * @param file
     * @return
     */
    public String updateAppuserImage(String token, MultipartFile file);


    AppUser getAppUserByOpenid(String openid);

    AppUser getAppUserByQOpenid(String openid);


    List<AppUser> findPage(AppUser appUser, Integer page, Integer size);

    int addDeal(String userId, Double payMoney);

    PageInfo<AppUser> findPageByCondition(HashMap<String, String> map);

    JsonResult daysUserRecords(String startTime, String endTime);

    String getUserId(String ss);

    int reGivenUserAmount(String userId, String incrAmount);

    int onlyRefundBalance(String userId, String money);

    int updateMemberInfo(AppUser user);
}