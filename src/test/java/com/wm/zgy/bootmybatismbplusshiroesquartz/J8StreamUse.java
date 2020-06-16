package com.wm.zgy.bootmybatismbplusshiroesquartz;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author renjiaxin
 * @Date 2020/6/16
 * @Description
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
    }
}
