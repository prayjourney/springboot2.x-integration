package com.wm.zgy.bootmybatismbplusshiroesquartz;

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
 * @Author renjiaxin
 * @Date 2020/6/17
 * @Description 如果Lambda表达式体中的内容已有方法实现，那么我们就可以直接使用，而不用再去写一遍，这个时候就可以使用"方法引用"来代替
 * https://www.jianshu.com/p/ba2e025cca37
 * https://www.cnblogs.com/yangfei629/p/11426986.html
 * https://blog.csdn.net/weixin_45225595/article/details/106203264
 * https://www.cnblogs.com/junjiang3/p/8998509.html
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
