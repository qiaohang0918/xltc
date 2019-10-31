package com.qigan.qiganshop.controller.frontend;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.pojo.Balance;
import com.qigan.qiganshop.service.BalanceService;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 规格 controller
 *
 * @author wanghao
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/app/Balance")
@Api(value = "消费记录controller", tags = {"APP 消费记录controller"})
public class BalanceController {
    @Autowired
    private JsonResult jr;
    @Autowired
    private PublicControl pc;
    @Autowired
    private BalanceService service;


    /**
     * 增加消费信息
     *
     * @return
     */

    @RequestMapping("add.do")
    @ApiOperation(
            value = "增加消费信息",
            notes = "增加消费信息,实体说明:\r\n" +
                    "balanceId:余额 ID (除了查询单个余额信息,一般用不到)\r\n" +
                    "userId:用户的唯一标识,传递 token 后可以忽略\r\n" +
                    "oldMoney:变更前余额,用于后台防止计算错误,查询订单用\r\n" +
                    "money:可用余额,用于前端展示\r\n" +
                    "moneyFlow:取值+,- 如果是充值,红包,传递 + ;消费传递 - \r\n" +
                    "flowNum: 本次交易流动金额,不管增加还是减少,均传递正值\r\n" +
                    "flowInfo:资金流动说明(充值,红包,消费)\r\n" +
                    "orderId:订单Id 消费时传递\r\n" +
                    "createTime:创建时间",
            httpMethod = "POST")
    public JsonResult add(@RequestBody Balance balance, String token) {
        if (!PublicControl.checkNotNull(token)) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "登录信息失效");

        }
        if (PublicControl.checkNotNull(balance.getFlowInfo()) &&
                PublicControl.checkNotNull(balance.getMoney()) &&
                PublicControl.checkNotNull(balance.getFlowNum()) &&
                PublicControl.checkNotNull(balance.getMoneyFlow()) &&
                (balance.getMoney() >= 0) &&
                (balance.getFlowNum() >= 0)
        ) {

            return pc.addUtils(service.insert(balance, token));

        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "余额信息不全,创建失败!");


    }

    /**
     * 查询消费信息
     *
     * @return
     */

    @RequestMapping("findPage.do")
    @ApiOperation(
            value = "查询消费信息",
            notes = "查询消费信息",
            httpMethod = "POST")
    public JsonResult findPage(
            @RequestParam(defaultValue = "2010-01-01 00:00:00") String beginTime,
            @RequestParam String endTime,
            @RequestParam String token,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows) {
        if (endTime == null) {
            endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
        if (!PublicControl.checkNotNull(token)) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "登录信息失效!", new PageResult(0, new ArrayList()));
        }
        PageResult result = service.findPage(beginTime, endTime, token, page, rows);
        return jr.jsonResultData(ResultCode.AUTH.res_code(), "查询成功!", result);
    }

    /**
     * 查询单个消费信息
     *
     * @return
     */

    @RequestMapping("findOne.do")
    @ApiIgnore
    @ApiOperation(
            value = "查询单条消费信息,返回对应订单信息",
            notes = "查询消费信息",
            httpMethod = "POST")
    public JsonResult findOne(HttpServletRequest request, String beginTime, String endTime, String token, Integer
            page, Integer rows) {
        beginTime = ServletRequestUtils.getStringParameter(request, "beginTime", "2010-01-01 00:00:00");
        endTime = ServletRequestUtils.getStringParameter(request, "endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if (!PublicControl.checkNotNull(token)) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), ResultCode.FAIL.message(), new PageResult(0, new ArrayList()));
        }
        PageResult result = service.findPage(beginTime, endTime, token, page, rows);
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), result);

    }


}
