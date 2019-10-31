package com.qigan.qiganshop.util.IPUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取公网IP
 *
 * @author wanghao
 * @time 2019-06-16 05:06
 */
public class IPUtil {
    public String getIP(){
        try {
            URL url = null;
            URLConnection urlconn = null;
            BufferedReader br = null;
            url = new URL("http://2017.ip138.com/ic.asp");//爬取的网站是百度搜索ip时排名第一的那个
            urlconn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(
                    urlconn.getInputStream()));
            String buf = null;
            String get= null;
            while ((buf = br.readLine()) != null) {
                get+=buf;
            }
            int where,end;
            for(where=0;where<get.length()&&get.charAt(where)!='[';where++) {
                ;
            }
            for(end=where;end<get.length()&&get.charAt(end)!=']';end++) {
                ;
            }
            get=get.substring(where+1,end);
            return get;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
