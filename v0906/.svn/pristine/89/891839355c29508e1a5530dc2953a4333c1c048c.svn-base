package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.pojo.Area;
import com.qigan.qiganshop.pojo.Version;
import com.qigan.qiganshop.service.AreaService;
import com.qigan.qiganshop.service.VersionService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 版本
 *
 * @author wanghao
 */
@SuppressWarnings("all")
@RestController
@Api(value = "APP 版本controller", tags = {"安卓安装包controller"})
public class VersionController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private PublicControl pc;
    @Autowired
    private VersionService service;


    /**
     * 上传包
     *
     * @return
     */

    @RequestMapping("/version/add.do")
    @ApiOperation(
            value = "增加",
            notes = "增加,添加状态",
            httpMethod = "POST")
    public JsonResult add(String versionCode, String appUrl, String type) {
        if (NotNull.checkString(versionCode, appUrl, type)) {

            return pc.addUtils(service.add(versionCode, appUrl, type));
        } else {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "参数不能为空");
        }
    }

    /**
     * 列表
     *
     * @return
     */

    @RequestMapping("/version/get.do")
    @ApiOperation(
            value = "查询",
            notes = "查询包状态",
            httpMethod = "POST")
    public JsonResult get(String type) {

        List<Version> list = service.get(type);
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "查询成功", list);
    }

    /**
     * 查找更新
     *
     * @return
     */

    @RequestMapping("/app/version/getMax.do")
    @ApiOperation(
            value = "查询最新版本",
            notes = "查询最新版本",
            httpMethod = "POST")
    public JsonResult getMax(String type) {
        Version version = service.getMax(type);
        if (version != null) {

            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "查询成功", version);
        } else {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "当前已是最新版本");
        }
    }

}
