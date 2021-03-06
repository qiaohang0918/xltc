package com.qigan.qiganshop.controller.frontend;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.Cart;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.CartService;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.UserCartService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 标签 controller
 *
 * @author wanghao
 * @time 2019-04-15 10:30
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/app/Cart")
@Api(value = "购物车 controller", tags = {"APP 购物车操作接口"})
public class AppCartController {
    @Autowired
    private CartService service;
    @Autowired
    private UserCartService userCartService;
    @Autowired
    private JsonResult jr;
    @Autowired
    private PublicControl pc;
    @Autowired
    private AppUserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    JedisUtil jedisUtil;

    /**
     * 查询库存
     * @param coordinate
     * @param token
     * @param itemIds
     * @return
     */
    @RequestMapping("/check.do")
    @ApiOperation(
            value = "购物车页面 ,为提交订单做准备",
            notes = "查询库存   \r\n" +
                    "无论库存数量是否充足,直接返回商品数量,APP端自己做判断",
            httpMethod = "POST")
    public JsonResult check(String coordinate, String token, String itemIds) {
        AppUser appUser = userService.getAppUserByToken(token);
        if (appUser == null) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "token 已失效");
        }
        if (StringNotNull.check(coordinate) && StringNotNull.check(itemIds)) {
            // 用实体进行接收订单信息
            return service.check(coordinate, itemIds);
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "itemIds 不能为空");

    }

    /**
     * 检查订单
     *
     * @param cart
     * @return
     */
    @RequestMapping("/Settlement.do")
    @ApiOperation(
            value = "购物车页面 ,结算按钮",
            notes = "结算按钮   \r\n" +
                    "传递购物车商品及数量信息,仅用于计算金额和查询优惠券,地址必传,切换地址重新调用该方法",
            httpMethod = "POST")
    public JsonResult settlement(@RequestBody Cart cart) {

        AppUser appUser = userService.getAppUserByToken(cart.getToken());
        if (appUser == null) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "token 已失效");
        }
        if (cart != null && appUser != null) {
            // 用实体进行接收订单信息
            JsonResult settlement = service.settlement(cart);
//            settlement.setIsVip(appUser.getMember());
            return settlement;
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cart不能为空");

    }

    /**
     * 添加商品
     *
     * @param cart
     * @return
     */
    @RequestMapping("/addGoods.do")
    @ApiOperation(
            value = "购物车添加单个商品",
            notes = "添加到服务器" +
                    "传递购物车商品及数量信息,仅用于保存购物车信息,地址必传,切换地址重新调用该方法",
            httpMethod = "POST")
    public JsonResult addGoods(@RequestParam String token, @RequestParam String coordinate, @RequestBody Params params) {
        if (params == null) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "参数为空");
        }
        AppUser appUser = userService.getAppUserByToken(token);
        if (appUser == null) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "token 已失效");
        }
        if (!params.getItems().isEmpty()) {
            try {
                // 用实体进行接收订单信息
                return pc.addUtils(userCartService.addOneGoods(appUser, coordinate, params.getItems()));
            }catch (XltcRuntimeException e){
                return jr.jsonResultData(ResultCode.FAIL.res_code(), e.getMessage());
            }
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "items不能为空");

    }


    /**
     * 检查订单
     * @param cart
     * @return
     */
    @RequestMapping("/saveCart.do")
    @ApiOperation(
            value = "注意: 本接口为全量保存,如果有为传递的数据,则直接删除 \r\n" +
                    "购物车保存",
            notes = "购物车更新后保存操作   \r\n" +
                    "传递购物车商品及数量信息,切换地址重新调用该方法",
            httpMethod = "POST")
    public JsonResult saveCart(@RequestParam String token, @RequestBody Params params) {
        if (params == null) {
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "参数为空");
        }
        AppUser appUser = userService.getAppUserByToken(token);
        if (appUser == null) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "token 已失效");
        }
        if (!params.getItems().isEmpty()) {
            boolean result = userCartService.saveCart(appUser, params.getItems());
            if (result) {
                return jr.jsonResultData("200");
            }
            return jr.jsonResultData("40x", "保存失败!");
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cart不能为空");
    }

    /**
     * 检查订单
     *
     * @param cart
     * @return
     */
    @RequestMapping("/getCart.do")
    @ApiOperation(
            value = "获取购物车信息",
            notes = "获取购物车信息   \r\n" +
                    "获取购物车信息  userChooseNum: 5 个数",
            httpMethod = "POST")
    public JsonResult getCart(@RequestParam String token, @RequestParam String coordinate) {
        AppUser appUser = userService.getAppUserByToken(token);
        if (appUser == null) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "token 已失效");
        }
        if (appUser != null) {
            // 用实体进行接收订单信息
            Map map = userCartService.getCart(appUser.getUserId(), coordinate);
            if (map != null) {
                return new JsonResult("200", "success", map);
            }
            return new JsonResult("40x", "fail");
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cart不能为空");

    }

    /**
     * 修改购物车
     *
     * @param cart
     * @return
     */
//    @RequestMapping("/updateCart.do")
//    @ApiOperation(
//            value = "更新购物车信息",
//            notes = "更新购物车信息   \r\n" +
//                    "更新购物车信息",
//            httpMethod = "POST")
//    public JsonResult updateCart(@RequestParam String token, @RequestBody Params params) {
//        AppUser appUser = userService.getAppUserByToken(token);
//        if (appUser == null) {
//            return jr.jsonResultData(ResultCode.AUTH.res_code(), "token 已失效");
//        }
//        if (appUser != null && !params.getItems().isEmpty()) {
//            // 用实体进行接收订单信息
//            boolean result = userCartService.updateCart(appUser.getUserId(), params.getItems());
//            if (result) {
//                return jr.jsonResultData("200");
//            }
//            return jr.jsonResultData("40x", "修改失败!");
//
//        }
//        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cart不能为空");
//
//    }



    @RequestMapping("/updateCart.do")
    @ApiOperation(
            value = "更新购物车信息",
            notes = "更新购物车信息   \r\n" +
                    "更新购物车信息",
            httpMethod = "POST")
    public JsonResult updateCart(@RequestParam String token, @RequestParam String coordinate , @RequestBody Params params) {

//        //校验token
//        if(token ==null || "".equals(token) ){
//            return jr.jsonResultData(ResultCode.FAIL.res_code(), "token不能为空");
//        }
//
//        //校验经纬度
//        if(coordinate ==null || "".equals(coordinate) ){
//            return jr.jsonResultData(ResultCode.FAIL.res_code(), "经纬度不能为空");
//        }
//
//        //查询仓库id
//        JsonResult warehouseId = goodsService.getWarehouseId(coordinate);
//
//        if(warehouseId==null || "".equals(warehouseId.toString())){
//            jr.jsonResultData(ResultCode.FAIL.res_code(), "当前地理位置没有仓库!");
//        }
//
//        //尝试缓存获取（token_wareHouseId）
//        String userCart = jedisUtil.getFromHash("UserCart", token+"_"+warehouseId);
//
//        Params currentCartRecord;
//
//        //获取当前购物车记录
//        if(userCart!=null && !"".equals(userCart)){
//            currentCartRecord = JSON.parseObject(userCart, Params.class);
//        }else {
//            currentCartRecord=
//        }

        //校验token
        if(token ==null || "".equals(token) ){
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "token不能为空");
        }

        //校验经纬度
        if(coordinate ==null || "".equals(coordinate) ){
            return jr.jsonResultData(ResultCode.FAIL.res_code(), "经纬度不能为空");
        }

        //查询仓库id
        JsonResult warehouse = goodsService.getWarehouseId(coordinate);

        AppUser appUser = userService.getAppUserByToken(token);

        if (appUser == null) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "token 已失效");
        }
        if (appUser != null && !params.getItems().isEmpty()) {
            // 用实体进行接收订单信息
            String wareHouseId = warehouse.getMessage();
            boolean result = userCartService.updateCart(appUser.getUserId(),wareHouseId, params.getItems());
            if (result) {
                return jr.jsonResultData("200");
            }
            return jr.jsonResultData("40x", "修改失败，该商品库存已达上限!");
        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cart不能为空");

    }

    /**
     * 修改购物车
     *
     * @param cart
     * @return
     */
    @RequestMapping("/deleteCart.do")
    @ApiOperation(
            value = "删除购物车信息",
            notes = "删除购物车信息   \r\n" +
                    "删除购物车信息",
            httpMethod = "POST")
    public JsonResult deleteCart(@RequestParam String token, @RequestParam String[] skuIds) {
        AppUser appUser = userService.getAppUserByToken(token);
        if (appUser == null) {
            return jr.jsonResultData(ResultCode.AUTH.res_code(), "token 已失效");
        }
        if (appUser != null && skuIds.length > 0) {
            // 用实体进行接收订单信息
            boolean result = userCartService.deleteCart(appUser.getUserId(), skuIds);
            if (result) {
                return jr.jsonResultData("200");
            }
            return jr.jsonResultData("40x", "删除失败!");

        }
        return jr.jsonResultData(ResultCode.FAIL.res_code(), "cart不能为空");

    }

    @ApiModel(value = "购物车信息接收")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Params {
        @ApiModelProperty(value = "参数 {\"117470594771\":1}")
        private LinkedHashMap<String, Integer> items;

        public LinkedHashMap<String, Integer> getItems() {
            return items;
        }

        public void setItems(LinkedHashMap<String, Integer> items) {
            this.items = items;
        }
    }


}
