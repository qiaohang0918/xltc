package test;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.qigan.qiganshop.annocation.StepRecordWatchAble;
import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.constant.TimeFormat;
import com.qigan.qiganshop.controller.backstage.ManagerController;
import com.qigan.qiganshop.controller.backstage.SynchController;
import com.qigan.qiganshop.controller.backstage.VersionController;
import com.qigan.qiganshop.controller.backstage.XltcPreSellOrderController;
import com.qigan.qiganshop.controller.frontend.AppUserController;
import com.qigan.qiganshop.mapper.AppUserMapper;
import com.qigan.qiganshop.mapper.TbClueUserMapper;
import com.qigan.qiganshop.myutils.ExplainSql;
import com.qigan.qiganshop.myutils.SupperDoc;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.TbPresellOrder;
import com.qigan.qiganshop.pojo.group.LevelPreSellReporter;
import com.qigan.qiganshop.pojo.group.SingtonLevel;
import com.qigan.qiganshop.service.*;
import com.qigan.qiganshop.util.Redis.RedisUtil;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.result.XltcResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * create by qh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
@WebAppConfiguration
@ComponentScan("test.annotation")
public class TestQ {

   @Autowired
   AppUserMapper userMapper;
   @Autowired
   JedisUtil jedisUtil;


   @Test
    public  void createOrder(){
       SupperDoc<AppUser> supperDoc = new SupperDoc<>();
       Map<String, Object> able = supperDoc.getPageAble();
       System.out.println(able);
       Object page = able.get("page");
       Object size = able.get("size");
       System.out.println(page+"----------"+size);
       System.out.println(new Date().toLocaleString());

   }

   @Autowired
   ManagerController managerController;

   @Test
   public void aaaqq(){
      JsonResult goods = managerController.findCurrentLimitedGoods();
      System.out.println(goods);
   }

   @Test
   public void testSetRedis(){
      jedisUtil.set("aa","Aa");
      String aa = jedisUtil.get("aa");
      System.out.println(aa);
   }

   @Test
   public void testParseMap(){
      String str ="{" +
              "    \"out_trade_no\":\"" + "out_trade_no" + "\"," +
              "    \"total_amount\":\"" + "total" + "\"," +
              "    \"subject\":\"" + "subject" + "\"," +
              "    \"time_expire\":\"" + "expire" + "\"," +
              "    \"product_code\":\"" + "QUICK_MSECURITY_PAY" + "\"" +
              "  }";
      System.out.println(str);
      SortedMap<String, String> treeMap = new TreeMap<>();
      treeMap.put("out_trade_no","out_trade_no");
      treeMap.put("total_amount","total_amount");
      treeMap.put("subject","subject");
      treeMap.put("time_expire","time_expire");
      treeMap.put("product_code","QUICK_MSECURITY_PAY");
      String strTwo = JSON.toJSONString(treeMap);
      System.out.println(strTwo);
   }

   private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


   @Test
   public void parseLocalDateime () {
      String now = TimeFormat.nomalFormat.format(new Date());
      LocalDateTime localDateTime = LocalDateTime.parse(now.substring(0,now.lastIndexOf(":")),dateTimeFormatter);
      System.out.println(localDateTime.plusMinutes(2).format(dateTimeFormatter));
   }

   @Autowired
   TbClueUserMapper clueUserMapper;

   @Test
   public void testQuDaoData(){
   }


   @Test
   public void parseTimeStamp () {
      long l = new Date().getTime() / 1000;
      System.out.println(String.valueOf(l));
   }

   @Test
   public void testFilter(){
      HashMap<String, String> map = new HashMap<>();
      map.put("a","1");
      map.put("b","2");
      map.put("c","3");
      map.put("d","4");
      map.put("e","5");
      List<Map.Entry<String, String>> collect = map.entrySet().stream().filter(x -> {
         return Integer.parseInt(x.getValue()) > 2;
      }).collect(Collectors.toList());
      for (Map.Entry<String, String> entry : collect) {
         System.out.println(entry.getKey());
      }
   }

   @Test
   public void setTrim(){
//      jedisUtil.set("qq","qiao hang");
      jedisUtil.setToHash(RedisConstant.PRESELL_GOODS_CODE,"aaa",1+"@"+TimeFormat.nomalFormat.format(new Date()));
   }

   @Autowired
   XltcPreSellOrderController preSellOrderController;

   @Test
   public void testPreSellPushByPerson(){
      ArrayList<String> preSellOrderIds = new ArrayList<>();
      preSellOrderIds.add("1910151571143191838219030");
      preSellOrderIds.add("1910151571144150347484557");
      preSellOrderIds.add("1910171571311313587108063");
      XltcResult result = preSellOrderController.pushPreSellOrderToGuanyi(preSellOrderIds, "5052");
      System.out.println(result.getMessage());
   }

   @Test
   public void setTrimHset(){
      //缓存中待检查的限购记录
      Set<String> limitedRecords = new HashSet<>();
      Jedis conn = jedisUtil.getConn();
      Map<String, String> map = conn.hgetAll("LIMITED_GODE_USER");
      System.out.println(map.size());
      if(map!=null && map.size()>0){
         //只检查value = 1 的数据( value = 1 标识未经检测过 )
         Set<Map.Entry<String, String>> set = map.entrySet().stream().filter(x -> {
            return "1".equals(x.getValue());
         }).collect(Collectors.toSet());
         if(set!=null && set.size()>0){
            for (Map.Entry<String, String> entry : set) {
               limitedRecords.add(entry.getKey());
            }
         }
      }
      Set<String> smembers = conn.smembers(RedisConstant.LIMITED_CODES);
      conn.close();
      if(limitedRecords!=null){
         for (String limitedRecord : limitedRecords) {
            System.out.println(limitedRecord);
         }
      }
   }


   @Test
   public void printResult(){
      SupperDoc<AppUser> supperDoc = new SupperDoc<AppUser>();
      Class<AppUser> appUserClass = supperDoc.getaClass();
     // supperDoc.setaClass(AppUser.class);
      supperDoc.setTable_name("tb_user");
      supperDoc.setConditions(initConditions());
      supperDoc.setGroupFilters(initGroupFields());
      supperDoc.setWantedFields(initFields());
      supperDoc.setGroupDeplay("userId,balance");
      supperDoc.setPageAble(initPageAble());
      supperDoc.setSortedField("createTime");
      String sql = ExplainSql.findDataByConditions(supperDoc);
//      if(sql.indexOf(ExplainSql.ERROR)>0){
//         System.out.println(ExplainSql.ERROR);
//      }else {
//         List<Map> result= userMapper.selectMultiFun(sql);
//         if(result!=null && result.size()>0){
//            System.out.println("总条数："+result.size());
//            for (Map map : result) {
//               System.out.println(map);
//            }
//         }
//      }
      //查询总条数，不计分组
      supperDoc.getPageAble().put("wantTotal",true);
      String countSql = ExplainSql.countTotalByCase(supperDoc);
//      if(countSql.indexOf(ExplainSql.ERROR)>0){
//         System.out.println(ExplainSql.ERROR);
//      }else {
//       //  List<Map> result= userMapper.selectMultiFun(sql);
//         Long total = userMapper.countTotalByCase(countSql);
//         System.out.println(total);
//      }
   }


   @Test
   public void containsSomeOne(){
      HashSet<String> set = new HashSet<>();
      set.add(" 00012");
      set.add("qwer");
      if(set.contains("qwer")){
         System.out.println("包含qwer");
      }
      if(set.contains("00012")){
         System.out.println("包含00012");
      }
   }

   private Map<String, Object> initGroupFields() {
      LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();
      hashMap.put("phone~neq","17611660918");
      hashMap.put("createTime$gt","2019-08-25");
      hashMap.put("createTime$lte","2019-08-26");
      return hashMap;
   }

   private Map<String, Object> initPageAble() {
      LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();
      hashMap.put("wantPage",true);
      hashMap.put("page",1);
      hashMap.put("size",5);
      //hashMap.put("wantTotal",true);
      return hashMap;
   }


   public Map<String,Object> initConditions(){
      LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();
      hashMap.put("phone~neq","17611660918");
      hashMap.put("createTime$gt","2019-08-05");
      hashMap.put("createTime$lte",new Date().toLocaleString());
      return hashMap;
   }

   public ArrayList<String> initFields(){
      ArrayList<String> list = new ArrayList<>();
      list.add("userId");
      list.add("phone as PHONE2");
      list.add("password as PASSWORD2");
      list.add("createTime as CREATETIME2");
      return list;
   }

   @Test
   public void testField(){
      ArrayList<Class> list = new ArrayList<>();
      list.add(String.class);
      list.add(Double.class);
      list.add(float.class);
      list.add(Integer.class);
      list.add(int.class);
      list.add(boolean.class);
      Field[] declaredFields = AppUser.class.getDeclaredFields();
      for (Field field : declaredFields) {
         Class<?> type = field.getType();
         if(list.contains(type)){
            System.out.println(field.getName()+"----------"+type);
         }
         System.out.println(type);
      }
   }

   @Test
   public void testLocalFormat(){
      String format = LocalDateTime.now().plusDays(5).format(TimeFormat.normalDateTimeFormater);
      System.out.println(format);
   }


   @StepRecordWatchAble
   public void aaa(String no,String name,String inter,String func){
      System.out.println("fun ---- finish!");
   }

   @Test
   public void testAAA(){
      System.out.println(new Date().toLocaleString());
   }

   @Test
   public void testAAARe(){
      HashMap<String, Object> map = new HashMap<>();
//      map.put("oldName","qiaohang");
      printMap(map);
      map.put("newName",map.remove("oldName"));
      printMap(map);

      System.out.println(new Date().toLocaleString());
   }

   public void printMap(HashMap<String, Object> map){
      for (Map.Entry<String, Object> entry : map.entrySet()) {
         System.out.println(entry.getKey()+"-------------"+entry.getValue());
      }
   }

   @Autowired
   XltcPreSellOrderService preSellOrderService;

   @Test
   public void aaa() {
      List<TbPresellOrder> preSellOrders = preSellOrderService.findCurrentSuitPushOrders();
      System.out.println(preSellOrders);
   }


   @Autowired
   XltcOrderService xltcOrderService;

   @Test
   public void aaaa(){
      String s = xltcOrderService.pullToGuanYi("1910061570328283859152259");
   }

   @Test
   public void balanceMoney(){
      double v = Double.parseDouble("1.05");
      System.out.println(v);
   }

   @Test
   public void balanceMoneyaaa(){
      Double aDouble = new Double("10.35");
      System.out.println(String.valueOf(aDouble));
   }

   @Test
   public void setToRedis(){
      Jedis jedis = RedisUtil.getConnection();
//      String cc = jedis.set("cc", "0");
      String luaScript = "if redis.call('get' , KEYS[1]) <= ARGV[1] then return redis.call('incr',KEYS[1]) else return 0 end";
      //凭证相同才可以释放锁（删除lock）
      jedis.eval(luaScript, Collections.singletonList("cc"),Collections.singletonList("100"));
   }

   @Test
   public void testListToArray(){
      ArrayList<String> list = new ArrayList<>();
      list.add("ss");
      list.add("ss");
      list.add("qq");
      list.add("aa");
      String[] array = list.toArray(new String[]{});
      Object[] objects = list.toArray();
      System.out.println(array.length+"-------------"+objects.length);
      System.out.println(Arrays.asList(array));
      System.out.println(Arrays.asList(objects));
   }

   @Autowired
   SynchController synchController;

//   String start_date,String end_date,
//   @RequestParam(required = true) String no,
//   @RequestParam(required = true) String name,
//   @RequestParam(defaultValue = "/asyncGooyTime.do") String inter,
//   @RequestParam(defaultValue = "同步部分商品")String Func

//   @Test
//   public void testSyanc(){
//      JsonResult jsonResult = synchController.asyncGooyTime("2019-10-01 00:00:00",
//              "2019-10-07 00:00:00",
//              "5052",
//              "周雪芹", null, null);
//      System.out.println(jsonResult);
//   }

   @Autowired
   VersionController versionController;

   @Test
   public void  printArray(){
      Jedis jedis = RedisUtil.getConnection();
      Set<String> limited_codes_casue = jedis.smembers("LIMITED_CODES_CASUE");
      System.out.println(limited_codes_casue.size());
      for (String s : limited_codes_casue) {
         System.out.println(s);
      }
   }

   @Test
   public void setStr(){
      Jedis jedis = RedisUtil.getConnection();
      jedis.hset("aaa","test","预售1.1发");
      String hget = jedis.hget("aaa", "test");
      System.out.println(hget);
   }


   @Test
   public void testFormat(){
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      LocalDateTime plus = LocalDateTime.now().plus(5, ChronoUnit.DAYS);
      System.out.println(plus.toString());
   }


    @Test
    public void testFormatList(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        System.out.println(strings);
    }


    @Test
    public void testFormatListTwo(){
        ArrayList<LevelPreSellReporter> strings = new ArrayList<>();
        LevelPreSellReporter reporter = new LevelPreSellReporter();
        reporter.setLevel("1");
        strings.add(reporter);
        LevelPreSellReporter reporter2 = new LevelPreSellReporter();
        reporter2.setLevel("2");
        strings.add(reporter2);
        for (LevelPreSellReporter string : strings) {

            System.out.println(string.toString()+"----------"+string.getQuestionGoodsCount());
        }
        System.out.println("-------------------------");
        List<LevelPreSellReporter> collect = strings.stream().filter(x -> {
            return "1".equals(x.getLevel());
        }).collect(Collectors.toList());
        for (LevelPreSellReporter levelPreSellReporter : collect) {
           System.out.println(levelPreSellReporter);
           levelPreSellReporter.setQuestionGoodsCount("pppppppp");
        }
       System.out.println("------------------------");
       for (LevelPreSellReporter string : strings) {

          System.out.println(string.toString()+"----------"+string.getQuestionGoodsCount());
       }
    }

    @Test
    public void testOrder(){
       ArrayList<SingtonLevel> list = new ArrayList<>();
       for (int i = 0; i < 5; i++) {
          SingtonLevel singtonLevel = new SingtonLevel();
          String random = RandomStringUtils.randomNumeric(1);
          singtonLevel.setLevel(random+"");
          list.add(singtonLevel);
       }
       for (SingtonLevel singtonLevel : list) {
          System.out.print(singtonLevel.getLevel() +"  ");
       }
       System.out.println("------------------------------------");
       List<SingtonLevel> collect = list.stream().sorted(new Comparator<SingtonLevel>() {
          @Override
          public int compare(SingtonLevel o1, SingtonLevel o2) {
             return Integer.parseInt(o1.getLevel()) - Integer.parseInt(o2.getLevel());
          }
       }).collect(Collectors.toList());
       System.out.println("------------------------------------");
       for (SingtonLevel singtonLevel : collect) {
          System.out.print(singtonLevel.getLevel()+"  ");
       }

    }
}
