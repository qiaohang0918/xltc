package com.qigan.qiganshop.service.synchronization.impl;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.pojo.Warehouse;
import com.qigan.qiganshop.pojo.synchronization.ResultWarehouse;
import com.qigan.qiganshop.pojo.synchronization.ResultWarehouse;
import com.qigan.qiganshop.service.CategoryService;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.ItemService;
import com.qigan.qiganshop.service.WareHouseService;
import com.qigan.qiganshop.service.synchronization.SynchWareHouseService;
import com.qigan.qiganshop.util.guanyierp.GetSign;
import com.qigan.qiganshop.util.guanyierp.SendPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author wanghao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SynchWareHouseServiceImpl implements SynchWareHouseService {
    @Autowired
    private SendPost post;
    @Autowired
    private GetSign getSign;
    @Autowired
    private WareHouseService service;
    @Autowired
    private CategoryService categoryService;
    @Value("${guanyierpUrl}")
    private String url;
    @Value("${appkey}")
    private String appkey;
    @Value("${sessionkey}")
    private String sessionkey;
    @Value("${secret}")
    private String secret;
    @Value("${wareHouseMethod}")
    private String wareHouseMethod;

    Integer firstWarehouseAdd = 0;
    Integer firstWarehouseUpdate = 0;
    Integer secondWarehouseAdd = 0;
    Integer secondWarehouseUpdate = 0;

    @Override
    public String updateAllWareHouse() {
        List<ResultWarehouse.WarehousesBean> source = this.getSource();
        for (ResultWarehouse.WarehousesBean warehousesBean : source) {
            copyWarehouseFromBean(warehousesBean);
        }

        String s = "本次数据同步,新增主仓" + firstWarehouseAdd + "个;更新主仓" + firstWarehouseUpdate + "个;新增分仓"
                + secondWarehouseAdd + "个;更新分仓" + secondWarehouseUpdate + "个.";
        firstWarehouseAdd = 0;
        firstWarehouseUpdate = 0;
        secondWarehouseAdd = 0;
        secondWarehouseUpdate = 0;
        return s;
    }


    private void copyWarehouseFromBean(ResultWarehouse.WarehousesBean bean) {
        Warehouse warehouse = new Warehouse();
        /**
         * 复制属性(接收后赋值为本地 WareHouse)
         */
        warehouse.setWarehouseId(bean.getCode());
        warehouse.setName(bean.getName());
        warehouse.setAddress(bean.getAddress());
        warehouse.setNote(bean.getNote());
        warehouse.setCreateDate(bean.getCreate_date());
        warehouse.setModifyDate(bean.getModify_date());
        warehouse.setContactName(bean.getContact_name());
        warehouse.setContactPhone(bean.getContact_phone());
        warehouse.setContactMobile(bean.getContact_mobile());
        warehouse.setTypeName(bean.getType_name());
        warehouse.setAreaName(bean.getArea_name());
        warehouse.setIsDel(bean.getIs_del());
        chooseInsertOrUpdate(bean.getCode(), warehouse);
    }

    private void chooseInsertOrUpdate(String warehouseId, Warehouse warehouse) {
        Warehouse checkExits = service.findOne(warehouseId);
        if (checkExits != null) {
            if (!"主仓".equals(warehouse.getTypeName())) {
                secondWarehouseUpdate += service.update(warehouse);
            } else {
                firstWarehouseUpdate += service.update(warehouse);
            }
        } else {
            if (!"主仓".equals(warehouse.getTypeName())) {
                secondWarehouseAdd += service.add(warehouse);
            } else {
                firstWarehouseAdd += service.add(warehouse);
            }
        }
    }


    /**
     * 获取数据
     *
     * @return
     */
    private List<ResultWarehouse.WarehousesBean> getSource() {
        /**
         * 从管易云平台获取数据,并将数据格式转换为当前系统的商品结构
         */

        /**
         * 设置分页数量
         */
        int pageSize = 50;

        ResultWarehouse result = this.findPageWarehouse(1, pageSize);
        /**
         * 获取商品总记录数
         */
        int goodsTotal = result.getTotal();
        /**
         * 计算总页数
         */
        double pageCount = Math.ceil(goodsTotal * 1.0 / pageSize);
        /**
         * 创建集合,准备接收所有的商品信息
         */
        ArrayList<ResultWarehouse.WarehousesBean> resultWarehouseList = new ArrayList<>();

        for (int i1 = 0; i1 < pageCount; i1++) {
            ResultWarehouse ResultWarehouse = this.findPageWarehouse(i1 + 1, pageSize);
            resultWarehouseList.addAll(ResultWarehouse.getWarehouses());
        }

        return resultWarehouseList;
    }


    /**
     * 发送商品查询请求
     *
     * @param page 页码
     * @param size 记录数
     * @return
     */
    private ResultWarehouse findPageWarehouse(Integer page, Integer size) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("appkey", appkey);
        map.put("sessionkey", sessionkey);
        map.put("method", wareHouseMethod);
        map.put("page_no", page);
        map.put("page_size", size);

        /**
         * 将请求参数转换为 json 格式的字符串,进行加密处理,获取授权
         */
        String json = JSON.toJSONString(map);
        /**
         * 获取授权
         */
        String sign = getSign.sign(json, secret);
        map.put("sign", sign);
        String data = JSON.toJSONString(map);
        String result = post.sendPost(url, data);
        System.err.println(result);
        ResultWarehouse resultWarehouse = JSON.parseObject(result, ResultWarehouse.class);
        return resultWarehouse;

    }


}
