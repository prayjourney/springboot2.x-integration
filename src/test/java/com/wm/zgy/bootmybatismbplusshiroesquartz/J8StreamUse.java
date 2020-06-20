package com.wm.zgy.bootmybatismbplusshiroesquartz;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author renjiaxin
 * @Date 2020/6/16
 * @Description
 * Stream操作的三个步骤：1. 创建流, 2.中间操作, 3.结束操作
 */
public class J8StreamUse {
    public static void main(String[] args) {
        System.out.println("==========1.创建流===============");
        createStream();
        System.out.println("==========2.中间操作：筛选分片===============");
        filterMethod();
        System.out.println("==========2.中间操作：映射===============");
        mapMethod();
        System.out.println("==========2.中间操作：排序===============");
        sortMethod();
        System.out.println("==========3.终止操作===============");
        terminalMethod();
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

        System.out.println("-----------------------");
        Stream<Stream<Character>> s2tm =
                list.stream().map(x -> J8StreamUse.filterCharacter(x));
        // 流中取流
        s2tm.forEach(sm ->{
            sm.forEach(System.out::println);
        });

        // 流中取流使用flatMap代替, 作用于集合的add，addAll类似
        Stream<Character> s2tmFlat = list.stream().flatMap(J8StreamUse::filterCharacter);
        s2tmFlat.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character ch: str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 排序
     * sorted()--->自然排序， comparable
     * sorted(Comparator com)--->定制排序， comparator
     */
    public static void sortMethod(){
        List<String> list = Arrays.asList("张三", "lisi", "22","7","黑山老妖");
        list.stream().sorted().forEach(System.out::println);

        // 定制排序
        System.out.println("======定制排序======");
        Stream<String> sorted = list.stream().sorted((x, y) -> {
            if (x.length() > y.length()) {
                return 1;
            } else if (x.length() == y.length()) {
                return 0;
            } else {
                return -1;
            }
        });
        sorted.forEach(System.out::println);
    }

    // 数据准备
    static List<LittleDog> dogs = Arrays.asList(
            LittleDog.builder().id(1).name("lili").kind("阿拉斯加").height(23).status(Status.OKAY).build(),
            LittleDog.builder().id(2).name("milu").kind("柴犬").height(45).status(Status.BAD).build(),
            LittleDog.builder().id(3).name("makle").kind("阿拉斯加").height(67).status(Status.GOOD).build(),
            LittleDog.builder().id(4).name("哈哈").kind("阿拉斯加").height(88).status(Status.GREAT).build(),
            LittleDog.builder().id(5).name("孩子3").kind("柴犬").height(46).status(Status.GREAT).build(),
            LittleDog.builder().id(6).name("MGDP").kind("中华田园犬").height(38).status(Status.GOOD).build(),
            LittleDog.builder().id(7).name("特朗普").kind("柴犬").height(28).status(Status.OKAY).build(),
            LittleDog.builder().id(8).name("小马").kind("中华田园犬").height(83).status(Status.OKAY).build(),
            LittleDog.builder().id(9).name("校长").kind("德国牧羊犬").height(99).status(Status.BAD).build()
    );

    /**
     * 终止操作
     */
    public static void terminalMethod(){
        // 是否全都匹配，检查所有元素
        boolean match1 = dogs.stream().allMatch(e -> e.getStatus().getStatus() == "OKAY");
        System.out.println(match1);
        // 是否匹配一个元素
        boolean match2 = dogs.stream().anyMatch(e -> e.getStatus().getStatus() == "OKAY");
        System.out.println(match2);
        // 是否没有匹配所有元素
        boolean match3 = dogs.stream().noneMatch(Status.OKAY.getStatus()::equals);
        System.out.println(match3);
        // 获取第一个
        System.out.println(dogs.stream().findFirst().get().toString());
        Optional<LittleDog> first = dogs.stream().sorted((x, y) -> Integer.compare(y.getHeight(), x.getHeight())).findFirst();
        System.out.println(first.get());
        // 随便获取一个
        Optional<LittleDog> littleDog = dogs.parallelStream().filter(x -> x.getKind().equals("中华田园犬")).findAny();
        System.out.println(littleDog.get().getName());
        // 计算流中总数
        System.out.println(dogs.stream().count());
        System.out.println(dogs.stream().filter(x -> x.getKind().equals("柴犬")).count());
        // 计算大小
        Optional<LittleDog> maxDog =
                dogs.stream().filter(x -> x.getKind().equals("阿拉斯加")).max((x, y) -> Integer.compare(x.getHeight(),
                        y.getHeight()));
        System.out.println(maxDog.get().getName());
        Optional<Integer> min = dogs.stream().map(LittleDog::getHeight).min(Integer::compare);
        System.out.println(min);

    }





















    @Data
    @Builder
    static class LittleDog{
        private Integer id;
        private String name;
        private String kind;
        private Integer height;
        private Status status;
    }
}

enum Status{
    GREAT(1, "great"), GOOD(2,"good"), OKAY(3,"okay"), BAD(4,"bad");
    private int index;
    private String status;

    private Status(int idex, String  str) {
        index = idex;
        status = str;
    }

    public String getStatus(){
        return status;
    }
}