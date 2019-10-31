package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.pojo.TbClueActivity;
import com.qigan.qiganshop.service.XltcClueUserActivityService;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/22 17:49
 * @Modified By:
 */
@RestController
@RequestMapping("/clue/user/activity")
public class XltcClueUserActivityController {

    @Autowired
    XltcClueUserActivityService clueUserActivityService;

    /**
     * 插入线索用户奖励活动
     * @param clueActivity
     * @return
     */
    @RequestMapping(value = "/insertClueActivity.do",method = RequestMethod.POST)
    public XltcResult insertClueActivity(@RequestBody  TbClueActivity clueActivity){
        if(clueActivity==null){
            return XltcResult.error();
        }
        if(!StringNotNull.check(clueActivity.getMoney())){
            return XltcResult.error("请配置活动奖励金额！");
        }
        return clueUserActivityService.insertClueActivity(clueActivity);
    }

    /**
     * 修改线索用户奖励活动是否启用
     * @param enable
     * @param clueActivityId
     * @return
     */
    @RequestMapping(value = "/updateClueActivityStatus.do",method = RequestMethod.POST)
    public XltcResult updateClueActivityStatus(String pictureUrl,String enable,String clueActivityId,String money){
        if(!StringNotNull.check(enable,clueActivityId))
            return XltcResult.error("未传入状态值,或者活动id！");
        try {
            if(StringNotNull.check(money)){
                Double.parseDouble(money);
            }
        }catch (Exception e){
            return XltcResult.error("money格式不正确！");
        }
        return clueUserActivityService.updateClueActivityStatus(pictureUrl,enable,clueActivityId,money);
    }


    /**
     * 根据状态查询线索金额活动
     * @param enable
     * @return
     */
    @RequestMapping(value = "/findClueActivityNyConditions.do",method = RequestMethod.GET)
    public XltcResult findClueActivityNyConditions(String enable){
        return clueUserActivityService.findClueActivityNyConditions(enable);
    }

    /**
     * 根据状态查询线索金额活动（后台用）
     * @param enable
     * @return
     */
    @RequestMapping(value = "/findClueActivityNyConditionsManager.do",method = RequestMethod.GET)
    public XltcResult findClueActivityNyConditionsManager(String enable){
        return clueUserActivityService.findClueActivityNyConditionsManager(enable);
    }

}
