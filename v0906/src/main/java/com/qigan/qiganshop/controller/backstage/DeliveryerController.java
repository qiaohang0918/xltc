package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.pojo.Deliveryer;
import com.qigan.qiganshop.service.DeliveryerService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配送员
 *
 * @author wanghao
 */
@SuppressWarnings("all")

@RestController
@Api(tags = {"(Web App)配送员基本信息操作接口"})
public class DeliveryerController {
    @Autowired
    private DeliveryerService service;
    @Autowired
    private JsonResult jr;
    @Autowired
    private PublicControl pc;

    /**
     * 查询单个配送员
     *
     * @param deliveryerId
     * @return
     */

    @RequestMapping(value = "Deliveryer/findOne.do")
    @ApiOperation(
            value = "根据 ID 查询配送员",
            notes = "根据ID查询配送员",
            httpMethod = "POST")
    public JsonResult findOne(Integer deliveryerId) {
        if (deliveryerId != null) {
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findOne(deliveryerId));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "deliveryerId 不能为空!", NotNull.checkNull(new Deliveryer()));
    }

    /**
     * 条件查询配送员
     *
     * @param deliveryerId
     * @return
     */

    @RequestMapping("Deliveryer/findPage.do")
    @ApiOperation(
            value = "条件查询,分页查询,",
            notes = "根据ID查询配送员",
            httpMethod = "POST")
    public JsonResult findPage(@RequestBody(required = false) Deliveryer deliveryer, Integer page, Integer size) {
        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), service.findPage(deliveryer, page, size));
    }

    /**
     * 新增
     *
     * @param deliveryer
     * @return
     */

    @RequestMapping("Deliveryer/add.do")
    @ApiOperation(
            value = "新增",
            notes = "新增配送员",
            httpMethod = "POST")
    public JsonResult add(@RequestBody(required = true) Deliveryer deliveryer) {
        if (NotNull.checkString(deliveryer.getName(), deliveryer.getPhoneNum())) {

            return pc.addUtils(service.add(deliveryer));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "参数不全,姓名 密码 手机号必填", null);
    }

    /**
     * 更新配送员
     *
     * @param deliveryer
     * @return
     */

    @RequestMapping("Deliveryer/update.do")
    @ApiOperation(
            value = "更新配送员",
            notes = "根据ID更新配送员",
            httpMethod = "POST")
    public JsonResult update(@RequestBody(required = true) Deliveryer deliveryer) {
        if (deliveryer != null && deliveryer.getDeliveryerId() != null) {
            return pc.updateUtils(service.update(deliveryer));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "ID 不能为空", new Object());
    }

    /**
     * 登录
     *
     * @param deliveryerId
     * @param passwd
     * @return
     */

    @RequestMapping("app/Deliveryer/login.do")
    @ApiOperation(
            value = "配送员登录",
            notes = "登录",
            httpMethod = "POST")
    public JsonResult update(Integer deliveryerId, String passwd) {
        if (deliveryerId != null && NotNull.checkString(passwd)) {
            Deliveryer login = service.login(deliveryerId, passwd);
            return NotNull.checkString(login.getName(), login.getPhoneNum()) ? jr.jsonResultData(ResultCode.SUCCESS.res_code(), "登陆成功", login) :
                    jr.jsonResultData(ResultCode.AUTH.res_code(), "登录失败,用户名或密码错误", login);
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "ID 和 密码 不能为空");
    }

    @RequestMapping("Deliveryer/reloadPasswd.do")
    @ApiOperation(
            value = "重置密码",
            notes = "重置密码",
            httpMethod = "POST")
    public JsonResult reloadPasswd(Integer deliveryerId) {
        if (deliveryerId != null) {
            Deliveryer deliveryer = new Deliveryer();
            deliveryer.setDeliveryerId(deliveryerId);
            deliveryer.setPasswd("123456");
            return pc.updateUtils(service.update(deliveryer));
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "ID 不能为空");
    }
}



