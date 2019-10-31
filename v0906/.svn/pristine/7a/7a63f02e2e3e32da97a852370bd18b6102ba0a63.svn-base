package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.controller.utils.SortType;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.Feedback;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.FeedbackService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author starlord
 */
@RestController
@RequestMapping("/feedback.do")
@Api(value = "用户反馈信息", tags = {"用户反馈信息"})
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 成功,其它为错误") })
    @ApiOperation(httpMethod = "POST", value = "添加反馈信息")
    public JsonResult addFeedback(@RequestParam String content, @RequestParam String token) {
        try {
            AppUser appUser = appUserService.getAppUserByToken(token);
            if (appUser == null) {
                return new JsonResult(ResultCode.FAIL.res_code(), ResultCode.FAIL.message());
            } else {
                feedbackService.addFeedback(new Feedback(content, appUser.getUserId()));
                return new JsonResult(null);
            }
        } catch (Exception e) {
            return new JsonResult(ResultCode.FAIL.res_code(), e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 成功,其它为错误")})
    @ApiOperation(httpMethod = "GET", value = "反馈信息翻页")
    public JsonResult getFeedbackPage(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "create_time") String order,
                                      @RequestParam(defaultValue = "ASC") SortType sort) {
        return new JsonResult(feedbackService.getFeedbackPage(page, size, order, sort.name()));
    }

}
