package com.zgy.bootintegration;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.dataformat.smile.SmileGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author renjiaxin
 * @Date 2020/7/6
 * @Description 我们所说的String， 都是符合json格式的string
 */
public class JacksonLearn {
    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        // str -> obj
        String jsonStr = "{\"name\": \"北京\", \"province\": \"北京\", \"postCode\": \"010\", \"area\": 192345.9}";
        str2Obj(jsonStr);

        // obj -> str
        ChinaBigCity bigCity = ChinaBigCity.builder().name("南京").province("江苏").postCode("331908").area(189345.6f).build();
        obj2Str(bigCity);

        // map -> str
        Map<String, Object> map = new HashMap<>();
        map.put("name", "hello");
        map.put("ls", Arrays.asList("张三","李四","Lily","小明"));
        map.put("city", ChinaBigCity.builder().name("成都").postCode("232341").province("四川省").area(476522.f).build());
        map.put("array", new Integer[]{1, 2, 34, 55});
        map2Str(map);

        // str -> map
        String str ="{\"city\":{\"name\":\"成都\",\"province\":\"四川省\",\"postCode\":\"232341\",\"area\":476522.0}," +
                "\"ls\":[\"张三\",\"李四\",\"Lily\",\"小明\"],\"name\":\"hello\"}";
        str2Map(str);

        // obj -> 序列化为json file
        objWrite2JSON(bigCity, "chinabigcity.json");

        // json file 反序列化 -> obj
        File file = new File("chinabigcity.json");
        readJSON2Obj(file, ChinaBigCity.class);

        // 使用jackson树模型来读取json str内容
        String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        readTree(jsonString);

        // 使用jackson树模型来写入内容到json str
        writeTree();

        // 使用JsonParser+JsonFactory 来解析 json Str
        String parseString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        parse4String(parseString);

        // 使用JsonGenerator+JsonFactory 来生成 json Str, 写到文件, 写到字符串
        generate2File();
        generate2String();

    }


    //================================================================================//
    //================================================================================//
    //========================使用ObjectMapper，来读写json str的内容======================//
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
        String name = (String)map.get("name");
        List<String> ls = (List<String>) map.get("ls");
        Object city = map.get("city");
        System.out.println(name + ", " +ls.size());
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


    //================================================================================//
    //================================================================================//
    //========================使用树形模型，来读写json str的内容===========================//
    //================================================================================//
    //================================================================================//

    // 使用jackson的树模型, 读取jsonStr的内容
    public static void readTree(String str) throws JsonProcessingException {
        // 使用JSON构建JsonNode树对象
        JsonNode rootNode = mapper.readTree(str);
        // 遍历Tree
        Iterator<JsonNode> iterator = rootNode.elements();
        while(iterator.hasNext()){
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
        rootNode.put("token","123");
        rootNode.put("name","张三");
        rootNode.put("age",22);
        // 往根节点中添加一个子对象
        ObjectNode petsNode = jsonNodeFactory.objectNode();
        petsNode.put("petName","kitty")
                .put("petAge",3);
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

    //================================================================================//
    //================================================================================//
    //========================使用流式API，来读写json str的内容===========================//
    //================================================================================//
    //================================================================================//

    // 使用jackson的JsonParser来解析JSON对象, 可以从字符串解析，也可以从文件解析
    public static void parse4String(String str) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(str);
        while (jsonParser.nextToken()!= JsonToken.END_OBJECT){
            String fieldName = jsonParser.getCurrentName();

            if ("name".equals(fieldName)) {
                jsonParser.nextToken();
                System.out.println(jsonParser.getText());
            }
            if("age".equals(fieldName)){
                jsonParser.nextToken();
                System.out.println(jsonParser.getNumberValue());
            }
            if("verified".equals(fieldName)){
                jsonParser.nextToken();
                System.out.println(jsonParser.getBooleanValue());
            }
            if("marks".equals(fieldName)){
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
