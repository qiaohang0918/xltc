package com.qigan.qiganshop.util.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qigan.qiganshop.util.guanyierp.SendGet;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-06-17 19:32
 */
@Aspect
public class LoggerAspect {
    @Autowired
    private SendGet get;
    /**
     * 开始时间
     */
    private long startTimeMillis = 0;
    /**
     * 结束时间
     */
    private long endTimeMillis = 0;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.qigan.qiganshop.controller..*.*(..)) && (@annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis();
    }

    @After("execution(* com.qigan.qiganshop.controller..*.*(..)) && (@annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis();
    }

    @Around("execution(* com.qigan.qiganshop.controller..*.*(..)) && (@annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return this.printOptLog(pjp, request);
    }

    private Object printOptLog(ProceedingJoinPoint proceedingJoinPoint, HttpServletRequest request) throws Throwable {
        StringBuilder log = new StringBuilder();
        log.append("\n+******************************************************************************************+\n").append(
                "                                         AOP日志输出                                          \n").append(
                "请求时间：" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(endTimeMillis) + "\n").append(
                "响应时间：" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(endTimeMillis) + "\n").append(
                "耗时：" + (endTimeMillis - startTimeMillis) + " ms\n").append(
                "请求IP地址：" + this.getAddress(this.getIpAdrress(request)) + "\n").append(
                "请求方式：" + request.getMethod() + "\n").append(
                "请求方法：" + request.getRequestURI() + "\n").append(
                "传入参数：{");
        request.getParameterMap().entrySet().forEach(entry -> {
            log.append(entry.getKey()).append(":");
            Arrays.stream(entry.getValue()).forEach(s -> log.append(s).append(","));
        });
        log.append("json:");
        String requestURI = request.getRequestURI();
        try {
            if (!"/qiganshop/upload/uploads.do".equals(requestURI) ||
                    !"/qiganshop/app/user/updateImage.do".equals(requestURI)) {

                log.append(JSON.toJSONString(proceedingJoinPoint.getArgs()));
            }
        } catch (Exception e) {
            log.append("null");
        }
        log.append("}");
        Object result = proceedingJoinPoint.proceed();
        log.append("\n").append("响应参数：")
                .append(JSON.toJSONString(result))
                .append("\n+******************************************************************************************+\n");
        logger.info(log.toString());
        return result;
    }

    public String getAddress(String ip) {
        String path = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        String inputline = "";
        String info = "";
        try {
            URL url;
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10 * 1000);
            conn.setRequestMethod("GET");
            InputStreamReader inStream = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader buffer = new BufferedReader(inStream);
            while ((inputline = buffer.readLine()) != null) {
                info += inputline;
            }
        } catch (MalformedURLException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
        String result = "";
        if (!StringUtils.isBlank(info)) {
            JSONObject jsonob = JSONObject.parseObject((JSONObject.parseObject(info).getString("data")));

            String country = StringEscapeUtils.escapeSql(jsonob.getString("country"));
            String city = StringEscapeUtils.escapeSql(jsonob.getString("city"));
            String isp = StringEscapeUtils.escapeSql(jsonob.getString("isp"));
            String region = StringEscapeUtils.escapeSql(jsonob.getString("region"));

            result = isp + ":" + ip + " " + country + " " + region + " " + city;
        }

        return result;
    }

    private String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if (index != -1) {
                return XFor.substring(0, index);
            } else {
                return XFor;
            }
        }
        XFor = Xip;
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

}
