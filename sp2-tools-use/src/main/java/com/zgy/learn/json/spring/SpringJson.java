package com.zgy.learn.json.spring;


import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * @author: z.g.y
 * @date: 2021/1/29
 */
public class SpringJson {
    public static void main(String[] args) throws JSONException {
        long start = System.currentTimeMillis();

        System.out.println("======JSONObject======");
        JSONObject json1 = new JSONObject();
        json1.put("name", "小黑");
        json1.put("age", 22);
        JSONObject json2 = new JSONObject();
        json2.put("name", "张三");
        json2.put("age", 22);
        json2.put("info", new String[]{"123", "11", "11"});
        System.out.println(json1.toString());
        System.out.println(json2.toString() + "\n\n");


        System.out.println("======JSONArray======");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(json1);
        jsonArray.put(json2);
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
        System.out.println(jsonArray3.getString(1));
        System.out.println(jsonArray4);
        System.out.println(jsonArray4.getJSONObject(1));
        System.out.println(jsonArray4.getJSONArray(2));

        long end = System.currentTimeMillis();
        System.out.printf("完成这些操作一共耗时: %s ms", end - start);
    }
}
