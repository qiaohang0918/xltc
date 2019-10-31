package demo;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-13 21:45
 */
public class es {
    public static void main(String[] args) throws Exception{

/**
 * 向ES添加索引对象
 * @author donlian
 */
        Settings settings = Settings.builder()                //指定集群名称
                .put("cluster.name", "carryless-es")
                //探测集群中机器状态
                .put("client.transport.sniff", false).build();
        /*
         * 创建客户端，所有的操作都由客户端开始，这个就好像是JDBC的Connection对象
         * 用完记得要关闭
         */
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),
                        9300));
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        builder.field("name", "哈哈");
        builder.field("age", 12);
        builder.field("title","测试");
        builder.field("created",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        builder.endObject();
        //在这里创建我们要索引的对象
        IndexResponse response = client.prepareIndex("testgoods", "tetgoods")

                .setSource(builder)
                .execute()
                .actionGet();
        //多次index这个版本号会变
        System.out.println("response.version():" + response.getResult().toString());
        client.close();
    }

}
