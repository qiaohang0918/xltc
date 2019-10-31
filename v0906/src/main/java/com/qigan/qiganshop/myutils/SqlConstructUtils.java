package com.qigan.qiganshop.myutils;

import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.util.result.XltcResult;

import java.util.*;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/11 8:18
 * @Modified By:
 */
public class SqlConstructUtils {

    /**
     * list to sql
     * @param list
     * @return
     */
    public static String constructListToStringsOnIn(List<String> list ,Set<String> set){
        StringBuffer buffer = new StringBuffer(" ");
        if(list!=null  && list.size()>0){
            for (String o : list) {
                buffer.append("'"+o.trim()+"',");
            }
        }else if(set != null && set.size()>0){
            for (String o : set) {
                buffer.append("'"+o.trim()+"',");
            }
        }
        String replace = buffer.replace(buffer.length() - 1, buffer.length(), "").toString();
        return  replace==null || "".equals(replace.trim()) ? null : replace;
    }

    /**
     * strings to  sql
     * @param someS
     * @param seperator
     * @return
     */
    public static String constructStringToStringsOnIn(String someS,String seperator){
        if(someS==null || "".equals(someS.trim()))
            return null;
        //默认分隔符
        String realSeperator = ",";
        if(seperator!=null && !"".equals(seperator.trim()))
            realSeperator=seperator;
        String[] split = someS.split(realSeperator);

        StringBuffer buffer = new StringBuffer(" ");
        for (String someOne : split) {
            buffer.append("'"+someOne.trim()+"',");
        }
        String replace = buffer.replace(buffer.length() - 1, buffer.length(), "").toString();
        return  replace==null || "".equals(replace.trim()) ? null : replace;
    }

    /**
     * 检查空集合
     * @param list
     * @return
     */
    public static boolean nullList(List list){
        if(list==null || list.size()<=0)
            return true;
        return false;
    }

    /**
     * 处理null集合
     * @param list
     * @return
     */
    public static List<?> dealNullList(List<?> list ){
        if(SqlConstructUtils.nullList(list))
            return new ArrayList<>();
        else
            return list;
    }

    /**
     * 检测number类型string
     * @param key
     * @param tipKeyWord
     * @return
     */
    public static void checkNumberString(String key,String tipKeyWord){
        try {
            Integer.parseInt(key);
        }catch (Exception e){
            throw XltcRuntimeException.throwable(tipKeyWord + " 格式错误，必须为数字格式 (且不支持小数点)！" );
        }
    }

    /**
     * 检测double类型string
     * @param key
     * @param tipKeyWord
     * @return
     */
    public static void checkDoubleString(String key,String tipKeyWord){
        try {
            Double.parseDouble(key);
        }catch (Exception e){
            throw XltcRuntimeException.throwable(tipKeyWord + " 格式错误，必须为数字格式 (且不支持小数点)！" );
        }
    }

    /**
     * 获取uuid
     * @return
     */
    public static String generatorStringUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
