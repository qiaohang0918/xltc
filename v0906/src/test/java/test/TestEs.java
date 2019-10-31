/**
 * 
 */
package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * @author wyy
 *
 */
public class TestEs {

	public static void main(String[] args) throws Exception {
		 Settings settings = Settings.builder()                //指定集群名称
	                .put("cluster.name", "xiletongcheng")
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
	        builder.field("goodsId", 1);
            builder.field("goodsName", "test");
            builder.field("goodsNote", "1");
            builder.field("goodSimpleName", "testGoods");
            //builder.field("skuName", item.getName());
	        builder.field("created",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	        builder.endObject();
	        //在这里创建我们要索引的对象
	        IndexResponse response = client.prepareIndex("goods", "goods")
	                .setSource(builder)
	                .execute()
	                .actionGet();
	        //多次index这个版本号会变
	        System.out.println("response.version():" + response.getResult().toString());
	        client.close();
	}
	
	
}
