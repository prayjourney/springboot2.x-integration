package com.zgy.learn.annotation;

import cn.hutool.core.annotation.AnnotationUtil;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author: z.g.y
 * @date: 2021/4/7
 */
public class ParsingAnnotation {
    public static boolean parsingTestByHutool() throws NoSuchFieldException {
        if (AnnotationUtil.hasAnnotation(Computer.class.getDeclaredField("brand"), MyAnnotation.class)) {
            return true;
        }
        return false;
    }

    public static Computer parsingByHutool() throws NoSuchFieldException {
        Computer computer = new Computer();
        if (AnnotationUtil.hasAnnotation(Computer.class.getDeclaredField("brand"), MyAnnotation.class)) {
            Object brand = AnnotationUtil.getAnnotationValue(Computer.class.getDeclaredField("brand"),
                    MyAnnotation.class);
            System.out.printf("brand: %s \n", brand);
            computer.setBrand(String.valueOf(brand));
        }
        if (AnnotationUtil.hasAnnotation(Computer.class.getDeclaredField("kind"), MyAnnotation.class)) {
            Object kind = AnnotationUtil.getAnnotationValue(Computer.class.getDeclaredField("kind"),
                    MyAnnotation.class);
            System.out.printf("kind: %s \n", kind);
            computer.setKind(String.valueOf(kind));
        }
        if (AnnotationUtil.hasAnnotation(Computer.class.getDeclaredField("price"), MyAnnotation.class)) {
            Object objPrice = AnnotationUtil.getAnnotationValue(Computer.class.getDeclaredField("price"), MyAnnotation.class);
            int price = Integer.parseInt(String.valueOf(objPrice));
            System.out.printf("price: %s \n", price);
            computer.setPrice(price);
        }
        return computer;
    }

    public static boolean parsingTestBySpring() throws NoSuchFieldException {
        MyAnnotation annotation = AnnotationUtils.findAnnotation(Computer.class.getDeclaredField("kind"),
                MyAnnotation.class);
        System.out.println(annotation);
        return true;
    }


    public static Computer parsingBySpring() throws NoSuchFieldException {
        Computer computer = new Computer();
        MyAnnotation brand = AnnotationUtils.findAnnotation(Computer.class.getDeclaredField("brand"),
                MyAnnotation.class);
        MyAnnotation kind = AnnotationUtils.findAnnotation(Computer.class.getDeclaredField("kind"),
                MyAnnotation.class);
        MyAnnotation price = AnnotationUtils.findAnnotation(Computer.class.getDeclaredField("price"),
                MyAnnotation.class);

        System.out.printf("brand: %s \n", brand.value());
        System.out.printf("kind: %s \n", kind.value());
        System.out.printf("price: %s \n", price.value());
        computer.setBrand(brand.value()).setKind(kind.value()).setPrice(Integer.valueOf(price.value()));
        return computer;

    }

    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(parsingTestByHutool());
        System.out.println("===================");
        // 需要在创建这个对象, 调用这个方法之前, 进行注解的检测分析
        Computer computer1 = parsingByHutool();
        Computer computer2 = parsingByHutool();
        System.out.println(computer1);
        computer2.setBrand("asus").setPrice(7777);
        System.out.println(computer2);
        System.out.println("===================");

        parsingTestBySpring();
        Computer computer3 = parsingBySpring();
        Computer computer4 = parsingBySpring();
        System.out.println(computer3);
        computer4.setBrand("dell").setPrice(6666);
        System.out.println(computer4);

    }
}
