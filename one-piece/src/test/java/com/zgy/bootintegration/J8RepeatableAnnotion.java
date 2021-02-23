package com.zgy.bootintegration;

import lombok.Builder;
import lombok.Data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 自定义的注解， 要是可重复的, 那就要用@Repeatable注解修饰，然后在其中指定注解的容器类
@Repeatable(MyAnnotationCollect.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    // 注解之中好像必须有value
    String value() default "woshi测试的一个";
}


// 想要是可重复的，那就需要制定一个注解的容器, 这个就是MyAnnotation的容器
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationCollect {
    // 返回的是一个数组
    MyAnnotation[] value();
}

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/6/21 11:55
 * @modified:
 */
public class J8RepeatableAnnotion {
    public static void main(String[] args) throws NoSuchFieldException {
        getPersonNames(PersonInfo.builder().build());

    }

    // 解析该对象的信息， 这个其实就是注解的解析器
    public static void getPersonNames(PersonInfo p) throws NoSuchFieldException {
        Class<PersonInfo> pClass = PersonInfo.class;
        Method[] methods = pClass.getMethods();
        Constructor[] constructors = pClass.getConstructors();
        Field field = pClass.getDeclaredField("name");
        MyAnnotation[] fieldAnnotationsByType = field.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation ma : fieldAnnotationsByType) {
            System.out.println(ma.value());
        }

        // 这个还是空的
        System.out.println(p.toString());
        // 赋值, 不过这个时候，它的值被多次赋值冲掉了
        for (MyAnnotation ma : fieldAnnotationsByType) {
            p.setName(ma.value());
        }
        System.out.println(p.toString());
    }

    @Data
    @Builder
    static class PersonInfo {
        @MyAnnotation("张三")
        @MyAnnotation("张二狗")
        @MyAnnotation("张海涛")
        private String name;

        private Integer age;

        public void show(@MyAnnotation("Hello") String name) {
            System.out.println("123");
        }

        public void getInfo() {
            System.out.println("getInfo method!");
        }
    }
}



