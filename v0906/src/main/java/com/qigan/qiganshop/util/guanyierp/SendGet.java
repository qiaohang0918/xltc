package com.qigan.qiganshop.util.guanyierp;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 发送 post 请求
 *
 * @author wanghao
 * @time 2019-04-10 11:12
 */
@Service
public class SendGet {
    private static final Logger logger = LoggerFactory.getLogger(SendGet.class);

    public String sendGet(String url) {
        logger.info("url:   " + url);
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                httpresponse = httpclient.execute(httpGet);
                String response = EntityUtils.toString(httpresponse.getEntity());
                logger.info("response:   " + response);
                return response;
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
