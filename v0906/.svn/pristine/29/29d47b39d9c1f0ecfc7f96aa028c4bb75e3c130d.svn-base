package com.qigan.qiganshop.util.charUtil;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-07-12 15:50
 */
@Component
public class CharUtil {

    /*判断是不是汉字*/
    public boolean isChinese(String con) {

        for (int i = 0; i < con.length(); i = i + 1) {
            if (!compile("[\u4e00-\u9fa5]").matcher(
                    String.valueOf(con.charAt(i))).find()) {
                return false;
            }
        }

        return true;
    }
/*
##判断是不是中文或英文字母
*/

    public boolean conValidate(String con) {
        return null != con && !"".equals(con) && (this.isChinese(con) || con.matches("^[A-Za-z]+$")) && (con.length() <= 10);
    }
/*
##判断字符串是不是数字
*/

    public boolean isNumeric(String str) {
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


}
