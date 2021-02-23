package com.zgy.bootintegration;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author: z.g.y
 * @date: 2020/6/17
 * @description: 如果Lambda表达式体中的内容已有方法实现，那么我们就可以直接使用，而不用再去写一遍，这个时候就可以使用"方法引用"来代替
 * @type: 类型
 * * 对象::实例方法
 * * 类::静态方法
 * * 类::实例方法
 * * 构造器::new
 * * 类型[]::new
 * 类型有如上的5种, 总体而言, 方法引用是为简化lambda而出现, 何时才可以使用? 就是当我们使用的时候, 要定义的方法已经存在,
 * 入参和返回值都满足要求, 我们就可以使用方法引用, 前三种的话, 其实没有太多的限制, 而对于后面两种，也就是构造器和数组的方法引用
 * 构造器::new + 类型[]::new, 我们一定要明白, 我们使用这两种, 是为了创建对象， 创建对象, 就是要往供给型的接口方向去凑
 * 怎么凑呢? 无非还是入参和返回值, 没有入参用Supplier, 一个入参用Function, 两个用BiFunction, 返回值都是一个,
 * 生成的对象或者是数组, 使用get或者apply方法来获取。
 * <p>
 * 方法引用的类型和使用，其实前几种没有太大的问题，主要是注意入参和返回值，对于后面的构造器和数组，主要是要理解，
 * 我们是要通过它来创建出我们的对象或者数组，所以一定要有返回值，意味着就不能使用消费接口去生成，而应该使用供给或者函数接口去生成，
 * 至于入参的多少，我们就要根据情况来决定了。
 * @ref: 参考链接
 * https://www.jianshu.com/p/ba2e025cca37
 * https://www.cnblogs.com/yangfei629/p/11426986.html
 * https://blog.csdn.net/weixin_45225595/article/details/106203264
 * https://www.cnblogs.com/junjiang3/p/8998509.html
 * https://juejin.im/post/5c6bba1bf265da2dd4276094
 */
public class J8MethodRefUse {
    // 对象::实例方法
    // Lambda 表达实体中调用方法的参数列表、返回类型必须和函数式接口中抽象方法保持一致
    public static void objMethod() {
        Consumer customizer = xx -> System.out.println(xx);
        customizer.accept("hello world!");

        Consumer<String> consumer02 = System.out::println;
        consumer02.accept("hello java!");
    }

    // 类::静态方法
    // Lambda 表达实体中调用方法的参数列表、返回类型必须和函数式接口中抽象方法保持一致
    public static void staticMethod() {
        Function<Integer, Double> function = xx -> Math.sqrt(xx);
        System.out.println(function.apply(123));

        Function<Integer, Double> function02 = Math::sqrt;
        Double result = function02.apply(1233);
        System.out.println(result);

    }

    // 类::实例方法
    // Lambda 参数列表中的第一个参数是方法的调用者，第二个参数是方法的参数时，才能使用 ClassName :: Method
    public static <T, U> Map clsObjMethod(T p1, U p2) {
        Map<String, Boolean> resultMap = new HashMap();
        BiFunction<T, U, Boolean> function = (x, y) -> x.equals(y);
        Boolean result = function.apply(p1, p2);
        resultMap.put("1", result);


        BiFunction<T, U, Boolean> function02 = Object::equals;
        resultMap.put("2", function02.apply(p1, p2));

        return resultMap;
    }

    // 构造器::new ClassName :: new
    public static void constructorMethod() {
        Supplier<List> supplier = ArrayList<String>::new;
        List list = supplier.get();
        list.add("张三");
        list.add("李二隋");
        list.add("王晓阳");
        list.add("赵柳");
        Stream<String> stream = list.stream();
        System.out.println(list.size());
        // forEach之中需要的就是一个消费者, 如下创建, 正好又可以去使用引用
        Consumer<String> consumer = x -> System.out.println(x); //System.out::println
        stream.forEach(consumer);


        // 说明, 不管是怎么方式, 都可以创建出来Stream, 但是使用的时候, 还是要看，我们是消费还是供给, 供给的时候需要创造,
        // 而消费的时候, 就需要直接喂东西了, 所以上面的supplier可以get到一个list, 去装, 而下面的consumer02只能直接消费提供好的了
        // Consumer<List<Dog>> consumer2 = ArrayList<Dog>::new; // 这样有问题，用起来怪怪的, 消费应该定义的是消费的方式
        Consumer<List<Dog>> consumer2 = z -> {
            for (Dog g : z) {
                System.out.println(g.toString());
            }
        };
        List<Dog> dogs = new ArrayList<>();
        dogs.add(Dog.builder().age(2).name("哈士奇").build());
        dogs.add(Dog.builder().age(1).name("小肥柴").build());
        dogs.add(Dog.builder().age(2).name("秋田犬").build());
        consumer2.accept(dogs);

    }

    // 类型[]::new 数组类型 Type::new
    public static void strArrayMethod() {
        Function<Integer, String[]> array1 = (x) -> new String[x];
        Function<Integer, String[]> array2 = String[]::new;
        array1.apply(1);
        array1.apply(2);
        array1.apply(3);
        String[] ss = array1.apply(4);
        System.out.println(ss.length);
    }

    public static void main(String[] args) {
        objMethod();
        staticMethod();
        System.out.println(clsObjMethod("zhangsan", "张三"));

        constructorMethod();
        strArrayMethod();
    }
}

@Data
@Builder
class Dog {
    private int age;
    private String name;
}
