package com.qigan.qiganshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.constant.TimeFormat;
import com.qigan.qiganshop.mapper.GoodsMapper;
import com.qigan.qiganshop.mapper.TbActivityMainMapper;
import com.qigan.qiganshop.mapper.TbActivityUnionGoodsMapper;
import com.qigan.qiganshop.mapper.TbActivityUnionMapper;
import com.qigan.qiganshop.myutils.SqlConstructUtils;
import com.qigan.qiganshop.pojo.*;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.XltcActivityManageService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.XltcResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import io.swagger.annotations.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/17 14:20
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XltcActivityManageServiceImpl implements XltcActivityManageService {

    @Autowired
    TbActivityMainMapper activityMainMapper;
    @Autowired
    TbActivityUnionMapper activityUnionMapper;
    @Autowired
    TbActivityUnionGoodsMapper activityUnionGoodsMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Value("${local_pic_url}")
    private String localPicHead;
    @Autowired
    JedisUtil jedisUtil;
    @Autowired
    GoodsService goodsService;


    /**
     * 查询某分类下已经关联 和 未关联的数据 ，准备关联！ （根据code 分组！）
     * @param parms
     * @return
     */
    @Override
    public XltcResult readyUnionGoods(Map<String, String> parms) {
        String union_id = parms.get("union_id");
        if(!StringNotNull.check(union_id)){
            return XltcResult.error("参数不全,联合分类id [ union_id ] 必传！");
        }
        TbActivityUnion union = activityUnionMapper.selectByPrimaryKey(union_id);
        if(union == null){
            return XltcResult.error("该分类不存在！请校验参数 [union_id] ,或刷新页面重试！");
        }
        StringBuffer buffer = new StringBuffer(" ");
        String categoryCode = parms.get("categoryCode");
        //状态 in ('')
        String status = parms.get("status");
        String del = parms.get("del");
        String code = parms.get("code");
        String name = parms.get("name");
        //用于校验库存>0
        String validateSalableNum = parms.get("validateSalableNum");
        if(StringNotNull.check(categoryCode)){
            buffer.append(" and a.categoryCode = '"+categoryCode+"' ");
        }
        if(StringNotNull.check(status)){
            buffer.append(" and a.status in (" + SqlConstructUtils.constructStringToStringsOnIn(status,",") + ") ");
        }
        if(StringNotNull.check(del)){
            buffer.append(" and a.del = '"+del+"' ");
        }
        if(StringNotNull.check(code)){
            buffer.append(" and a.code like '%"+code+"%' ");
        }
        if(StringNotNull.check(name)){
            buffer.append(" and a.name like '%"+name+"%' ");
        }
        if(StringNotNull.check(validateSalableNum)){
            buffer.append(" and b.salableNum > '0' ");
        }
       // buffer.append(" and a.code not in (select goods_code from tb_activity_union_goods where union_id = '"+union_id+"') ");
        buffer.append(" group by a.code ");
        //查询未关联的商品
        List<Map<String,Object>> leftList = activityUnionMapper.selectDisUnionGoodsInfo(buffer.toString());
        //查询已关联的商品
        List<String> rightList = activityUnionMapper.selectUnionedGoodsInfo(union_id);
        //返回体
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("left",SqlConstructUtils.dealNullList(leftList));
        resultMap.put("right",SqlConstructUtils.dealNullList(rightList));
        return XltcResult.ok(resultMap);
    }

    /**
     * 修改关联商品排序号
     * @param unionGoods
     * @return
     */
    @Override
    public XltcResult updateGoodsSort(TbActivityUnionGoods unionGoods) {
        //查询是否重复
        TbActivityUnionGoodsExample example = new TbActivityUnionGoodsExample();
        TbActivityUnionGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andUnionIdEqualTo(unionGoods.getUnionId());
        //criteria.andGoodsIdEqualTo(unionGoods.getGoodsId());
        criteria.andSortEqualTo(unionGoods.getSort());
        List<TbActivityUnionGoods> tbActivityUnionGoods = activityUnionGoodsMapper.selectByExample(example);
        if(SqlConstructUtils.nullList(tbActivityUnionGoods)  || (tbActivityUnionGoods.size()>0 && unionGoods.getGoodsId().equals(tbActivityUnionGoods.get(0).getGoodsId()))){
            //不存在重复排序，可以修改
            TbActivityUnionGoodsExample searchExample = new TbActivityUnionGoodsExample();
            TbActivityUnionGoodsExample.Criteria criteria1 = searchExample.createCriteria();
            criteria1.andUnionIdEqualTo(unionGoods.getUnionId());
            criteria1.andGoodsIdEqualTo(unionGoods.getGoodsId());
            List<TbActivityUnionGoods> goods = activityUnionGoodsMapper.selectByExample(searchExample);
            TbActivityUnionGoods goods1 = goods.get(0);
            goods1.setSort(unionGoods.getSort());
            activityUnionGoodsMapper.updateByPrimaryKeySelective(goods1);
        }
        return XltcResult.ok();
    }

    /**
     * 查询当前活动，返回活动信息，活动下的分类信息，以及分类下的商品信息
     * @param activityId
     * @param coordinate
     * @return
     */
    @Override
    public XltcResult selectCurrentActivityCascade(String activityId, String coordinate) {
        //查询活动
        TbActivityMain main = activityMainMapper.selectByPrimaryKey(activityId);
        if(main==null)
            return XltcResult.ok(new HashMap<String,Object>());
        //根据经纬度查询仓库
        JsonResult wareIdJson = goodsService.getWarehouseId(coordinate);
        String wareId = "";
        if ("200".equals(wareIdJson.getRes_code())) {
            wareId = wareIdJson.getMessage();
        }
        //是否装载商品标识
        boolean flag = "".equals(wareId) ? false:true;
        //装载主活动
        Map<String,Object> resultMap = JSON.parseObject(JSON.toJSONString(main), Map.class);
        //查询该活动下的分类
        TbActivityUnionExample example = new TbActivityUnionExample();
        TbActivityUnionExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(main.getActivityId());
        example.setOrderByClause("union_sort asc");
        List<TbActivityUnion> unions = activityUnionMapper.selectByExample(example);
        if(SqlConstructUtils.nullList(unions)){
            resultMap.put("unionList",new ArrayList<>());
            return XltcResult.ok(resultMap);
        }else {
            ArrayList<Map> unionList = new ArrayList<>();
            for (TbActivityUnion union : unions) {
                //获取联合分类，查询联合商品
                Map unionMap = JSON.parseObject(JSON.toJSONString(union), Map.class);
                if(flag) {
                    //装载商品集合
                    List<XltcGoodsModel> unionGoods = activityUnionGoodsMapper.findUnionGoods(union.getUnionId(), localPicHead, wareId);
                    unionMap.put("goodsList",unionGoods==null ? new ArrayList<>() : unionGoods);
                }
                unionList.add(unionMap);
            }
            //装载unionList
            resultMap.put("unionList",unionList);
        }
        return XltcResult.ok(resultMap);
    }

    @Override
    public XltcResult selectCurrentActivityCascadeLimitedUnion(String activityId, String coordinate, int page, int size) {
        //查询活动
        TbActivityMain main = activityMainMapper.selectByPrimaryKey(activityId);
        if(main==null)
            return XltcResult.ok(new HashMap<String,Object>());
        //根据经纬度查询仓库
        JsonResult wareIdJson = goodsService.getWarehouseId(coordinate);
        String wareId = "";
        if ("200".equals(wareIdJson.getRes_code())) {
            wareId = wareIdJson.getMessage();
        }
        //是否装载商品标识
        boolean flag = "".equals(wareId) ? false:true;
        //装载主活动
        Map<String,Object> resultMap = JSON.parseObject(JSON.toJSONString(main), Map.class);
        //查询该活动下的分类
        PageHelper.startPage(page,size);
        TbActivityUnionExample example = new TbActivityUnionExample();
        TbActivityUnionExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(main.getActivityId());
        example.setOrderByClause("union_sort asc");
        List<TbActivityUnion> unions = activityUnionMapper.selectByExample(example);
        PageInfo<TbActivityUnion> info = new PageInfo<>(unions);
        resultMap.put("total",info.getTotal());
        if(SqlConstructUtils.nullList(info.getList())){
            resultMap.put("unionList",new ArrayList<>());
            return XltcResult.ok(resultMap);
        }else {
            ArrayList<Map> unionList = new ArrayList<>();
            for (TbActivityUnion union : info.getList()) {
                //获取联合分类，查询联合商品
                Map unionMap = JSON.parseObject(JSON.toJSONString(union), Map.class);
                if(flag) {
                    //装载商品集合
                    List<XltcGoodsModel> unionGoods = activityUnionGoodsMapper.findUnionGoods(union.getUnionId(), localPicHead, wareId);
                    unionMap.put("goodsList",unionGoods==null ? new ArrayList<>() : unionGoods);
                }
                unionList.add(unionMap);
            }
            //装载unionList
            resultMap.put("unionList",unionList);
        }
        return XltcResult.ok(resultMap);
    }

    /**
     * 添加主活动
     * @param activity
     * @return
     */
    @Override
    public XltcResult addMainActivity(TbActivityMain activity) {
        //校验重复活动id
        TbActivityMain activityMain = activityMainMapper.selectByPrimaryKey(activity.getActivityId());
        if(activityMain!=null)
            return XltcResult.error("已存在活动唯一Key,换一个Key重新添加!");
        //插入新活动
        activity.setMainPicture(activity.getTopPicture());
        activity.setCreateTime(TimeFormat.nomalFormat.format(new Date()));
        activity.setActivityStatus("1");
        int i = activityMainMapper.insertSelective(activity);
        return XltcResult.ok();
    }

    /**
     * 修改活动分类
     * @param activity
     * @return
     */
    @Override
    public XltcResult updateMainActivity(TbActivityMain activity) {
        if(StringNotNull.check(activity.getTopPicture())){
            activity.setMainPicture(activity.getTopPicture());
        }
        int i = activityMainMapper.updateByPrimaryKeySelective(activity);
        return XltcResult.ok();
    }

    /**
     * 查询活动分类
     * @param activity/a
     * @return
     */
    @Override
    public XltcResult findMainActivity(TbActivityMain activity) {
        TbActivityMainExample example = new TbActivityMainExample();
        TbActivityMainExample.Criteria criteria = example.createCriteria();
        if(StringNotNull.check(activity.getActivityId())){
            criteria.andActivityIdEqualTo(activity.getActivityId());
        }
        if(StringNotNull.check(activity.getActivityStatus())){
            criteria.andActivityStatusEqualTo(activity.getActivityStatus());
        }
        if(StringNotNull.check(activity.getActivityName())){
            criteria.andActivityNameLike("%"+activity.getActivityName()+"%");
        }
        List<TbActivityMain> activityMains = activityMainMapper.selectByExample(example);
        return XltcResult.ok(SqlConstructUtils.nullList(activityMains)?new ArrayList<TbActivityMain>():activityMains);
    }

    /**
     * 删除主活动(连带其活动子分类以及关联的商品)
     * @param activity
     * @return
     */
    @Override
    public XltcResult deleteMainActivity(TbActivityMain activity) {
        //删除主活动
        int deleteMain = activityMainMapper.deleteByPrimaryKey(activity);
        if(deleteMain>0){
            //查询主活动下的关联活动
            TbActivityUnionExample unionExample = new TbActivityUnionExample();
            TbActivityUnionExample.Criteria criteria = unionExample.createCriteria();
            criteria.andActivityIdEqualTo(activity.getActivityId());
            List<TbActivityUnion> unions = activityUnionMapper.selectByExample(unionExample);
            //删除 该活动相关 union
            int deleteResult = activityUnionMapper.deleteByExample(unionExample);
            //获取 union primarykey id
            List<String> unionIds = this.getUnionIds(unions);
            if(!SqlConstructUtils.nullList(unionIds)){
                //删除关联商品 tb_activity_union_goods
                TbActivityUnionGoodsExample unionGoodsExample = new TbActivityUnionGoodsExample();
                TbActivityUnionGoodsExample.Criteria unionGoodsCriteria = unionGoodsExample.createCriteria();
                unionGoodsCriteria.andUnionIdIn(unionIds);
                activityUnionGoodsMapper.deleteByExample(unionGoodsExample);
            }
            return XltcResult.ok();
        }else {
            return XltcResult.error("该活动不存在，请刷新页面！");
        }
    }

    /**
     * 添加联合分类
     * @param union
     * @return
     */
    @Override
    public XltcResult addUnionType(TbActivityUnion union) {
        //校验活动存在
        TbActivityMain tbActivityMain = activityMainMapper.selectByPrimaryKey(union.getActivityId());
        if(tbActivityMain == null) {
            return XltcResult.error("该活动不存在！");
        }else {
            //排除重复的 该分类下的联合排序
            TbActivityUnionExample example = new TbActivityUnionExample();
            TbActivityUnionExample.Criteria criteria = example.createCriteria();
            criteria.andActivityIdEqualTo(union.getActivityId());
            List<TbActivityUnion> tbActivityUnions = activityUnionMapper.selectByExample(example);
            if(!SqlConstructUtils.nullList(tbActivityUnions)) {
                List<TbActivityUnion> collect = tbActivityUnions.stream().filter(x -> {
                    return union.getUnionSort().equalsIgnoreCase(x.getUnionSort());
                }).collect(Collectors.toList());
                if (!SqlConstructUtils.nullList(collect)) {
                    return XltcResult.error("该活动下已经存在排序号为[ " + union.getUnionSort() + " ]的联合分类，请重新选择排序号！");
                }
            }
        }
        union.setUnionId(this.generatorUUID());
        int i = activityUnionMapper.insertSelective(union);
        return XltcResult.ok();
    }


    /**
     * 编辑联合分类
     * @param union
     * @return
     */
    @Override
    public XltcResult updateUnionType(TbActivityUnion union) {
        TbActivityUnion tbActivityUnion = activityUnionMapper.selectByPrimaryKey(union.getUnionId());
        if(tbActivityUnion==null)
            return XltcResult.error("当前编辑的关联分类不存在,请刷新重试！");
        //这里不校验排序字段，后台操作人员手输入，控制排序，学小越靠前...
        if(StringNotNull.check(union.getUnionSort())){
            try {
                Integer.parseInt(union.getUnionSort());
            }catch (Exception e){
                return XltcResult.error("排序号必须是数字格式，排序号越小越靠前，请尽量保证统计分类下排序号不同！");
            }
        }
        int i = activityUnionMapper.updateByPrimaryKeySelective(union);
        return XltcResult.ok();
    }

    /**
     * 删除关联分类
     * @param union
     * @return
     */
    @Override
    public XltcResult deleteUnionType(TbActivityUnion union) {
        int i = activityUnionMapper.deleteByPrimaryKey(union.getUnionId());
        if(i>0){
            //删除该关联分类下的关联商品
            TbActivityUnionGoodsExample example = new TbActivityUnionGoodsExample();
            TbActivityUnionGoodsExample.Criteria criteria = example.createCriteria();
            criteria.andUnionIdEqualTo(union.getUnionId());
            int i1 = activityUnionGoodsMapper.deleteByExample(example);
        }
        return XltcResult.ok();
    }

    /**
     * 关联商品
     * @param list
     * @return
     */
    @Override
    public XltcResult unionGoods(List<TbActivityUnionGoods> list) {
        int i = 0;
        for (TbActivityUnionGoods unionGoods : list) {
            if(StringNotNull.check(unionGoods.getUnionId(),unionGoods.getGoodsCode())) {
                //查询是否已经关联（如果已经关联，则 continue！）
                TbActivityUnionGoodsExample example = new TbActivityUnionGoodsExample();
                TbActivityUnionGoodsExample.Criteria criteria = example.createCriteria();
                criteria.andGoodsCodeEqualTo(unionGoods.getGoodsCode());
                criteria.andUnionIdEqualTo(unionGoods.getUnionId());
                List<TbActivityUnionGoods> tbActivityUnionGoods = activityUnionGoodsMapper.selectByExample(example);
                if(!SqlConstructUtils.nullList(tbActivityUnionGoods)) {
                    continue;
                }
                unionGoods.setUnionGoodsId(this.generatorUUID());
                //查询goodsCode
                String goodsId = goodsMapper.findGoodsIdByCode(unionGoods.getGoodsCode());
                unionGoods.setGoodsId(goodsId);
                int insert = activityUnionGoodsMapper.insert(unionGoods);
                ++i;
            }else {
                return XltcResult.error("部分商品关联失败[ code / unionId ]为空了！");
            }
        }
        if(list.size() == i )
            return XltcResult.ok();
        else
            return XltcResult.error("数据异常，或存在已经关联过的商品,请新页面后重新选择关联!");
    }

    /**
     * 解除关联商品
     * @param list
     * @return
     */
    @Override
    public XltcResult releaseUnionGoods(List<TbActivityUnionGoods> list) {
        int i = 0;
        for (TbActivityUnionGoods unionGoods : list) {
            if(StringNotNull.check(unionGoods.getUnionId(),unionGoods.getGoodsCode())) {
                TbActivityUnionGoodsExample example = new TbActivityUnionGoodsExample();
                TbActivityUnionGoodsExample.Criteria criteria = example.createCriteria();
                criteria.andUnionIdEqualTo(unionGoods.getUnionId());
                criteria.andGoodsCodeEqualTo(unionGoods.getGoodsCode());
                int insert = activityUnionGoodsMapper.deleteByExample(example);
                ++i;
            }else {
                return XltcResult.error("部分商品关联失败[ code / unionId ]为空了！");
            }
        }
        return XltcResult.ok();
    }

    /**
     * 查询关联分类(不查询商品 web用)
     * @param union
     * @return
     */
    @Override
    public XltcResult findUnionTypes(TbActivityUnion union) {
        TbActivityUnionExample example = new TbActivityUnionExample();
        TbActivityUnionExample.Criteria criteria = example.createCriteria();
        if(StringNotNull.check(union.getUnionId())){
            criteria.andUnionIdEqualTo(union.getUnionId());
        }
        if(StringNotNull.check(union.getActivityId())){
            criteria.andActivityIdEqualTo(union.getActivityId());
        }
        if(StringNotNull.check(union.getUnionName())){
            criteria.andUnionNameLike("%"+union.getUnionName()+"%");
        }
        example.setOrderByClause("union_sort asc");
        List<TbActivityUnion> unions = activityUnionMapper.selectByExample(example);
        if(!SqlConstructUtils.nullList(unions)){
            List<TbActivityUnion> collect = unions.stream().sorted(new Comparator<TbActivityUnion>() {
                @Override
                public int compare(TbActivityUnion o1, TbActivityUnion o2) {
                    String sort1 = o1.getUnionSort();
                    String sort2 = o2.getUnionSort();
                    return Integer.parseInt(sort1) - Integer.parseInt(sort2);
                }
            }).collect(Collectors.toList());
            return XltcResult.ok(collect);
        }
        return XltcResult.ok(SqlConstructUtils.nullList(unions) ? new ArrayList<TbActivityUnion>() : unions);
    }

    /**
     * 查询关联商品(分类id)
     * @param unionId
     * @param wareId
     * @return
     */
    @Override
    public XltcResult findUnionGoods(String unionId,String wareId) {
       List<XltcGoodsModel> goods =  activityUnionGoodsMapper.findUnionGoods(unionId,localPicHead,wareId);
       if(!SqlConstructUtils.nullList(goods)) {
           //限购商品打标
           this.tagLimitedCode(goods);
       }
       return XltcResult.ok(goods==null?new ArrayList<>():goods);
    }


    /**
     * 限购商品打标
     */
    public void tagLimitedCode(List<XltcGoodsModel> goods){
        //获取所有限购商品code
        Set<String> limitedCodes = this.getAllLimitedCodes();
        if(limitedCodes!=null){
            for (XltcGoodsModel good : goods) {
                String goodsCode = good.getGoodsCode();
                if(StringNotNull.check(goodsCode)){
                    if(limitedCodes.contains(goodsCode.trim())){
                        //是限购商品，打标
                        good.setIsLimitedGoods("1");
                    }
                }
            }
        }
    }

    /**
     * 获取所有限购商品code
     * @return
     */
    public Set<String> getAllLimitedCodes(){
        HashSet<String> resultCodes = new HashSet<>();
        Set<String> limitedCode = jedisUtil.getMembersFromSet(RedisConstant.LIMITED_CODES);
        Set<String> limitedCodeCasue = jedisUtil.getMembersFromSet(RedisConstant.LIMITED_CODES_CASUE);
        if(limitedCode!=null && limitedCode.size()>0)
            resultCodes.addAll(limitedCode);
        if(limitedCodeCasue!=null && limitedCodeCasue.size()>0)
            resultCodes.addAll(limitedCodeCasue);
        return resultCodes==null || resultCodes.size()<=0 ? null : resultCodes;
    }


    /**
     * 获取关联ids
     * @param unions
     * @param unions
     * @return
     */
    public List<String> getUnionIds(List<TbActivityUnion> unions){
        if(SqlConstructUtils.nullList(unions))
            return null;
        //获取关联id集合  unionId
        List<String> collect = unions.stream().map(x -> {
            return x.getUnionId();
        }).collect(Collectors.toList());
        return collect == null ? null : collect;
    }



    /**
     * 随机 UUID
     * @return
     */
    public String generatorUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}



