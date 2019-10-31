package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.constant.RedisConstant;
import com.qigan.qiganshop.mapper.HomepageCateMapper;
import com.qigan.qiganshop.pojo.CommonPage;
import com.qigan.qiganshop.pojo.Goods;
import com.qigan.qiganshop.pojo.HomepageCate;
import com.qigan.qiganshop.service.GoodsService;
import com.qigan.qiganshop.service.HomePageCateGoodService;
import com.qigan.qiganshop.service.HomePageCateService;
import com.qigan.qiganshop.util.access.JedisUtil;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.picture.PicUtil;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author wanghao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HomePageCateServiceImpl implements HomePageCateService {
    @Autowired
    private HomepageCateMapper mapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private HomePageCateGoodService cateGoodService;
    @Autowired
    private JsonResult jr;
    @Autowired
    private PicUtil picUtil;
    @Autowired
    private HomePageCateGoodService homePageCateGoodService;

    @Autowired
    JedisUtil jedis;
    /**
     * 返回全部列表
     *
     * @return
     */
    @Override
    public List<HomepageCate> findAll() {
        return (List<HomepageCate>) NotNull.checkListNull(mapper.findPage(null, null, null));
    }


    /**
     * 增加
     *
     * @param cate
     */
    @Override
    public Integer add(HomepageCate cate) {

        String cateId = cate.getHomepagecateId();
        if (!StringNotNull.check(cateId)) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            cate.setHomepagecateId(uuid);
            cate.setEnable("1");
        }
        int i = mapper.insert(cate);
        jedis.del(RedisConstant.HOME_CATE.getBytes());
        return i;
    }

    /**
     * 修改
     *
     * @param cate
     */
    @Override
    public Integer update(HomepageCate cate) {
        if (cate.getHomepagecateId() == null || "".equals(cate.getHomepagecateId())) {
            return 0;
        }
        int i = mapper.update(cate);
        jedis.del(RedisConstant.HOME_CATE.getBytes());
        return i;
    }

    /**
     * 根据ID获取实体
     *
     * @param cateId
     * @return
     */
    @Override
    public HomepageCate findOne(String cateId) {
        HomepageCate cate = mapper.findOne(cateId);
        if (cate != null) {
            cate.setPicUrl(picUtil.addOneUrlHead(cate.getPicUrl()));
        }
        return cate;
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
            num += mapper.delete(id);
        }
        jedis.del(RedisConstant.HOME_CATE.getBytes());
        return num;
    }

    /**
     * 按照条件进行分页查询
     *
     * @param cate
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @return
     */

    @Override
    public PageResult findPage(HomepageCate cate, Integer pageNum, Integer pageSize) {
        List<HomepageCate> search = new ArrayList<>();
        if (pageNum != null && pageSize != null) {
            search = mapper.findPage(cate, (pageNum - 1) * pageSize, pageSize);
        } else {
            search = mapper.findPage(cate, null, null);
        }
        for (HomepageCate homepageCate : search) {
            if (StringNotNull.check(homepageCate.getPicUrl())) {
                homepageCate.setPicUrl(picUtil.addOneUrlHead(homepageCate.getPicUrl()));
            }
        }
        return new PageResult(mapper.findPage(cate, null, null).size(),
                (List<HomepageCate>) NotNull.checkListNull(search));
    }

    /**
     * 更改排序
     *
     * @param cateId1
     * @param cateId2
     * @return
     */
    @Override
    public Integer updateSort(String cateId1, String cateId2) {
        int count = 0;
        HomepageCate one = this.findOne(cateId1);
        HomepageCate two = this.findOne(cateId2);

        if (one == null || two == null) {
            return -1;
        }
        Integer sort1 = one.getSort();
        Integer sort2 = two.getSort();
        two.setSort(sort1);
        one.setSort(sort2);
        count += this.update(one);
        count += this.update(two);

        return count;
    }

    /**
     * 首页横向滑动
     *
     * @param coordinate
     * @return
     */
    @Override
    public JsonResult findHomePageCenter(String coordinate) {
        /**
         * 查询会员商品
         */
        ArrayList<HomepageCate> result = new ArrayList<>();
        HomepageCate cate = new HomepageCate();
        cate.setLocation("center");
        cate.setEnable("1");
        List<HomepageCate> cateList = this.findPage(cate, null, null).getRows();
        for (HomepageCate cate1 : cateList) {
            List<Goods> goodsList = homePageCateGoodService.findGoodsByCateId(cate1.getHomepagecateId(), null, new CommonPage());
            cate1.setGoodsList(goodsList);
            result.add(cate1);
        }
        System.err.println(result.size());

        return jr.jsonResultData(ResultCode.SUCCESS.res_code(), ResultCode.SUCCESS.message(), result);
    }

    /**
     * 分类关联商品
     *
     * @param one
     * @param goodsIds
     * @param flag
     * @return
     */
    @Override
    public Integer unionGoods(HomepageCate one, String[] goodsIds, String flag) {
        int count = 0;
        if ("add".equals(flag)) {
            count = cateGoodService.add(one.getHomepagecateId(), goodsIds);
        } else if ("del".equals(flag)) {
            count = cateGoodService.delete(one.getHomepagecateId(), goodsIds);
    }
        return count;
    }

}
