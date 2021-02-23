package com.zgy.bootintegration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: z.g.y
 * @date: 2020/6/15
 * @description: 测试传参是否可以修改
 */
public class TestParam {

    public static void main(String[] args) {
        TestParam testParam = new TestParam();
        System.out.println(testParam.setAge(11));
        System.out.println(testParam.setName("张三"));
        // System.out.println(testParam.setInfo(null)); // 传入为null的时候, 就无法修改, 不为null的时候， 可以修改
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "wangwu");
        map.put("address", "河北衡水");
        System.out.println(testParam.setInfo(map));
    }

    // 传参
    public String setName(String name) {
        name = name + "123";
        return name;
    }

    public Integer setAge(Integer age) {
        age += 1;
        return age;
    }

    public Map<String, String> setInfo(Map<String, String> mp) {
        // 修改mp的值
        mp.put("name", "zhangsan");
        return mp;
    }

}
