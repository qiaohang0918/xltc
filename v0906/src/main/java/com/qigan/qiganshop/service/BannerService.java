package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Banner;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.List;

public interface BannerService {

    List<Banner> selectBanner(String kind);

    int insertBanner(Banner banner);

    int updateBanner(Banner banner);

    int deleteBanner(Integer bannerId);

    int sortBanner(Integer bannerId1, Integer bannerId2);
    
    /**
     * 0 启用
     * 1 禁用
     * @param status
     * @param ids
     */
    void updateEnabel(String status, Integer... ids);
    
    void updateWare(String wareId, Integer id);
    
    void enabelTask4Banner(Integer id, String startDate, String endDate);
    
    void delEnabelTask(Integer id);
    
    PageResult findAllBannerByType(String moduleType, CommonPage page);
    
    void deleteBannerById(Integer id);
    
}
