package com.qigan.qiganshop.util.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @JmsListener(destination = "sms")
    public void sendSms(Map<String, String> map) {

        try {
           /* String response = smsUtil.sendMessage ( baidumap.get ( "phoneNum" ),
                    baidumap.get ( "checkCode" ),
                    baidumap.get ( "signName" ),
                    baidumap.get ( "templateParam" ) );
            System.out.println ( "code:" + response );*/

            System.err.println(map);
            System.err.println(map.get("phoneNum") + map.get("param") +
                    new String(map.get("sign_name").getBytes(), "UTF-8") +
                    map.get("templateParam"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
