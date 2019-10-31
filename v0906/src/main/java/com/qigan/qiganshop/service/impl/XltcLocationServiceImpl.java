package com.qigan.qiganshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.mapper.XltcLocationsMapper;
import com.qigan.qiganshop.pojo.XltcLocations;
import com.qigan.qiganshop.pojo.XltcLocationsExample;
import com.qigan.qiganshop.service.XltcLocationService;
import com.qigan.qiganshop.util.access.JedisUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create nby qh
 */
@Service
@Transactional
public class XltcLocationServiceImpl implements XltcLocationService {

    @Resource
    JedisUtil jedisUtil;

    @Resource
    XltcLocationsMapper locationsMapper;

    /**
     *查询省市区
     * @return
     */
    @Override
    public Map<String, Object> getLocationsWithInCountry() throws InvocationTargetException, IllegalAccessException {
        Map resultMap = new HashMap<>();
       // resultMap.put("data",new ArrayList<XltcLocations>());
        //查询缓存
        String locations = jedisUtil.get("locations");
        if(locations!=null && !"".equals(locations.trim())){
            resultMap = JSON.parseObject(locations, Map.class);
        }else {
            //查询数据库放入缓存
            XltcLocationsExample example = new XltcLocationsExample();
            XltcLocationsExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(0);
            List<XltcLocations> leavel_1 = locationsMapper.selectByExample(example);
            ArrayList<XltcLocations> list = new ArrayList<>();

            //递归获取子区域
            if(leavel_1!=null && leavel_1.size()>0){
                for (XltcLocations xltcLocations : leavel_1) {
                    list.add(xltcLocations);
                    reTryFindChidlrenDepaetments(xltcLocations);
                }
            }
            resultMap.put("data",list);
            //把结果集放入缓存
            jedisUtil.set("locations", JSON.toJSONString(resultMap));
        }
        return resultMap;
    }


    /**
     * 递归查询子区域
     * @param locations
     * @return
     */
    public void reTryFindChidlrenDepaetments(XltcLocations locations) throws InvocationTargetException, IllegalAccessException {
        //递归查询子类
        XltcLocationsExample example = new XltcLocationsExample();
        XltcLocationsExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(locations.getId());
        List<XltcLocations> xltcLocations = locationsMapper.selectByExample(example);
        //如果有子对象
        if(xltcLocations!=null && xltcLocations.size()>0){
            for (XltcLocations location : xltcLocations) {
                //克隆
                XltcLocations newBean = new XltcLocations();
                BeanUtils.copyProperties(newBean,location);
                //将对象放入kids集合
                List<XltcLocations> childDepartments = locations.getChildDepartments();
                childDepartments.add(newBean);
                //是用newBean去递归
                reTryFindChidlrenDepaetments(newBean);
            }
        }
    }
}
