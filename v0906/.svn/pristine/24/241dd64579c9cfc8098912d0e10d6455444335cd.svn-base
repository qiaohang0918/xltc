package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.DeliveryerMapper;
import com.qigan.qiganshop.pojo.Deliveryer;
import com.qigan.qiganshop.service.DeliveryerService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.PageResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 配送员服务层
 *
 * @author wanghao
 * @time 2019-05-05 14:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeliveryerServiceImpl implements DeliveryerService {

    @Autowired
    private DeliveryerMapper mapper;

    /**
     * 增加快递员
     * 生成默认工号,创建时间
     *
     * @param deliveryer
     * @return
     */
    @Override
    public Integer add(Deliveryer deliveryer) {
        // 设置入职时间
        deliveryer.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        // 设置默认启用
        deliveryer.setEnable("1");
        deliveryer.setPasswd("123456");
        // 查询并设置员工编号
        Integer id = mapper.getMaxId();
        Integer repeat = mapper.getByPhone(deliveryer.getPhoneNum());
        if (repeat > 0) {
            return -1;
        }
        if (id == null) {
            deliveryer.setDeliveryerId(10000001);
        } else {
            deliveryer.setDeliveryerId(id + 1);
        }
        // 设置 MD5 加密密码
        deliveryer.setPasswd(DigestUtils.md5Hex(deliveryer.getPasswd()));
        return mapper.insert(deliveryer);
    }


    /**
     * 修改配送员信息
     *
     * @param deliveryer
     * @return
     */
    @Override
    public Integer update(Deliveryer deliveryer) {
        if (deliveryer.getDeliveryerId() != null) {
            if (NotNull.checkString(deliveryer.getPasswd())) {
                deliveryer.setPasswd(DigestUtils.md5Hex(deliveryer.getPasswd()));
            }
            return mapper.update(deliveryer);
        }
        return 0;
    }


    /**
     * 查询单个配送员
     *
     * @param deliveryerId
     * @return
     */
    @Override
    public Deliveryer findOne(Integer deliveryerId) {
        Deliveryer one = mapper.findOne(deliveryerId);
        return (Deliveryer) NotNull.checkNull(one == null ? new Deliveryer() : one);
    }

    /**
     * 查询全部,分页查询 条件查询
     *
     * @param deliveryer
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult findPage(Deliveryer deliveryer, Integer page, Integer size) {
        ArrayList<Deliveryer> result = new ArrayList<>();
        if (page != null && size != null) {
            // 启用分页
            result = mapper.findPage(deliveryer, (page - 1) * size, size);
        } else {
            result = mapper.findPage(deliveryer, null, null);
        }
        return new PageResult(mapper.getCount(), result);
    }

    /**
     * 修改配送员状态
     *
     * @param deliveryerId
     * @param flag
     * @return
     */
    @Override
    public Integer updateEnable(Integer deliveryerId, boolean flag) {
        Deliveryer one = new Deliveryer();
        one.setDeliveryerId(deliveryerId);
        if (flag) {
            // 启用
            one.setEnable("1");
        } else {
            // 禁用
            one.setEnable("0");
        }
        return mapper.update(one);
    }

    /**
     * 简单登录接口
     *
     * @param deliveryerId
     * @param passwd
     * @return
     */
    @Override
    public Deliveryer login(Integer deliveryerId, String passwd) {
        Deliveryer one = this.findOne(deliveryerId);
        String s = DigestUtils.md5Hex(passwd);
        if (s.equals(one.getPasswd())) {
            return one;
        }
        return new Deliveryer();
    }

}
