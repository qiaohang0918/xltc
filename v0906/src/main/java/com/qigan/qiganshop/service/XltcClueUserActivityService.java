package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.TbClueActivity;
import com.qigan.qiganshop.util.result.XltcResult;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/22 17:50
 * @Modified By:
 */
public interface XltcClueUserActivityService {

    XltcResult insertClueActivity(TbClueActivity clueActivity);

    XltcResult updateClueActivityStatus(String pictureUrl, String status, String clueActivityId, String money);


    TbClueActivity selectByIds(String clueActivityId);

    XltcResult findClueActivityNyConditions(String status);

    XltcResult findClueActivityNyConditionsManager(String status);
}
