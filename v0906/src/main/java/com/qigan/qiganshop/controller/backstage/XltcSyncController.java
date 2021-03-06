/**
 * 
 */
package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.annocation.StepRecordWatchAble;
import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.service.synchronization.XltcAsyncGoodsService;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.format.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qigan.qiganshop.service.XltcGoodsService;
import com.qigan.qiganshop.service.XltcStockService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**true
 * @author wyy
 *
 */
@RestController
@RequestMapping("/xltc/sync")
public class XltcSyncController {
	
	@Autowired
	XltcGoodsService goodsService;
	
	@Autowired
	XltcStockService stockService;

	@Autowired
	XltcAsyncGoodsService asyncGoodsService;
	
	@Autowired
	JsonResult jr;

	@Autowired
    JedisPool jedisPool;


	@RequestMapping("/goods.do")
	@StepRecordWatchAble
	public JsonResult syncGoods(@RequestParam(required = true) String no,
			  					@RequestParam(required = true) String name,
			  					@RequestParam(defaultValue = "/xltc/sync/goods.do") String inter,
			  					@RequestParam(defaultValue = "同步所有商品信息")String Func){
		goodsService.syncGoodsAll();
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功");
	}
	
	@RequestMapping("/goodsByCode.do")
	@StepRecordWatchAble
	public JsonResult syncGoodsByCode(@RequestParam(required = true) String no,
			  					@RequestParam(required = true) String name,
			  					@RequestParam(defaultValue = "/xltc/sync/goodsByCode.do") String inter,
			  					@RequestParam(defaultValue = "同步指定code商品信息")String Func,
			  					@RequestParam String... codes){
		goodsService.syncGoodsByCode(codes);
		return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功");
	}
	
	@RequestMapping("/stockByCodes.do")
	@StepRecordWatchAble
	public JsonResult sycnStockByCode(String wareId,
									  @RequestParam(required = true) String no,
									  @RequestParam(required = true) String name,
									  @RequestParam(defaultValue = "/xltc/sync/stockByCodes.do") String inter,
									  @RequestParam(defaultValue = "同步指定商品库存")String Func,
									  String... codes){
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get(RedisConstant.ASYNCRO_STEP);
        if(StringNotNull.check(s))
            return new JsonResult(ResultCode.FAIL.res_code(), "其他管理员正在操作，稍后重试！");
        JsonResult jsonResult =null;
        try {
            jedis.set(RedisConstant.ASYNCRO_STEP,"1");
            jsonResult = stockService.syncStock(wareId, codes);
        }catch (Exception e){
            jedis.del(RedisConstant.ASYNCRO_STEP);
            jsonResult = new JsonResult(ResultCode.FAIL.res_code(), ResultCode.FAIL.message());
        }finally {
            jedis.del(RedisConstant.ASYNCRO_STEP);
            jedis.close();
        }
        return jsonResult;
		
	}



}
