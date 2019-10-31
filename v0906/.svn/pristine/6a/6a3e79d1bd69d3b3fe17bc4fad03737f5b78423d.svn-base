package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.service.XltcDepositService;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/8 11:41
 * @Modified By:
 */
@RestController
@RequestMapping("/deposit")
public class XltcDepositController {

    @Autowired
    XltcDepositService depositService;

    /**
     * 查询充值记录 (报表使用)
     * @param parms
     * @return
     */
    @RequestMapping(value = "/findDepositRecordByConditions.do",method = RequestMethod.POST)
    public XltcResult findDepositRecordByConditions(@RequestBody Map<String,String> parms){
        return depositService.findDepositRecordByConditions(parms);
    }

}
