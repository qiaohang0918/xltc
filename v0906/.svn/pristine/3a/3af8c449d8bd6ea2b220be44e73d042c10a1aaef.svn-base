package com.qigan.qiganshop.service.synchronization.impl;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.mapper.GoodsMapper;
import com.qigan.qiganshop.pojo.Category;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.HomepageCate;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.pojo.synchronization.ResultGoods;
import com.qigan.qiganshop.service.CategoryService;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.HomePageCateService;
import com.qigan.qiganshop.service.ItemService;
import com.qigan.qiganshop.service.synchronization.SynchGoodsService;
import com.qigan.qiganshop.util.guanyierp.GetSign;
import com.qigan.qiganshop.util.guanyierp.SendPost;
import com.qigan.qiganshop.utils.json.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wanghao
 */
@Service
@Transactional(rollbackFor = Exception.class)

public class SynchGoodsServiceImpl implements SynchGoodsService {
    @Autowired
    private SendPost post;
    @Autowired
    private GoodsService service;
    @Autowired
    private GetSign getSign;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HomePageCateService pageCateService;
    
    @Autowired
    GoodsMapper goodsMapper;
    
    @Value("${guanyierpUrl}")
    private String url;
    @Value("${appkey}")
    private String appkey;
    @Value("${sessionkey}")
    private String sessionkey;
    @Value("${secret}")
    private String secret;
    @Value("${itemsMethod}")
    private String itemsMethod;


    int spuUpdateCount = 0;
    int spuInsertCount = 0;
    int skuUpdateCount = 0;
    int skuInsertCount = 0;

    /**
     * 同步获取所有商品及其规格
     * 首先,获取到所有的商品信息
     * 然后,将所有的商品信息及其包含的规格信息进行获取
     * 如果商品没有启用规格,则不添加规格
     * 如果商品启用规格,则直接进行添加
     * 如果商品添加过,则进行商品信息的更新
     * 如果商品从未添加过,则进行商品信息的插入
     *
     * @return
     */

    private void writeLocalStrOne(String str,String path){
    	try {
    		System.out.println(path);
    	File file = new File(path);
    	if (!file.getParentFile().exists()) {
    	file.getParentFile().mkdirs();
    	}
    	file.createNewFile();
    	if(str != null && !"".equals(str)){
    	FileWriter fw = new FileWriter(file, true);
    	fw.write(str);//写入本地文件中
    	fw.flush();
    	fw.close();
    	System.out.println("执行完毕!");
    	}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}

    	}

    
    @Override
    public String updateAllGoods() {
    	
    	List<Map<String, String>> pics = goodsMapper.findGoodsAllPic();
    	if(pics != null ){
    		String fileStr = JsonUtils.writeValueAsString(pics);
    		writeLocalStrOne(fileStr, "C:\\Users\\Administrator\\Desktop\\log\\syncGoods"+ LocalDateTime.now().toString().replaceAll(":", "_") +".txt");
    	}
    	
        /**
         * 从仓储系统获取数据,数据格式是 goods 包含 spec
         */
        List<ResultGoods.ItemsBean> source = getSource();
        /**
         * 创建本服务商品实体,通过属性赋值进行数据的拷贝
         */
        if (source.size() > 0) {


            Goods goods = new Goods();
            for (ResultGoods.ItemsBean bean : source) {
                /**
                 * 设置spu 属性
                 */
                goods.setGoodsId(bean.getId());
                goods.setCreateDate(bean.getCreate_date());
                goods.setModifyDate(bean.getModify_date());
                goods.setCode(bean.getCode());
                goods.setName(bean.getName());
                goods.setWeight(bean.getWeight());
                if (bean.isCombine()) {
                    break;
                }
                goods.setCombine(bean.isCombine() ? "1" : "0");
                goods.setDel(bean.isDel() ? "1" : "0");
                if (bean.isDel()) {
                    continue;
                }
                goods.setLength(bean.getLength());
                goods.setWidth(bean.getWidth());
                goods.setHeight(bean.getHeight());
                goods.setVolume(bean.getVolume());
                goods.setSimpleName(bean.getSimple_name());
                /**
                 * 分类相关开始
                 * 管易云一级分类和二级分类的分类编码存在重复现象,所以本系统分类编码使用 UUID 进行保存
                 * 管易云商品修改不需要传递分类 ID,可以进行此操作
                 */
                String cateName = bean.getCategory_name();
                if (cateName != null && !"".equals(cateName)) {
                    goods.setCategoryName(bean.getCategory_name());
                    /**
                     * 确定分类 ID 的值
                     * 先查询是否存在
                     */
                    Category category = categoryService.findByName(cateName);
                    if (category == null) {
                        /**
                         * 不存在,先进行添加
                         */
                        Category add = new Category();
                        add.setEnable(true);
                        String ID = UUID.randomUUID().toString().replace("-", "");
                        add.setCateId(ID);
                        add.setCateName(bean.getCategory_name());
                        category = categoryService.add(add);
                        HomepageCate homepageCate = new HomepageCate();
                        homepageCate.setHomepagecateId(ID);
                        homepageCate.setTitle(bean.getCategory_name());
                        homepageCate.setLocation("head");
                        homepageCate.setEnable("0");
                        pageCateService.add(homepageCate);
                        goods.setCategoryCode(category.getCateId());
                    } else {
                        /**
                         * 存在,直接赋值
                         */
                        goods.setCategoryCode(category.getCateId());
                    }
                } else {
                    goods.setCategoryName("");
                    goods.setCategoryCode("");
                }
                /**
                 * 分类相关结束
                 */
                goods.setItemUnitCode(bean.getItem_unit_code());
                goods.setItemUnitName(bean.getItem_unit_name());
                goods.setSalesPrice(bean.getSales_price());
                goods.setCostPrice(bean.getCost_price());
                goods.setStockStatusCode(bean.getStock_status_code());
                goods.setPicUrls(bean.getPic_url());
                goods.setOriginArea(bean.getOrigin_area());
                goods.setShelfLife(bean.getShelf_life());
                goods.setItemAddAttribute(bean.getItem_add_attribute());
                goods.setItemBrandCode(bean.getItem_brand_code());
                goods.setItemBrandName(bean.getItem_brand_name());
                goods.setStatus("0");
                goods.setPush("0");
                /**
                 * 获取规格信息
                 */
                List<ResultGoods.ItemsBean.sku> skus = bean.getSkus();
                if (skus.size() > 0) {
                    /**
                     * 存在一到多个 sku
                     * 启用规格
                     */
                    goods.setIsSpec("1");
                    for (ResultGoods.ItemsBean.sku sku : skus) {
                        copyItem(bean, sku);
                    }
                } else {
                    /**
                     * 管易云商品中仅仅添加了 SPU,没有 sku
                     * 无规格
                     */
                    goods.setIsSpec("0");
                    copyItemFromBean(bean);

                }
                Goods oneGoods = service.findOneGoods(goods.getGoodsId());
                if (oneGoods != null) {
                    goods.setPicUrls(null);
                    goods.setNote(null);
                    spuUpdateCount += service.update(goods);
                } else {
                    spuInsertCount += service.add(goods);
                }
            }
        }
        String result = "本次数据同步,新增 spu 商品" + spuInsertCount + "个;更新 spu 商品" + spuUpdateCount + "个;新增 sku 商品"
                + skuInsertCount + "个;更新 sku 商品" + skuUpdateCount + "个";
        spuUpdateCount = 0;
        spuInsertCount = 0;
        skuUpdateCount = 0;
        skuInsertCount = 0;
        return result;
    }

    private void copyItem(ResultGoods.ItemsBean bean, ResultGoods.ItemsBean.sku sku) {
        Item item = new Item();
        /**
         * 复制属性
         */
        item.setItemId(sku.getId());
        item.setSpuId(bean.getId());
        item.setCode(sku.getCode());
        item.setName(sku.getName());
        item.setNote(sku.getNote());
        item.setWeight(sku.getWeight());
        item.setVolume(sku.getVolume());
        item.setSalesPrice(sku.getSales_price());
        item.setCostPrice(sku.getCost_price());
        item.setStockStatusCode(sku.getStock_status_code());
        item.setBarCode(sku.getBar_code());
        item.setOriginArea(sku.getOrigin_area());
        item.setDel("0");

        chooseInsertOrUpdate(sku.getId(), item);
    }

    private void copyItemFromBean(ResultGoods.ItemsBean bean) {
        Item item = new Item();
        /**
         * 复制属性(spu直接复制到 SKU)
         * 没有开启规格的商品
         */
        item.setItemId(bean.getId());
        item.setSpuId(bean.getId());
        item.setCode(bean.getCode());
        item.setName(bean.getName());
        item.setNote(bean.getNote());
        item.setWeight(bean.getWeight());
        item.setVolume(bean.getVolume());
        item.setSalesPrice(bean.getSales_price());
        item.setCostPrice(bean.getCost_price());
        item.setStockStatusCode(bean.getStock_status_code());
        item.setOriginArea(bean.getOrigin_area());
        item.setDel("0");
        chooseInsertOrUpdate(bean.getId(), item);
    }

    private void chooseInsertOrUpdate(String itemId, Item item) {
        Item checkExits = itemService.findOne(itemId);
        if (checkExits != null) {
            skuUpdateCount += itemService.update(item);
        } else {
            skuInsertCount += itemService.add(item);
        }
    }


    /**
     * 获取数据
     *
     * @return
     */
    private List<ResultGoods.ItemsBean> getSource() {
        /**
         * 从管易云平台获取数据,并将数据格式转换为当前系统的商品结构
         */

        /**
         * 设置分页数量
         */
        int pageSize = 50;

        ResultGoods result = this.findPageGoods(1, pageSize);
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
        ArrayList<ResultGoods.ItemsBean> resultGoodsList = new ArrayList<>();

        for (int i1 = 0; i1 < pageCount; i1++) {
            ResultGoods resultGoods = this.findPageGoods(i1 + 1, pageSize);
            resultGoodsList.addAll(resultGoods.getItems());
        }

        return resultGoodsList;
    }


    /**
     * 发送商品查询请求
     *
     * @param page 页码
     * @param size 记录数
     * @param code 商品条码
     * @return
     */
    private ResultGoods findPageGoods(Integer page, Integer size, String code) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("appkey", appkey);
        map.put("sessionkey", sessionkey);
        map.put("method", itemsMethod);
        map.put("page_no", page);
        map.put("page_size", size);
        if (!"".equals(code) && code != null) {
        } else {
            map.put("code", code);
        }
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
        ResultGoods resultGoods = JSON.parseObject(result, ResultGoods.class);
        return resultGoods;

    }



    /**
     * 发送商品查询请求
     *
     * @param page 页码
     * @param size 记录数
     * @return
     */
    private ResultGoods findPageGoods(Integer page, Integer size) {
        return this.findPageGoods(page, size, null);

    }


}
