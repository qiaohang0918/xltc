package demo;

import com.qigan.qiganshop.util.guanyierp.SendGet;
import lombok.val;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.BufferedWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@SuppressWarnings("all")

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-14 16:35
 */
public class HelloJob implements Job {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //System.err.println("hahaha");
        // String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.err.println(orderId);
        System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }

   /* public static void main(String[] args) {
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet("http://ip.taobao.com/service/getIpInfo.php?ip=58.59.18.101");
                httpresponse = httpclient.execute(httpGet);
                String response = EntityUtils.toString(httpresponse.getEntity());
                System.err.println(response);
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
}*/

 /*   public static void main(String[] args) throws Exception {
        int i = 1;
        while (true) {
            val source = new File("/Volumes/emmm 1/1.iso");
        val dest = new File("/Volumes/emmm 1/" + (++i) + ".iso");
        FileUtils.copyFile(source, dest);
    }

    }*/
}
