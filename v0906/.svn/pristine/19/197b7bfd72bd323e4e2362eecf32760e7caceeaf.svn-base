package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.pojo.game.RedeemType;
import com.qigan.qiganshop.service.GameSettingService;
import com.qigan.qiganshop.service.RedeemRecordService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
//@Api(value = "游戏设置", tags = {"游戏设置"})
public class GameController {

    @Autowired
    private GameSettingService settingService;

    @Autowired
    private RedeemRecordService redeemRecordService;

    @RequestMapping(value = "/setting/save.do", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 成功,其它为错误")})
    @ApiOperation(httpMethod = "POST", value = "修改游戏设置")
    @ResponseBody
    public JsonResult saveSetting(@RequestParam RedeemType redeemType, @RequestParam String name,
                                  @RequestParam String describe, @RequestParam int points) {
        try {
            settingService.save(redeemType, name, describe, points);
            return new JsonResult(null);
        } catch (Exception e) {
            return new JsonResult(ResultCode.FAIL.res_code(), e.getMessage());
        }
    }

    @RequestMapping(value = "/setting/list.do", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 成功,其它为错误")})
    @ApiOperation(httpMethod = "GET", value = "获取游戏设置")
    @ResponseBody
    public JsonResult getSettings() {
        try {
            return new JsonResult(settingService.getSettings());
        } catch (Exception e) {
            return new JsonResult(ResultCode.FAIL.res_code(), e.getMessage());
        }
    }

    @RequestMapping(value = "/record/add.do", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 成功,其它为错误")})
    @ApiOperation(httpMethod = "POST", value = "添加使用道具记录")
    @ResponseBody
    public JsonResult addRedeemRecord(@RequestParam RedeemType redeemType, @RequestParam String userId) {
        try {
            redeemRecordService.addRedeemRecord(redeemType, userId);
            return new JsonResult(null);
        } catch (Exception e) {
            return new JsonResult(ResultCode.FAIL.res_code(), e.getMessage());
        }
    }

}
