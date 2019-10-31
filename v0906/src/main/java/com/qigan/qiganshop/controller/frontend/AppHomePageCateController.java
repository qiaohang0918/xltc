package com.qigan.qiganshop.controller.frontend;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.HomepageCate;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.HomePageCateGoodService;
import com.qigan.qiganshop.service.HomePageCateService;
import com.qigan.qiganshop.service.XltcHomeService;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 首页分类 controller
 *
 * @author wanghao
 * @time 2019-04-25 14:27
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/app/homePageCate")
@Api(tags = {"APP 首页分类管理接口"})
public class AppHomePageCateController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private PublicControl pc;
    @Autowired
    private HomePageCateService service;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private HomePageCateGoodService homePageCateGoodService;
    
    @Autowired
    XltcHomeService homeService;


    @RequestMapping("findOne.do")
    @ApiOperation(
            value = "根据主键查询首页分类信息 标签 ID 查询商品 测试坐标值(coordinate):117.27393,34.799088",
            notes = "根据主键查询首页分类信息,请注意 location 字段,head 为首页上部的图标式分类,end 为首页底部的商品分类",
            httpMethod = "POST")
    public JsonResult findOne(String coordinate, String cateId, CommonPage page) {
        if (StringNotNull.check(cateId)) {
        	if("gooodsRand".equals(cateId)){
        		List<?> resultRand = homeService.gooodsRand(coordinate);
        		if(resultRand == null){
        			return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), new PageResult(0, new ArrayList<>()));
        		}
        		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), new PageResult(resultRand.size(), resultRand));
        	}
        	
            List<Goods> list = homePageCateGoodService.findGoodsByCateId(cateId, coordinate, page).stream().distinct().collect(Collectors.toList());
            if (list != null && list.size() > 0) {
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), new PageResult(list.size(), list));
            }
        }
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "cateId 查询不到记录", new PageResult(0, new ArrayList()));
    }
/*
    @RequestMapping("findVip.do")
    @ApiOperation(
            value = "根据主键查询首页分类信息 标签 ID 查询商品 测试坐标值(coordinate):116.865782,36.666315",
            notes = "根据主键查询首页分类信息,请注意 location 字段,head 为首页上部的图标式分类,end 为首页底部的商品分类",
            httpMethod = "POST")
    public JsonResult findVip(String coordinate, Integer page, Integer rows) {
        Goods goods = new Goods();
        goods.setIsVIPGoods("1");
        return goodsService.findPage(goods, coordinate, page, rows);

    }*/

    @RequestMapping("findHomePageCenter.do")
    @ApiOperation(
            value = "首页横向查询 标签 ID 查询商品 测试坐标值(coordinate):116.865782,36.666315",
            notes = "首页横向查询",
            httpMethod = "POST")
    public JsonResult findHomePageCenter(String coordinate) {
        return service.findHomePageCenter(coordinate);
    }

    @RequestMapping("findAll.do")
    @ApiOperation(
            value = "查询分类",
            notes = "请注意 location 字段,head 为首页上部的图标式分类,end 为首页底部的商品分类",
            httpMethod = "POST")
    public JsonResult findPage(String location, Integer page, Integer size) {
        if (StringNotNull.check(location)) {
            HomepageCate homepageCate = new HomepageCate();
            homepageCate.setLocation(location);
            homepageCate.setEnable("1");
            return jr.jsonResultData(
                    ResultCode.SUCCESS.res_code(),
                    ResultCode.SUCCESS.message(),
                    service.findPage(homepageCate, page, size));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cateId 为空!");
    }


}
