package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.service.ItemService;
import com.qigan.qiganshop.service.ItemService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品 controller(SKU)
 *
 * @author wanghao
 * @time 2019-04-22 13:50
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/item")
@Api(tags = {"WEB SKU管理接口"})
public class ItemController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private PublicControl pc;
    @Autowired
    private ItemService service;

    /**
     * SKU 商品仅提供修改,查询功能,无新增,删除
     */
    @RequestMapping("update.do")
    @ApiOperation(
            value = "修改SKU 商品信息",
            notes = "修改商品信息,其中,仅可修改以下字段:  \r\n" +
                    "<span style=\"color: red\">备注OR商品详情标准售价\n" +
                    "成本价\n" +
                    "主图图片地址\n" +
                    "商品状态\n" +
                    "是否推荐,0不推荐,1推荐\n" +
                    "是否VIP商品\n" +
                    "VIP价格 </span>",
            httpMethod = "POST")
    public JsonResult update(@RequestBody Item item) {
       
        if (StringNotNull.check(item.getItemId())) {

            return pc.updateUtils(service.update(item));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "商品 ID 不能为空");

    }

    /**
     * SKU 查询详情
     */
    @RequestMapping("findOne.do")
    @ApiOperation(
            value = "查询单个 SKU 商品信息",
            notes = "itemId 必传",
            httpMethod = "POST")
    public JsonResult findOne(String itemId) {
        if (StringNotNull.check(itemId)) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), (Item) NotNull.checkNull(service.findOne(itemId)));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "商品 ID 不能为空");

    }

    /**
     * SKU 商品仅提供修改,查询功能,无新增,删除
     */
    @RequestMapping("findBySpuId.do")
    @ApiOperation(
            value = "根据 SPUID 查询 SKU 商品信息",
            notes = "根据 SPUID 查询 SKU 商品信息",
            httpMethod = "POST")
    public JsonResult findPage(String spuId) {
        if (StringNotNull.check(spuId)){

            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findBySpuIdNotStock(spuId));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "商品 ID 不能为空");

    }

    /**
     * SKU 商品仅提供修改,查询功能,无新增,删除
     */
    @RequestMapping("updateStatus.do")
    @ApiOperation(
            value = "修改状态",
            notes = "itemIds[] 商品 ID 数组  status 状态值",
            httpMethod = "POST")
    public JsonResult updateStatus(String[] itemIds, String status) {

        if (itemIds.length > 0 && StringNotNull.check(status)) {
            return pc.updateUtils(service.updateStatus(itemIds, status));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "参数有误!");


    }
    /**
     * SKU 商品仅提供修改,查询功能,无新增,删除
     */
    @RequestMapping("updateEnable.do")
    @ApiOperation(
            value = "修改是否启用",
            notes = "itemIds[] 商品 ID 数组  status 状态值 1,禁用  0 启用",
            httpMethod = "POST")
    public JsonResult updateEnable(String[] itemIds, String status) {

        if (itemIds.length > 0 && StringNotNull.check(status)) {
            return pc.updateUtils(service.updateEnable(itemIds, status));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "参数有误!");
    }
}
