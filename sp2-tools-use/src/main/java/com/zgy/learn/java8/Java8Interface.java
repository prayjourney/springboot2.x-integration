package com.zgy.learn.java8;

/**
 * @author: pray-journey.io
 * @date: 2021/4/13
 */
class Java8Interface {

    public static void main(String[] args) {
        // 接口的静态方法
        Test.getName();
        Test test = new TestImpl();
        // 抽象方法, 让子类去重写实现
        System.out.println(test.getInfo());
        // 调用接口提供的默认方法
        Integer age = test.getAge();
        System.out.printf("age is : %d \n", age);
        // 重写default方法然后调用
        System.out.println(test.getAddress());
    }

}

interface Test {
    String NAME = "张三";

    /**
     * 静态方法
     */
    static void getName() {
        System.out.printf("我的名字是: %s \n", NAME);
    }

    /**
     * 默认方法
     *
     * @return
     */
    default Integer getAge() {
        return 28;
    }

    default String getAddress() {
        return "中国北京";
    }

    /**
     * 抽象方法
     *
     * @return
     */
    String getInfo();
}

class TestImpl implements Test {
    @Override
    public String getInfo() {
        return "我是Test接口的抽象方法！";
    }

    @Override
    public String getAddress() {
        return "中国重庆";
    }
}
