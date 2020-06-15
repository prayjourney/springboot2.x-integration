package com.wm.zgy.bootmybatismbplusshiroesquartz;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author renjiaxin
 * @Date 2020/6/15
 * @Description
 */
public class LambdaAndStream {
    public static void main(String[] args) {
        消费型("hello", x -> System.out.println(x) );
        供给型("hello", ()-> "hello");
    }

    /**
     * 消费型：没有返回值，一个参数
     */
    public static <T> void 消费型(T t, Consumer<T> consumer) {
        // 有无参数，有无返回值，说的是这个
        consumer.accept(t);
    }

    /**
     * 供给型：有返回值，没有参数
     * @return
     */
    public static <T> String 供给型(String str, Supplier<T> supplier){
        return (String) supplier.get();
    }
}
