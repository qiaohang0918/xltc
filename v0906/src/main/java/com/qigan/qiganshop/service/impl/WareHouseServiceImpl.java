package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.WarehouseMapper;
import com.qigan.qiganshop.pojo.Warehouse;
import com.qigan.qiganshop.service.WareHouseService;
import com.qigan.qiganshop.util.baidumap.BaiDuMapService;
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
public class WareHouseServiceImpl implements WareHouseService {

    @Autowired
    private WarehouseMapper mapper;
    @Autowired
    private BaiDuMapService baiDuMapService;


    /**
     * 增加
     *
     * @param warehouse
     */
    @Override
    public Integer add(Warehouse warehouse) {

        warehouse.setIsDel(false);

        return mapper.insert(warehouse);
    }

    /**
     * 修改
     *
     * @param warehouse
     */
    @Override
    public Integer update(Warehouse warehouse) {
        return mapper.update(warehouse);
    }

    /**
     * 根据ID获取实体
     *
     * @param warehouseId
     * @return
     */
    @Override
    public Warehouse findOne(String warehouseId) {
        return mapper.findOne(warehouseId);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public Integer delete(String[] ids) {
        Integer count = 0;
        for (String id : ids) {
            Warehouse warehouse = new Warehouse();
            warehouse.setWarehouseId(id);
            warehouse.setIsDel(true);
            count += mapper.update(warehouse);
        }
        return count;
    }

    /**
     * 分页
     *
     * @param warehouse
     * @param pageNum   当前页 码
     * @param pageSize  每页记录数
     * @return
     */
    @Override
    public PageResult findPage(Warehouse warehouse, Integer pageNum, Integer pageSize) {
        List<Warehouse> list = null;
        List<Warehouse> all = mapper.findByWareHouse(warehouse, null, null);
        if (pageNum != null && pageSize != null) {
            list = mapper.findByWareHouse(warehouse, (pageNum - 1) * pageSize, pageSize);
            return new PageResult(all.size(), list);
        } else {
            return new PageResult(all.size(), all);
        }

    }


}
