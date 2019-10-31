package com.qigan.qiganshop.controller.frontend;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.exception.XltcTokenRuntimeException;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.UserAddress;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.UserAddrService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import com.qigan.qiganshop.utils.json.JsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 规格 controller
 *
 * @author wanghao
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/app/UserAddr")
@Api(value = "用户收货地址 controller", tags = { "APP用户收货地址操作接口" })
public class UserAddrController {
	@Autowired
	private JsonResult jr;
	@Autowired
	private UserAddrService userAddService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private PublicControl pc;

	@Autowired
	JedisUtil jedis;

	/**
	 * 查询所有规格信息或者分页查询或者条件查询,三者随意组合
	 *
	 * @param request
	 * @param token
	 * @param page
	 * @param rows
	 * @return
	 */

	public AppUser appUser;

	@ModelAttribute
	public void appUserModel(String token) {
		if(StringUtils.isBlank(token)){
			throw XltcTokenRuntimeException.throwable("认证失败");
		}
		String userStr = jedis.getFromHash(RedisConstant.USER, token);
		if (StringUtils.isNotBlank(userStr)) {
			appUser = JsonUtils.readValue(userStr, AppUser.class);
		} else {
			appUser = appUserService.getAppUserByToken(token);
		}
	}

	@RequestMapping("findPage.do")
	@ApiOperation(value = "分页获取用户自己所有的收货地址列表", notes = "分页获取收货地址,token 为唯一标识 ,page 为页码,rows 为每页记录数 \r\n"
			+ "page,rows 不传默认 page=1,rows=10 ", httpMethod = "POST")
	public JsonResult findPage(HttpServletRequest request, String token, Integer page, Integer rows) {
		page = ServletRequestUtils.getIntParameter(request, "page", 1);
		rows = ServletRequestUtils.getIntParameter(request, "rows", 10);
		if (token == null || "".equals(token)) {
			return jr.jsonResultData(ResultCode.FAIL.res_code(), ResultCode.FAIL.message(),
					new PageResult(0, new ArrayList()));
		} else {
//			AppUser appUser;
//			String userStr = jedis.getFromHash(RedisConstant.USER, token);
//			if (StringUtils.isNotBlank(userStr)) {
//				appUser = JsonUtils.readValue(userStr, AppUser.class);
//			} else {
//				appUser = appUserService.getAppUserByToken(token);
//			}
			if (appUser == null) {
				return jr.jsonResultData(ResultCode.FAIL.res_code(), "token 失效!!", new PageResult(0, new ArrayList()));

			}
			String addStr = jedis.getFromHash(RedisConstant.USER_ADDR_ALL, appUser.getUserId());
			if (StringUtils.isNotBlank(addStr)) {
				List<UserAddress> list = JsonUtils.readToList(addStr, UserAddress.class);
				return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(),
						new PageResult(list.size(), list));
			}
			PageResult result = userAddService.findPage(appUser.getUserId(), page, rows);
			return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), result);
		}
	}

	/**
	 * 根据主键查询单个规格信息
	 *
	 * @param userAddId
	 * @return
	 */

	@RequestMapping("findOne.do")
	@ApiOperation(value = "根据 ID 查询地址", notes = "根据 ID 查询地址", httpMethod = "POST")
	public JsonResult findOne(String userAddId) {
		if (userAddId != null && !"".equals(userAddId)) {
			String getStr = jedis.getFromHash(RedisConstant.USER_ADDR_ONE, userAddId);
			if (StringUtils.isNotBlank(getStr)) {
				return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(),
						JsonUtils.readValue(getStr, UserAddress.class));
			}

			return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(),
					userAddService.findOne(userAddId));
		}
		return jr.jsonResultData(ResultCode.FAIL.res_code(), ResultCode.FAIL.message(), new UserAddress());

	}

	/**
	 * 增加商品规格,同时增加商品规格详情
	 *
	 * @param token
	 * @param userAddress
	 * @return
	 */
	@RequestMapping("add.do")
	@ApiOperation(value = "增加收货地址", notes = "新增收货地址,必须登录,传递token,userAddrId 不传,createTime 不传", httpMethod = "POST")
	public JsonResult add(@RequestBody UserAddress userAddress, String token) {
//		AppUser appUser = appUserService.getAppUserByToken(token);
		if (appUser != null) {
			String userId = appUser.getUserId();
			userAddress.setUserId(userId);
		} else {
			return jr.jsonResultData(ResultCode.FAIL.res_code(), "token失效!");

		}
		if (userAddress.getName() != null && !"".equals(userAddress.getName())) {
			return pc.addUtils(userAddService.add(userAddress));
		} else {
			return jr.jsonResultData(ResultCode.FAIL.res_code(), "收件人不能为空!");
		}

	}

	/**
	 * 删除商品规格,同时删除商品详情
	 *
	 * @param token
	 * @param userAddId
	 * @return
	 */
	@RequestMapping("delete.do")
	@ApiOperation(value = "删除收货地址", notes = "删除收货地址", httpMethod = "POST")
	public JsonResult delete(String token, String userAddId) {
		if (token != null && !"".equals(token)) {
//			AppUser appUser = appUserService.getAppUserByToken(token);
			if (appUser == null) {
				return jr.jsonResultData(ResultCode.FAIL.res_code(), "token 失效!");

			}
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(appUser.getUserId());
			userAddress.setUserAddId(userAddId);
			Integer count = userAddService.delete(userAddress);
			Integer delete = count;
			if (count == 1) {
				return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "删除成功!");
			} else {
				return jr.jsonResultData(ResultCode.FAIL.res_code(), "删除失败!");
			}
		}
		return jr.jsonResultData(ResultCode.FAIL.res_code(), "删除失败,必填参数为空,请检查大小写等问题!");

	}

	/**
	 * 修改商品规格信息,为了保证数据简单处理,先删后增
	 *
	 * @param token
	 * @param userAddress
	 * @return
	 */
	@RequestMapping("update.do")
	@ApiOperation(value = "修改收货地址", notes = "修改收货地址", httpMethod = "POST")
	public JsonResult update(@RequestBody UserAddress userAddress, String token) {
//		AppUser appUser = appUserService.getAppUserByToken(token);
		if (appUser != null) {
			String userId = appUser.getUserId();
			userAddress.setUserId(userId);
		} else {
			return jr.jsonResultData(ResultCode.FAIL.res_code(), "token 已失效!");
		}
		if (userAddress.getName() != null && !"".equals(userAddress.getName()) && userAddress.getUserAddId() != null
				&& !"".equals(userAddress.getUserAddId())) {
			return pc.updateUtils(userAddService.update(userAddress));
		} else {
			return jr.jsonResultData(ResultCode.FAIL.res_code(), "收件人不能为空!");
		}

	}

}
