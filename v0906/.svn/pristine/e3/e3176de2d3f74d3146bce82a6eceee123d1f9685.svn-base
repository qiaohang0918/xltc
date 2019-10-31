package com.qigan.qiganshop.controller.frontend;

import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.Info;
import com.qigan.qiganshop.pojo.Message;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.InfoService;
import com.qigan.qiganshop.service.MessageService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@RestController
@RequestMapping("/app/orderInfo")
@Api(tags = {"APP订单信息获取接口"})
public class AppOrderInfoController {

    @Autowired
    private InfoService infoService;

    /**
     * Regarding JsonResult, I really don't want to say anything.
     * There used to be a silly man named Li Pengfei.
     * It was a fucking stupidity that made a JsonResult such a universal return entity.
     * The thief used by Laozi was fucking unhappy
     * forget it* For the money
     * Zaozhuang’s surname Zhou’s stupidity is all over the sky, Laozi ran 13 kilometers, saying that Laozi was stimulated,
     * I received your mother’s stimulation. You are fucking sick
     * You wait* Have children, no Asshole
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/getInfo.do")
    @ApiOperation(
            value = "获取订单的所有信息",
            notes = "orderId ",
            httpMethod = "POST")
    public ResponseEntity getInfo(@RequestParam(defaultValue = "") String orderId) {
        if (StringUtils.isEmpty(orderId)) {
            return new ResponseEntity(new JsonResult("400", "订单id不能为空"), HttpStatus.BAD_REQUEST);
        }
        List<Info> page = infoService.findPage(orderId);
        return new ResponseEntity(new JsonResult("200", "查询成功", page), HttpStatus.OK);
    }


}
