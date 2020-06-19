package com.wm.zgy.bootmybatismbplusshiroesquartz;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author renjiaxin
 * @Date 2020/6/16
 * @Description
 * Stream操作的三个步骤：1. 创建流, 2.中间操作, 3.结束操作
 */
public class J8StreamUse {
    public static void main(String[] args) {
        streamMethod();
    }

    public static void streamMethod() {
        List<String> ls = new ArrayList<>();
        ls.add("刘备");
        ls.add("关羽");
        ls.add("张飞");
        ls.add("马超");
        ls.add("黄忠");
        ls.add("赵云");

        Stream<String> stream = ls.stream();
        Stream<Integer> integerStream = Stream.of(1, 20, 13, 34, 15, 61, 27, 84, 29);

        //integerStream.filter(x -> x >=20).limit(10).distinct().skip(0).forEach(System.out::println);
        stream.forEach(System.out::println);

        // 排序
        // integerStream.filter(x -> x > 10).sorted().limit(20).skip(0).forEach(System.out::println);

        Comparator<Integer> comparator = (x, y) -> x - y;
        System.out.println(integerStream.filter(x -> x > 13).sorted().min(comparator).get());

        // 无限流, 种子是起始值, 下面的filter是一个检测操作， boolean类型的
        Stream<Integer> stream3 = Stream.iterate(1, x ->  x + 2);
        stream3.limit(20).skip(2).filter(x -> x % 2 != 0).forEach(System.out::println);
        // log(0)是无意义的
        System.out.println(Math.log(0));
    }
}
