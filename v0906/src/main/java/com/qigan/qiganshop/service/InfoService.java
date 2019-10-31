package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Info;

import java.util.List;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-07-13 16:31
 */
public interface InfoService {
    Integer add(Info info);

    List<Info> findPage(String orderId);
}
