package com.zgy.bootintegration;

/**
 * @author: z.g.y
 * @date: 2020/7/13
 * @description:
 */
public class MathTest {
    public static void main(String[] args) {
        // floor为向下取整
        System.out.println(Math.floor(10.2f));   // 10.0
        System.out.println(Math.floor(10.8f));   // 10.0
        System.out.println(Math.floor(-10.8f));  // -11.0
        // ceil为向上取整
        System.out.println(Math.ceil(10.3f));    // 11.0
        System.out.println(Math.ceil(10.8f));    // 11.0
        System.out.println(Math.ceil(-10.8f));   // -10.0
        // round中的四舍五入函数
        System.out.println(Math.round(10.2f));   // 10
        System.out.println(Math.round(10.8f));   // 11
        System.out.println(Math.round(-10.8f));  // -11
    }
}
