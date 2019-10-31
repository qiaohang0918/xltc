package com.qigan.qiganshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.mapper.AppUserMapper;
import com.qigan.qiganshop.mapper.TbUserWishesMapper;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.TbUserWishes;
import com.qigan.qiganshop.pojo.TbUserWishesExample;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.XltcUserWishesService;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/18 11:53
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XltcUserWishesServiceImpl implements XltcUserWishesService {

    @Autowired
    TbUserWishesMapper userWishesMapper;
    @Autowired
    AppUserMapper appUserMapper;

    /**
     * 获取当前用户的福利信息
     * @param token
     * @return
     */
    @Override
    public XltcResult getCurrentUserWishes(String token) {
        if(!StringNotNull.check(token)){
            return XltcResult.error("token不能为空！");
        }
        AppUser user = appUserMapper.getAppUserByToken(token);
        System.out.println(user);
        if(user==null){
            return XltcResult.error("user不存在！");
        }
        TbUserWishesExample example=new TbUserWishesExample();
        TbUserWishesExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(user.getUserId());
        List<TbUserWishes> wishes = userWishesMapper.selectByExample(example);
        return XltcResult.ok(wishes);
    }

    /**
     * 查询所有用户的福利下发情况
     * @param parms
     * @return
     */
    @Override
    public XltcResult findAllUsersWishesByConditions(Map<String, Object> parms) {
        TbUserWishesExample example=new TbUserWishesExample();
        TbUserWishesExample.Criteria criteria = example.createCriteria();
        if(parms!=null && parms.size()>0){
            for (Map.Entry<String, Object> entry : parms.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String strValue=null;
                if(StringNotNull.check(key) && value!=null && !"".equals(strValue=String.valueOf(value))){
                    if("wishesId".equalsIgnoreCase(key)){
                        criteria.andWishesidEqualTo(strValue);
                    }else if("userId".equalsIgnoreCase(key)){
                        criteria.andUseridEqualTo(strValue);
                    }else if("wishesType".equalsIgnoreCase(key)){
                        criteria.andWishestypeEqualTo(strValue);
                    }else if("startTime".equalsIgnoreCase(key)){
                        criteria.andWishescreatetimeGreaterThanOrEqualTo(strValue);
                    }else if("endTime".equalsIgnoreCase(key)){
                        criteria.andWishescreatetimeLessThanOrEqualTo(strValue);
                    }else {

                    }
                }
            }
            String orderPattern = parms.get("order")==null || "".equals((String)parms.get("order"))? "DESC" : String.valueOf(parms.get("order"));
            example.setOrderByClause(" wishesCreateTime "+orderPattern);
        }
        Integer page = parms.get("page")==null? 1 : (Integer)parms.get("page");
        Integer size = parms.get("size")==null? 10 : (Integer)parms.get("size");
        PageHelper.startPage(page,size);
        List<Map> wishes = userWishesMapper.selectByExampleWith_Name_Phone(example);
        PageInfo<Map> info = new PageInfo<>(wishes);
        return XltcResult.ok(info);
    }


}
