package com.zgy.bootintegration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: z.g.y
 * @date: 2020/6/15
 * @description: 总的结论： Java是按照值传递的，而不是按照引用，
 * 按值传递：  就是把原来的参数的值，拷贝一份，将这个拷贝的值修改，而原来的旧的值不会修改
 * 引用传递：就是将参数的地址拿来使用，不去拷贝，直接在这个地址上操作，这样的话，就会把原来的值也修改了
 * <p>
 * 虽然值传递和引用传递尘埃落定，在Java之中只有值传递，但是有个操作很容易造成迷惑，就是赋值，以如下基本类型和Map对象来演示
 */
public class TransferParam2 {
    public static void main(String[] args) {
        // 基本类型的值传递
        System.out.println("===========基本类型的值传递============");
        int age = 22;
        System.out.println("调用changeData(int data)方法前: " + age);
        changeDataBasic(age);
        System.out.println("调用changeData(int data)方法后: " + age + "\n");

        // 对象类型的值传递
        System.out.println("===========对象类型的值传递============");
        Map<String, String> map = new HashMap<>();
        map.put("name", "李小龙");
        System.out.println("调用changeDataObject(Map<String, String> map)方法前: " + map);
        changeDataObject(map);
        System.out.println("调用changeDataObject(Map<String, String> map)方法后: " + map + "\n\n");

        // 基本类型的值传递, 但是赋值行为改变了作用范围
        System.out.println("++++++++++++基本类型的值传递: 返回值赋值修改了值+++++++++++++");
        int age2 = 22;
        System.out.println("调用changeDataReturn(int data)方法前: " + age2);
        // 赋值行为修改了原先的值
        age2 = changeDataBasicReturn(age);
        System.out.println("调用changeDataReturn(int data)方法后: " + age2 + "\n");

        // 对象类型的值传递, 但是赋值行为改变了作用范围
        System.out.println("++++++++++++对象类型的值传递: 返回值赋值修改了值++++++++++++++");
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "陈真");
        System.out.println("调用changeDataObjectReturn(Map<String, String> map)方法前: " + map2);
        // 赋值行为修改了原先的值
        map2 = changeDataObjectReturn(map2);
        System.out.println("调用changeDataObjectReturn(Map<String, String> map)方法后: " + map2 + "\n\n");
    }

    // 这是空方法, 没有赋值行为, 对拷贝内容的修改, 仅仅局限于方法内, 返回是void, 对外不造成影响
    public static void changeDataBasic(int data) {
        data = 100;
        System.out.println("方法中： " + data);
    }

    public static void changeDataObject(Map<String, String> map) {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("name", "zhangsan");
        map = testMap;
        System.out.println("方法中： " + map);
    }

    // 该方法有返回值, 值传递中拷贝的内容, 在修改之后, 可以通过该方法进行赋值, 所以会对外造成影响
    public static int changeDataBasicReturn(int data) {
        data = 100;
        System.out.println("方法中： " + data);
        return data;
    }

    public static Map<String, String> changeDataObjectReturn(Map<String, String> map) {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("name", "zhangsan");
        map = testMap;
        System.out.println("方法中： " + map);
        return map;
    }
}
