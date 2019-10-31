package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.pojo.Scope;
import com.qigan.qiganshop.service.ScopeService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 规格 controller
 *
 * @author wanghao
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/Scope")
@Api(value = "配送范围 controller", tags = {"WEB 配送范围操作接口"})
public class ScopeController {
    @Autowired
    private ScopeService service;
    @Autowired
    private JsonResult jr;
    @Autowired
    private PublicControl pc;


    /**
     * 查询所有规格信息或者分页查询或者条件查询,三者随意组合
     *
     * @param request
     * @param page
     * @param rows
     * @return
     */

    @RequestMapping("findPage.do")
    @ApiOperation(
            value = "分页获取仓库列表",
            notes = "分页获取仓库列表,page 为页码,rows 为每页记录数 \r\n" +
                    "page,rows 不传默认 page=1,rows=10 ",
            httpMethod = "POST")
    public JsonResult findPage(HttpServletRequest request, @RequestBody Scope scope, Integer page, Integer rows) {
        page = ServletRequestUtils.getIntParameter(request, "page", 1);
        rows = ServletRequestUtils.getIntParameter(request, "rows", 10);

        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findPage(scope, page, rows));


    }


    /**
     * 根据主键查询单个配送范围信息
     *
     * @param scopeId
     * @return
     */

    @RequestMapping("findOne.do")
    @ApiOperation(
            value = "根据 ID 查询配送范围",
            notes = "根据 ID 查询配送范围",
            httpMethod = "POST")
    public JsonResult findOne(String scopeId) {
        if (scopeId != null && !"".equals(scopeId)) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findOne(scopeId));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "scopeId 不能为空!", new Scope());

    }

    /**
     * 增加仓库
     *
     * @param scope
     * @return
     */

    @RequestMapping("add.do")
    @ApiOperation(
            value = "增加配送范围",
            notes = "增加配送范围",
            httpMethod = "POST")
    public JsonResult add(@RequestBody Scope scope) {
        if (scope.getApexs() != null && !"".equals(scope.getApexs())) {
            /**
             * 检测是否已经关联了仓库
             */
            Integer add = service.add(scope);
            if(add == -1){

                return jr.jsonResultData(ResultCode.FAIL.res_code(), "区域名称重复或者区域存在重合!!");
            }
            return pc.addUtils(add);
        } else {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "配送范围多边形顶点不能为空!!!");
        }

    }


    /**
     * 删除仓库
     *
     * @param scopeIds
     * @return
     */
    @RequestMapping("delete.do")
    @ApiOperation(
            value = "删除配送范围",
            notes = "删除配送范围",
            httpMethod = "POST")
    public JsonResult delete(String[] scopeIds) {
        return pc.deleteUtils(scopeIds, service.delete(scopeIds));
    }


    /**
     * 修改仓库信息
     *
     * @param scope
     * @return
     */
    @RequestMapping("update.do")
    @ApiOperation(
            value = "修改仓库信息",
            notes = "修改仓库信息",
            httpMethod = "POST")
    public JsonResult update(@RequestBody Scope scope) {
        return pc.updateUtils(service.update(scope));

    }

    /**
     * 判断两个多边形是否重合
     *
     * @param apexs       多边形顶点集合
     * @param wareHouseId 仓库 ID
     * @return
     */
   /* @RequestMapping("findCoincide.do")
    @ApiOperation(
            value = "判断两个多边形是否重合",
            notes = "添加仓库配送范围时,检测当前区域是否存在重合现象,同一个仓库不做处理",
            httpMethod = "POST")
    public JsonResult findCoincide(String wareHouseId, String apexs, String city) {
        if (wareHouseId == null || "".equals(wareHouseId) || apexs == null || "".equals(apexs)) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "wareHouseId 或 apexs 不能为空!");
        }
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findCoincide(wareHouseId, apexs, city) ? "当前选择区域与其他仓库区域存在重合" : "未重合,可以使用");
    }*/


}
