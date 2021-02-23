package com.zgy.bootintegration;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// java8新接口
interface MyDefaultMethod {
    // 使用default修饰的默认接口方法, 看起来是可以有多个
    default double sqrt(Integer num) {
        return Math.sqrt(num);
    }

    default int getNum() {
        return 1;
    }

    // 普通的接口方法
    String info(String name, String age);

}

/**
 * @author: z.g.y
 * @date: 2020/6/15
 * @description: 介绍
 * * 使用规律:
 * * 总体要求: ->作为区分，左边是参数(无参), 右边是操作，调用得结果
 * * 参数(无参) -> do SomeThing ;  result = call;
 * *
 * * 四大接口:
 * * * 供给型接口: 没有参数,    有一个返回值
 * * * 消费型接口: 提供一个参数, 没有返回值
 * * * 断言型接口: 提供一个参数, 返回一个布尔值结果
 * * * 函数型接口: 提供一个参数, 返回一个规定类型结果
 * *
 * 供给接口和消费接口有点容易混淆, 其实区分它们的关键点在于理解他们的职责,
 * 供给职责: 在于通过供给接口供给, 消费职责: 在于通过消费接口消费，
 * 供给要提供东西, 消费要有东西消费, 所以供给一定有返回值, 消费一定有入参, 供给无中生有, 消费完了就没事
 * *
 * 供给型: 没有输入, 通过供给接口, 产生对象, 给第三方调用, 所以要有返回值, 可以没有入参， 供给接口职责在于: 供给
 * 消费型: 要有入参, 通过消费型接口, 把出入的对象消费掉, 消费掉既完成了任务, 所以不需要返回值, 消费接口职责在于: 消费
 * *
 * * 如何写:
 * * 函数式接口, 其实就是匿名内部类的简化方法, 所以当我们使用的时候, 如果不会写, 就先创建匿名内部类,
 * * 匿名内部类返回的接口是这种类型的接口对象, 然后复写内部的处理逻辑, 最后转化成lambda表达式
 * <p>
 * 另外:
 * 不是说lambda我们只能使用官方提供的一些, 我们也可以自己去写, 怎么写呢？
 * 很简单, 我们自己定义的接口, 我们就可以使用, 比如下面的MyDefaultMethod, 其中的info方法需要我们自己去实现,
 * 而这正不是我们的四大接口之中要去自己定义和实现的东西吗? 就是说, 怎么去干活， 还是要我们自己去定义的, 定义好了之后, 我们就去调用
 * 比如消费的时候, 我们就要调用accept, 供给的时候, 我们就要调用get, 其他类比断言调用test, 函数调用apply是一样的
 */
public class J8LambdaUse {

    public static void main(String[] args) {
        //----------------消费型接口--------------------//
        System.out.println("----------------消费型接口--------------------");
        // 消费型接口: 有一个参数, 没有返回值
        // 这个定义了这个consumer要怎么做事情
        Consumer<String> consumer = str -> System.out.println(str + "\n\n");
        consumerMethodOld("hello");
        consumerMethodNew("hello");
        consumerMethodNew02("hello", consumer);

        //----------------供给型接口--------------------//
        System.out.println("----------------供给型接口--------------------");
        // 供给型接口: 没有参数, 有一个返回值
        System.out.println(supplierMethodOld("hello world!"));
        System.out.println(supplierMethodNew("我草! \n\n"));

        //----------------断言型接口--------------------//
        System.out.println("----------------断言型接口--------------------");
        System.out.println("年龄大于18岁: " + predicateMethodOld(22));
        System.out.println("姓名长度超过20: " + predicateMethodNew("张飞") + "\n\n");

        //----------------函数型接口--------------------//
        System.out.println("----------------函数型接口--------------------");
        System.out.println("姓名长度: " + functionMethodOld("张飞万人敌"));
        System.out.println("姓名长度超过20: " + functionMethodNew("张飞"));

        //----------------多参数接口--------------------//
        System.out.println("----------------多参数接口--------------------");
        System.out.println("两个参数的函数型接口: BiFunction:: " + biFunctionMethod("张飞", 25));
        biConsumerMethod("两个参数的消费型接口: BiConsumer:: 张飞", "李逵");

        //----------------接口的新特性--------------------//
        System.out.println("----------------接口的新特性--------------------");
        useNewInterface();

    }

    /**
     * 消费型：没有返回值，一个参数
     */
    public static <String> void consumerMethodOld(String string) {

        // 有无参数，有无返回值，说的是这个, consumer接口有一个参数, 没有返回值
        Consumer<String> consumer = new Consumer<String>() {
            // 这个str就是我们的消费型接口的一个参数
            @Override
            public void accept(String str) {
                System.out.println("字符串的长度是: " + str.toString().length());
            }
        };
        // 调用外部参数, 发挥实际的作用，在这个函数之中，Consumer接口是为这个函数而服务的
        consumer.accept(string);
    }

    public static <T> void consumerMethodNew(T t) {
        // 这个str就是我们的消费型接口的一个参数
        Consumer<T> consumer2 = str -> System.out.println("字符串的长度是: " + str.toString().length());
        consumer2.accept(t);
    }

    public static <T> void consumerMethodNew02(T t, Consumer<T> consumer) {
        // 这个str就是我们的消费型接口的一个参数
        consumer.accept(t);
    }

    /**
     * 供给型：有返回值，没有参数
     *
     * @return
     */
    public static <T> T supplierMethodOld(T str) {
        Supplier<T> supplier = new Supplier<T>() {
            @Override
            public T get() {
                return str;
            }
        };
        return supplier.get();
    }

    public static <String> String supplierMethodNew(String str) {
        // Supplier接口之中，是没有参数的
        Supplier<String> supplier = () -> str;
        return supplier.get();
    }

    public static boolean predicateMethodOld(Integer age) {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer temp) {
                return temp > 18;
            }
        };
        return predicate.test(age);
    }

    public static <String> boolean predicateMethodNew(String name) {
        Predicate<String> predicate = ss -> ss.toString().length() > 20;
        return predicate.test(name);
    }

    public static Integer functionMethodOld(String name) {
        // 参数类型, 返回值类型
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                // 返回长度
                return s.length();
            }
        };
        Integer apply = function.apply(name);
        return apply;
    }

    public static Boolean functionMethodNew(String name) {
        // 参数类型, 返回值类型
        Function<String, Boolean> function = x -> x.length() > 20;
        return function.apply(name);
    }

    // 两个入参的方法, 一个返回值
    public static String biFunctionMethod(String name, Integer age) {
        BiFunction biFunction = (x, y) -> {
            // 这是一个返回的结果
            return "name: " + x + ", age: " + y;
        };
        return (String) biFunction.apply(name, age);
    }

    // 两个入参的消费, 没有返回值
    public static void biConsumerMethod(String p1, String p2) {
        BiConsumer biConsumer = (x, y) -> System.out.println(x + ", " + y);
        biConsumer.accept(p1, p2);
    }

    public static void useNewInterface() {
        MyDefaultMethod defaultMethod = new MyDefaultMethod() {
            @Override
            public String info(String name, String age) {
                return name + ", " + age;
            }
        };
        System.out.println("getNum的默认方法获取的值: " + defaultMethod.getNum());
        System.out.println("sqrt的默认方法获取的值: " + defaultMethod.sqrt(33));

        // 相比于4大接口而言, 自定义的接口, 我们要实现的方法是自定义的， 而不是像以前一样规定好了
        MyDefaultMethod defaultMethod02 = (x, y) -> String.valueOf(x.length() * y.length());
        System.out.println(defaultMethod02.info("hello", "world!"));
        System.out.println(defaultMethod02.sqrt(22));
    }


}