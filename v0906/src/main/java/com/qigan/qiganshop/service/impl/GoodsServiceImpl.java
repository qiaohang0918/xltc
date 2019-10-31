package com.qigan.qiganshop.service.impl;

import com.alibaba.druid.sql.ast.statement.SQLConstraint;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.controller.frontend.TimeController;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.*;
import com.qigan.qiganshop.myutils.SqlConstructUtils;
import com.qigan.qiganshop.pojo.*;
import com.qigan.qiganshop.service.*;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.baidumap.BaiDuMapService;
import com.qigan.qiganshop.util.baidumap.Polygon;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.picture.PicUtil;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.XltcResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author wanghao
 */
@Service
@Transactional(rollbackFor = Exception.class)
@SuppressWarnings("all")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper mapper;
	@Autowired
	private ItemService service;
	@Autowired
	private LabelGoodsMapper unionMapper;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private BaiDuMapService mapService;
	@Autowired
	private ScopeService scopeService;
	@Autowired
	private JsonResult jr;
	@Autowired
	private StockService stockService;
	@Autowired
	private CollectService collectService;

	@Autowired
	private XltcActivityMapper actMapper;
	@Autowired
	private TimeController timeController;

	@Autowired
	private PicUtil picUtil;

	@Autowired
	private JedisUtil jedisUtil;

	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private TbClueActivityMapper clueActivityMapper;

	/**
	 * 查询商品id
	 * @param code
	 * @return
	 */
	@Override
	public String findGoodsIdByCode(String code) {
		String goodsId = mapper.findGoodsIdByCode(code);
		return goodsId;
	}

	/**
	 * 查询商品名称
	 * @param code
	 * @return
	 */
	@Override
	public String findNameByCode(String code) {
		String name = mapper.findNameByCode(code);
		return name;
	}


	/**
	 * 根据商品id / code 查询商品详情
	 * @param parms
	 * @return
	 */
	@Override
	public XltcResult findGoodsByConditions(Map<String,String> parms) {
		StringBuffer conditionBuffer = new StringBuffer(" ");
		if(StringNotNull.check(parms.get("goodsId"))){
			conditionBuffer.append(" and goodsId = '"+parms.get("goodsId")+"'");
		}
		if(StringNotNull.check(parms.get("code"))){
			conditionBuffer.append(" and code = '"+parms.get("code")+"'");
		}
		List<Goods> goodsList=  mapper.findGoodsByConditions(conditionBuffer.toString());
		if(!SqlConstructUtils.nullList(goodsList)){
			for (Goods goods : goodsList) {
				this.getItemsNotStock(goods);
			}
		}
		return XltcResult.ok(goodsList);
	}

	@Override
	public List<String> findGoodsIdsByCodes(String codesSqlFormat) {
		return mapper.findGoodsIdsByCodes(codesSqlFormat);
	}

	/**
	 * 检查缓存中的限购商品的有效性
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	@Override
	public String checkUserLimitedRecord(String userId, String goodsId) {
		return mapper.checkUserLimitedRecord(userId,goodsId);
	}

	/**
	 * 返回全部列表
	 *
	 * @return
	 */
	@Override
	public List<Goods> findAll() {
		return (List<Goods>) NotNull.checkListNull(mapper.findByGoods(null, null, null));
	}

	/**
	 * 增加
	 *
	 * @param goods
	 */
	@Override
	public Integer add(Goods goods) {
		/**
		 * 增加 spu
		 */
		String goodsId = goods.getGoodsId();
		// 新增
		if (goodsId == null || "".equals(goodsId)) {

			String uuid = UUID.randomUUID().toString().replace("-", "");
			goods.setGoodsId(uuid);

		}
		goods.setPicUrls(picUtil.deleteUrlHead2(goods.getPicUrls()));
		goods.setNote(picUtil.deleteUrlHead2(goods.getNote()));
		goods.setStatus("0");
		return mapper.insert(goods);
	}

	/**
	 * 修改
	 *
	 * @param goods
	 */
	@Override
	public Integer update(Goods goods) {
		if (NotNull.checkString(goods.getPicUrls())) {
			goods.setPicUrls(picUtil.deleteUrlHead2(goods.getPicUrls()));
		}
		if (NotNull.checkString(goods.getNote())) {
			goods.setNote(picUtil.deleteUrlHead2(goods.getNote()));
		}
		return mapper.update(goods);
	}

	/**
	 * 根据ID获取实体,包含 sku 信息
	 *
	 * @param goodsId
	 * @return
	 */
	@Override
	public Goods findOne(String token, String goodsId) {
		Goods goods = mapper.findOne(goodsId);
		if (goods != null) {
			//搜索商品的预售信息
			searchGoodsPreSellInfo(goods);
			//封装商品的其他信息（配送时间..）
			searchGoodsOtherInfo(goods);
			Goods goodAndItems = getItems(token, goods);
			return goodAndItems;
		}
		return new Goods();
	}

	/**
	 * 封装商品的与预售信息(如果是预售商品)
	 * @param goods
	 */
	@Override
	public void searchGoodsPreSellInfo(Goods goods){
		//查询商品code
		if(!StringNotNull.check(goods.getCode())){
			String code = mapper.findCodeByGoodsId(goods.getGoodsId());
			goods.setCode(code);
		}
		//判断是否预售
		Set<String> preSellGoods = this.findPreSellGoods();
		if(preSellGoods!=null && preSellGoods.size()>0 && preSellGoods.contains(goods.getCode())){
			//查询该商品的预售信息
			Map<String,Object> map =  mapper.findPreSellInfoByPreSellCode(goods.getCode());
			goods.setPreSellInfo(map);
		}
	}

	/**
	 * 查询缓存中的预售商品信息
	 */
	public Set<String> findPreSellGoods(){
		Set<String> preSellGoodsCodes = jedisUtil.getElementsFromHash(RedisConstant.PRESELL_GOODS_CODE);
		return preSellGoodsCodes;
	}


	@Override
	public Goods findOne(String goodsId) {
		Goods goods = mapper.findOne(goodsId);
		if (goods != null) {
			Goods goodAndItems = getItems(goods);
			return goodAndItems;
		}
		return null;
	}

	/**
	 * 获取 sku 列表
	 *
	 * @param goods
	 */
	private Goods getItems(Goods goods) {
		List<Item> itemList = (List<Item>) NotNull.checkListNull(service.findBySpuId(goods.getGoodsId()));

		goods.setItems(itemList.stream().distinct().collect(Collectors.toList()));
		goods.setPicList(picUtil.addUrlHead(goods.getPicUrls()));
		goods.setNoteList(picUtil.addUrlHead(goods.getNote()));
		return (Goods) NotNull.checkNull(goods);
	}

	/**
	 * 获取 sku 列表
	 *
	 * @param goods
	 */
	private Goods getItemsNotStock(Goods goods) {
		//查询商品详情，不去校验库存
		List<Item> itemList = (List<Item>) NotNull.checkListNull(service.findBySpuIdNotStock(goods.getGoodsId()));

		goods.setItems(itemList.stream().distinct().collect(Collectors.toList()));
		goods.setPicList(picUtil.addUrlHead(goods.getPicUrls()));
		goods.setNoteList(picUtil.addUrlHead(goods.getNote()));
		return (Goods) NotNull.checkNull(goods);
	}

	/**
	 * 获取 sku 列表
	 *
	 * @param goods
	 */
	private Goods getItems(String token, Goods goods) {
		String userId = null;
		AppUser appUserByToken = appUserService.getAppUserByToken(token);
		if (appUserByToken != null) {
			userId = appUserByToken.getUserId();
		}

		List<Item> itemList = (List<Item>) NotNull.checkListNull(service.findBySpuId(goods.getGoodsId()));
		for (Item item : itemList) {
			Collect collect = null;
			if (userId != null) {
				collect = collectService.selectCollectByUseridAndItemid(userId, item.getItemId());
			}

			if (collect == null || userId == null) {
				// 未关联
				item.setCollection("no");
			} else {
				// 关联
				item.setCollection("yes");
			}
		}
		goods.setItems(itemList);
		goods.setPicList(picUtil.addUrlHead(goods.getPicUrls()));
		goods.setNoteList(picUtil.addUrlHead(goods.getNote()));
		return (Goods) NotNull.checkNull(goods);
	}

	/**
	 * 根据ID获取实体
	 *
	 * @param goodsId
	 * @return
	 */
	@Override
	public Goods findOneGoods(String goodsId) {

		Goods one = mapper.findOne(goodsId);

		return one;
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
			/**
			 * 删除 spu
			 */
			num += mapper.delete(id);
		}
		return num;
	}

	/**
	 * 按照条件进行分页查询 根据配送范围查询商品 根据库存信息决定是否返回信息
	 *
	 * @param goods
	 * @param pageNum
	 *            当前页码
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	@Override
	public JsonResult findPage(Goods goods, String coordinate, Integer pageNum, Integer pageSize) {
		String wareHouseId = null;
		JsonResult jsonResult = this.getWarehouseId(coordinate);
		if (!"200".equals(jsonResult.getRes_code())) {
			return jsonResult;
		}
		wareHouseId = jsonResult.getMessage();
		/**
		 * 查询仓库的所有库存大于 0 的商品
		 */
		List<Stock> stockList = null;

		stockList = stockService.findStock(wareHouseId, null, null);
		List<Goods> result = this.findUnionGoods(goods.getCategoryCode(), goods.getLabelId(), new CommonPage()).getRows();
		ArrayList<Goods> list = new ArrayList<>();
		if (stockList.size() > 0) {
			for (Goods one : result) {
				//搜索封装预售信息
				searchGoodsPreSellInfo(one);
				//封装商品的其他信息（配送时间..）
				searchGoodsOtherInfo(goods);
				for (Stock stock : stockList) {
					if (one.getGoodsId().equals(stock.getGoodsId())) {
						List<Item> items = service.findBySpuId(stock.getGoodsId());
						List<Item> add = new ArrayList<>();
						for (Item item : items) {
							Stock search = stockService.findByItemIdAndWareHouseId(item.getItemId(), wareHouseId);
							if (search.getSalableNum() > 0) {
								item.setStockNum(search.getSalableNum());
								add.add(item);
							}
						}
						goods.setItems(add);
						if(one != null && "2".equals(one.getStatus())){
							list.add(one);
						}
					}
				}
			}

		}

		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(),
				new PageResult(list.size(), (ArrayList<Goods>) NotNull.checkNull(list)));
	}

	/**
	 * 封装商品的配送时间
	 * @param goods
	 */
	@Override
	public void searchGoodsOtherInfo(Goods goods) {
		//添加预计配饰时间
		JsonResult time = timeController.getTime("");
		Map<String,List> timeMap = (Map<String, List>) time.getData();
		List today = timeMap.get("today");
		Object timeObject = null;
		if(!SqlConstructUtils.nullList(today)){
			timeObject = today.get(0);
		}else {
			List tomorrow = timeMap.get("tomorrow");
			timeObject = tomorrow.get(0);
		}
		goods.getOtherInfos().put("nomalPreSendTime",String.valueOf(timeObject));
		//查询新人邀请福利 图片url
		TbClueActivityExample example = new TbClueActivityExample();
		TbClueActivityExample.Criteria criteria = example.createCriteria();
		criteria.andEnableEqualTo("1");
		List<TbClueActivity> activities = clueActivityMapper.selectByExample(example);
		if(!SqlConstructUtils.nullList(activities)){
			TbClueActivity activity = activities.get(0);
			String pictureUrl = activity.getPictureUrl();
			if(StringNotNull.check(pictureUrl)){
				String[] split = pictureUrl.split(",");
				for (int i = 0; i < split.length; i++) {
					if (i == 0) {
						//主图
						activity.setPictureUrl(split[i]);
					} else if (i == 1) {
						//分享模板图
						activity.setTemplatePicture(split[i]);
					} else if (i == 2) {
						//主图(小图)
						activity.setSmallPicture(split[i]);
					}
				}
			}
			goods.getOtherInfos().put("clueActivity",activity);
		}
	}

	/**
	 * 批量修改商品状态
	 * @param goodsList
	 * @return
	 */
	@Override
	public Integer updateStatus(String[] goodsIds, String status) {
		Integer count = 0;
		for (String goodsId : goodsIds) {
			Goods goods = new Goods();
			goods.setGoodsId(goodsId);
			goods.setStatus(status);
			count += this.update(goods);
		}
		return count;
	}

	/**
	 * 批量修改商品状态
	 *
	 * @param goodsList
	 * @return
	 */
	@Override
	public Integer updateEnable(String[] goodsIds, String status) {
		Integer count = 0;
		for (String goodsId : goodsIds) {
			Goods goods = new Goods();
			goods.setGoodsId(goodsId);
			goods.setDel(status);
			count += this.update(goods);
		}
		return count;
	}

	/**
	 * 随机查询商品
	 *
	 * @return
	 */
	@Override
	public List<Goods> findByRand(String coordinate) {
		JsonResult jsonResult = this.getWarehouseId(coordinate);
		if (!"200".equals(jsonResult.getRes_code())) {
			return new ArrayList<>();
		}
		List<Goods> byRand = (List<Goods>) NotNull.checkListNull(mapper.findByRand(jsonResult.getMessage()));
		ArrayList<Goods> result = new ArrayList<>();
		for (Goods goods : byRand) {
			//搜索封装预售信息
			searchGoodsPreSellInfo(goods);
			//封装商品的其他信息（配送时间..）
			searchGoodsOtherInfo(goods);
			Goods goods1 = this.getItems(goods);
			result.add(goods1);
		}
		return (List<Goods>) NotNull.checkNull(result);
	}

	/**
	 * 普通分页查询,不涉及库存,配送范围 后端专用
	 *
	 * @param goods
	 * @return
	 */
	@Override
	public PageResult findPageGoods(Goods goods, Integer page, Integer size) {
		List<Goods> list = new ArrayList<Goods>();
		List<Goods> all = new ArrayList<Goods>();
		if (goods != null && !StringUtils.isEmpty(goods.getWarehouseId())) {
			all = mapper.findByWarehouseIdAndAll(goods, goods.getWarehouseId(), null, null);
			if (page != null && size != null) {
				list = mapper.findByWarehouseIdAndAll(goods, goods.getWarehouseId(), (page - 1) * size, size);
			} else {
				list = all;
			}

		} else {

			all = mapper.findByGoods(goods, null, null);
			if (page != null && size != null) {
				list = mapper.findByGoods(goods, (page - 1) * size, size);
			} else {
				list = all;
			}

		}
		ArrayList<Goods> result = new ArrayList<>();
		for (Goods goods1 : list) {
			if (goods1.getStockNum() == null || goods1.getStockNum() < 0) {
				goods1.setStockNum(0);
			}
			Goods goodAndItems = getItemsNotStock(goods1);
			result.add(goodAndItems);
		}
		return new PageResult(all.size(), (ArrayList<Goods>) NotNull.checkNull(result));
	}

	
	@Override
	public List<?> findGoodsByPlatform(String goodsId){
		if(org.apache.commons.lang3.StringUtils.isBlank(goodsId))
			throw XltcRuntimeException.throwable("id为空");
		return stockMapper.findStockById(goodsId);
	}

	@Override
	public String findCodeByGoodsId(String goodsId) {
		return mapper.findCodeByGoodsId(goodsId);
	}

	/**
	 * 查询商品详情
	 * @param searchCodes
	 * @return
	 */
	@Override
	public List<Goods> findGoodsByCodes(String searchCodes) {
		return mapper.findGoodsByCodes(searchCodes);
	}

	/**
	 * 根据标签查询商品
	 *
	 * @param categoryId
	 * @param labelId
	 * @return
	 */
	@Override
	public PageResult findUnionGoods(String categoryId, String labelId, CommonPage page) {
		page.startPageHelper();
		List<XltcGoodsModel> list = unionMapper.findUnitGoodsByLabelId(labelId);
		PageInfo<XltcGoodsModel> result = new PageInfo<>(list);
//		for (Goods goods : list) {
//			result.add(this.getItems(goods));
//		}
		return new PageResult(result.getTotal(), (ArrayList<Goods>) NotNull.checkNull(list));
	}

	/**
	 * 根据标签查询商品
	 *
	 * @param categoryId
	 * @param labelId
	 * @return
	 */
	@Override
	public PageResult findUnionGoods(String categoryId, String coordinate, String labelId, Integer page, Integer size) {
		JsonResult jsonResult = this.getWarehouseId(coordinate);
		if (!"200".equals(jsonResult.getRes_code())) {
			return new PageResult(0, new ArrayList());
		}
		List<Goods> list = new ArrayList<>();
		if (page != null && size != null) {
			list = mapper.findByCateLabelStock(jsonResult.getMessage(), categoryId, labelId, (page - 1) * size, size);
		} else {

			list = mapper.findByCateLabelStock(jsonResult.getMessage(), categoryId, labelId, null, null);
		}
		ArrayList<Goods> result = new ArrayList<>();
		for (Goods goods : list) {
			result.add(this.getItems(goods));
		}
		return new PageResult(
				mapper.findByCateLabelStock(jsonResult.getMessage(), categoryId, labelId, null, null).size(), result);
	}

	/**
	 * 根据分类类型以及分类 ID 查询商品
	 *
	 * @param cateId
	 * @param location
	 * @return
	 */
	@Override
	public List<Goods> findByLocation(String cateId, String location) {
		if ("head".equals(location)) {
			// 首页头部分类
		} else if ("end".equals(location)) {
			// 首页尾部分类
		}
		return null;
	}

	/**
	 * 根据经纬度获取仓库 Id
	 *
	 * @param coordinate
	 * @return
	 */
	@Override
	public JsonResult getWarehouseId(String coordinate) {
		/**
		 * 查询配送范围
		 */
		String city = mapService.getCity(coordinate);
		// 记录仓库 Id
		String wareHouseId = null;
		if (city == null || "".equals(city)) {
			return jr.jsonResultData(ResultCode.FAIL.res_code(), "经纬度不能定位到城市", new PageResult(0, new ArrayList()));
		} else {
			/**
			 * 获取该城市的所有配送范围
			 */
			Scope scope = new Scope();
			scope.setCity(city);
			List<Scope> rows = scopeService.findPage(scope, null, null).getRows();
			/**
			 * 可以配送的仓库
			 */

			if (rows.size() > 0) {
				int a = 0;
				boolean result = false;
				for (Scope row : rows) {
					/**
					 * 判断当前点是否在配送范围内
					 */
					boolean b = Polygon.checkIn(coordinate, row.getApexs());
					result |= b;
					if (b) {
						wareHouseId = row.getWarehouseId();
						break;
					}
				}
				if (!result) {

					return jr.jsonResultData(ResultCode.FAIL.res_code(), "该城市无配送范围",
							new PageResult(0, new ArrayList()));
				}

			} else {
				return jr.jsonResultData(ResultCode.FAIL.res_code(), "该城市无配送范围", new PageResult(0, new ArrayList()));
			}
		}
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), wareHouseId, new PageResult(0, new ArrayList()));
	}

	/**
	 * 根据 spuId 和坐标查询商品
	 *
	 * @param spuId
	 * @param coordinate
	 * @return
	 */
	@Override
	public Goods findOneByCoordinate(Item item, String coordinate) {
		String wareHouseId = null;
		JsonResult jsonResult = this.getWarehouseId(coordinate);
		if (!"200".equals(jsonResult.getRes_code())) {
			return null;
		}
		wareHouseId = jsonResult.getMessage();
		/**
		 * 查询仓库的所有库存大于 0 的商品
		 */
		List<Stock> stockList = null;

		stockList = stockService.findStock(wareHouseId, null, null);
		Goods one = this.findOne(item.getSpuId());
		if (one == null) {
			return null;
		}
		if (stockList.size() > 0) {
			for (Stock stock : stockList) {
				if (one.getGoodsId().equals(stock.getGoodsId())) {
					List<Item> add = new ArrayList<>();
					Stock search = stockService.findByItemIdAndWareHouseId(item.getItemId(), wareHouseId);
					if (search.getSalableNum() > 0) {
						item.setStockNum(search.getSalableNum());
						add.add(item);
					}
					one.setItems(add);
				}
			}
			return one;
		}
		return null;
	}

	@Value("${local_pic_url}")
	private String localPicHead;

	@Override
	public List<XltcGoodsModel> goodsByIds() {
		// TODO Auto-generated method stub
		List<XltcGoodsModel> result = new ArrayList<>();
		List<String> goodsIds = actMapper.findGoodsId();
		Set<String> sets = new HashSet<>(goodsIds);
		if (goodsIds == null)
			throw XltcRuntimeException.throwable("商品Id为空");
		for (String id : sets) {
			List<XltcGoodsModel> l = mapper.findGoodsByIds(id, localPicHead);
			if (l != null && l.size() > 0) {
				result.add(l.get(0));
			}
		}
		return result;
	}

	@Override
	public List<XltcGoodsModel> searchGoodsByWordsAndWareHouse(String keyWord, String wareHouseId) {
		List<XltcGoodsModel> models = mapper.searchGoodsByWordsAndWareHouse(keyWord, wareHouseId, localPicHead);
		if(!SqlConstructUtils.nullList(models)){
			Set<String> preSellGoods = this.findPreSellGoods();
			if(preSellGoods!=null && preSellGoods.size()>0){
				for (XltcGoodsModel model : models) {
					String code = model.getGoodsCode();
					//查询是否是预售商品
					if(preSellGoods.contains(code)){
						//查询预售信息
						Map<String,Object> preSellInfoMap =  mapper.findPreSellInfoByPreSellCode(code);
						if(preSellInfoMap!=null){
							model.setIsSelling(String.valueOf(preSellInfoMap.get("isSelling")));
							model.setBeforeTimeStamp(String.valueOf(preSellInfoMap.get("beforeTimeStamp")));
							model.setAfterTimeStamp(String.valueOf(preSellInfoMap.get("afterTimeStamp")));
							model.setCurrentTimeStamp(String.valueOf(preSellInfoMap.get("currentTimeStamp")));
							model.setSellNum(String.valueOf(preSellInfoMap.get("sellNum")));
							model.setPreSendTime(String.valueOf(preSellInfoMap.get("preSendTime")));
							model.setPreSell(String.valueOf(preSellInfoMap.get("preSell")));
						}
					}
				}
			}
		}
		return models;
	}
	 
	@Override
	public Goods findOnes(String goodsId, String wareId) {
		// TODO Auto-generated method stub
		List<Goods> goodsList = mapper.findOnes(goodsId, wareId);
		if (goodsList != null && goodsList.size() > 0) {
			Goods goods = goodsList.get(0);
			goods.setPicList(picUtil.addUrlHead( goods.getPicUrls()));
//			if (goods != null) {
//				Goods goodAndItems = getItems(goods);
//				return goodAndItems;
//			}
			return goods;
		}
		return new Goods();

	}

	@Override
	public void deleteGoods(String token, String... goodsId) {
		// TODO Auto-generated method stub
		long cantDel = mapper.findGoodsIsDel(new ArrayList<>(Arrays.asList(goodsId)));
		if (cantDel > 0)
			throw XltcRuntimeException.throwable("存在未停用商品，无法删除");
		for (String id : goodsId) {
			mapper.delete(id);
		}

	}

	@Override
	public PageResult findAllGoods(XltcGoodsPojo goods) {
		// TODO Auto-generated method stub
		goods.startPageHelper();
		PageInfo<?> pageInfo = new PageInfo<>(mapper.findAllGoods(goods));
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

}
