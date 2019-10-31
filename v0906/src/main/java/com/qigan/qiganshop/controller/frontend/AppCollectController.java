package com.qigan.qiganshop.controller.frontend;


import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.Collect;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.CollectService;
import com.qigan.qiganshop.util.exception.ExceptionUtil;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.picture.PicUtil;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("all")
@RestController
@RequestMapping("/app/collect")
@Api(tags = {"APP 用户收藏管理接口"})
public class AppCollectController {

    @Autowired
    private JsonResult jr;
    @Autowired
    private CollectService service;
    @Autowired
    private AppUserService userService;
    @Autowired
    private PicUtil picUtil;


    @RequestMapping(value = "/insertCollect.do")
    @ApiOperation(
            value = "收藏商品",
            notes = "token 用户标识 itemId 商品id",
            httpMethod = "POST")
    public JsonResult insertCollect(String token, String itemId, HttpServletRequest request) throws Exception {
        try {
            AppUser user = userService.getAppUserByToken(token);
            if(user != null){
                Collect collect = service.selectCollectByUseridAndItemid(user.getUserId(),itemId);
                if(collect == null){
                    int suc = service.insertCollect(user.getUserId(),itemId);
                    if(suc == 1){
                        return jr.jsonResultData(ResultCode.SUCCESS.res_code());
                    }
                }else{
                    return jr.jsonResultData(ResultCode.FAIL.res_code(),"当前商品已经收藏");
                }
            }
            return jr.jsonResultData(ResultCode.FAIL.res_code());
        } catch (Exception e) {
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }

    @RequestMapping(value = "/selectCollectList.do")
    @ApiOperation(
            value = "查询收藏集合",
            notes = "token 用户标识",
            httpMethod = "POST")
    public JsonResult selectCollectList(String token, HttpServletRequest request) throws Exception {
        try {
            AppUser user = userService.getAppUserByToken(token);
            if(user != null){
                List<Collect> list = service.selectCollectList(user.getUserId());
                if(list != null){
                    List<Goods> listmap = new ArrayList<>();
                    for(Collect collect : list){
                        Goods goods = service.selectGoodsByItemid(collect.getItemId());
                        goods.setPicList(picUtil.addUrlHead(goods.getPicUrls()));
                       // map.put("collectId",collect.getItemId());
                        listmap.add(goods);
                    }
                    NotNull.checkListNull(listmap);
                    return jr.jsonResultData(ResultCode.SUCCESS.res_code(),"查询成功",listmap);
                }
            }
            return jr.jsonResultData(ResultCode.FAIL.res_code());
        } catch (Exception e) {
            e.printStackTrace();
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }

    @RequestMapping(value = "/delectCollect.do")
    @ApiOperation(
            value = "删除收藏商品",
            notes = "token 用户标识 itemId 商品收藏id",
            httpMethod = "POST")
    public JsonResult delectCollect(String token, String itemId, HttpServletRequest request) throws Exception {
        try {
            AppUser user = userService.getAppUserByToken(token);
            if(user != null){
                int suc = service.delectCollect(user.getUserId(),itemId);
                if(suc == 1){
                    return jr.jsonResultData(ResultCode.SUCCESS.res_code());
                }
            }
            return jr.jsonResultData(ResultCode.FAIL.res_code());
        } catch (Exception e) {
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }
}
