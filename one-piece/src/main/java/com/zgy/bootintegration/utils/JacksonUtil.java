package com.zgy.bootintegration.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JacksonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    //================================================================================//
    //========================使用ObjectMapper, 来完成解析和转换==========================//
    //================================================================================//

    // obj -> jsonStr
    @Deprecated
    public static String getJsonFromObject(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    // map -> jsonStr
    @Deprecated
    public static String getJsonStrFromMap(Map<String, Object> map) throws JsonProcessingException {
        return mapper.writeValueAsString(map);
    }

    // jsonStr -> obj
    public static Object str2Obj(String str, Class cls) throws JsonProcessingException {
        Object obj = mapper.readValue(str, cls);
        return obj;
    }

    // obj -> jsonStr
    public static String obj2Str(Object obj) throws JsonProcessingException {
        String str = mapper.writeValueAsString(obj);
        return str;
    }

    // map -> jsonStr
    public static String map2Str(Map<String, Object> map) throws JsonProcessingException {
        String str = mapper.writeValueAsString(map);
        return str;
    }

    // jsonStr -> map
    public static Map<String, Object> str2Map(String str) throws JsonProcessingException {
        Map<String, Object> map = mapper.readValue(str, Map.class);
        return map;
    }

    // obj 序列化为json文件
    public static void objWrite2JSON(Object obj, String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), obj);
    }

    // obj 反序列化，从json文件中获取对象
    public static Object readJSON2Obj(File file, Class cls) throws IOException {
        Object object = mapper.readValue(file, cls);
        return object;
    }


    //================================================================================//
    //========================使用树形模型, 读写与转换jsonString内容=======================//
    //================================================================================//

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

}
