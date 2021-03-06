package com.qigan.qiganshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qigan.qiganshop.constant.TimeFormat;
import com.qigan.qiganshop.mapper.*;
import com.qigan.qiganshop.myutils.SqlConstructUtils;
import com.qigan.qiganshop.pojo.*;
import com.qigan.qiganshop.service.XltcSpecialManageService;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/23 15:18
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XltcSepcialManageServiceImpl implements XltcSpecialManageService {

    @Autowired
    TbSpecialMapper specialMapper;
    @Autowired
    TbSpecialConcatMapper specialConcatMapper;
    @Autowired
    TbSpecialTopicMapper specialTopicMapper;
    @Autowired
    TbSpecialUnitMapper specialUnitMapper;

    /**
     * 添加特色
     * @param special
     * @return
     */
    @Override
    public XltcResult addSpecial(TbSpecial special) {
        special.setCreateTime(TimeFormat.nomalFormat.format(new Date()));
        special.setEnable("1");
        special.setIsHot("0");
        special.setSpecialId(SqlConstructUtils.generatorStringUUID());
        specialMapper.insert(special);
        return XltcResult.ok();
    }

    /**
     * 修改特色栏目
     * @param special
     * @return
     */
    @Override
    public XltcResult updateSpecial(TbSpecial special) {
        if(StringNotNull.check(special.getIsHot()) && "1".equals(special.getIsHot())){
            //禁用其他热门推荐isHot
            int i = specialMapper.changeAllSpecialsIsHot(special.getIsHot());
        }
        special.setUpdateTime(TimeFormat.nomalFormat.format(new Date()));
        int i = specialMapper.updateByPrimaryKeySelective(special);
        return XltcResult.ok();
    }

    /**
     * 删除特色栏目
     * @param special
     * @return
     */
    @Override
    public XltcResult deleteSpecial(TbSpecial special) {
        int i = specialMapper.deleteByPrimaryKey(special.getSpecialId());
        if(i>0){
            //查询关联的主题
            List<String> topicIdList = specialTopicMapper.selectTopicIdBySpecialId(special.getSpecialId());
            if(!SqlConstructUtils.nullList(topicIdList)){
                //删除主题。以及删除其下的关联特色单元记录
                for (String topicId : topicIdList) {
                    //删除主题
                    specialTopicMapper.deleteByPrimaryKey(topicId);
                    //删除主题下的关联商品记录
                    TbSpecialConcatExample example = new TbSpecialConcatExample();
                    TbSpecialConcatExample.Criteria criteria = example.createCriteria();
                    criteria.andTopicIdEqualTo(topicId);
                    specialConcatMapper.deleteByExample(example);
                }
            }
        }
        return XltcResult.ok();
    }

    /**
     * 查询特色栏目 (支持条件)
     * @param special
     * @return
     */
    @Override
    public XltcResult findSpecialByConditions(TbSpecial special) {
        TbSpecialExample example = new TbSpecialExample();
        if(special != null) {
            TbSpecialExample.Criteria criteria = example.createCriteria();
            String specialId = special.getSpecialId();
            String specialName = special.getSpecialName();
            String enable = special.getEnable();
            String isHot = special.getIsHot();
            if(StringNotNull.check(specialId)){
                criteria.andSpecialIdEqualTo(specialId);
            }
            if(StringNotNull.check(specialName)){
                criteria.andSpecialNameLike("%"+specialName+"%");
            }
            if(StringNotNull.check(enable)){
                criteria.andEnableEqualTo(enable);
            }
            if(StringNotNull.check(isHot)){
                criteria.andIsHotEqualTo(isHot);
            }
        }
        example.setOrderByClause("special_sort asc");

        return null;
    }

    /**
     * 添加特色主题(目前主题默认关联到 special 1 下)
     * @param topic
     * @return
     */
    @Override
    public XltcResult addTopicForSpecial(TbSpecialTopic topic) {
        //校验排序重复性
        TbSpecialTopicExample sortExample = new TbSpecialTopicExample();
        TbSpecialTopicExample.Criteria criteria1 = sortExample.createCriteria();
        criteria1.andTopicSortEqualTo(topic.getTopicSort());
        List<TbSpecialTopic> tbSpecialTopics = specialTopicMapper.selectByExample(sortExample);
        if(tbSpecialTopics!=null && tbSpecialTopics.size()>0){
            return XltcResult.error("存在排序为 ["+topic.getTopicSort()+"] 的主题,请重新选择！");
        }
        if(!StringNotNull.check(topic.getSpecialId())){
            //查询默认的special
            PageHelper.startPage(1,1);
            TbSpecialExample example = new TbSpecialExample();
            TbSpecialExample.Criteria criteria = example.createCriteria();
            criteria.andEnableEqualTo("1");
            example.setOrderByClause("special_sort asc");
            List<TbSpecial> tbSpecials = specialMapper.selectByExample(example);
            if(!SqlConstructUtils.nullList(tbSpecials)) {
                topic.setSpecialId(tbSpecials.get(0).getSpecialId());
            }
            topic.setCreateTime(TimeFormat.nomalFormat.format(new Date()));
            topic.setEnable("1");
            topic.setTopicId(SqlConstructUtils.generatorStringUUID());
        }
        int insert = specialTopicMapper.insert(topic);
        if(insert>0)
            return XltcResult.ok();
        else
            return XltcResult.error("添加失败！");
    }

    /**
     * 删除主题，考虑级联
     * @param topic
     * @return
     */
    @Override
    public XltcResult deleteTopicCasecade(TbSpecialTopic topic) {
        //删除主题
        int i = specialTopicMapper.deleteByPrimaryKey(topic.getTopicId());
        //删除主题下的所有关联关系
        TbSpecialConcatExample example = new TbSpecialConcatExample();
        TbSpecialConcatExample.Criteria criteria = example.createCriteria();
        criteria.andTopicIdEqualTo(topic.getTopicId());
        specialConcatMapper.deleteByExample(example);
        return XltcResult.ok();
    }

    /**
     * 修改主题
     * @param topic
     * @return
     */
    @Override
    public XltcResult updateSpecialTopic(TbSpecialTopic topic) {
        if(StringNotNull.check(topic.getTopicSort())){
            //检测sort 格式
            SqlConstructUtils.checkNumberString(topic.getTopicSort(),"主题排序");
            //查询修改的排序是否冲突
            TbSpecialTopicExample example = new TbSpecialTopicExample();
            TbSpecialTopicExample.Criteria criteria = example.createCriteria();
            criteria.andTopicSortEqualTo(topic.getTopicSort());
            List<TbSpecialTopic> tbSpecialTopics = specialTopicMapper.selectByExample(example);
            if(!SqlConstructUtils.nullList(tbSpecialTopics)){
                if(tbSpecialTopics.size()>1){
                    return XltcResult.error("修改的分类排序存在冲突，请修改排序值！");
                }else if(tbSpecialTopics.size()==1 && !topic.getTopicId().equals(tbSpecialTopics.get(0).getTopicId())){
                    return XltcResult.error("修改的分类排序存在冲突，请修改排序值！");
                }else {

                }
            }
        }
        //修改
        topic.setUpdateTime(TimeFormat.nomalFormat.format(new Date()));
        int i = specialTopicMapper.updateByPrimaryKeySelective(topic);
        if(i>0)
            return XltcResult.ok();
        else
            return XltcResult.error("操作失败！");
    }

    /**
     * 查询主题（多条件）
     * @param parms
     * @return
     */
    @Override
    public XltcResult findSpecialTopic( Map<String,String> parms) {
        String topicId = parms.get("topicId");
        String enable = parms.get("enable");
        String topicName = parms.get("topicName");
        String specialId = parms.get("specialId");
        String page = parms.get("page");
        String size = parms.get("size");
        StringBuffer buffer = new StringBuffer(" ");
        if(StringNotNull.check(topicId)){
            buffer.append("and topic_id = '"+topicId+"' ");
        }
        if(StringNotNull.check(topicName)){
            buffer.append("and topic_name like '%"+topicName+"%' ");
        }
        if(StringNotNull.check(specialId)){
            buffer.append("and special_id = '"+specialId+"' ");
        }
        if(StringNotNull.check(enable)){
            buffer.append("and enable = '"+enable+"' ");
        }
        buffer.append(" order by topic_sort asc");
        if(page==null){
            //app请求，不分页
            List<TbSpecialTopic> topics = specialTopicMapper.findSpecialTopic(buffer.toString());
            List<TbSpecialTopic> collect = new ArrayList<>();
            if(!SqlConstructUtils.nullList(topics)){
                collect = topics.stream().sorted(new Comparator<TbSpecialTopic>() {
                    @Override
                    public int compare(TbSpecialTopic o1, TbSpecialTopic o2) {
                        return Integer.parseInt(o1.getTopicSort()) - Integer.parseInt(o1.getTopicSort());
                    }
                }).collect(Collectors.toList());
            }
            return XltcResult.ok(SqlConstructUtils.dealNullList(collect));
        }else {
            //web请求，
            int pageNum = StringNotNull.check(page)?Integer.parseInt(page):1;
            int sizeNum = StringNotNull.check(size)?Integer.parseInt(size):10;
            PageHelper.startPage(pageNum,sizeNum);
            List<TbSpecialTopic> topics = specialTopicMapper.findSpecialTopic(buffer.toString());
            PageInfo<TbSpecialTopic> info = new PageInfo<>(topics);
            List<TbSpecialTopic> plist = info.getList();
            if(!SqlConstructUtils.nullList(plist)) {
                List<TbSpecialTopic> collect = plist.stream().sorted(new Comparator<TbSpecialTopic>() {
                    @Override
                    public int compare(TbSpecialTopic o1, TbSpecialTopic o2) {
                        return Integer.parseInt(o1.getTopicSort()) - Integer.parseInt(o1.getTopicSort());
                    }
                }).collect(Collectors.toList());
                info.setList(collect);
            }
            return XltcResult.ok(info);
        }
    }

    /**
     * 查询主题下的特色单元（支持条件，分页）
     * @param parms
     * @return
     */
    @Override
    public XltcResult findUnitByCondition(Map<String, String> parms) {
        int page = StringNotNull.check(parms.get("page")) ? Integer.parseInt(parms.get("page")) : 1;
        int size =StringNotNull.check(parms.get("size")) ? Integer.parseInt(parms.get("size")) : 20;
        String topicId = parms.get("topicId");
        String enable = parms.get("enable");
        String unitTitle = parms.get("unitTitle");
        String createTimeStart = parms.get("createTimeStart");
        String createTimeEnd = parms.get("createTimeEnd");
        String topicName = parms.get("topicName");
        StringBuffer buffer = new StringBuffer(" ");
        if(StringNotNull.check(topicId)){
            buffer.append(" and a.topic_id = '"+topicId+"' ");
        }
        if(StringNotNull.check(enable)){
            buffer.append(" and b.enable = '"+enable+"' ");
        }
        if(StringNotNull.check(unitTitle)){
            buffer.append(" and b.unit_title like '%"+unitTitle+"%' ");
        }
        if(StringNotNull.check(createTimeStart)){
            buffer.append(" and b.create_time >= '"+createTimeStart+"' ");
        }
        if(StringNotNull.check(createTimeEnd)){
            buffer.append("and b.create_time <= '"+createTimeEnd+"' ");
        }
        if(StringNotNull.check(topicName)){
            buffer.append("and c.topic_name like  '%"+topicName+"%' ");
        }
        PageHelper.startPage(page,size);
        List<TbSpecialUnit> list = specialUnitMapper.findUnitByCondition(buffer.toString());
        PageInfo<TbSpecialUnit> info = new PageInfo<>(list);
        return XltcResult.ok(info);
    }

    /**
     * 添加特色单元
     * @param specialUnit
     * @return
     */
    @Override
    public XltcResult addSpecialUnit(TbSpecialUnit specialUnit) {
        //获取主题分类id
        String topicIds = specialUnit.getUnitId();
        //生成id
        String generatorUnitId = SqlConstructUtils.generatorStringUUID();
        specialUnit.setUnitId(generatorUnitId);
        specialUnit.setCreateTime(TimeFormat.nomalFormat.format(new Date()));
        specialUnit.setEnable("1");
        specialUnit.setUnitStored("0");
        specialUnit.setUnitLike("0");
//        specialUnit.setUnitOwnerName(StringNotNull.check(specialUnit.getUnitOwnerName()) ? specialUnit.getUnitOwnerName() : "喜乐同城");
//        specialUnit.setUnitOwnerNo(StringNotNull.check(specialUnit.getUnitOwnerNo()) ? specialUnit.getUnitOwnerNo() : "xltc");
        int insert = specialUnitMapper.insert(specialUnit);
        if(insert>0) {
            if(StringNotNull.check(topicIds)) {
                String[] split = topicIds.split(",");
                if (split != null && split.length > 0) {
                    TbSpecialConcat concat = new TbSpecialConcat();
                    for (String topicId : split) {
                        //关联主题和单元
                        concat.setConcatId(SqlConstructUtils.generatorStringUUID());
                        concat.setTopicId(topicId);
                        concat.setUnitId(generatorUnitId);
                        specialConcatMapper.insert(concat);
                    }
                }
            }
        }
        return XltcResult.ok();
    }

    /**
     * 修改特色单元
     * @param specialUnit
     * @return
     */
    @Override
    public XltcResult updateSpecialUnit(TbSpecialUnit specialUnit) {
        int i = specialUnitMapper.updateByPrimaryKeySelective(specialUnit);
        if(i>0)
            return XltcResult.ok();
        else {
            return XltcResult.error("操作失败，请联系管理员！");
        }
    }

    /**
     * 删除特色单元 （级联删除关联关系）
     * @param specialUnit
     * @return
     */
    @Override
    public XltcResult deleteSpecialUnit(TbSpecialUnit specialUnit) {
        //删除特色单元
        int i = specialUnitMapper.deleteByPrimaryKey(specialUnit.getUnitId());
        if(i>0){
            //删除关联关系
            TbSpecialConcatExample example = new TbSpecialConcatExample();
            TbSpecialConcatExample.Criteria criteria = example.createCriteria();
            criteria.andUnitIdEqualTo(specialUnit.getUnitId());
            int i1 = specialConcatMapper.deleteByExample(example);
            return XltcResult.ok();
        }else {
            return XltcResult.error("该特色单元不存在，请刷新页面重试！");
        }
    }

    /**
     * 查询某一特色单元（详情）
     * @param specialUnit
     * @return
     */
    @Override
    public XltcResult findSpecialUnitDetails(TbSpecialUnit specialUnit) {
        //查询特色单元
        TbSpecialUnit tbSpecialUnit = specialUnitMapper.selectByPrimaryKey(specialUnit.getUnitId());
        if(tbSpecialUnit!=null){
            tbSpecialUnit.setUnitOwnerNo(tbSpecialUnit.getUnitOwnerNo()==null?"":tbSpecialUnit.getUnitOwnerNo());
            tbSpecialUnit.setUnitOwnerPicture(tbSpecialUnit.getUnitOwnerPicture()==null?"":tbSpecialUnit.getUnitOwnerPicture());
            tbSpecialUnit.setUnitSubNotice(tbSpecialUnit.getUnitSubNotice()==null?"":tbSpecialUnit.getUnitSubNotice());
            tbSpecialUnit.setUnitOwnerName(tbSpecialUnit.getUnitOwnerName()==null?"":tbSpecialUnit.getUnitOwnerName());
            tbSpecialUnit.setUpdateTime(tbSpecialUnit.getUpdateTime()==null?"":tbSpecialUnit.getUpdateTime());
            Map<String,Object> resultMap = JSON.parseObject(JSON.toJSONString(tbSpecialUnit), Map.class);
            //查询他的关联主题
            List<Map<String,String>> casecadeTopics =  specialConcatMapper.selectUnitsCasecadeTopic(tbSpecialUnit.getUnitId());
            resultMap.put("casecadeTopics",SqlConstructUtils.dealNullList(casecadeTopics));
            return XltcResult.ok(resultMap);
        }else {
            return XltcResult.error("该特色单元不存在，请刷新页面重试！");
        }
    }

    /**
     * 关联特色单元 (如果已存在关联，则解除当前关联关系，重新关联！)
     * @param specialConcat
     * @return
     */
    @Override
    public XltcResult casecadeUnitToTopic(TbSpecialConcat specialConcat) {
        //查询当前特色单元是否存在关联关系(不管有无)
        TbSpecialConcatExample example = new TbSpecialConcatExample();
        TbSpecialConcatExample.Criteria criteria = example.createCriteria();
        criteria.andUnitIdEqualTo(specialConcat.getUnitId());
        specialConcatMapper.deleteByExample(example);
        specialConcat.setConcatId(SqlConstructUtils.generatorStringUUID());
        int insert = specialConcatMapper.insert(specialConcat);
        if(insert>0)
            return XltcResult.ok();
        else
            return XltcResult.error("关联失败,请刷新页面重试！");
    }

    /**
     * 查询所有的特色主题 (供添加完特色单元后---想要关联/修改关联) 使用
     * @param specialTopic
     * @return
     */
    @Override
    public XltcResult findSimpleSpecialTopic(TbSpecialTopic specialTopic) {
        TbSpecialTopicExample example = new TbSpecialTopicExample();
        if(StringNotNull.check(specialTopic.getEnable())){
            TbSpecialTopicExample.Criteria criteria = example.createCriteria();
            criteria.andEnableEqualTo(specialTopic.getEnable());
        }
        List<TbSpecialTopic> tbSpecialTopics = specialTopicMapper.selectByExample(example);
        return XltcResult.ok(SqlConstructUtils.dealNullList(tbSpecialTopics));
    }

    /**
     * 查询特色单元 for App 正序
     * @param parms
     * @return
     */
    @Override
    public XltcResult findUnitByConditionForApp(HashMap<String, String> parms) {
        int page = StringNotNull.check(parms.get("page")) ? Integer.parseInt(parms.get("page")) : 1;
        int size =StringNotNull.check(parms.get("size")) ? Integer.parseInt(parms.get("size")) : 20;
        String topicId = parms.get("topicId");
        String enable = parms.get("enable");
        String unitTitle = parms.get("unitTitle");
        String createTimeStart = parms.get("createTimeStart");
        String createTimeEnd = parms.get("createTimeEnd");
        String topicName = parms.get("topicName");
        StringBuffer buffer = new StringBuffer(" ");
        if(StringNotNull.check(topicId)){
            buffer.append(" and a.topic_id = '"+topicId+"' ");
        }
        if(StringNotNull.check(enable)){
            buffer.append(" and b.enable = '"+enable+"' ");
        }
        if(StringNotNull.check(unitTitle)){
            buffer.append(" and b.unit_title like '%"+unitTitle+"%' ");
        }
        if(StringNotNull.check(createTimeStart)){
            buffer.append(" and b.create_time >= '"+createTimeStart+"' ");
        }
        if(StringNotNull.check(createTimeEnd)){
            buffer.append("and b.create_time <= '"+createTimeEnd+"' ");
        }
        if(StringNotNull.check(topicName)){
            buffer.append("and c.topic_name like  '%"+topicName+"%' ");
        }
        PageHelper.startPage(page,size);
        List<TbSpecialUnit> list = specialUnitMapper.findUnitByConditionForApp(buffer.toString());
        PageInfo<TbSpecialUnit> info = new PageInfo<>(list);
        return XltcResult.ok(info);
    }

}
