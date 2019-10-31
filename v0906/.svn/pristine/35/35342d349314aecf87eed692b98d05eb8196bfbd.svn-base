package com.qigan.qiganshop.controller.frontend;


import com.qigan.qiganshop.pojo.Pay;
import com.qigan.qiganshop.service.PayService;
import com.qigan.qiganshop.util.exception.ExceptionUtil;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/app/pay")
@Api(tags = {"支付订单管理接口"})
public class PayController {

    @Autowired
    private JsonResult jr;
    @Autowired
    private PayService payService;

    @RequestMapping(value = "/select.do")
    @ApiOperation(
            value = "支付查询接口",
            notes = "orderId 订单号",
            httpMethod = "POST")
    public JsonResult select(String orderId,HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws  Exception {
        try{
            //Pay pay = payService.selectByOutpaycode(out_trade_no);
            Pay pay1 = payService.selectByOrderId(orderId);
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功",pay1);
        } catch (Exception e) {
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }

}
