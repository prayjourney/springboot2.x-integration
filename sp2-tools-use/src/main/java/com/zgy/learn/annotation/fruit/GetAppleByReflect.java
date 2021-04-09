package com.zgy.learn.annotation.fruit;

import java.lang.reflect.Method;

/**
 * @author: z.g.y
 * @date: 2021/4/7
 * @desc: 使用传统的反射方式来获取注解的信息，然后完成对象的设置
 */
public class GetAppleByReflect {

    @Fruit(kind = "国光苹果", origin = {"山东","河北"})
    public Apple getGuoguangApple() throws NoSuchMethodException {
        Apple guoguang = new Apple();
        Class<GetAppleByReflect> getApple = GetAppleByReflect.class;
        Method getGuoguangApple = getApple.getDeclaredMethod("getGuoguangApple");
        Fruit annotation = getGuoguangApple.getAnnotation(Fruit.class);
        String kind = annotation.kind();
        String[] origin = annotation.origin();
        guoguang.setKind(kind);
        guoguang.setOrigin(origin);
        return guoguang;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        GetAppleByReflect apple = new GetAppleByReflect();
        Apple guoguangApple = apple.getGuoguangApple();
        guoguangApple.setPrice(8);
        System.out.println(guoguangApple);

    }
}
