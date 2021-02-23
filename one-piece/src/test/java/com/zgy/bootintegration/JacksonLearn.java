package com.zgy.bootintegration;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zgy.bootintegration.utils.JacksonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: z.g.y
 * @date: 2020/7/6
 * @description: 我们所说的String, 都是符合json格式的string
 */
public class JacksonLearn {
    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        System.out.println("=============================使用ObjectMapper，来读写json str的内容===========================");
        // json Str -> obj
        String jsonStr = "{\"name\": \"北京\", \"province\": \"北京\", \"postCode\": \"010\", \"area\": 192345.9}";
        str2Obj(jsonStr);

        // obj -> json Str
        ChinaBigCity bigCity = ChinaBigCity.builder().name("南京").province("江苏").postCode("331908").area(189345.6f).build();
        obj2Str(bigCity);

        // map -> json Str
        Map<String, Object> map = new HashMap<>();
        map.put("name", "hello");
        map.put("ls", Arrays.asList("张三", "李四", "Lily", "小明"));
        map.put("city", ChinaBigCity.builder().name("成都").postCode("232341").province("四川省").area(476522.f).build());
        map.put("array", new Integer[]{1, 2, 34, 55});
        map2Str(map);

        // json Str -> map
        String str = "{\"city\":{\"name\":\"成都\",\"province\":\"四川省\",\"postCode\":\"232341\",\"area\":476522.0}," +
                "\"ls\":[\"张三\",\"李四\",\"Lily\",\"小明\"],\"name\":\"hello\"}";
        str2Map(str);

        // obj -> 序列化为json file
        objWrite2JSON(bigCity, "chinabigcity.json");

        // json file 反序列化 -> obj
        File file = new File("chinabigcity.json");
        readJSON2Obj(file, ChinaBigCity.class);


        System.out.println("\n\n=============================使用树形模型，来读写json str的内容===========================");
        // 使用jackson树模型来读取json Str内容
        String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        readTree(jsonString);

        // 使用jackson树模型来写入内容到json Str
        writeTree();

        // 创建一个JsonArray的String
        createJsonArrayString();

        // 读取一个JsonArray的String
        String jsonArrayStr = "[{\"name\":\"张三\",\"age\":22},{\"name\":\"李四\",\"age\":22,\"school\":\"清华大学\"," +
                "\"home\":\"洛阳\",\"gender\":\"男\"},{\"city\":\"天水\"}]";
        readJsonArrayString(jsonArrayStr);

        // list(map) -> jsonArray(jsonStr)
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        map1.put("name", "西安");
        map1.put("province", "陕西");
        map1.put("postCode", "232849");
        map1.put("area", 123789.3f);
        map2.put("name", "桂林");
        map2.put("province", "广西");
        map2.put("postCode", "123222");
        map2.put("area", 443789.3f);
        map3.put("name", "昆明");
        map3.put("province", "云南");
        List<Map<String, Object>> chinaBigCities = Arrays.asList(map1, map2, map3);

        // 直接转化为string, 有问题
        // 输出结果: "[{area=123789.3, province=陕西, name=西安, postCode=232849}, {area=443789.3, province=广西, name=桂林, postCode=123222}, {province=云南, name=昆明}]"
        System.out.println(JacksonUtil.getJsonFromObject("chinaBigCities:  " + chinaBigCities));

        // list(map) -> jsonArray(jsonStr)
        String list2JsonArrayStr = list2JsonArrayStr(chinaBigCities);
        System.out.println(list2JsonArrayStr);

        // jsonArray(jsonStr) -> list(map)
        String arrayStr = "[{\"area\" : 123789.3, \"province\" : \"陕西\", \"name\" : \"西安\", \"postCode\" : \"232849\"}, " +
                "{\"area\" : \"443789.3\", \"province\" : \"广西\", \"name\" : \"桂林\", \"postCode\" : \"123222\"}, " +
                "{\"province\" : \"云南\", \"name\" : \"昆明\"}]";
        jsonArrayStr2List(arrayStr);


        System.out.println("\n\n=============================使用流式API，来读写json str的内容===========================");
        // 使用JsonParser+JsonFactory 来解析 json Str
        String parseString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        parse4String(parseString);

        // 使用JsonGenerator+JsonFactory 来生成 json Str, 写到文件, 写到字符串
        generate2File();
        generate2String();

        // List<Map<String, Object>> list = Arrays.asList(map1, map2, map3);
        // ls2Array(list);
    }


    //================================================================================//
    //================================================================================//
    //========================使用ObjectMapper, 来完成解析和转换==========================//
    //================================================================================//
    //================================================================================//

    // jsonStr -> obj
    public static void str2Obj(String str) throws JsonProcessingException {
        ChinaBigCity city = mapper.readValue(str, ChinaBigCity.class);
        System.out.println(city.toString());
    }

    // obj -> jsonStr
    public static void obj2Str(Object obj) throws JsonProcessingException {
        String str = mapper.writeValueAsString(obj);
        System.out.println(str);
    }

    // map -> jsonStr
    public static void map2Str(Map<String, Object> map) throws JsonProcessingException {
        String str = mapper.writeValueAsString(map);
        System.out.println(str);
    }

    // jsonStr -> map
    public static void str2Map(String str) throws JsonProcessingException {
        Map<String, Object> map = mapper.readValue(str, Map.class);
        String name = (String) map.get("name");
        List<String> ls = (List<String>) map.get("ls");
        Object city = map.get("city");
        System.out.println(name + ", " + ls.size());
        System.out.println(city.toString());
    }

    // obj 序列化为json文件
    public static void objWrite2JSON(Object obj, String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), obj);
    }

    // obj 反序列化，从json文件中获取对象
    public static void readJSON2Obj(File file, Class cls) throws IOException {
        Object city = mapper.readValue(file, cls);
        System.out.println(city.toString());
    }

    // list(map) -> jsonArray(jsonStr)
    /**
     * public static <T> void list2Array(List<T> list) throws JsonProcessingException {
     *    String value = mapper.writeValueAsString(list.toString()).replaceAll("=",":");
     *    System.out.println(value);
     * }
     */

    // jsonArray(jsonStr) -> list(map) // 这个处理有问题

    /**
     * public static void array2List(String strArray) throws JsonProcessingException {
     * List value = mapper.readValue(strArray, new TypeReference<List<String>>(){});
     * System.out.println(value.size());
     * System.out.println(value.get(0));
     * }
     */


    //================================================================================//
    //================================================================================//
    //========================使用树形模型, 读写与转换jsonString内容=======================//
    //================================================================================//
    //================================================================================//

    // 使用jackson的树模型, 读取jsonStr的内容
    public static void readTree(String str) throws JsonProcessingException {
        // 使用JSON构建JsonNode树对象
        JsonNode rootNode = mapper.readTree(str);
        // 遍历Tree
        Iterator<JsonNode> iterator = rootNode.elements();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().asText());
        }
        // 直接通过关键字获取node对象
        String jsonNodeStr = rootNode.path("name").asText();
        System.out.println(jsonNodeStr);
    }

    // 使用jackson的树模型，写入内容到jsonStr
    public static void writeTree() throws IOException {
        // JsonNodeFactory是单例的, 可以全局使用
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        // 根节点
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("token", "123");
        rootNode.put("name", "张三");
        rootNode.put("age", 22);
        // 往根节点中添加一个子对象
        ObjectNode petsNode = jsonNodeFactory.objectNode();
        petsNode.put("petName", "kitty")
                .put("petAge", 3);
        rootNode.set("pets", petsNode);
        // 往根节点中添加一个数组
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        arrayNode.add("java")
                .add("linux")
                .add("mysql");
        rootNode.set("skills", arrayNode);

        // 调用ObjectMapper的writeTree方法根据节点生成最终json字符串
        JsonFactory jsonFactory = new JsonFactory();
        StringWriter writer = new StringWriter();
        JsonGenerator generator = jsonFactory.createGenerator(writer);
        mapper.writeTree(generator, rootNode);
        System.out.println(writer.toString());
    }

    // 创建 jsonArray的String
    public static void createJsonArrayString() throws IOException {
        /**
         [
         {
         "petName": "kitty",
         "petAge": 3
         },
         {
         "petName": "kitty",
         "petAge": 3
         },
         {
         "petName": "kitty",
         "petAge": 3
         }
         ]
         */
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        // 当做根节点
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        ObjectNode rootNode1 = jsonNodeFactory.objectNode();
        ObjectNode rootNode2 = jsonNodeFactory.objectNode();
        ObjectNode rootNode3 = jsonNodeFactory.objectNode();
        rootNode1.put("name", "张三");
        rootNode1.put("age", 22);
        rootNode2.put("name", "李四");
        rootNode2.put("age", 22);
        rootNode2.put("school", "清华大学");
        rootNode2.put("home", "洛阳");
        rootNode2.put("gender", "男");
        rootNode3.put("city", "天水");
        arrayNode.add(rootNode1);
        arrayNode.add(rootNode2);
        arrayNode.add(rootNode3);
        JsonFactory jsonFactory = new JsonFactory();
        StringWriter writer = new StringWriter();
        JsonGenerator generator = jsonFactory.createGenerator(writer);
        mapper.writeTree(generator, arrayNode);
        System.out.println(writer.toString());
        // [{"name":"张三","age":22},{"name":"李四","age":22,"school":"清华大学","home":"洛阳","gender":"男"},{"city":"天水"}]
    }

    // 解析 jsonArray的String
    public static void readJsonArrayString(String str) throws JsonProcessingException {
        // 使用ObjectMapper的readValue方法将json字符串解析到JsonNode实例中
        JsonNode rootNode = mapper.readTree(str);
        // 直接从rootNode中获取某个键的值, 这种方式在如果我们只需要一个长json串中某个字段值时非常方便
        if (rootNode.isArray()) {
            int size = rootNode.size();
            for (int i = 0; i < size; i++) {
                // 获取每一个子node下面的所有的节点，包括key和value
                Iterator<Map.Entry<String, JsonNode>> fields = rootNode.get(i).fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> nodeKey = fields.next();
                    System.out.print("key: " + nodeKey.getKey() + ",  value: " + nodeKey.getValue() + "  . ");
                }
                System.out.println("  第" + i + "个节点遍历完毕！");
            }
        }
        // http://cn.voidcc.com/question/p-esesbdru-vn.html
        // http://www.itkeyword.com/doc/8040207729284257879/JsonNode-jackson-json
    }

    // 从list(map) -> jsonArray的String
    public static <K, V> String list2JsonArrayStr(List<Map<K, V>> list) throws IOException {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        // 当做根节点
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        for (int i = 0; i < list.size(); i++) {
            ObjectNode rootNode = jsonNodeFactory.objectNode();
            Set<Map.Entry<K, V>> entries = list.get(i).entrySet();
            Iterator<Map.Entry<K, V>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<K, V> kvEntry = iterator.next();
                rootNode.put(kvEntry.getKey().toString(), kvEntry.getValue().toString());
            }
            arrayNode.add(rootNode);
        }
        JsonFactory jsonFactory = new JsonFactory();
        StringWriter writer = new StringWriter();
        JsonGenerator generator = jsonFactory.createGenerator(writer);
        mapper.writeTree(generator, arrayNode);
        return writer.toString();
    }

    // 从jsonArray的String -> list(map)
    public static <K, V> List<Map<K, V>> jsonArrayStr2List(String str) throws JsonProcessingException {
        List<Map<K, V>> list = new ArrayList<>();
        JsonNode rootNode = mapper.readTree(str);
        if (rootNode.isArray()) {
            int size = rootNode.size();
            for (int i = 0; i < size; i++) {
                // 获取每一个子node下面的所有的节点，包括key和value
                Iterator<Map.Entry<String, JsonNode>> fields = rootNode.get(i).fields();
                Map<K, V> map = new HashMap<>();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> nodeKey = fields.next();
                    map.put((K) nodeKey.getKey(), (V) nodeKey.getValue().asText());
                }
                list.add(map);
            }
        }
        return list;
    }


    //================================================================================//
    //================================================================================//
    //========================使用流式API, 读写与转换jsonString内容=======================//
    //================================================================================//
    //================================================================================//

    // 使用jackson的JsonParser来解析JSON对象, 可以从字符串解析，也可以从文件解析
    public static void parse4String(String str) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(str);
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jsonParser.getCurrentName();

            if ("name".equals(fieldName)) {
                jsonParser.nextToken();
                System.out.println(jsonParser.getText());
            }
            if ("age".equals(fieldName)) {
                jsonParser.nextToken();
                System.out.println(jsonParser.getNumberValue());
            }
            if ("verified".equals(fieldName)) {
                jsonParser.nextToken();
                System.out.println(jsonParser.getBooleanValue());
            }
            if ("marks".equals(fieldName)) {
                //move to [
                jsonParser.nextToken();
                // loop till token equal to "]"
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    System.out.println(jsonParser.getNumberValue());
                }
            }
        }
    }

    // 使用jackson的JsonGenerator来构建JSON对象, 写入到字符串
    public static void generate2String() throws IOException {
        StringWriter sw = new StringWriter();
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(sw);
        // 开始写入
        jsonGenerator.writeStartObject();
        // 写入字段和值
        jsonGenerator.writeStringField("name", "eric");
        jsonGenerator.writeNumberField("age", 21);
        jsonGenerator.writeBooleanField("verified", false);
        jsonGenerator.writeStringField("name", "eric");

        // 写入Array
        jsonGenerator.writeFieldName("marks");
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(100);
        jsonGenerator.writeNumber(90);
        jsonGenerator.writeNumber(85);
        jsonGenerator.writeEndArray();

        // 结束写入
        jsonGenerator.writeEndObject();
        jsonGenerator.close();
        System.out.println(sw.toString());
    }

    // 使用jackson的JsonGenerator来构建JSON对象, 写入到文件
    public static void generate2File() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File("file.json"), JsonEncoding.UTF8);
        // 开始写入
        jsonGenerator.writeStartObject();
        // 写入字段和值
        jsonGenerator.writeStringField("name", "eric");
        jsonGenerator.writeNumberField("age", 21);
        jsonGenerator.writeBooleanField("verified", false);
        String cities = "\'上海\', \'北京\', \'东京\', \'墨尔本\', \'伦敦\', \'柏林\'";
        jsonGenerator.writeBinaryField("cities", cities.getBytes());
        jsonGenerator.writeStringField("name", "eric");

        // 写入Array
        jsonGenerator.writeFieldName("marks");
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(100);
        jsonGenerator.writeNumber(90);
        jsonGenerator.writeNumber(85);
        jsonGenerator.writeEndArray();

        // 结束写入
        jsonGenerator.writeEndObject();
        jsonGenerator.close();
    }

    /**
     * 感觉这个流式操作，不得行啊
     // list(map) -> jsonArray(jsonStr)
     public static <T> void ls2Array(List<Map<String, Object>> list) throws IOException {
     StringWriter sw = new StringWriter();
     JsonFactory jsonFactory = new JsonFactory();
     JsonGenerator generator = jsonFactory.createGenerator(sw);
     // 开始写入
     generator.writeStartObject();
     for (int i = 0; i < list.size(); i++) {
     generator.writeFieldName("hello" + i);
     generator.writeStartArray();
     generator.writeStringField("name", list.get(i).get("name").toString());
     generator.writeStringField("province", list.get(i).get("province").toString());
     generator.writeStringField("postCode", list.get(i).get("postCode").toString());
     generator.writeEndArray();
     }
     generator.writeEndObject();
     generator.close();
     System.out.println(sw.toString());

     }

     // jsonArray(jsonStr) -> list(map)
     public static <T> void array2Ls(String string) throws IOException {
     StringWriter sw = new StringWriter();
     JsonFactory jsonFactory = new JsonFactory();
     JsonParser parser = jsonFactory.createParser(string);

     // 开始解析"["
     if (parser.isExpectedStartArrayToken()) {
     String currentName = parser.getCurrentName();
     JsonToken jsonToken = parser.nextToken();
     }
     }
     **/

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ChinaBigCity {
    // 名字
    private String name;
    // 省份
    private String province;
    // 邮编
    private String postCode;
    // 面积
    private Float area;
}
