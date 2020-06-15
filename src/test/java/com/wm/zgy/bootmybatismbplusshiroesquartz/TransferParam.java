package com.wm.zgy.bootmybatismbplusshiroesquartz;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author renjiaxin
 * @Date 2020/6/15
 * @Description 总的结论： Java是按照值传递的，而不是按照引用，
 * 值传递：  就是把原来的参数的值，拷贝一份，将这个拷贝的值修改，而原来的旧的值不会修改
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

        // 对象类型的值传递
        System.out.println("+++++++++++本类型的值传递++++++++++++++");
        Map<String, String > map = new HashMap<>();
        map.put("name", "李小龙");
        System.out.println("调用changeDataObject(Map<String, String> map)方法前: " + map);
        changeDataObject(map);
        System.out.println("调用changeDataObject(Map<String, String> map)方法后: " + map);



    }

    public static void changeDataBasic(int data) {
        data = 100;
        System.out.println("方法中： " + data);
    }

    public static void changeDataObject(Map<String, String> map){
        Map<String, String > testMap = new HashMap<>();
        testMap.put("name", "zhangsan");
        map = testMap;
        System.out.println("方法中： " + map);
    }
}

@Data
class People {
    private String name;
    private Integer age;
    private Map<String, String> info;
}
