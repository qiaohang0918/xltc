package com.qigan.qiganshop.util.es;

import com.qigan.qiganshop.util.IPUtil.IPUtil;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 用来创建关闭es客户端
 *
 * @author wanghao
 */
@Component
public class EsUtils {
    /**
     * 获取ElasticSearch
     */
    @Value("${ES.host}")
    String ESHost ;
    public TransportClient getClient() {

        TransportAddress master = null;
        Settings settings = Settings.builder().put("cluster.name", "xiletongcheng").put("client.transport.sniff", true).build();
        TransportClient client = new PreBuiltTransportClient(settings);
        try {
            client.addTransportAddress(
                    new InetSocketTransportAddress(
                            InetAddress.getByName(ESHost),
                            9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * 用于关闭elasticSearch
     */
    public void closeClient(Client client) {
        if (null != client) {
            try {
                client.close();
            } catch (Exception e) {
                throw new RuntimeException("连接关闭失败");
            }
        }
    }

}