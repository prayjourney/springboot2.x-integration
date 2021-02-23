package com.zgy.learn.assertuse;

/**
 * @author: pray-journey.io
 * @description: -enableassertions，开启断言，平时的时候是关闭断言的
 * @date: created in 2021-02-14
 * @modified:
 */
public class AssertUse {
    public static void main(String[] args) {
        System.out.println(1234);
        int a = 1;
        String name = "11";
        assertUse(a, name);
    }

    public static void assertUse(int num, String name) {
        assert num < 10;
        assert name.length() > 3 : "长度有问题";
    }
}
