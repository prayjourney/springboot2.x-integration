package com.zgy.learn.json.hutool;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * @author: z.g.y
 * @date: 2021/1/29
 */
public class HutoolJson {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        System.out.println("======JSONObject======");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.set("name", "zhangsan");
        jsonObject1.set("age", 22);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.set("name", "张三");
        jsonObject2.set("age", 22);
        jsonObject2.put("info", new String[]{"123", "11", "11"});
        System.out.println(jsonObject1.toString());
        System.out.println(jsonObject2.toString() + "\n\n");


        System.out.println("======JSONArray======");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject1);
        jsonArray.put(jsonObject2);
        jsonArray.put(10);
        jsonArray.put(20);
        System.out.println(jsonArray.toString() + "\n\n");


        System.out.println("======String--->JSONObject======");
        JSONObject json4String01 = new JSONObject("{'name':'李四','age':22,'home':'bj'}");
        String str = "{'name':'王友佳','age':22,'home':'taipei','friend':{\"name\":\"李四\",\"age\":22,\"home\":\"bj\"}}";
        JSONObject json4String02 = new JSONObject(str);
        System.out.println(json4String01.toString());
        System.out.println(json4String02);
        System.out.println(json4String02.get("home"));
        System.out.println(json4String02.getJSONObject("friend"));
        System.out.println(json4String02.toString() + "\n\n");


        System.out.println("======String--->JSONArray======");
        String array01 = "[1,2,3,4]";
        String array02 = "[{\"name\":\"张三\",\"age\":22},{\"name\":\"李四\",\"age\":22}]";
        String array03 = "[{\"name\":\"张三\",\"age\":22},{\"name\":\"王五\",\"age\":22, \"friend\": {\"name\":\"李四\"," +
                "\"age\":22,\"home\":\"bj\"}}]";
        String array04 = "[{\"name\":\"张三\",\"age\":22},{\"name\":\"王五\",\"info\" :[\"hello\", \"年方二八\", \"red-pom\"]}, [1,2,3,4]]";
        JSONArray jsonArray1 = new JSONArray(array01);
        JSONArray jsonArray2 = new JSONArray(array02);
        JSONArray jsonArray3 = new JSONArray(array03);
        JSONArray jsonArray4 = new JSONArray(array04);
        System.out.println(array01);
        System.out.println(array02);
        System.out.println(array03);
        System.out.println(array04);
        System.out.println(jsonArray1);
        System.out.println(jsonArray1.get(1));
        System.out.println(jsonArray2.getJSONObject(1));
        System.out.println(jsonArray3);
        System.out.println(jsonArray3.getStr(1));
        System.out.println(jsonArray4);
        System.out.println(jsonArray4.getJSONObject(1));
        System.out.println(jsonArray4.getJSONArray(2));

        long end = System.currentTimeMillis();
        System.out.printf("完成这些操作一共耗时: %s ms", end - start);

    }
}
