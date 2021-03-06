/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.GoodsMapper;
import com.qigan.qiganshop.mapper.ItemMapper;
import com.qigan.qiganshop.mapper.StockMapper;
import com.qigan.qiganshop.mapper.WarehouseMapper;
import com.qigan.qiganshop.pojo.Item;
import com.qigan.qiganshop.pojo.Stock;
import com.qigan.qiganshop.pojo.synchronization.ResultGoods;
import com.qigan.qiganshop.pojo.synchronization.ResultStock;
import com.qigan.qiganshop.pojo.synchronization.ResultStock.StocksBean;
import com.qigan.qiganshop.service.ItemService;
import com.qigan.qiganshop.service.StockService;
import com.qigan.qiganshop.service.WareHouseService;
import com.qigan.qiganshop.service.XltcStockService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import com.qigan.qiganshop.utils.guanyi.GuanYiDataSource;
import com.qigan.qiganshop.utils.json.JsonUtils;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class XltcStockServiceImpl implements XltcStockService {

	@Autowired
	private GuanYiDataSource dataSource;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private StockMapper stockMapper;

	@Autowired
	private JsonResult jr;

	@Value("${stockMethod}")
	private String stockMethod;

	@Autowired
	private GuanYiDataSource gy;

	private List<ResultStock.StocksBean> list = new ArrayList<>();

	private static Object lock = new Object();

	private static CountDownLatch latch;

	private static final String IS_DEL = "1";

	private static final String IS_NOT_DEL = "0";

	private static final String GOODS_CODE = "goodsCode";

	private static final String MESSAGE = "message";

	private static final String WAREHOUSE_CODE = "warehouse_code";

	@Autowired
	StockService stockService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qigan.qiganshop.service.XltcStockService#syncStock(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public JsonResult syncStock(String wareId, String... codes) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(wareId))
			throw XltcRuntimeException.throwable("请选择仓库");

		List<Map<?, ?>> list = new ArrayList<>();
		for (String code : codes) {
			Map<String, String> result = new HashMap<>();
			Integer count = stockMapper.selectByCode(wareId, code);
			if (count == null) {
				result.put(GOODS_CODE, code);
				result.put(MESSAGE, "存在锁定库存");
				list.add(result);
				continue;
			}

			goodsMapper.updateGoodsStatus(code, IS_DEL);
			Map<String, Object> guanyiParams = new HashMap<>();
			guanyiParams.put(WAREHOUSE_CODE, wareId);
			ResultStock resultStock = dataSource.get(stockMethod, code, ResultStock.class, guanyiParams);
			if (resultStock != null && !resultStock.isSuccess()) {
				result.put(GOODS_CODE, code);
				result.put(MESSAGE, "管易库存获取失败");
				list.add(result);
				continue;
			}
			List<StocksBean> stocks = resultStock.getStocks();
			if (stocks != null && stocks.size() > 0) {
				StocksBean bean = stocks.get(0);
				Map<String, Object> params = new HashMap<>();
				params.put("stockNum", bean.getQty());
				params.put("salableNum", bean.getSalable_qty());
//				params.put("lockNum", bean.getQty() - bean.getSalable_qty());
				params.put("wareId", wareId);
				params.put("code", code);
				//查询本地库存
				Stock stock= stockMapper.findStockByCodeAndWarehouseId((String) params.get("wareId"),(String) params.get("code"));
				if(stock==null){	//直接插入stock
					//查询shangpinspuid 和 skuid
					Map<String,String> map= stockMapper.findSpuSkuId((String) params.get("code"));
					if(map!=null){
						Stock stock1 = new Stock();
//						stock1.setLockNum(bean.getQty() - bean.getSalable_qty());
						stock1.setSalableNum(bean.getSalable_qty());
						stock1.setStockNum(bean.getQty());
						stock1.setWarehouseId(params.get("wareId") + "");
						stock1.setGoodsId(map.get("goodsId"));
						stock1.setItemId(map.get("itemId"));
						stock1.setIsDel(false);
						stockService.add(stock1);
						goodsMapper.updateGoodsStatus(code, IS_NOT_DEL);
						continue;
					}
				}
				stockMapper.updateStockByCode(params);
			}
			goodsMapper.updateGoodsStatus(code, IS_NOT_DEL);
		}
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", list);
	}

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

	@Override
	public JsonResult syncStockAll() {
		// TODO Auto-generated method stub
		int page = 1, size = 100;
		ResultGoods results = gy.get(stockMethod, null, 1, null, ResultGoods.class, null);
		int pageCount = (int) Math.ceil(results.getTotal() * 1.0 / size);
		System.err.println(results.getTotal());
		latch = new CountDownLatch(pageCount);
		ExecutorService ex = Executors.newFixedThreadPool(30);
		for (int i = 1; i <= pageCount; i++) {
			ex.submit(new GuanYiStock(i, null));
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
		List<Stock> stockList = new ArrayList<>();
		for (StocksBean stocksBean : list) {
			Stock stockObj = copyWarehouseFromBean(stocksBean);
			if(stockObj != null){
				stockList.add(stockObj);
			}
		}
		int c = stockMapper.insertOrUpdate(stockList);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "", c);
	}

	class GuanYiStock implements Runnable {

		private int i;

		private Map<String, Object> params;

		public GuanYiStock(int i, Map<String, Object> params) {
			this.i = i;
			this.params = params;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ResultStock resultStocks = gy.get(stockMethod, i, 100, null, ResultStock.class, params);
			synchronized (lock) {
				try {
					list.addAll(resultStocks.getStocks());
				} finally {
					// TODO: handle finally clause
					latch.countDown();
				}

			}
		}
	}
	@Autowired
	private WarehouseMapper wardMapper;
	
	@Autowired
	private ItemMapper itemMapper;

	private Stock copyWarehouseFromBean(ResultStock.StocksBean bean) {
		Stock stock = new Stock();
		/**
		 * 该商品为 sku 根据 item_code(spu 的商品代码)和sku_code(sku 的商品代码)查询出是否包含商品, 获取
		 * spuId 和 skuId
		 */
		List<Item> itemList = itemMapper.findItemByCode(bean.getItem_code(), bean.getSku_code());
		/**
		 * 复制属性
		 */
		if (itemList.size() == 1) {
			stock.setGoodsId(itemList.get(0).getSpuId());
			stock.setItemId(itemList.get(0).getItemId());
			// 检查仓库代码是否有效
			String one = wardMapper.countById(bean.getWarehouse_code());
			if (StringUtils.isNotBlank(one)) {
				// 仓库代码
				stock.setWarehouseId(bean.getWarehouse_code());
				// 可销售数量,仓储的可销售数量,为电商的库存数量
				stock.setStockNum(bean.getSalable_qty());
				// 库存记录状态
				stock.setIsDel(bean.isDel());
				return chooseInsertOrUpdate(stock);
			}
		}
		return null;

	}

	@Autowired
	private StockService service;

	private Stock chooseInsertOrUpdate(Stock stock) {
		/**
		 * 根据商品 Id 和仓库ID 查询是否存在该商品在该仓库的库存
		 */
		Stock checkExits = service.checkExits(stock);
		if (checkExits != null) {
			/**
			 * 存在,说明已经添加该记录,直接更新库存
			 */
			checkExits.setStockNum(stock.getStockNum());
			checkExits.setSalableNum(stock.getStockNum() - checkExits.getLockNum());
			return checkExits;
		} else {
			/**
			 * 不存在,说明已经未记录,直接插入新纪录
			 */
			stock.setLockNum(0);
			stock.setSalableNum(stock.getStockNum());
			return stock;
		}
	}
	
	
	@Override
	public JsonResult syncStockByWareId(String wardId) {
		// TODO Auto-generated method stub
		Map<String, Object> guanyiParams = new HashMap<>();
		guanyiParams.put("warehouse_code", wardId);
		int page = 1, size = 100;
		ResultGoods results = gy.get(stockMethod, page, 1, null, ResultGoods.class, guanyiParams);
		int pageCount = (int) Math.ceil(results.getTotal() * 1.0 / size);
		System.err.println(results.getTotal());
		latch = new CountDownLatch(pageCount);
		ExecutorService ex = Executors.newFixedThreadPool(15);
		for (int i = 1; i <= pageCount; i++) {
			ex.submit(new GuanYiStock(i, guanyiParams));
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
		System.err.println(JsonUtils.writeValueAsString(list));
		List<Stock> stockList = new ArrayList<>();
		for (StocksBean stocksBean : list) {
			Stock stockObj = copyWarehouseFromBean(stocksBean);
			if(stockObj != null){
				stockList.add(stockObj);
			}
		}
		int c = stockMapper.insertOrUpdate(stockList);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "", null);
	}

}
