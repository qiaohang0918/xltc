package test;

import com.alibaba.fastjson.JSON;
import com.qigan.qiganshop.controller.utils.AlipayController;
import com.qigan.qiganshop.pojo.Stock;
import com.qigan.qiganshop.pojo.group.GroupOrder;
import com.qigan.qiganshop.pojo.synchronization.ResultGoods;
import com.qigan.qiganshop.pojo.synchronization.ResultStock;
import com.qigan.qiganshop.pojo.synchronization.ResultWarehouse;
import com.qigan.qiganshop.service.*;
import com.qigan.qiganshop.service.synchronization.impl.SynchStockServiceImpl;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.jiguang.JiGuangSend;
import com.qigan.qiganshop.utils.guanyi.GuanYiDataSource;
import com.qigan.qiganshop.utils.json.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * create by qh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
@WebAppConfiguration
@ComponentScan("test.annotation")
public class TestM {

    @Autowired
    GuanYiGoodsService service;

    @Autowired
    GuanYiStockService service2;

    @Autowired
    CategoryService service3;

    @Autowired
    GuanYiDataSource guanYiDataSource;

    @Autowired
    XltcReportDeleryerService service4;

    @Autowired
    JiGuangSend send;

    private MockMvc mockMvc;

//
//    @Before
//    public void setUp() {
//        SearchController apiController = new SearchController();
//        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
//    }

    @Test
    public void printA() {
        System.out.printf("aaa");
    }

    @Test
    public void getAllGoods() {
        //ResultGoods results = gy.get(itemsMethod, page, 1, null, ResultGoods.class, null);
        ResultGoods goods = guanYiDataSource.get("gy.erp.items.get", null, 5, "wxsmt20190809", ResultGoods.class, null);
        List<ResultGoods.ItemsBean> items = goods.getItems();
        // System.out.println(items!=null? items.size() : "nullll");
        for (ResultGoods.ItemsBean item : items) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void getStocks() {
        //ResultGoods results = gy.get(itemsMethod, page, 1, null, ResultGoods.class, null);
        ResultGoods goods = guanYiDataSource.get("gy.erp.warehouse.get", null, 10, null, ResultGoods.class, null);
        List<ResultGoods.ItemsBean> items = goods.getItems();
        // System.out.println(items!=null? items.size() : "nullll");
        for (ResultGoods.ItemsBean item : items) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void getWareHouse() {
        ResultWarehouse warehouseList = guanYiDataSource.get("gy.erp.warehouse.get", null, null, null, ResultWarehouse.class, null);
        List<ResultWarehouse.WarehousesBean> warehouses = warehouseList.getWarehouses();
        for (ResultWarehouse.WarehousesBean warehouse : warehouses) {
            System.out.println(warehouse.getArea_name());
        }

    }

    @Autowired
    private CartService cartService;


    /**
     * 测试删除购物车
     */
    @Test
    public void testDeleteCart() {
        String str = "{\"token\":\"to_d56d14c566bd40f6a9825d320e900217\",\"items\":{\"142601960567\":\"1\",\"136452965187\":\"1\",\"140571874313\":\"1\",\"143586003513\":\"1\"},\"coupons\":[],\"userAddrId\":\"ded5714b07ee46269944956d8ad9a51b\",\"userChooseTime\":\"1小时内\"}";
        GroupOrder parse = JSON.parseObject(str, GroupOrder.class);

        ArrayList<String> list = new ArrayList<>();
        list.add("143186070141");
        list.add("140571877464");
        int i = cartService.deleteCurrentOrderItemsFromCart(parse.getToken(), list);
        System.out.println(i > 0 ? i : 0);
    }


    @Test
    public void findWareHouse() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        testWare(linkedHashMap);
    }


    public void testWare(Map<String, Object> map) {
        System.out.println("---------{}" + "目标方法执行testWare");
    }


    @Test
    public void testWare2() {
        System.out.println("---------{}" + "目标方法执行testWare");
    }


    @Resource
    CategoryService categoryService;


    @Resource
    XltcGoodsService xltcGoodsService;

    @Test
    public void synchGoods() {
        xltcGoodsService.syncGoodsAll();
    }

    @Resource
    HomePageCateService homePageCateService;


    public HashMap<String, String> readCodes() throws IOException {
        HashMap<String, String> map = new HashMap<>();
//        FileReader reader = new FileReader(new File("C:\\Users\\xltc-1\\Desktop\\redis\\updatet.txt"));
        FileReader reader = new FileReader(new File("d:\\bbb.txt"));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String read = null;
        int count = 0;
        while ((read = bufferedReader.readLine()) != null) {
            String s = map.get(read);
            if (s != null) {
                System.out.println(s + "{}重复");
            }
            String[] split = read.split("\\t");
            map.put(split[0], split[1]);
            ++count;
        }
        System.out.println("共录入HashEntry:" + count + "个！");
        //map.put("0888200088824","HXG20190626");
        return map;
        // System.out.println(map.size());
    }


    public HashMap<String, String> readCodesAddZero() throws IOException {
        HashMap<String, String> map = new HashMap<>();
        FileReader reader = new FileReader(new File("C:\\Users\\xltc-1\\Desktop\\redis\\111.txt"));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String read = null;
        int count = 0;
        while ((read = bufferedReader.readLine()) != null) {
            String s = map.get(read);
            if (s != null) {
                System.out.println(s + "{}重复");
            }
            map.put(read, "0" + read);
            ++count;
        }
        System.out.println("共录入HashEntry:" + count + "个！");
        // map.put("0888200088824","HXG20190626");
        return map;
        // System.out.println(map.size());
    }

    @Resource
    ItemService itemService;

//    @Test
//    public void updateItemsCodes() throws IOException {
//        int i=0;
//        Map<String, String> map = readCodesAddZero();
//        Set<Map.Entry<String, String>> entrySet = map.entrySet();
//        for (Map.Entry<String, String> entry : entrySet) {
//            int j=itemService.updateItemsCodes(entry.getKey(),entry.getValue());
//            if(j>0){
//                System.out.println("成功更新ordCode:("+entry.getKey()+")------newCode:("+entry.getValue()+")");
//                i+=j;
//            }else {
//                System.out.println("失败----------------------------------------------"+entry.getKey()+"-------------"+entry.getValue());
//            }
//
//        }
//        System.out.println("成功更新的商品code总数："+i);
//    }


    @Test
    public void testStrConcat() {
        ArrayList<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        StringBuffer buffer = new StringBuffer();
        for (String s : list) {
            buffer.append("'" + s + "',");
        }
        String statusStrList = buffer.replace(buffer.length() - 1, buffer.length(), "").toString();
        System.out.println(statusStrList);
    }

    @Autowired
    JedisUtil jedisUtil;

    @Test
    public void setToPub() {
        Set<String> elements = jedisUtil.getElementsFromHash("pub_err");
        if (elements != null) {
            for (String element : elements) {
                System.out.println(element);
            }
        } else {
            System.out.println("null of set!");
        }
    }

    @Test
    public void getKeys() {
        String key = "test";
        String value = jedisUtil.getFromHash("pub_err", key);
        System.out.println("value:---{}" + value);
    }

    @Test
    public void removeKey() {
        String key = "test";
        Long result = jedisUtil.removeElementFromHash("pub_err", key);
        System.out.println(result);
    }

    @Test
    public void setKey() {
        Long aLong = jedisUtil.setToHash("pub_err", "test", "0");
        System.out.println(aLong);
    }

    @Test
    public void testCtrlShiftT() throws ExecutionException, InterruptedException {
        Callable<Object> callable = new Callable<Object>() {
            public Object call() throws Exception {
                int i = 0;

                while (i<100) {
                    i++;
                    System.out.println("当前i++" + i);
                }
                return i;
            }
        };

        FutureTask<Object> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        thread.start();
        while (true) {
            if(task.isDone()){
                System.out.println("done-----------------"+task.get());
                break;
            }else {
                System.out.println("done----no----------------");
            }
        }
    }

    @Test
    public void testCtrlShiftT_two(){
        Runnable runnable = new Runnable() {
            public void run() {
                int i=0;
                while (i<100) {
                    i++;
                    System.out.println("当前i++" + i);
                }
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }

    @Test
    public void  testCateIdCancat(){
        String goodsIds="";
        String statusStrList=null;
        if(goodsIds!=null && goodsIds.length()>0){
            StringBuffer buffer = new StringBuffer();
            String[] split = goodsIds.split(",");
            for (String s : split) {
                buffer.append("'"+s+"',");
            }
            statusStrList = buffer.replace(buffer.length()-1, buffer.length(), "").toString();
        }
        System.out.println(statusStrList);
    }


    @Autowired
    private XltcOrderService xltcOrderService;


    @Test
    public void tryPublish(){
        String result = xltcOrderService.pullToGuanYi("201908261566799123929085");
        if ("ok".equals(result.trim()))
            jedisUtil.removeElementFromHash("pub_err", "201908261566799123929085");
    }

    @Test
    public void  testPublish(){
        Set<String> sets=new HashSet<>();
        sets.add("aaa");
        sets.add("bbb");
        sets.add("ccc");
        while (sets.size()>0) {
            if (sets != null && sets.size() > 0) {
                for (String set : sets) {
                    int i = 0;
                    sets.remove(set);
                    ++i;
                    if (i > 0) {
                        break;
                    }
                }
            }
            System.out.println(sets.size() + "----------------");
        }
    }

    @Autowired
    SynchStockServiceImpl synchStockService;

    @Autowired
    GuanYiDataSource dataSource;

    @Autowired
    StockService stockService;

    @Test
    public void testGuanyi(){
        Map<String, Object> guanyiParams = new HashMap<>();
        guanyiParams.put("warehouse_code", "XCFC001");
        String a = JsonUtils.writeValueAsString(dataSource.get("gy.erp.new.stock.get", "0888200889325", ResultStock.class, guanyiParams));
        System.out.println(a);
        Map map = JSON.parseObject(a, Map.class);
        System.out.println(map);
//        String ss="{\"barcode\":\"0888200889325\",\"del\":false,\"qty\":5,\"warehouse_code\":\"XCFC001\",\"item_code\":\"0888200889325\",\"sku_code\":null,\"salable_qty\":5,\"road_qty\":0,\"pick_qty\":5,\"item_name\":\"千禧小番茄\",\"item_sku_name\":null,\"warehouse_name\":\"薛城万洲仓\"}";
//        Stock stock = new Stock();
//        //stock.setStockId(UUID.randomUUID().toString().replace("-","").replace("_",""));
//        stock.setWarehouseId("XCFC001");
//        stock.setGoodsId("148211512242");
//        stock.setItemId("148211512242");
//        stock.setSalableNum(5);
//        stock.setLockNum(0);
//        stock.setStockNum(5);
//        stock.setIsDel(false);
//        stockService.add(stock);
//        stock.setGoodsId("148211512242");
//        stock.setGoodsId("148211512242");
//        System.err.println(a);
    };


    @Autowired
    AlipayController alipayController;

    @Test
    public void testClose(){
       //boolean result = alipayController.closeTradeOrder("201908101565420075919245");
       // System.out.println(result);
    }

}
