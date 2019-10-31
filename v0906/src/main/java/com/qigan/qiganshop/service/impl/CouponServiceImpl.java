package com.qigan.qiganshop.service.impl;

import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.constant.DataState;
import com.qigan.qiganshop.domain.TbCouponDetails;
import com.qigan.qiganshop.domain.TbCouponDetailsExample;
import com.qigan.qiganshop.domain.TbCouponDetailsExample.Criteria;
import com.qigan.qiganshop.enums.CouponType;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.CouponMapper;
import com.qigan.qiganshop.mapper.TbCouponDetailsMapper;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Coupon;
import com.qigan.qiganshop.pojo.CouponDetailsModel;
import com.qigan.qiganshop.service.CouponService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.PageResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-05 14:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper mapper;
    
    @Autowired
    private TbCouponDetailsMapper detailsMapper;

    /**
     * 新增优惠券
     *
     * @param coupon
     * @return
     */
    @Override
    public int add(Coupon coupon) {
    	if(coupon == null)
    		throw XltcRuntimeException.throwable("参数不全");
    	if(StringUtils.isBlank(coupon.getType()))
    		throw XltcRuntimeException.throwable("类型不能为空");
    	
        coupon.setCouponId(UUID.randomUUID().toString().replace("-", ""));
        coupon.setEnable("0");
        coupon.setEnableTime(null);
        if (StringNotNull.check(coupon.getBegin()) && StringNotNull.check(coupon.getEnd())) {
        	if ("3".equals(coupon.getType())) {
                // 鸡蛋券
                if (coupon.getEggNumber() > 0) {
                    return mapper.insert(coupon);
                }
            } 
        	return mapper.insert(coupon);
        }else if(StringUtils.isNotBlank(coupon.getEnd())){
        	return mapper.insert(coupon);
        }
        return 0;
//        if (StringNotNull.check(coupon.getBegin()) &&
//                StringNotNull.check(coupon.getEnd())) {
//            if ("1".equals(coupon.getType())) {
//                // 满减优惠券
//                return mapper.insert(coupon);
//            } else if ("2".equals(coupon.getType())) {
//                // 运费券
//                return mapper.insert(coupon);
//            } else if ("3".equals(coupon.getType())) {
//                // 鸡蛋券
//                if (coupon.getEggNumber() > 0) {
//                    return mapper.insert(coupon);
//                }
//            } 
//        }else if(StringUtils.isNotBlank(coupon.getEnd())){
//        	return mapper.insert(coupon);
//        }
//        return 0;
    }

    /**
     * 删除优惠券
     *
     * @param ids
     * @return
     */
    @Override
    public int delete(String[] ids) {
        int count = 0;
        for (String id : ids) {
            Coupon one = this.findOne(id);
            if (one != null && !StringNotNull.check(one.getEnableTime())) {
                // 之前未启用过该分类,用户从未领取,可删除
                count += mapper.delete(id);
            }
        }
        return count;
    }

    /**
     * 更新优惠券
     *
     * @param coupon
     * @return
     */
    @Override
    public int update(Coupon coupon) {
    	if(coupon == null)
    		throw XltcRuntimeException.throwable("参数不全");
    	
    	if(StringUtils.isBlank(coupon.getType()) || StringUtils.isBlank(coupon.getCouponId()))
    		throw XltcRuntimeException.throwable("类型或id不能为空");
    	
        if (StringNotNull.check(coupon.getBegin()) &&
                StringNotNull.check(coupon.getEnd())) {
            if ("1".equals(coupon.getType())) {
                // 满减优惠券
                return mapper.update(coupon);
            } else if ("2".equals(coupon.getType())) {
                // 运费券
                return mapper.update(coupon);
            } else if ("3".equals(coupon.getType())) {
                // 鸡蛋券
                if (coupon.getEggNumber() > 0) {
                    return mapper.update(coupon);
                }
            }else{
            	coupon.setBegin("");
            	return mapper.update(coupon);
            }
        }
        return 0;
    }

    /**
     * 更新优惠券
     *
     * @param couponIds
     * @param flag
     * @return
     */
    @Override
    public int updateEnable(String[] couponIds, String flag) {
        int count = 0;
        for (String couponId : couponIds) {
            Coupon coupon = new Coupon();
            coupon.setCouponId(couponId);
            coupon.setEnable(flag);
            Coupon one = this.findOne(couponId);
            if (one != null && StringNotNull.check(one.getEnableTime())) {
                // 第一次启用
                coupon.setEnableTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
            count += mapper.update(coupon);
        }
        return count;
    }

    /**
     * 查询单个
     *
     * @param couponId
     * @return
     */
    @Override
    public Coupon findOne(String couponId) {
        return mapper.findOne(couponId);
    }

    /**
     * 查询集合
     *
     * @param coupon
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult findPage(Coupon coupon, Integer page, Integer rows) {
        List<Coupon> list = null;
        if (page != null && rows != null) {
            list = mapper.findPage(coupon, (page - 1) * rows, rows);
        } else {
            list = mapper.findPage(coupon, null, null);

        }
        return new PageResult(mapper.findPage(coupon, null, null).size(),
                (List<Coupon>) NotNull.checkListNull(list));
    }

    @Override
    public List<Coupon> findCouponByIds(String couponIds) {
        return mapper.findSome(couponIds);
    }

	@Override
	public void saveCouponDetails(CouponDetailsModel model) {
		// TODO Auto-generated method stub
		if(model == null)
			throw XltcRuntimeException.throwable("参数为空");
		
		List<TbCouponDetails> details = model.getCouponDetails();
		if(details == null || details.size() == 0)
			throw XltcRuntimeException.throwable("参数为空");
		
		for (TbCouponDetails tbCouponDetails : details) {
			tbCouponDetails.setStatus(DataState.NOR);
			detailsMapper.insertSelective(tbCouponDetails);
		}
		
	}

	@Override
	public PageResult selectCouponDetails(String couponId, CommonPage page) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(couponId))
			throw XltcRuntimeException.throwable("优惠券Id参数为空");
		page.startPageHelper();
		List<?> list = detailsMapper.selectCouponDetails(couponId);
		PageInfo<?> result = new PageInfo<>(list);
		return new PageResult(result.getTotal(), list);
	}

	@Override
	public void deleteDetails(String couponId, String type, String... codes) {
		// TODO Auto-generated method stub
		for (String code : codes) {
			TbCouponDetailsExample example = new TbCouponDetailsExample();
			Criteria criteria = example.createCriteria();
			criteria.andCouponidEqualTo(couponId);
			if("type".equals(type)){
				criteria.andTypeidEqualTo(code);
			}else{
				criteria.andGoodscodeEqualTo(code);			
			}
			detailsMapper.deleteByExample(example);
		}
	}

	@Override
	public List<Coupon> findNewFirstCoupon(CouponType type) {
		// TODO Auto-generated method stub
		if(type == null)
			return null;
		return mapper.findCouponByType(type.getValue());
	}

	@Override
	public void deleteCoupon(String... ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			Integer userReciveCoupon = mapper.countUserCoupon();
			if(userReciveCoupon != null && userReciveCoupon > 0)
				throw XltcRuntimeException.throwable("用户已领取该优惠券，无法删除");
			
			mapper.delete(id);
			TbCouponDetailsExample example = new TbCouponDetailsExample();
			example.createCriteria().andCouponidEqualTo(id);
			detailsMapper.deleteByExample(example);
		}
	}
	
}
