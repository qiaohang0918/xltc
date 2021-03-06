package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.annocation.XltcLogRecord;
import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.HomepageCate;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.HomePageCateGoodService;
import com.qigan.qiganshop.service.HomePageCateService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.picture.PicUtil;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 首页分类 controller
 *
 * @author wanghao
 * @time 2019-04-25 14:27
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/homePageCate")
@Api(tags = {"WEB 首页分类管理接口"})
public class HomePageCateController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private PublicControl pc;
    @Autowired
    private HomePageCateService service;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private HomePageCateGoodService cateGoodService;
    @Autowired
    private PicUtil picUtil;
    
    @RequestMapping("update.do")
    @ApiOperation(
            value = "修改首页分类信息",
            notes = "修改首页分类信息,排序字段留空,无需传递修改",
            httpMethod = "POST")
    @XltcLogRecord(apiName = "修改首页分类信息")
    public JsonResult update(@RequestBody HomepageCate homepageCate) {
        if (homepageCate == null) {
            return pc.updateUtils(0);
        } else {
            return pc.updateUtils(service.update(homepageCate));
        }
    }

    @RequestMapping("add.do")
    @ApiOperation(
            value = "增加首页分类信息",
            notes = "增加首页分类信息,排序按照添加顺序直接重新排序  \n" +
                    "请注意 location 字段,head 为首页上部的图标式分类,end 为首页底部的商品分类  \n" +
                    "填写 head 时,副标题 subtitle 无需填写,填写 end 时,subtitle必须填写" +
                    "homepagecateId 字段无需填写,写了也不生效,不信拉倒 \n" +
                    "enable 只能传 0 或 1 ,表示是否启用,1 启用,0 不启用",
            httpMethod = "POST")
    @XltcLogRecord(apiName = "增加首页分类信息")
    public JsonResult add(@RequestBody HomepageCate homepageCate) {
        if (homepageCate != null) {
            homepageCate.setPicUrl(picUtil.deleteUrlHead(homepageCate.getPicUrl()));
            return pc.addUtils(service.add(homepageCate));
        }
        return pc.addUtils(0);
    }

    @RequestMapping("delete.do")
    @ApiOperation(
            value = "删除首页分类信息",
            notes = "删除首页分类,请注意 location 字段,head 为首页上部的图标式分类,end 为首页底部的商品分类",
            httpMethod = "POST")
    @XltcLogRecord(apiName = "删除首页分类信息")
    public JsonResult delete(String[] homepageCateIds) {
        if (homepageCateIds != null) {
            return pc.deleteUtils(homepageCateIds, service.delete(homepageCateIds));
        }
        return pc.deleteUtils(homepageCateIds, 0);
    }

    @RequestMapping("findOne.do")
    @ApiOperation(
            value = "根据主键查询首页分类信息",
            notes = "根据主键查询首页分类信息,请注意 location 字段,head 为首页上部的图标式分类,end 为首页底部的商品分类",
            httpMethod = "POST")
    public JsonResult findOne(String homepageCateId) {
        if (homepageCateId != null) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findOne(homepageCateId));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), ResultCode.FAIL.message(), null);
    }

    @RequestMapping("findPage.do")
    @ApiOperation(
            value = "条件查询,全部查询,分页查询 三合一随机组合接口",
            notes = "条件查询,全部查询,分页查询 三合一随机组合接口,请注意 location 字段,head 为首页上部的图标式分类,end 为首页底部的商品分类",
            httpMethod = "POST")
    public JsonResult findPage(@RequestBody HomepageCate homepageCate, Integer page, Integer size) {
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findPage(homepageCate, page, size));
    }


    /**
     * 释放关联商品ids
     * @param categoryId
     * @param goodsIds
     * @return
     */
    @RequestMapping(value = "/releaseUnionGoods.do",method = RequestMethod.POST)
    @XltcLogRecord(apiName = "释放关联商品")
    public JsonResult releaseUnionGoods(String categoryId,String goodsIds){
        if(!StringNotNull.check(categoryId)){
            return new JsonResult(ResultCode.FAIL.res_code(),"分类id不能为空");
        }
        if(!StringNotNull.check(goodsIds)){
            return new JsonResult(ResultCode.FAIL.res_code(),"商品id不能为空");
        }
        //partten goodsId
        String statusStrList=null;
        if(goodsIds!=null && goodsIds.length()>0){
            StringBuffer buffer = new StringBuffer();
            String[] split = goodsIds.split(",");
            for (String s : split) {
                buffer.append("'"+s+"',");
            }
            statusStrList = buffer.replace(buffer.length()-1, buffer.length(), "").toString();
        }
        boolean result=cateGoodService.releaseUnionGoods(categoryId,statusStrList);
        if(result)
            return  new JsonResult(ResultCode.SUCCESS.res_code(),"解除关联成功");
        return new JsonResult(ResultCode.FAIL.res_code(),"解除关联失败");
    }


    @RequestMapping("unionGoods.do")
    @ApiOperation(
            value = "关联商品",
            notes = "分类关联商品接口,请注意 location 字段, \n" +
                    "flag 为标识位,填写 add,则关联商品,填写 del,则取消关联商品 \n",
            httpMethod = "POST")
    @XltcLogRecord(apiName = "首页关联商品")
    public JsonResult unionGoods(String homepageCateId, String[] goodsIds, String flag) {
        if (homepageCateId == null || "".equals(homepageCateId) || goodsIds.length == 0) {

            return jr.jsonResultData(ResultCode.FAIL.res_code(), "关联失败,参数为空!");
        }
        HomepageCate one = service.findOne(homepageCateId);
        if (one == null) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "关联失败,没有该分类!");
        }
        return pc.updateUtils(service.unionGoods(one, goodsIds, flag));

    }
    /*
     *
     * 修改商品规格信息,为了保证数据简单处理,先删后增
     *
     * @param
     * @return

     */

    @RequestMapping("updateSort.do")
    @ApiOperation(
            value = "修改分类排序",
            notes = "修改分类排序,交换位置而已",
            httpMethod = "POST")
    @XltcLogRecord(apiName = "修改分类排序")
    public JsonResult updateSort(String homepageCateId1, String homepageCateId2) {
        if (homepageCateId1 != null && !"".equals(homepageCateId1) && homepageCateId1 != null && !"".equals(homepageCateId2)) {
            Integer count = service.updateSort(homepageCateId1, homepageCateId2);
            if (count == 2) {
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "修改排序成功!");
            } else {
                return jr.jsonResultData(ResultCode.FAIL.res_code(), "修改排序失败!");

            }
        } else {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "修改排序失败,ID为空!");

        }
    }

    /**
     * 同一规格内商品的排序
     *
     * @param cateId1
     * @param cateId2
     * @return
     */

    @RequestMapping("updateGoodsSort.do")
    @ApiOperation(
            value = "修改同一规格内商品的排序",
            notes = "修改同一规格内商品的排序,交换位置而已",
            httpMethod = "POST")
    @XltcLogRecord(apiName = "修改同一规格内商品的排序")
    public JsonResult updateGoodsSort(String homepageCateId, String goodsId1, String goodsId2) {
        if (StringNotNull.check(homepageCateId, goodsId1, goodsId2)) {
            Integer count = cateGoodService.updateSort(homepageCateId, goodsId1, goodsId2);
            if (count == 2) {
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "修改排序成功!");
            } else {
                return jr.jsonResultData(ResultCode.FAIL.res_code(), "修改排序失败!");
            }
        } else {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "修改排序失败,ID为空!");
        }

    }
    
    @RequestMapping("updateSortById.do")
    @XltcLogRecord(apiName = "修改分类排序")
    public JsonResult updateSort(String homepageCateId, String goodsId, Integer sort){
    	return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", 
    			cateGoodService.updateGoodsSort(homepageCateId, goodsId, sort));
    }

    /**
     * 查询 vip 商品
     *
     * @return
     */

    @RequestMapping("findVipGoods.do")
    @ApiOperation(
            value = "查询 vip 商品",
            notes = "查询 vip 商品,首页会员商品分类查询用",
            httpMethod = "POST")
    public JsonResult findVipGoods() {
        Goods goods = new Goods();
        goods.setIsVIPGoods("1");
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), goodsService.findPageGoods(goods, null, null));

    }

    @RequestMapping("updateEnable.do")
    @ApiOperation(
            value = "修改分类启用禁用",
            notes = "修改分类启用禁用 ,启用 1 禁用,0",
            httpMethod = "POST")
    @XltcLogRecord(apiName = "修改分类启用禁用")
    public JsonResult updateEnable(String homepageCateId, String flag) {
        if (StringNotNull.check(homepageCateId) && StringNotNull.check(flag)) {
            HomepageCate homepageCate = new HomepageCate();
            homepageCate.setHomepagecateId(homepageCateId);
            if ("0".equals(flag)) {

                homepageCate.setEnable("0");
            } else {

                homepageCate.setEnable("1");
            }
            return pc.updateUtils(service.update(homepageCate));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "操作失败!");
    }

}
