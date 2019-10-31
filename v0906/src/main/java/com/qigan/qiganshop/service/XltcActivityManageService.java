package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.TbActivityMain;
import com.qigan.qiganshop.pojo.TbActivityUnion;
import com.qigan.qiganshop.pojo.TbActivityUnionGoods;
import com.qigan.qiganshop.util.result.XltcResult;

import java.util.List;
import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/17 14:20
 * @Modified By:
 */
public interface XltcActivityManageService {

    XltcResult addMainActivity(TbActivityMain parms);

    XltcResult updateMainActivity(TbActivityMain activity);

    XltcResult findMainActivity(TbActivityMain activity);

    XltcResult deleteMainActivity(TbActivityMain activity);

    XltcResult addUnionType(TbActivityUnion union);

    XltcResult updateUnionType(TbActivityUnion union);

    XltcResult deleteUnionType(TbActivityUnion union);

    XltcResult unionGoods(List<TbActivityUnionGoods> list);

    XltcResult releaseUnionGoods(List<TbActivityUnionGoods> list);

    XltcResult findUnionTypes(TbActivityUnion union);

    XltcResult findUnionGoods(String unionId,String wareId);

    XltcResult selectCurrentActivityCascade(String activityId, String coordinate);

    XltcResult selectCurrentActivityCascadeLimitedUnion(String activityId, String coordinate, int page, int size);

    XltcResult readyUnionGoods(Map<String, String> parms);

    XltcResult updateGoodsSort(TbActivityUnionGoods unionGoods);
}
