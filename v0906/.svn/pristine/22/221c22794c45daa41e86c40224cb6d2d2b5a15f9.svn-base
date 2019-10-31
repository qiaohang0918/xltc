package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.pojo.Warehouse;
import com.qigan.qiganshop.service.WareHouseService;
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
@RequestMapping("/WareHouse")
@Api(value = "WEB 仓库信息 controller", tags = {"WEB 仓库信息操作接口"})
public class WareHouseController {
    @Autowired
    private WareHouseService service;
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
    public JsonResult findPage(HttpServletRequest request, @RequestBody Warehouse warehouse, Integer page, Integer rows) {
        page = ServletRequestUtils.getIntParameter(request, "page", 1);
        rows = ServletRequestUtils.getIntParameter(request, "rows", 10);

        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findPage(warehouse, page, rows));
    }


    /**
     * 根据主键查询单个规格信息
     *
     * @param wareHouseId
     * @return
     */

    @RequestMapping("findOne.do")
    @ApiOperation(
            value = "根据 ID 查询仓库",
            notes = "根据 ID 查询仓库",
            httpMethod = "POST")
    public JsonResult findOne(String wareHouseId) {
        if (wareHouseId != null && !"".equals(wareHouseId)) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findOne(wareHouseId));
        }
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), new Warehouse());

    }

    /**
     * 增加仓库
     *
     * @param warehouse
     * @return
     */

   /* @RequestMapping("add.do")
    @ApiOperation(
            value = "增加仓库",
            notes = "增加仓库",
            httpMethod = "POST")
    public JsonResult add(@RequestBody Warehouse warehouse) {
        if (warehouse.getName() != null && !"".equals(warehouse.getName())) {
            return pc.addUtils(service.add(warehouse));
        } else {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "仓库名称不能为空!");
        }

    }*/


    /**
     * 删除仓库
     *
     * @param wareHouseIds
     * @return
     */
   /* @RequestMapping("delete.do")
    @ApiOperation(
            value = "删除仓库",
            notes = "删除仓库",
            httpMethod = "POST")
    public JsonResult delete(String[] wareHouseIds) {
        return pc.deleteUtils(wareHouseIds, service.delete(wareHouseIds));
    }
*/

    /**
     * 修改仓库信息
     *
     * @param warehouse
     * @return
     */
   /* @RequestMapping("update.do")
    @ApiOperation(
            value = "修改仓库信息",
            notes = "修改仓库信息",
            httpMethod = "POST")
    public JsonResult update(@RequestBody Warehouse warehouse) {
        return pc.updateUtils(service.update(warehouse));

    }*/


}
