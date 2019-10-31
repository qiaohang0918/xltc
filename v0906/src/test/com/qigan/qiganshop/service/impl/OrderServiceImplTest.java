package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.util.es.EsUtils;
import com.qigan.qiganshop.util.sms.SmsUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-10 14:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class OrderServiceImplTest {
    @Autowired
    private EsUtils client;
    @Autowired
    private SmsUtil smsUtil;

    /**
     * "goodsId": {
     * "type": "keyword"
     * },
     * "goodsName": {
     * "type": "text"
     * },
     * "goodsNote": {
     * "type": "text"
     * },
     * "goodSimpleName": {
     * "type": "text"
     * },
     * "skuName": {
     * "type": "text"
     * },
     * "createTime": {
     * "type": "date",
     * "format": "strict_date_optional_time || epoch_millis"
     */

    @Test
    public void findorderItems() {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
            builder.field("goodsId", "abc");
            builder.field("goodsName", "abc");
            builder.field("goodsNote", "abc");
            builder.field("goodSimpleName", "abc");
            builder.field("skuName", "abc");
            builder.field("createTime", new Date());
            builder.endObject();


            this.client.getClient().prepareIndex("goods", "goods")
                    .setSource(builder).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void delete() {
        String aa = "SMS_169113401";
        String signName = "喜乐同城电子";

        try {
            String haha = smsUtil.sendDeliveryMessage("15628892281", "1234", "haha", "18888888888", signName, aa);
            System.err.println(haha);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void search() {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            System.err.println("第" + (i + 1) + "次:");
            for (int i1 = 0; i1 < 6; i1++) {
                int nextInt = random.nextInt(100);
                if (nextInt % 2 == 0) {
                    System.err.print("正");
                } else {
                    System.err.print("反");
                }
            }
            System.err.println();
        }
    }


}

