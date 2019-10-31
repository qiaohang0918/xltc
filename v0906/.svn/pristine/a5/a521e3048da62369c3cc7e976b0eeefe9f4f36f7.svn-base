package com.qigan.qiganshop.controller.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qigan.qiganshop.pojo.XltcGoodsModel;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.WareHouseService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.qigan.qiganshop.mapper.GoodsMapper;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.synchronization.ResultGoods;
import com.qigan.qiganshop.service.GuanYiGoodsService;
import com.qigan.qiganshop.service.SearchService;
import com.qigan.qiganshop.util.charUtil.CharUtil;
import com.qigan.qiganshop.util.es.EsUtils;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SuppressWarnings("all")
@RestController
@RequestMapping("/app/search")
@Api(tags = {"APP端搜索管理接口"})
public class SearchController {

    @Autowired
    private JsonResult jr;
    @Autowired
    private EsUtils client;
    @Autowired
    private SearchService searchService;
    
    @Autowired
    private CharUtil charUtil;
    
    @Autowired
    private GoodsMapper mapper;
    
    @Autowired
    private GuanYiGoodsService service;

    @Autowired
    private GoodsService goodsService;

    /**
     * 关键词联想
     *
     * @param word
     * @return
     */
    @RequestMapping("/getSearchSimple.do")
    @ApiOperation(
            value = "简单搜索调用接口",
            notes = "word 搜索词",
            httpMethod = "POST")
    public JsonResult getSearchSimple(String word,String coordinate) {
//        if (StringUtils.isEmpty(word)) {
//            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "关键字为空！");
//        }
//        try {
//            BoolQueryBuilder boolQuy = QueryBuilders.boolQuery();
//            if (charUtil.isNumeric(word) || charUtil.conValidate(word)) {
//                // 纯数字 或 英文字母
//                boolQuy.should(QueryBuilders.wildcardQuery("goodsName", "*" + word + "*"));
//                boolQuy.should(QueryBuilders.wildcardQuery("goodsNote", "*" + word + "*"));
//                boolQuy.should(QueryBuilders.wildcardQuery("goodSimpleName", "*" + word + "*"));
//                boolQuy.should(QueryBuilders.wildcardQuery("skuName", "*" + word + "*"));
//            } else {
//                boolQuy.should(QueryBuilders.multiMatchQuery(word, "goodsName", "goodsNote", "goodSimpleName", "skuName"));
//            }
//            SearchRequestBuilder searchRequestBuilder = client.getClient().prepareSearch("goods", "goods")
//                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                    .setQuery(boolQuy)
//                    .setFrom(0)
//                    .addSort("_score", SortOrder.DESC)
//                    .setSize(10);
//            SearchResponse searchResponse = searchRequestBuilder.get();
//            ArrayList<Object> list = new ArrayList<>();
//
//            for (SearchHit hit : searchResponse.getHits().getHits()) {
//
//                Map<String, Object> map = new HashMap<String, Object>();
//                if (hit != null) {
//                    String id = hit.getSource().get("goodsId").toString();
//                    String name = hit.getSource().get("goodsName").toString();
//                    String code = mapper.findGoodsCode(id);
//                    ResultGoods result = service.findGuanYiGoods(code);
//                    if(result == null)
//                    	continue;
//                    if(result.getItems().size() < 1)
//                    	continue;
//                    map.put("id", id);
//                    map.put("name", name);
//                }
//                list.add(map);
//            }
//            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "搜索成功", list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return jr.jsonResultData(ResultCode.ERROR.res_code());
//        }

        try {
            if (StringUtils.isEmpty(word)) {
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "关键字为空！");
            }
            if(StringUtils.isEmpty(coordinate)){
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "经纬度为空！");
            }
            //查询仓库
            JsonResult warehouse = goodsService.getWarehouseId(coordinate);
            if(warehouse==null){
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "周围没有仓库！");
            }
            String wareHouseId = warehouse.getMessage();

            //搜索
            List<XltcGoodsModel> lists= goodsService.searchGoodsByWordsAndWareHouse(word.trim(),wareHouseId);

            List<XltcGoodsModel> collect = lists.stream().distinct().collect(Collectors.toList());
           // Map map = new HashMap<>();
           // map.put("goods", lists.size() == 0 ? new ArrayList<>() : collect);
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "搜索成功", collect);
        } catch (Exception e) {
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }

    /**
     * 点击联想的关键词 精确搜索（不分词）
     *
     * @param word
     * @return
     */
    @RequestMapping("/getSearchClick.do")
    @ApiOperation(
            value = "点击后搜索接口",
            notes = "word 搜索词 page当前页码, size 页码大小 ",
            httpMethod = "POST")
    public JsonResult getSearchClick(String word,String coordinate) {
        try {
            if (StringUtils.isEmpty(word)) {
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "关键字为空！");
            }
            if(StringUtils.isEmpty(coordinate)){
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "经纬度为空！");
            }
            //查询仓库
            JsonResult warehouse = goodsService.getWarehouseId(coordinate);
            if(warehouse==null){
                return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "周围没有仓库！");
            }
            String wareHouseId = warehouse.getMessage();

            //搜索
            List<XltcGoodsModel> lists= goodsService.searchGoodsByWordsAndWareHouse(word.trim(),wareHouseId);

            List<XltcGoodsModel> collect = lists.stream().distinct().collect(Collectors.toList());
            Map map = new HashMap<>();
            map.put("goods", lists.size() == 0 ? new ArrayList<>() : collect);
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "搜索成功", map);
        } catch (Exception e) {
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }

//    @RequestMapping("/getSearchClick.do")
//    @ApiOperation(
//            value = "点击后搜索接口",
//            notes = "word 搜索词 page当前页码, size 页码大小 ",
//            httpMethod = "POST")
//    public JsonResult getSearchClick(String word,String coordinate) {
//        if (StringUtils.isEmpty(word)) {
//            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "关键字为空！");
//        }
//        if (StringUtils.isEmpty(coordinate)) {
//            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "无法获取经纬度!");
//        }
//         //-----------------------------------------------------
//        try {
//            BoolQueryBuilder boolQuy = QueryBuilders.boolQuery();
//            boolQuy.should(QueryBuilders.matchPhraseQuery("goodsName", word));
//            boolQuy.should(QueryBuilders.matchPhraseQuery("goodsNote", word));
//            boolQuy.should(QueryBuilders.matchPhraseQuery("goodSimpleName", word));
//            boolQuy.should(QueryBuilders.matchPhraseQuery("skuName", word));
//            SearchRequestBuilder searchRequestBuilder = client.getClient().prepareSearch("goods", "goods")
//                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                    .setQuery(boolQuy)
//                    .setFrom(0)
//                    .addSort("_score", SortOrder.DESC)
//                    .setSize(10);
//            SearchResponse searchResponse = searchRequestBuilder.get();
//
//            //-----------------------------------
//            List<Goods> lists = new ArrayList<>();
//            for (SearchHit hit : searchResponse.getHits().getHits()) {
//                Goods goods = searchService.findOneSearch(hit.getSource().get("goodsId").toString());
//                if (goods != null && goods.getGoodsId() != null) {
//                    lists.add(goods);
//                }
//            }
//            List<Goods> collect = lists.stream().distinct().collect(Collectors.toList());
//            //按仓库过滤
//            JsonResult warehouse = goodsService.getWarehouseId(coordinate);
//            String wareHouseId = warehouse.getMessage();
//
//
//
//            Map map = new HashMap<>();
//            map.put("goods", lists.size() == 0 ? new ArrayList<>() : collect);
//            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "搜索成功", map);
//        } catch (Exception e) {
//            return jr.jsonResultData(ResultCode.ERROR.res_code());
//        }
//    }


    @RequestMapping("/hotbot.do")
    @ApiOperation(
            value = "热门搜索接口",
            notes = "用于展示热门搜索词汇",
            httpMethod = "POST")
    public JsonResult hotbot(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<String> rank = searchService.getHotBotRank();
            int f = 6;
            if (rank.size() < f) {
                List<String> number = searchService.getHotBotOrderNumber();
                int k = number.size() > f - rank.size() ? f - rank.size() : number.size();
                for (int i = 0; i < k; i++) {
                    rank.add(number.get(i));
                }
            }
            return jr.jsonResultData(ResultCode.SUCCESS.res_code(), "操作成功", rank);
        } catch (Exception e) {
            return jr.jsonResultData(ResultCode.ERROR.res_code());
        }
    }

}
