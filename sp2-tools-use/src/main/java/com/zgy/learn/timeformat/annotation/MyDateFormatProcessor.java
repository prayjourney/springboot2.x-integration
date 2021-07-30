package com.zgy.learn.timeformat.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zgy
 * @date 2021/7/30
 * @description 获取注解, 然后去解析, 对于对象的信息按照我们注解之中定义的方式去修改即可
 */
public class MyDateFormatProcessor {
    /**
     * 获取注解的情况
     *
     * @param clazz
     */
    public static Map<String, Field> getAnnotation(Class<?> clazz) {
        Map<String, Field> map = new HashMap<>();
        // 从类上面获取注解
        MyDateFormat annotation = clazz.getAnnotation(MyDateFormat.class);

        // 从字段上面获取注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            // 如果这个字段包含了注解
            if (f.isAnnotationPresent(MyDateFormat.class)) {
                System.out.println("已经取得注解");
                map.put(f.getName(), f);
            }
        }
        return map;
    }
}
