package demo;

import com.qigan.qiganshop.util.notnull.StringNotNull;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-24 22:11
 */
public class testUtil {
    public static void main(String[] args) throws Exception{
        String URI= "http://a.huodong.mi.com/activity/live/submit/antiuid/0?&jsonpcallback=jQuery111303811700184059672_1559025167567&content=6666&code=fbh20190528&channel=pc&_=";
        for (int i = 0; i < 100; i++) {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            String l = System.currentTimeMillis()+"";
            HttpGet get = new HttpGet(URI +l);
            httpresponse = httpclient.execute(get);
            String response = EntityUtils.toString(httpresponse.getEntity());
            System.err.println(response);
        }

    }
}
