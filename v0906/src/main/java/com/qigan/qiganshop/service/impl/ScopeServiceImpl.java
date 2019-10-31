package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.ScopeMapper;
import com.qigan.qiganshop.mapper.WarehouseMapper;
import com.qigan.qiganshop.pojo.Scope;
import com.qigan.qiganshop.pojo.Warehouse;
import com.qigan.qiganshop.service.ScopeService;
import com.qigan.qiganshop.util.baidumap.BaiDuMapService;
import com.qigan.qiganshop.util.baidumap.Polygon;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScopeServiceImpl implements ScopeService {
    @Autowired
    private ScopeMapper mapper;

    @Autowired
    private WarehouseMapper wareHouseMapper;
    @Autowired
    private BaiDuMapService baiDuMapService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @Override
    public List<Scope> findAll() {

        return (List<Scope>) NotNull.checkListNull(mapper.findByScope(null, null, null));
    }

    /**
     * 增加
     *
     * @param scope
     */
    @Override
    public Integer add(Scope scope) {
        if (scope.getRangeId() == null || "".equals(scope.getRangeId())) {
            Boolean flag = this.checkNameExits(scope.getWarehouseId(), scope.getName());
            if (flag) {
                scope.setRangeId(UUID.randomUUID().toString().replace("-", ""));
                Warehouse one = wareHouseMapper.findOne(scope.getWarehouseId());
                if (one != null) {
                    scope.setWarehouseName(one.getName());
                }
                if (this.findCoincide(scope.getWarehouseId(), scope.getApexs(), scope.getCity())) {
                    // 多边形存在重复
                    return -1;
                }
                return mapper.insert(scope);
            } else {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 修改
     *
     * @param scope
     */
    @Override
    public Integer update(Scope scope) {
        return mapper.update(scope);
    }

    /**
     * 根据ID获取实体
     *
     * @param scopeId
     * @return
     */
    @Override
    public Scope findOne(String scopeId) {
        return mapper.findOne(scopeId);
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
            count += mapper.delete(id);
        }
        return count;
    }

    /**
     * 分页
     *
     * @param scope
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageResult findPage(Scope scope, Integer pageNum, Integer pageSize) {
        List<Scope> list = new ArrayList<>();
        if (pageNum != null && pageSize != null) {
            list = mapper.findByScope(scope, (pageNum - 1) * pageSize, pageSize);
        } else {
            list = mapper.findByScope(scope, null, null);
        }
        return new PageResult(mapper.findByScope(scope, null, null).size(), (List<Scope>) NotNull.checkListNull(list));

    }

    /**
     * 添加仓库配送范围时,检测当前区域是否存在重合现象,同一个仓库不做处理
     *
     * @param wareHouseId
     * @param apexs
     * @return
     */
    @Override
    public Boolean findCoincide(String wareHouseId, String apexs, String city) {
        boolean flag = false;
        List<Scope> list = mapper.findOtherWarehouse(wareHouseId, city);
        for (Scope scope : list) {
            /**
             * 查询已经录入的多边形顶点是否在本次的多边形中
             */
            String[] split = scope.getApexs().split(",");
            for (String s : split) {
                String[] strings = s.split("_");
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("X", strings[0]);
                hashMap.put("Y", strings[1]);
                flag = flag | Polygon.isInPolygon(hashMap, apexs);
            }
            /**
             * 查询本次录入的多边形顶点是否在已经录入的多边形中
             */
            String[] apexArr = apexs.split(",");
            for (String s : apexArr) {
                String[] strings = s.split("_");
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("X", strings[0]);
                hashMap.put("Y", strings[1]);
                flag = flag | Polygon.isInPolygon(hashMap, scope.getApexs());
            }
        }
        return flag;
    }

    /**
     * 检测同一仓库是否重名
     *
     * @param wareHouseId
     * @param name
     * @return
     */
    @Override
    public Boolean checkNameExits(String wareHouseId, String name) {
        Scope scope = new Scope();
        scope.setName(name);
        int size = this.findPage(scope, null, null).getRows().size();
        if (size != 0) {
            return false;
        } else {

            return true;
        }
    }

    /**
     * 检测当前点是否存在配送范围
     *
     * @param coordinate
     * @return
     */
    @Override
    public boolean checkIn(String coordinate) {
        String city = baiDuMapService.getCity(coordinate);
        Scope scope = new Scope();
        scope.setCity(city);
        List<Scope> rows = this.findPage(scope, null, null).getRows();
        Boolean flag = false;
        for (Scope row : rows) {
            flag |= Polygon.checkIn(coordinate, row.getApexs());
            if (flag) {
                break;
            }
        }
        return flag;
    }

}
