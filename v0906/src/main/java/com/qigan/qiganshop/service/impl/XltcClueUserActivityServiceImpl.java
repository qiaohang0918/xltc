package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.mapper.TbClueActivityMapper;
import com.qigan.qiganshop.myutils.SqlConstructUtils;
import com.qigan.qiganshop.pojo.TbClueActivity;
import com.qigan.qiganshop.pojo.TbClueActivityExample;
import com.qigan.qiganshop.service.XltcClueUserActivityService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/22 17:50
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XltcClueUserActivityServiceImpl  implements XltcClueUserActivityService {

    @Autowired
    TbClueActivityMapper clueActivityMapper;
    @Autowired
    JedisUtil jedisUtil;

    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 插入线索用户奖励活动
     * @param clueActivity
     * @return
     */
    @Override
    public XltcResult insertClueActivity(TbClueActivity clueActivity) {
        clueActivity.setClueActivityId(UUID.randomUUID().toString().replace("-",""));
        clueActivity.setCreateTime(dateFormat.format(new Date()));
        if(StringNotNull.check(clueActivity.getPictureUrl())) {
            String[] split = clueActivity.getPictureUrl().split(",");
            if(split.length<3){
                return XltcResult.error("活动图需要三张,顺序：[入口主图,分享模板图,入口小图]");
            }
        }
        clueActivity.setEnable("0");
        int result = clueActivityMapper.insert(clueActivity);
        if(result>0)
            return XltcResult.ok();
        else
            return XltcResult.error();

    }

    /**
     * 修改线索用户奖励活动是否启用
     *
     * @param pictureUrl
     * @param status
     * @param clueActivityId
     * @param money
     * @return
     */
    @Override
    public XltcResult updateClueActivityStatus(String pictureUrl, String status, String clueActivityId, String money) {
        //修改活动状态
        TbClueActivity clueActivity = this.selectByIds(clueActivityId);
        if(clueActivity==null)
            return XltcResult.error("活动项不存在！");
        clueActivity.setEnable(status);
        clueActivity.setUpdateTime(dateFormat.format(new Date()));
        if(StringNotNull.check(pictureUrl)) {
            String[] split = pictureUrl.split(",");
            if(split.length<3){
                return XltcResult.error("活动图需要三张,顺序：[入口主图,分享模板图,入口小图]");
            }
            clueActivity.setPictureUrl(pictureUrl);
        }
        if(StringNotNull.check(money))
            clueActivity.setMoney(money);
        //判断具体的状态值，启用/禁用 CLUE_USER_AMOUNT
        if("0".equalsIgnoreCase(status)){
            //禁用，删除
            jedisUtil.del(RedisConstant.CLUE_USER_AMOUNT);
        }else if("1".equalsIgnoreCase(status)){
            //启用,添加
            jedisUtil.set(RedisConstant.CLUE_USER_AMOUNT,clueActivity.getMoney());
            //设置所有活动状态
            Integer i = clueActivityMapper.setAllActivityStatus("0");
        }
        //修改当前操作的activity的咋黄台
        int result = clueActivityMapper.updateByPrimaryKeySelective(clueActivity);
        if(result>0) {
            return XltcResult.ok();
        }
        else
            return XltcResult.error("操作失败！");
    }

    /**
     * 根据状态查询线索金额活动
     * @param status
     * @return
     */
    @Override
    public XltcResult findClueActivityNyConditions(String status) {
        List<TbClueActivity> activities = null;
        if(!StringNotNull.check(status)){
            activities = clueActivityMapper.selectByExample(null);
        }else {
            TbClueActivityExample example = new TbClueActivityExample();
            TbClueActivityExample.Criteria criteria = example.createCriteria();
            criteria.andEnableEqualTo(status);
            activities= clueActivityMapper.selectByExample(example);
        }
        if(!SqlConstructUtils.nullList(activities)){
            List<TbClueActivity> collect = activities.stream().map(x -> {
                String pictureUrl = x.getPictureUrl();
                if (StringNotNull.check(pictureUrl)) {
                    String[] split = pictureUrl.split(",");
                    for (int i = 0; i < split.length; i++) {
                        if (i == 0) {
                            //主图
                            x.setPictureUrl(split[i]);
                        } else if (i == 1) {
                            //分享模板图
                            x.setTemplatePicture(split[i]);
                        } else if (i == 2) {
                            //主图(小图)
                            x.setSmallPicture(split[i]);
                        }
                    }
                }
                return x;
            }).collect(Collectors.toList());
            return XltcResult.ok(collect);
        }else {
            return XltcResult.ok(activities);
        }
    }

    @Override
    public XltcResult findClueActivityNyConditionsManager(String status) {
        List<TbClueActivity> activities = null;
        if(!StringNotNull.check(status)){
            activities = clueActivityMapper.selectByExample(null);
        }else {
            TbClueActivityExample example = new TbClueActivityExample();
            TbClueActivityExample.Criteria criteria = example.createCriteria();
            criteria.andEnableEqualTo(status);
            activities= clueActivityMapper.selectByExample(example);
        }
        if(!SqlConstructUtils.nullList(activities)){
            List<TbClueActivity> collect = activities.stream().map(x -> {
                String pictureUrl = x.getPictureUrl();
                if (StringNotNull.check(pictureUrl)) {
                    String[] split = pictureUrl.split(",");
                    for (int i = 0; i < split.length; i++) {
                        if (i == 0) {
                            //主图
                            x.setPictureUrl(split[i]);
                        } else if (i == 1) {
                            //分享模板图
                            x.setTemplatePicture(split[i]);
                        } else if (i == 2) {
                            //主图(小图)
                            x.setSmallPicture(split[i]);
                        }
                    }
                }
                return x;
            }).collect(Collectors.toList());
            return XltcResult.ok(collect);
        }else {
            return XltcResult.ok(activities);
        }
    }


    @Override
    public TbClueActivity selectByIds(String clueActivityId){
        return clueActivityMapper.selectByPrimaryKey(clueActivityId);
    }


}
