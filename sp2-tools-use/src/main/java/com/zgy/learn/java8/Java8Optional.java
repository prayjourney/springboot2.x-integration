package com.zgy.learn.java8;

import java.util.Optional;

/**
 * @author: pray-journey.io
 * @date: 2021/4/13
 */
public class Java8Optional {
    public static void main(String[] args) {
        Integer value1 = null;
        Integer value2 = new Integer(10);
        // ofNullable: Optional.ofNullable - 允许传递为null参数
        Optional<Integer> opt01 = Optional.ofNullable(value1);
        // of: Optional.of - 如果传递的参数是null, 抛出异常NullPointerException
        Optional<Integer> opt02 = Optional.of(value2);
        System.out.println(opt01);
        // get: 获取Optional的值
        System.out.println(opt02.get());

        // orElse: 如果不为空就设置值为88
        Integer age = opt01.orElse(88);
        System.out.println("age: " + age);

        // orElseGet: 如果不为空就设置值为33, 是通过供给型接口提供的
        Integer price = opt02.orElseGet(() -> {
            return 33;
        });
        System.out.println("price: " + price);

        printDoubleArg(opt01, opt02);

    }

    public static void printDoubleArg(Optional<Integer> a, Optional<Integer> b) {
        System.out.println("a是否存在: " + a.isPresent());
        System.out.println("b是否存在: " + b.isPresent());
        // 如果存在就去做这件事情, 否则就不做, 适合于去做一些遍历, 或者是其他的操作
        a.ifPresent(x -> System.out.println(x + "==="));
        b.ifPresent(x -> System.out.println(x + "+++"));
    }

}
