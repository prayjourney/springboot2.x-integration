package com.zgy.bootintegration;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: z.g.y
 * @date: 2020/6/15
 * @description: 总的结论：Java是按照值传递的，而不是按照引用，
 * 按值传递：  就是把原来的参数的值，拷贝一份，将这个拷贝的值修改，而原来的旧的值不会修改
 * 引用传递：就是将参数的地址拿来使用，不去拷贝，直接在这个地址上操作，这样的话，就会把原来的值也修改了
 */
public class TransferParam {

    public static void main(String[] args) {
        // 基本类型的值传递
        System.out.println("===========基本类型的值传递============");
        int age = 22;
        System.out.println("调用changeData(int data)方法前: " + age);
        changeDataBasic(age);
        System.out.println("调用changeData(int data)方法后: " + age + "\n");

        // 基本类型的值传递, String
        System.out.println("===========基本类型的值传递:String============");
        String name = "张三";
        System.out.println("调用changeString(String str)方法前: " + name);
        changeString(name);
        System.out.println("调用changeString(String str)方法后: " + name + "\n\n");

        // 对象类型的值传递
        System.out.println("+++++++++++对象类型的值传递++++++++++++++");
        Map<String, String> map = new HashMap<>();
        map.put("name", "李小龙");
        System.out.println("调用changeDataObject(Map<String, String> map)方法前: " + map);
        changeDataObject(map);
        System.out.println("调用changeDataObject(Map<String, String> map)方法后: " + map + "\n");

        // 对象类型的值传递, 普通的对象
        System.out.println("+++++++++++对象类型的值传递:People++++++++++++++");
        Map<String, String> info = new HashMap<>();
        info.put("address", "北京朝阳");
        info.put("home", "四川成都");
        People people = People.builder().age(22).name("lee").info(info).build();
        System.out.println("调用changePeople(People people)方法前: " + people);
        changePeople(people);
        System.out.println("调用changePeople(People people)方法后: " + people);


    }

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

    public static void changeString(String str) {
        str = "Hello world!";
        System.out.println("方法中： " + str);
    }

    public static void changePeople(People people) {
        Map<String, String> info2 = new HashMap<>();
        info2.put("address", "北京");
        info2.put("home", "四川雅安");
        People people2 = People.builder().age(22).name("lee").info(info2).build();
        people = people2;
        System.out.println("方法中： " + people2);
    }
}

@Data
@Builder
class People {
    private String name;
    private Integer age;
    private Map<String, String> info;
}
