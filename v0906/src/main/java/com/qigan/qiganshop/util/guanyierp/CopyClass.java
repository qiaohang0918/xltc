package com.qigan.qiganshop.util.guanyierp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 通过反射进行对象属性的复制
 *
 * @author wanghao
 * @time 2019-04-11 00:12
 */
public class CopyClass {
    public static Object copy(Object obj, Object target) {

        try {

            Class<? extends Object> classz = obj.getClass();
            Class<? extends Object> clazz = target.getClass();
            Field[] fields = classz.getDeclaredFields();
            for (Field field : fields) {

                // 属性参数值首字母转成大写
                char[] cs = (field.getName()).toCharArray();
                cs[0] -= 32;
                String methodGetName = null;
                if (Boolean.class == field.getType() || boolean.class == field.getType()) {
                    methodGetName = "is" + String.valueOf(cs);
                } else if (List.class != field.getType()) {
                    methodGetName = "get" + String.valueOf(cs);

                } else {
                    continue;
                }
                //String methodSetName = "set" + String.valueOf(cs);
                String[] s = String.valueOf(cs).split("_");
                StringBuffer buffer = new StringBuffer();
                buffer.append("set");
                buffer.append(s[0]);
                for (int i = 1; i < s.length; i++) {
                    char[] chars = s[i].toCharArray();
                    chars[0] -= 32;
                    buffer.append(String.valueOf(chars));
                }
                String methodSetName = buffer.toString();
                Method getMethod = classz.getMethod(methodGetName, null);
                Method setMethod = clazz.getMethod(methodSetName, field.getType());
                Object value = getMethod.invoke(obj, null);
                if ("setId".equals(methodSetName)) {
                    continue;
                }
                setMethod.invoke(target, value);
                System.err.println(field.getName() + ":" + value);
                System.err.println(methodSetName);

            }
            return target;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }
}
