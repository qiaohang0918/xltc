package com.qigan.qiganshop.util.guanyierp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

/**
 * 获取签名
 *
 * @author wanghao
 * @time 2019-04-10 10:35
 */
@Service
public class GetSign {
    private static final Logger logger = LoggerFactory.getLogger(GetSign.class);

    // 得到sign的字符串

    public String sign(String str, String secret) {
        logger.info("source:   " + str);
        logger.info("secret:   " + secret);
        StringBuilder enValue = new StringBuilder();
        enValue.append(secret);
        enValue.append(str.toString());
        enValue.append(secret);
        logger.info("append   secret: " + enValue.toString());
        logger.info("sign:   " + encryptByMD5(enValue.toString()));
        return encryptByMD5(enValue.toString());
    }

    // MD5

    private String encryptByMD5(String data) {
        StringBuilder sign = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(data.getBytes("UTF-8"));
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(bytes[i] & 0xFF);
                if (hex.length() == 1) {
                    sign.append("0");
                }
                sign.append(hex.toUpperCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign.toString();
    }
}
