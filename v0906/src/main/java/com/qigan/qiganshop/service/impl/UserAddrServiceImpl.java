package com.qigan.qiganshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.exception.XltcTokenRuntimeException;
import com.qigan.qiganshop.mapper.UserAddressMapper;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.UserAddress;
import com.qigan.qiganshop.pojo.XltcPageResult;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.UserAddrService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.utils.json.JsonUtils;

/**
 * 用户收货地址实现层
 *
 * @author wanghao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAddrServiceImpl implements UserAddrService {
	@Autowired
	private UserAddressMapper mapper;
	
	@Autowired
	private AppUserService userService;
	
	@Autowired
	JedisUtil jedis;

	/**
	 * 增加
	 *
	 * @param userAddress
	 */
	@Override
	public Integer add(UserAddress userAddress) {
		if (userAddress.getUserAddId() == null || "".equals(userAddress.getUserAddId())) {
			userAddress.setUserAddId(UUID.randomUUID().toString().replace("-", ""));
		}
		if ("1".equals(userAddress.getIsDefault())) {
			updateNoDefault(userAddress.getUserId());
		} else {
			userAddress.setIsDefault("0");
		}
		jedis.removeElementFromHash(RedisConstant.USER_ADDR_ALL, userAddress.getUserId());
		return mapper.insert(userAddress);
	}

	/**
	 * 修改
	 *
	 * @param userAddress
	 */
	@Override
	public Integer update(UserAddress userAddress) {
		if ("1".equals(userAddress.getIsDefault())) {
			/**
			 * 说明该地址为默认地址,其他地址应修改为非默认
			 */
			updateNoDefault(userAddress.getUserId());
		} else {
			userAddress.setIsDefault("0");
		}
		jedis.removeElementFromHash(RedisConstant.USER_ADDR_ALL, userAddress.getUserId());
		return mapper.update(userAddress);
	}

	/**
	 * 将该用户的所有收货地址设置为非默认
	 *
	 * @param userId
	 */
	private void updateNoDefault(String userId) {
		jedis.removeElementFromHash(RedisConstant.USER_ADDR_ALL, userId);
		mapper.updateIsDefault(userId);
	}

	/**
	 * 根据ID获取实体
	 *
	 * @param userAddId
	 * @return
	 */
	@Override
	public UserAddress findOne(String userAddId) {
		UserAddress userAddr = mapper.findOne(userAddId);
		if(userAddr != null){
			jedis.setToHash(RedisConstant.USER_ADDR_ONE, userAddId, JsonUtils.writeValueAsString(userAddr));
		}
		return userAddr;
	}

	/**
	 * 批量删除
	 *
	 * @param userAddress
	 */
	@Override
	public Integer delete(UserAddress userAddress) {
		int delete = mapper.delete(userAddress);
		PageResult page = this.findPage(userAddress.getUserId(), null, null);
		if (page.getTotal() > 0) {
			/**
			 * 将第一个地址设置为默认地址
			 */
			UserAddress address = (UserAddress) page.getRows().get(0);
			address.setIsDefault("1");
			this.update(address);
		}
		jedis.removeElementFromHash(RedisConstant.USER_ADDR_ALL, userAddress.getUserId());
		return delete;

	}

	/**
	 * 分页
	 *
	 * @param userId
	 * @param pageNum
	 *            当前页 码
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	@Override
	public PageResult findPage(String userId, Integer pageNum, Integer pageSize) {
		List<UserAddress> list = new ArrayList<>();
//		if (pageNum != null && pageSize != null) {
//
//			list = mapper.findByUserId(userId, (pageNum - 1) * pageSize, pageSize);
//		} else {
			list = mapper.findByUserId(userId, null, null);
			if(list != null)
				jedis.setToHash(RedisConstant.USER_ADDR_ALL, userId, JsonUtils.writeValueAsString(list));

//		}
		return new PageResult(mapper.findByUserId(userId, null, null).size(),
				(List<UserAddress>) NotNull.checkListNull(list));
	}

	@Override
	public XltcPageResult findPage(CommonPage page) {
		XltcPageResult result = new XltcPageResult();
		if(page.getWheres() == null )
			throw XltcTokenRuntimeException.throwable("token为空");
		
		String token = page.getWheres().get("token") + "";
		if(StringUtils.isBlank(token))
			throw XltcTokenRuntimeException.throwable("token为空");
		
		AppUser user = userService.getAppUserByToken(token);
		if(user == null)
			throw new XltcTokenRuntimeException("token 失效");
		
		
		if(page.isPagination()){
			page.startPageHelper();
			List<UserAddress> adds =  mapper.findById(user.getUserId());
			PageInfo<UserAddress> pageInfo = new PageInfo<>(adds);
			result.setNowPage(page.getPage());
			result.setResult_list(adds);
			result.setTotalPage(pageInfo.getPages());
			result.setTotalData(pageInfo.getTotal());
			return result;
		}
		List<UserAddress> adds =  mapper.findById(user.getUserId());
		result.setTotalData(adds.size());
		result.setResult_list(adds);
		return result;
	}
}
