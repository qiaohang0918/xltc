package com.qigan.qiganshop.service.impl;

import static com.qigan.qiganshop.util.quartz.OrderQuartzJob.DF;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.BannerMapper;
import com.qigan.qiganshop.pojo.Banner;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.service.BannerService;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.picture.PicUtil;
import com.qigan.qiganshop.util.result.PageResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerMapper mapper;
	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER_URL;
	@Autowired
	private PicUtil picUtil;

	@Autowired
	GoodsService service;

	@Autowired
	JedisUtil jedis;

	@Override
	public List<Banner> selectBanner(String kind) {
		List<Banner> banners = mapper.selectBanner(kind);
		for (Banner banner : banners) {
			if (banner != null && banner.getImage() != null) {
				banner.setImage(picUtil.addOneUrlHead(banner.getImage()));
			}
		}
		return banners;
	}

	@Override
	public int insertBanner(Banner banner) {
		return mapper.insertBanner(banner);
	}

	@Override
	public int updateBanner(Banner banner) {
		return mapper.updateBanner(banner);
	}

	@Override
	public int deleteBanner(Integer bannerId) {
		return mapper.deleteBanner(bannerId);
	}

	@Override
	public int sortBanner(Integer bannerId1, Integer bannerId2) {
		Banner banner1 = mapper.selectBannerById(bannerId1);
		Banner banner2 = mapper.selectBannerById(bannerId2);
		int suc1 = mapper.sortBanner(bannerId1, banner2.getSort());
		int suc2 = mapper.sortBanner(bannerId2, banner1.getSort());
		if (suc1 == 1 && suc2 == 1) {
			return 1;
		}
		return 0;
	}

	@Override
	public void updateEnabel(String status, Integer... ids) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(status) || ids == null)
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");
		for (Integer id : ids) {
			mapper.updateEnabel(status, id);
		}
		jedis.del(RedisConstant.HOME_BANANER.getBytes());
	}

	@Override
	public void updateWare(String wareId, Integer id) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(wareId) || id == null)
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");
		mapper.updateWare(wareId, id);
		jedis.del(RedisConstant.HOME_BANANER.getBytes());
	}

	@Override
	public void enabelTask4Banner(Integer id, String startDate, String endDate) {
		// TODO Auto-generated method stub
		if (StringUtils.isAllBlank(startDate, endDate))
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");

		LocalDateTime startTime = LocalDateTime.parse(startDate, DF);
		LocalDateTime endTime = LocalDateTime.parse(endDate, DF);
		Long end = Duration.between(LocalDateTime.now(), endTime).toMillis() / 1000;
		Long start = Duration.between(LocalDateTime.now(), startTime).toMillis() / 1000;
		if (start < 0)
			throw XltcRuntimeException.throwable("开始时间不能小于当前时间");

		Long endCheck = Duration.between(startTime, endTime).toMillis() / 1000;
		if (endCheck < 0)
			throw XltcRuntimeException.throwable("结束时间不能小于开始时间");
		int insert = mapper.updateEnabelDate(startDate, endDate, id);
		if (insert > 0) {
			jedis.set(RedisConstant.OPEN_BANNER + id, "1");
			jedis.expire((RedisConstant.OPEN_BANNER + id).getBytes(), start.intValue());
			jedis.set(RedisConstant.CLOSE_BANNER + id, "1");
			jedis.expire((RedisConstant.CLOSE_BANNER + id).getBytes(), end.intValue());
		}
		jedis.del(RedisConstant.HOME_BANANER.getBytes());
	}

	@Override
	public void delEnabelTask(Integer id) {
		// TODO Auto-generated method stub
		int update = mapper.updateDelEnabelDate(id);
		if (update > 0) {
			jedis.del(RedisConstant.OPEN_BANNER + id);
			jedis.del(RedisConstant.CLOSE_BANNER + id);
		}
	}

	@Override
	public PageResult findAllBannerByType(String moduleType, CommonPage page) {
		// TODO Auto-generated method stub
		page.startPageHelper();
		List<Banner> banners = mapper.selectBannerByType(moduleType);
		for (Banner banner : banners) {
			if (banner != null && banner.getImage() != null) {
				banner.setImage(picUtil.addOneUrlHead(banner.getImage()));
			}
		}
		PageInfo<?> pageInfo = new PageInfo<>(banners);
		return new PageResult(pageInfo.getTotal(), banners);
	}

	@Override
	public void deleteBannerById(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteBannerById(id);
	}

}
