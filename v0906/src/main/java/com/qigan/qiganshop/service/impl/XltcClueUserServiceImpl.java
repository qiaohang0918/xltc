package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.*;
import com.qigan.qiganshop.mapper.TbUserWishesMapper;
import com.qigan.qiganshop.myutils.SqlConstructUtils;
import com.qigan.qiganshop.pojo.*;
import com.qigan.qiganshop.service.UserCouponService;
import com.qigan.qiganshop.service.XltcClueUserService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/11 8:00
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XltcClueUserServiceImpl implements XltcClueUserService {

    @Autowired
    TbClueUserMapper mapper;
    @Autowired
    UserCouponMapper userCouponMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AppUserMapper userMapper;
    @Autowired
    UserCouponService userCouponService;
    @Autowired
    TbUserWishesMapper userWishesMapper;
    @Autowired
    TbExplandMapper explandMapper;
    @Autowired
    JedisUtil jedisUtil;

    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    /**
     * 插入线索用户记录
     * @param orginUserId
     * @param clueType
     * @param couponOrAmount
     * @param newUserId
     * @return
     */
    @Override
    public boolean insertRecord( String orginUserId, String clueType, String couponOrAmount, String newUserId) {
        TbClueUser clueUser = new TbClueUser();
        clueUser.setClueid(UUID.randomUUID().toString().replaceAll("-", ""));	//线索记录id
        clueUser.setCluetype(clueType.toLowerCase());		//线索类型
        clueUser.setClueuserid(newUserId);	//线索用户id
        clueUser.setRegisttime(dateFormat.format(new Date()));	//注册时间
        clueUser.setUserid(orginUserId);	//源用户id
        clueUser.setExplandtype(couponOrAmount);    //扩展渠道方式
        //插入线索用户记录
        int insert = mapper.insert(clueUser);
        //给新用户添加字段orginUserId
        userMapper.addOrginUserToClueUser(orginUserId,newUserId);
        //给源用户增加扩展用户数量
        userMapper.incrementExplandRegistersForOrginUser(orginUserId);
        //临时测试无福利
//        explandMapper.addHisRegistersCount(orginUserId);

        //判断福利类型
        if("coupon".equals(couponOrAmount.trim())){
            //查询新人优惠卷
            List<Coupon> list = this.searchClueUserCoupons();
            //插入优惠卷
            // Integer count = this.insertClueUserCoupon(list, newUserId);
            if(!SqlConstructUtils.nullList(list)){
                for (Coupon coupon : list) {
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setCouponId(coupon.getCouponId());
                    userCoupon.setUserId(newUserId);
                    userCouponService.add(userCoupon,false);
                }
            }
        }else if("amount".equals(couponOrAmount.trim())){
            //来自托儿 用户邀请
            //增加【托儿】用户的扩展注册用户数量
            explandMapper.addHisRegistersCount(orginUserId);

            //查询新人优惠卷
            List<Coupon> list = this.searchClueUserCoupons();
            //插入优惠卷
            // Integer count = this.insertClueUserCoupon(list, newUserId);
            if(!SqlConstructUtils.nullList(list)){
                for (Coupon coupon : list) {
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setCouponId(coupon.getCouponId());
                    userCoupon.setUserId(newUserId);
                    userCouponService.add(userCoupon,false);
                }
            }
        }else if("coupon_amount".equals(couponOrAmount.trim())){
            //来自普通用户邀请
            //查询新人优惠卷
            List<Coupon> list = this.searchClueUserCoupons();
            //插入优惠卷
            // Integer count = this.insertClueUserCoupon(list, newUserId);
            if(!SqlConstructUtils.nullList(list)){
                for (Coupon coupon : list) {
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setCouponId(coupon.getCouponId());
                    userCoupon.setUserId(newUserId);
                    userCouponService.add(userCoupon,false);
                }
            }
        }else {

        }
        return true;
    }

    /**
     * 源用户福利下发
     * @param orginUserId
     * @param couponOrAmount
     * @param newUserId
     * @return
     */
    @Override
    public boolean givenOrginUserWishes(String orginUserId, String couponOrAmount, String newUserId) {
        if("coupon".equalsIgnoreCase(couponOrAmount.trim())){
            //查询线索优惠卷
            List<Coupon> coupons = this.searchClueUserCoupons();
            //源用户下发优惠卷
//            Integer count = this.insertClueUserCoupon(coupons, orginUserId);
            if(!SqlConstructUtils.nullList(coupons)){
                for (Coupon coupon : coupons) {
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setCouponId(coupon.getCouponId());
                    userCoupon.setUserId(orginUserId);
                    Integer add = userCouponService.add(userCoupon,false);
                    if(add.intValue()>0){
                        //插入福利优惠卷下发记录
                        recordWishesGivenDetail(orginUserId, "优惠卷", coupon.getCouponName());
                    }
                }
            }
            return true;
        }else if("amount".equalsIgnoreCase(couponOrAmount.trim())){
            //下发账户金额预添加记录 (暂时停用)
//            String wishesAmount = jedisUtil.get(RedisConstant.CLUE_USER_AMOUNT);
//            if(StringNotNull.check(wishesAmount)){
//                //预添加奖励金额
//                jedisUtil.setToHash(RedisConstant.ORGIN_USER_WISHES_AMOUNT,newUserId,wishesAmount);
//            }
            return true;
        }else if("coupon_amount".equals(couponOrAmount.trim())){

            //源用户优惠卷预下发记录 (新用户首单后到账)
            boolean result = this.preGivenOrginUserCoupon(newUserId);

//            //下发账户金额预添加记录
//            String wishesAmount = jedisUtil.get(RedisConstant.CLUE_USER_AMOUNT);
//            if(StringNotNull.check(wishesAmount)){
//                //预添加奖励金额
//                jedisUtil.setToHash(RedisConstant.ORGIN_USER_WISHES_AMOUNT,newUserId,wishesAmount);
//            }
            return true;
        }else {

        }
        return false;
    }

    /**
     * 记录福利下发详情
     * @param userId
     * @param wishesType
     * @param wishesDetail
     * @return
     */
    public int recordWishesGivenDetail(String userId,String wishesType,String wishesDetail){
        TbUserWishes userWishes = new TbUserWishes();
        userWishes.setWishesid(UUID.randomUUID().toString().replaceAll("-", ""));
        userWishes.setUserid(userId);
        userWishes.setWishescreatetime(dateFormat.format(new Date()));
        userWishes.setWishestype(wishesType);
        userWishes.setWishesdetail(wishesDetail);
        int insert = userWishesMapper.insert(userWishes);
        return insert;
    }

    /**
     * 判断是否是首单，下发奖励金额
     * @param orderId
     * @return
     */
    @Override
    public boolean checkFirstOrderGivenWishes(String orderId) {
        Order order = orderMapper.findOne(orderId);
        if(order==null)
            throw XltcRuntimeException.throwable("尝试下发福利，但订单不存在，请联系管理员！");
        String userId = order.getUserId();
        //查询clueUser记录
        List<TbClueUser> tbClueUsers = findClueUsersByClueUserId(userId);
        //查询是否是首单（下发金额）
        String wishesAmount = jedisUtil.getFromHash(RedisConstant.ORGIN_USER_WISHES_AMOUNT, userId);
        if(StringNotNull.check(wishesAmount)){
            //查询，下发源用户福利金额
            if(StringNotNull.check(userId)){
                if(!SqlConstructUtils.nullList(tbClueUsers)){
                    //源用户id
                    String orginUserId = tbClueUsers.get(0).getUserid();
                    //增加源用户的账户金额
                    int updateResult = userMapper.givenWishesAmount(orginUserId,Double.parseDouble(wishesAmount));
                    //插入福利金额下发记录
                    recordWishesGivenDetail(orginUserId,"奖励金",wishesAmount);
                    //移除首单预福利记录
                    jedisUtil.removeElementFromHash(RedisConstant.ORGIN_USER_WISHES_AMOUNT, userId);
//                    return updateResult > 0 ? true : false;
                }
            }
        }
        //查询是否是首单（下发优惠卷）
        String couponIds = jedisUtil.getFromHash(RedisConstant.ORGIN_USER_WISHES_COUPONS, userId);
        if(StringNotNull.check(couponIds)){
            String[] split = couponIds.split(",");
            if(!SqlConstructUtils.nullList(tbClueUsers)){
                //源用户id
                String orginUserId = tbClueUsers.get(0).getUserid();
                //下发福利优惠卷
                for (String couponId : split) {
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setCouponId(couponId);
                    userCoupon.setUserId(orginUserId);
                    try {
                        Integer add = userCouponService.add(userCoupon,false);
                    }catch (Exception e){

                    }
                }
                //移除首单预福利优惠卷记录
                jedisUtil.removeElementFromHash(RedisConstant.ORGIN_USER_WISHES_COUPONS, userId);
            }
        }
        return true;
    }

    /**
     * 根据新用户id 查询clueUser记录
     * @param clueUserId
     * @return
     */
    public List<TbClueUser> findClueUsersByClueUserId(String clueUserId){
        TbClueUserExample example = new TbClueUserExample();
        TbClueUserExample.Criteria criteria = example.createCriteria();
        criteria.andClueuseridEqualTo(clueUserId);
        List<TbClueUser> tbClueUsers = mapper.selectByExample(example);
        return  tbClueUsers;
    }


    /**
     * 源用户福利优惠卷 预下发记录
     * @param newUserId
     * @return
     */
    public boolean preGivenOrginUserCoupon(String newUserId){
        Set<String> wishCouponIds = jedisUtil.getElementsFromHash("CLUE_USER_COUPONS");
        if(wishCouponIds!=null && wishCouponIds.size()>0){
            //可能福利优惠卷存在多张
            StringBuffer buffer = new StringBuffer(" ");
            for (String couponId : wishCouponIds) {
                buffer.append(couponId+",");
            }
            buffer.replace(buffer.length()-1,buffer.length(),"");
            jedisUtil.setToHash(RedisConstant.ORGIN_USER_WISHES_COUPONS,newUserId,buffer.toString());
        }
        return true;
    }

    /**
     * 插入新人优惠卷
     * @return
     */
    public Integer insertClueUserCoupon(List<Coupon> couponList,String userId){
        if(SqlConstructUtils.nullList(couponList))
            return null;
        List<UserCoupon> userCoupons = new ArrayList<>();
        Integer count =0;
        for (Coupon coupon : couponList) {
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setUserCouponId(UUID.randomUUID().toString().replace("-",""));
            userCoupon.setUserId(userId);
            userCoupon.setCouponId(coupon.getCouponId());
            //未使用
            userCoupon.setIsUse("0");
            Date date = new Date();
            //开始时间
            userCoupon.setStartTime(dateFormat.format(date));
            String day = jedisUtil.getFromHash(RedisConstant.CLUE_USER_COUPONS, coupon.getCouponId());
            //不可用时间
            userCoupon.setDisableTime(dateFormat.format(opratorDay(date,day)));
            //插入usercoupon
            int insert = userCouponMapper.insert(userCoupon);
            count += insert;
        }
        return  count;
    }


    /**
     * 查询新人优惠卷
     * @return
     */
    public List<Coupon> searchClueUserCoupons(){
        List<Coupon> list =null;
        //查询新人优惠卷，插入优惠卷
        Set<String> elements = jedisUtil.getElementsFromHash("CLUE_USER_COUPONS");
        String couponIds = SqlConstructUtils.constructListToStringsOnIn(null,elements);
        if(StringNotNull.check(couponIds)){
            //根据couponid查询新人优惠卷
             list = couponMapper.findSome(couponIds);
        }
        return list;
    }

    /**
     * 计算优惠卷不可用时间(默认7天)
     * @param date
     * @param day
     * @return
     */
    public  Date opratorDay(Date date,String day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer useDay=7;
        if(StringNotNull.check(day)){
            useDay=Integer.parseInt(day);
        }
        calendar.add(Calendar.DAY_OF_MONTH, +useDay);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
}
