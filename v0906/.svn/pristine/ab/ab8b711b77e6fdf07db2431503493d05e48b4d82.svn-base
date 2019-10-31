package com.qigan.qiganshop.service.synchronization.impl;

import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.CategoryMapper;
import com.qigan.qiganshop.mapper.GoodsMapper;
import com.qigan.qiganshop.mapper.HomepageCateMapper;
import com.qigan.qiganshop.mapper.ItemMapper;
import com.qigan.qiganshop.pojo.Category;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.HomepageCate;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.pojo.synchronization.ResultGoods;
import com.qigan.qiganshop.service.impl.XltcGoodsServiceImpl;
import com.qigan.qiganshop.service.synchronization.XltcAsyncGoodsService;
import com.qigan.qiganshop.util.result.Result;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import com.qigan.qiganshop.utils.guanyi.GuanYiDataSource;
import com.qigan.qiganshop.utils.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Aurher: QiaoHang
 * @Description:    商品同步
 * @Data: 2019/8/27 10:02
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XltcAsyncGoodsServiceImpl implements XltcAsyncGoodsService {

    @Value("${itemsMethod}")
    private String itemsMethod;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CategoryMapper cateMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private HomepageCateMapper homeMapper;

    @Autowired
    private GuanYiDataSource gy;

    private List<ResultGoods.ItemsBean> list = new ArrayList<>();

    private static Object lock = new Object();

    private static CountDownLatch latch;

    private void writeLocalStrOne(String str, String path) {
        try {
            System.out.println(path);
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            if (str != null && !"".equals(str)) {
                FileWriter fw = new FileWriter(file, true);
                fw.write(str);// 写入本地文件中
                fw.flush();
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.qigan.qiganshop.service.XltcGoodsService#syncGoodsAll()
     */
    @Override
    // @Async
    public void syncGoodsAll(String start_date,String end_date) {
        // TODO Auto-generated method stub
        List<Map<String, String>> picsLi = goodsMapper.findGoodsAllPic();
        if(picsLi != null ){
            String fileStr = JsonUtils.writeValueAsString(picsLi);
           // writeLocalStrOne(fileStr, "D:\\aaa.txt");
            writeLocalStrOne(fileStr, "C:\\Users\\Administrator\\Desktop\\log\\syncGoods"+ LocalDateTime.now().toString().replaceAll(":", "_") +".txt");

        }
        int page = 1, size = 100;

        //需要更新的时间参数
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("start_date",start_date);
        hashMap.put("end_date",end_date);

        ResultGoods results = gy.get(itemsMethod, null, 1, null, ResultGoods.class, hashMap);
        int pageCount = (int) Math.ceil(results.getTotal() * 1.0 / size);
        List<Map<String, String>> maps = goodsMapper.findGoodsAllPic();
        Map<String, String> pics = new HashMap<>();
        Map<String, String> notes = new HashMap<>();
        Map<String, String> dels = new HashMap<>();
        for (Map<String, String> map : maps) {
            if (maps != null) {
                pics.put(map.get("goodsId"), map.get("picUrls"));
                notes.put(map.get("goodsId"), map.get("note"));
                dels.put(map.get("goodsId"), map.get("del"));
            }
        }
        // goodsMapper.truncate();
        // cateMapper.truncate();
        // itemMapper.truncate();
        latch = new CountDownLatch(pageCount);
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= pageCount; i++) {
            ex.submit(new XltcAsyncGoodsServiceImpl.GuanYiGoods(i));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            ex.shutdown();
        }
        if (list == null && list.size() == 0)
            throw XltcRuntimeException.throwable("管易云获取数据失败，请重试");
        List<String> typeId = new ArrayList<>();
        List<Item> litems = new ArrayList<>();
        List<Goods> listGoods = new ArrayList<>();
        List<Long> sss = new ArrayList<>();
        for (ResultGoods.ItemsBean bean : list) {
            sss.add(bean.getGoods_id());
            if (bean.isCombine() || bean.isDel())
                continue;
            Goods goods = new Goods();
            goods.setGoodsId(bean.getId());
            goods.setCreateDate(bean.getCreate_date());
            goods.setModifyDate(bean.getModify_date());
            goods.setCode(bean.getCode());
            goods.setName(bean.getName());
            goods.setWeight(bean.getWeight());
            goods.setCombine(bean.isCombine() ? "1" : "0");

            if (dels != null && dels.containsKey(bean.getId())) {
                goods.setDel(dels.get(bean.getId()));
            } else {
                goods.setDel(bean.isDel() ? "1" : "0");
            }

            goods.setCategoryCode(bean.getCategory_code());
            goods.setCategoryName(bean.getCategory_name());
            goods.setLength(bean.getLength());
            goods.setWidth(bean.getWidth());
            goods.setHeight(bean.getHeight());
            goods.setVolume(bean.getVolume());
            goods.setSimpleName(bean.getSimple_name());
            goods.setItemUnitCode(bean.getItem_unit_code());
            goods.setItemUnitName(bean.getItem_unit_name());
            goods.setSalesPrice(bean.getSales_price());
            goods.setCostPrice(bean.getCost_price());
            goods.setStockStatusCode(bean.getStock_status_code());
            goods.setPicUrls(null);
            goods.setOriginArea(bean.getOrigin_area());
            goods.setShelfLife(bean.getShelf_life());
            goods.setItemAddAttribute(bean.getItem_add_attribute());
            goods.setItemBrandCode(bean.getItem_brand_code());
            goods.setItemBrandName(bean.getItem_brand_name());
            goods.setStatus("0");
            goods.setPush("0");
            if (pics != null && pics.containsKey(bean.getId())) {
                goods.setPicUrls(pics.get(bean.getId()));
            }
            if (notes != null && notes.containsKey(bean.getId())) {
                goods.setNote(notes.get(bean.getId()));
            }
            List<ResultGoods.ItemsBean.sku> skus = bean.getSkus();
            if (skus.size() > 0) {
                goods.setIsSpec("1");
                for (ResultGoods.ItemsBean.sku sku : skus) {
                    litems.add(copyItem(bean, sku));
                }
            } else {
                goods.setIsSpec("0");
                litems.add(copyItemFromBean(bean));
            }
            if (StringUtils.isNotBlank(bean.getCategory_code())) {
                typeId.add(bean.getCategory_code());
            }
            listGoods.add(goods);
        }

        Set<String> set = new HashSet<>(typeId);
        List<Category> cates = new ArrayList<>();
        List<HomepageCate> homeCates = new ArrayList<>();

        for (String string : set) {
            for (ResultGoods.ItemsBean itemsBean : list) {
                if (string.equals(itemsBean.getCategory_code())) {
                    Category cate = new Category();
                    cate.setCateId(string);
                    cate.setCateName(itemsBean.getCategory_name());
                    cate.setEnable(true);
                    cates.add(cate);
                    HomepageCate homepageCate = new HomepageCate();
                    homepageCate.setHomepagecateId(string);
                    homepageCate.setTitle(itemsBean.getCategory_name());
                    homepageCate.setLocation("head");
                    homepageCate.setEnable("0");
                    homeCates.add(homepageCate);
                    break;
                }
            }
        }
        goodsMapper.insertAllByGY(listGoods);
//        cateMapper.insertByGY(cates);
//        homeMapper.insertByGY(homeCates);
        itemMapper.insertByGY(litems);

    }

    private Item copyItem(ResultGoods.ItemsBean bean, ResultGoods.ItemsBean.sku sku) {
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
        item.setDel(bean.isDel() ? "1" : "0");
        return item;
    }

    private Item copyItemFromBean(ResultGoods.ItemsBean bean) {
        Item item = new Item();
        /**
         * 复制属性(spu直接复制到 SKU) 没有开启规格的商品
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
        item.setDel(bean.isDel() ? "1" : "0");
        return item;
    }


    class GuanYiGoods implements Runnable {
        private int i;

        public GuanYiGoods(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            ResultGoods resultGoods = gy.get(itemsMethod, i, 100, null, ResultGoods.class, null);
            synchronized (lock) {
                try {
                    list.addAll(resultGoods.getItems());
                } finally {
                    // TODO: handle finally clause
                    latch.countDown();
                }

            }
        }

    }

}
