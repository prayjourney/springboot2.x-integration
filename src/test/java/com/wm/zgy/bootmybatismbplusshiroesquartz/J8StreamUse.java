package com.wm.zgy.bootmybatismbplusshiroesquartz;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println("==========创建流===============");
        createStream();
        System.out.println("==========筛选分片===============");
        filterMethod();
        System.out.println("==========映射===============");
        mapMethod();
    }

    /**
     * 创建流: 创建得到流
     */
    public static void createStream() {
        List<String> ls = new ArrayList<>();
        ls.add("刘备");
        ls.add("关羽");
        ls.add("张飞");
        ls.add("马超");
        ls.add("黄忠");
        ls.add("赵云");
        // 1. 从集合之中创建流
        Stream<String> listStream = ls.stream();
        System.out.println(listStream.findFirst().get());

        // 2. Arrays之中的静态方法获取数组流
        Integer[]  integers = new Integer[10];
        integers = new Integer[]{1, 2, 3, 4, 5};
        Stream<Integer> arrayStream = Arrays.stream(integers);
        arrayStream.forEach(x -> System.out.print(x));

        // 3. 使用of创建流
        Stream<String> stream = ls.stream();
        Stream<Integer> integerStream = Stream.of(1, 20, 13, 34, 15, 61, 27, 84, 29);
        //integerStream.filter(x -> x >=20).limit(10).distinct().skip(0).forEach(System.out::println);
        stream.forEach(System.out::println);
        // 排序
        // integerStream.filter(x -> x > 10).sorted().limit(20).skip(0).forEach(System.out::println);
        Comparator<Integer> comparator = (x, y) -> x - y;
        System.out.println(integerStream.filter(x -> x > 13).sorted().min(comparator).get());

        // 4. 使用无限流
        // 无限流, 种子是起始值, 下面的filter是一个检测操作， boolean类型的
        Stream<Integer> stream3 = Stream.iterate(1, x ->  x + 2);
        stream3.limit(20).skip(2).filter(x -> x % 2 != 0).forEach(System.out::println);
    }

    /**
     * 筛选与切片: 实际上就是对流里面的数据进行一个过滤或者其他的操作，比如截断limit，跳过skip,去重distinct，filter接收lambda
     */
    public static void filterMethod(){
        Stream<Integer> integerStream = Stream.of(1, 20, 13, 27 ,29, 34, 15, 61, 27, 84, 29);
        integerStream.filter(x -> x >=10).limit(10).distinct().skip(0).forEach(System.out::println);
        // 排序
        // Comparator<Integer> comparator = (x, y) -> x - y;
        // System.out.println(integerStream.filter(x -> x > 13).sorted().min(comparator).get());
    }

    /**
     * 映射
     * map: 接收lambda, 将元素转化成其他形式或者提取信息，接受一个函数作为参数，将该函数应用到每一个元素上，并将其映射成新的元素
     * flatMap: 接收一个函数作为参数，将流中的每个值换成另一个流，然后把所有的流连成一个流
     */
    public static void mapMethod(){
        List<String> list = Arrays.asList("aa","bb","ccc","dd");
        list.stream().map(String::toUpperCase).forEach(System.out::println);

    }


}
