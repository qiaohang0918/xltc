package com.qigan.qiganshop.controller.frontend;

import com.alibaba.druid.util.StringUtils;
import com.qigan.qiganshop.pojo.Category;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.service.CategoryService;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.XltcResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 规格 controller
 *
 * @author wanghao
 */

@SuppressWarnings("all")
@RestController
@RequestMapping("/app/Category")
@Api(value = "商品分类 controller", tags = {"APP 商品分类操作接口"})
public class AppCategoryController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private CategoryService service;
    @Autowired
    private GoodsService goodsService;


    @RequestMapping("/findPage.do")
    @ApiOperation(
            value = "分页获取所有的商品分类列表",
            notes = "分页获取规格信息,specName为规格名称,page 为页码,rows 为每页记录数 \r\n" +
                    "若要查询所有规格,不传递 specName 即可,page 传1,rows 传 10(默认) \r\n" +
                    "该接口可同时完成多个功能,按照规格名称进行查询,需要传递 specName,page,rows 不传查询全部 ",
            httpMethod = "POST")
    public JsonResult findPage(HttpServletRequest request, String categoryName, Integer page, Integer size) {

        categoryName = ServletRequestUtils.getStringParameter(request, "categoryName", "");
        Category category = new Category();
        if (categoryName != null && !"".equals(categoryName)) {
            /*
             * 条件查询
             */

            category.setCateName(categoryName);
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findPage(category, page, size));
        }
        /*
         * 全部查询
         */

        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findPage(null, null, null));

    }

    /*
     *
     * 根据主键查询单个规格信息
     *
     * @param cateId
     * @return

     */

    @RequestMapping("/findOne.do")
    @ApiOperation(
            value = "根据 ID 查询商品分类",
            notes = "根据ID查询分类",
            httpMethod = "POST")
    public JsonResult findOne(String cateId) {
        if (cateId != null && !"".equals(cateId)) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findByCate(cateId));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cateId 不能为空!", new Category());

    }
    /*
     *
     * 根据主键查询单个规格信息以及分类下的所有标签以及标签下的所有商品
     *
     * @param cateId
     * @return

     */

    @RequestMapping("/findAllByCateId.do")
    @ApiOperation(
            value = "根据 ID 查询单个规格信息以及分类下的所有标签以及标签下的所有商品",
            notes = "根据ID查询分类",
            httpMethod = "POST")
    public JsonResult findAllByCateId(String cateId, String coordinate, CommonPage page) {
        if (NotNull.checkString(cateId,coordinate)) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findAllByCateId(cateId,coordinate, page));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cateId 不能为空!", new Category());

    }
    
    @RequestMapping("/findGoodsByCateId.do")
    @ApiOperation(
            value = "根据 ID 查询单个规格信息以及分类下的所有标签以及标签下的所有商品",
            notes = "根据ID查询分类",
            httpMethod = "POST")
    public JsonResult findGoodsByCateId(String cateId, String coordinate) {
        if (NotNull.checkString(cateId,coordinate)) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findGoodsByCateId(cateId,coordinate));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cateId 不能为空!", new Category());

    }
    
    /*
     *
     * 根据主键查询单个规格信息
     *
     * @param cateId
     * @return

     */

    @RequestMapping("/findOneLabel.do")
    @ApiOperation(
            value = "根据 分类 ID ,标签 ID 查询商品 测试坐标值(coordinate):116.865782,36.666315",
            notes = "根据ID查询分类 分类 ID 标签 ID,坐标必传 \n" +
                    "适用场景:  \n" +
                    "分类标签页面,接口的调用",
            httpMethod = "POST")
    public JsonResult findOneLabel(String cateId, String labelId, String coordinate, Integer page, Integer size) {
        if (cateId != null && !"".equals(cateId) && labelId != null && !"".equals(labelId)) {
            PageResult unionGoods = goodsService.findUnionGoods(cateId, coordinate, labelId, page, size);
            if (unionGoods.getRows().size() == 0) {

                return jr.jsonResultData(ResultCode.FAIL.res_code(), "当前位置无法配送!", new PageResult(0, new ArrayList()));
            }
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), unionGoods);
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cateId 不能为空!", new PageResult(0, new ArrayList()));

    }
    
    @RequestMapping("findRandByCateId.do")
    public XltcResult findRandByCateId(String cateId, String coordinate){
    	return XltcResult.ok(service.findRandByCateId(cateId, coordinate));
    }


}



