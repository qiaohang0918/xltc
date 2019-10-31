package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.pojo.game.SourceType;
import com.qigan.qiganshop.service.ScoreSettingService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreSettingService scoreSettingService;

    @RequestMapping(value = "/setting/save.do", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 成功,其它为错误")})
    @ApiOperation(httpMethod = "POST", value = "修改积分设置")
    @ResponseBody
    public JsonResult saveSetting(@RequestParam SourceType sourceType, @RequestParam double money,
                                  @RequestParam int score) {
        try {
            scoreSettingService.save(sourceType, money, score);
            return new JsonResult(null);
        } catch (Exception e) {
            return new JsonResult(ResultCode.FAIL.res_code(), e.getMessage());
        }
    }

    @RequestMapping(value = "/setting/list.do", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 成功,其它为错误")})
    @ApiOperation(httpMethod = "GET", value = "获取积分设置")
    @ResponseBody
    public JsonResult getSettings() {
        try {
            return new JsonResult(scoreSettingService.getScoreSettings());
        } catch (Exception e) {
            return new JsonResult(ResultCode.FAIL.res_code(), e.getMessage());
        }
    }
}
