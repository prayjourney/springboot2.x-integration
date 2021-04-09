package com.zgy.learn.annotation.fruit;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author: z.g.y
 * @date: 2021/4/7
 * @desc: 使用Spring提供的AnnotationUtils，然后完成对象的设置
 */
public class GetApple {
    @Fruit(kind = "富士", origin = {"烟台", "富平"})
    public Apple getFushiApple() throws NoSuchMethodException {
        Apple apple = new Apple();
        Fruit fushi = AnnotationUtils.findAnnotation(GetApple.class.getDeclaredMethod("getFushiApple"), Fruit.class);
        String kind = fushi.kind();
        String[] origin = fushi.origin();
        apple.setKind(kind);
        apple.setOrigin(origin);
        return apple;
    }

    @Fruit(kind = "黄元帅", origin = {"天水"})
    public Apple getHuangysApple() throws NoSuchMethodException {
        Apple apple = new Apple();
        Fruit fushi = AnnotationUtils.findAnnotation(GetApple.class.getDeclaredMethod("getHuangysApple"), Fruit.class);
        String kind = fushi.kind();
        String[] origin = fushi.origin();
        apple.setKind(kind);
        apple.setOrigin(origin);
        return apple;
    }
    public static void main(String[] args) throws NoSuchMethodException {
        GetApple getApple = new GetApple();
        Apple fushiApple = getApple.getFushiApple();
        fushiApple.setPrice(12);
        Apple huangysApple = getApple.getHuangysApple();
        huangysApple.setPrice(15);
        System.out.println(fushiApple.toString());
        System.out.println(huangysApple);
    }
}
