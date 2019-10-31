package com.qigan.qiganshop.util.notnull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class NotNull {


    public static Object checkListNull(List<?> list) {
        List<Object> lists = new ArrayList<Object>();
        if (list != null) {
            for (Object obj : list) {
                Object o = checkNull(obj);
                lists.add(o);
            }
        }
        return lists;
    }

    /**
     * 把vo类中String类型的参数的null值或"NULL","null"转化成空字符串
     *
     * @param obj
     * @return
     */
    public static Object checkNull(Object obj) {
        try {
            if (obj == null) {
                return new Object();
            }
            Class<? extends Object> classz = obj.getClass();
            Field[] fields = classz.getDeclaredFields();
            for (Field field : fields) {
                if (String.class == field.getType()) {
                    // 属性参数值首字母转成大写
                    char[] cs = (field.getName()).toCharArray();
                    cs[0] = Character.toUpperCase(cs[0]);
                    String methodGetName = "get" + String.valueOf(cs);
                    String methodSetName = "set" + String.valueOf(cs);
                    Method getMethod = classz.getMethod(methodGetName, null);
                    Method setMethod = classz.getMethod(methodSetName, String.class);
                    Object value = getMethod.invoke(obj, null);
                    if (null == value || "NULL".equals((String) value) || "null".equals((String) value)) {

                        setMethod.invoke(obj, "");

                    }
                } else if (Integer.class == field.getType()) {
                    // 属性参数值首字母转成大写
                    char[] cs = (field.getName()).toCharArray();
                    cs[0] = Character.toUpperCase(cs[0]);
                    String methodGetName = "get" + String.valueOf(cs);
                    String methodSetName = "set" + String.valueOf(cs);
                    Method getMethod = classz.getMethod(methodGetName, null);
                    Method setMethod = classz.getMethod(methodSetName, Integer.class);
                    Object value = getMethod.invoke(obj, null);
                    if (null == value || "NULL".equals((Integer) value) || "null".equals((Integer) value)) {

                        setMethod.invoke(obj, 0);

                    }
                } else if (Long.class == field.getType()) {
                    // 属性参数值首字母转成大写
                    char[] cs = (field.getName()).toCharArray();
                    cs[0] = Character.toUpperCase(cs[0]);
                    String methodGetName = "get" + String.valueOf(cs);
                    String methodSetName = "set" + String.valueOf(cs);
                    Method getMethod = classz.getMethod(methodGetName, null);
                    Method setMethod = classz.getMethod(methodSetName, Long.class);
                    Object value = getMethod.invoke(obj, null);
                    if (null == value || "NULL".equals((Long) value) || "null".equals((Long) value)) {

                        setMethod.invoke(obj, 0L);

                    }
                } else if (Double.class == field.getType()) {
                    // 属性参数值首字母转成大写
                    char[] cs = (field.getName()).toCharArray();
                    cs[0] = Character.toUpperCase(cs[0]);
                    String methodGetName = "get" + String.valueOf(cs);
                    String methodSetName = "set" + String.valueOf(cs);
                    Method getMethod = classz.getMethod(methodGetName, null);
                    Method setMethod = classz.getMethod(methodSetName, Double.class);
                    Object value = getMethod.invoke(obj, null);
                    if (null == value || "NULL".equals((Double) value) || "null".equals((Double) value)) {

                        setMethod.invoke(obj, 0.00);

                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 把Map中String类型的参数的null值转化成空字符串
     *
     * @param map
     * @return
     */
    public static Map<String, Object> mapNullToString(Map<String, Object> map) {
        Set<String> set = map.keySet();
        if (set != null && !set.isEmpty()) {
            for (String key : set) {
                if (map.get(key) == null) {
                    map.put(key, "");
                }
            }
        }
        return map;
    }


    /**
     * 把ListMap中的String类型的参数的null值转化成空字符串
     *
     * @param
     * @return
     */
    public static List<Map<String, Object>> maplistNullToString(List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            Set<String> set = map.keySet();
            if (set != null && !set.isEmpty()) {
                for (String key : set) {
                    if (map.get(key) == null) {
                        map.put(key, "");
                    }
                }
            }
        }
        return list;
    }

    /**
     * 都不为空 返回 true
     * 任意一个为空,返回 false
     *
     * @param strings
     * @return
     */
    public static Boolean checkString(String... strings) {
        boolean b = true;
        for (String s : strings) {
            if (s != null && !"".equals(s)) {
                b &= true;
            } else {
                return false;
            }
        }

        return b;
    }

    public static Boolean checkObject(Object... objs) {
        boolean b = true;
        for (Object obj : objs) {
            if (obj != null && !"".equals(obj.toString())) {
                b &= true;
            } else {
                return false;
            }
        }

        return b;
    }
}

















