package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.TbExpland;
import com.qigan.qiganshop.util.result.XltcResult;

import java.util.List;
import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/18 18:42
 * @Modified By:
 */
public interface XltcExpladService {
    XltcResult addExplandUser(String phone);

    List<AppUser> findCurrentUsersExplandUsers(String searchField, String searchValue, String startTime, String endTime);

    List<Map> findExplandUsers();

    List<TbExpland> exportExcelFindExplandUsers(String startTime, String endTime, String phone);
}
