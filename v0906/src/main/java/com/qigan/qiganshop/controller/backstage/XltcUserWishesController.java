package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.service.XltcUserWishesService;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/18 11:52
 * @Modified By:
 */
@RestController
@RequestMapping("/user/wishes")
public class XltcUserWishesController {

    @Autowired
    XltcUserWishesService userWishesService;

    /**
     * 获取当前用户的福利信息
     * @param tokens
     * @return
     */
    @RequestMapping(value = "/getCurrentUserWishes.do",method = RequestMethod.GET)
    public XltcResult getCurrentUserWishes(String tokens){
        XltcResult result = userWishesService.getCurrentUserWishes(tokens);
        return result;
    }

    /**
     * 查询所有用户的福利下发情况
     * @param parms
     * @return
     */
    @RequestMapping(value = "/findAllUsersWishesByConditions.do",method = RequestMethod.POST)
    public XltcResult findAllUsersWishesByConditions(@RequestBody  Map<String,Object> parms){
        XltcResult result = userWishesService.findAllUsersWishesByConditions(parms);
        return result;
    }


    @RequestMapping(value = "hihi.do",method = RequestMethod.GET)
    public String hihi(){
        return "HIHI!";
    }


}
