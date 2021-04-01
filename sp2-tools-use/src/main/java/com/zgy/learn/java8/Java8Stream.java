package com.zgy.learn.java8;

import com.zgy.learn.beanutils.Animal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-04-02
 * @modified:
 */
public class Java8Stream {
    public static List<Animal> getList() {
        List<Animal> list = new ArrayList<>();
        list.add(new Animal(1, "张三", "土狗", 1, new Date()));
        list.add(new Animal(2, "aaa", "柴犬", 2, new Date()));
        list.add(new Animal(3, "bbb", "狼狗", 10, new Date()));
        list.add(new Animal(4, "ccc", "土狗", 4, new Date()));
        list.add(new Animal(5, "张三123", "土狗", 3, new Date()));
        list.add(new Animal(6, "张三111", "土狗", 2, new Date()));
        list.add(new Animal(7, "小红", "日狗", 5, new Date()));
        list.add(new Animal(8, "李四", "阿拉斯基", 10, new Date()));
        list.add(new Animal(9, "艾米", "阿拉斯加", 9, new Date()));
        list.add(new Animal(10, "Lily", "柯基", 4, new Date()));
        list.add(new Animal(11, "huTo", "柴犬", 3, new Date()));
        list.add(new Animal(12, "老西", "柴犬", 2, new Date()));
        list.add(new Animal(13, "买张", "土狗", 1, new Date()));
        return list;
    }

    public static void main(String[] args) {
        List<Animal> dogList = getList();
        List<String> collect = dogList.stream().filter(x -> x.getAge() > 2).distinct().limit(5).
                map(x -> x.getName().toUpperCase()).collect(Collectors.toList());
        collect.stream().forEach(System.out::print);


    }
}
