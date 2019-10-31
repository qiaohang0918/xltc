package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.pojo.Area;
import com.qigan.qiganshop.service.AreaService;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 规格 controller
 *
 * @author wanghao
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/Area")
@Api(value = "省市二级联动controller", tags = {"Web 省市二级联动controller"})
public class WebAreaController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private AreaService service;


    /**
     * 查询一级省份
     *
     * @return
     */

    @RequestMapping("findProvince.do")
    @ApiOperation(
            value = "查询省份",
            notes = "查询省份,返回 List",
            httpMethod = "POST")
    public JsonResult findProvince() {
        Area area = new Area();
        area.setLevel("1");
        PageResult page = service.findPage(area);
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "查询成功", page);
    }

    /**
     * 查询二级城市
     *
     * @return
     */

    @RequestMapping("findCity.do")
    @ApiOperation(
            value = "查询二级城市",
            notes = "查询二级城市,返回 pageResult",
            httpMethod = "POST")
    public JsonResult findCity(String areaId) {
        Area area = new Area();
        area.setParentId(areaId);
        PageResult page = service.findPage(area);

        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "查询成功", page);
    }
}
