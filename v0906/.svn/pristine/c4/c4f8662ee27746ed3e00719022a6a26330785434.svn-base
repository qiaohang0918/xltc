package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.AreaMapper;
import com.qigan.qiganshop.pojo.Area;
import com.qigan.qiganshop.service.AreaService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanghao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper mapper;

    /**
     * 增加
     *
     * @param area
     */
    @Override
    public Integer add(Area area) {

        if (area.getAreaId() == null || "".equals(area.getAreaId())) {
            return 0;
        }

        return mapper.insert(area);

    }

    /**
     * 修改
     *
     * @param area
     */
    @Override
    public Integer update(Area area) {
        if (area.getAreaId() == null || "".equals(area.getAreaId())) {
            return 0;
        }
        return mapper.update(area);

    }

    /**
     * 根据ID获取实体
     *
     * @param areaId
     * @return
     */
    @Override
    public Area findOne(String areaId) {
        return mapper.findOne(areaId);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public Integer delete(String[] ids) {
        int num = 0;
        for (String id : ids) {
            num += mapper.delete(id);
        }
        return num;
    }

    /**
     * 按照条件进行分页查询
     *
     * @param area
     * @return
     */

    @Override
    public PageResult findPage(Area area) {
        List<Area> list = (List<Area>) NotNull.checkListNull(mapper.findByArea(area));
        return new PageResult(list.size(), list);
    }


}
