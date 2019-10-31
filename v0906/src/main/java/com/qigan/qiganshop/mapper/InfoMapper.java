package com.qigan.qiganshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qigan.qiganshop.pojo.Info;

import java.util.List;

public interface InfoMapper {

    int add(Info info);

    List<Info> findPage(String orderId);

}