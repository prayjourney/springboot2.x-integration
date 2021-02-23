package com.zgy.bootintegration.handler;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.bootintegration.mapper.StudentMapper;
import com.zgy.bootintegration.pojo.Student;
import com.zgy.bootintegration.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: z.g.y
 * @date: 2020/6/24
 * @description: NoModelDataListener不能使用spring管理，所以需要自己注入，通过set注入
 */
@Slf4j
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {
    private static final int BATCH_COUNT = 3000;

    // JdbcTemplate template;
    // https://alibaba-easyexcel.github.io/quickstart/read.html#%E7%9B%91%E5%90%AC%E5%99%A8
    StudentMapper mapper;
    List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    //public NoModelDataListener(JdbcTemplate template) {
    //    this.template = template;
    //}
    public NoModelDataListener(StudentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        try {
            log.info("读取到一条数据: {} !", JacksonUtil.getJsonFromObject(data));
            String originStr = JacksonUtil.getJsonFromObject(data);
            String str = originStr.substring(1, originStr.length() - 1);
            String[] stringArray = str.split(",");
            Stream<String> stream = Stream.of(stringArray);
            List<String> strNotNull = stream.filter(x -> !x.contains("null")).collect(Collectors.toList());
            // 没有有效数据, 就停止操作
            if (strNotNull.size() < 2) {
                return;
            }
            // 单数才是正确的
            if (strNotNull.size() % 2 == 0) {
                List<String> subList = strNotNull.subList(0, strNotNull.size() - 1);
                deal(subList, BATCH_COUNT);
            } else {
                deal(strNotNull, BATCH_COUNT);
            }
        } catch (JsonProcessingException | JSONException e) {
            log.error("错误原因: " + e.getMessage());
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("导入数据处理完毕!");
    }

    public void deal(List<String> list, Integer maxSize) throws JSONException {
        Stream<String> stream = list.stream();
        List<String> stringList = stream.map(x -> {
            int index = x.indexOf(':');
            String temp = x.substring(index + 2, x.length() - 1);
            if (temp.contains("（")) {
                return temp.substring(0, temp.indexOf("（"));
            }
            return temp;
        }).skip(0).collect(Collectors.toList());

        String query = stringList.get(0);
        JSONArray array = new JSONArray();
        for (int i = 1; i < stringList.size(); ) {
            JSONObject object = new JSONObject();
            object.put("word", stringList.get(i));
            object.put("type", stringList.get(i + 1));
            i += 2;
            array.put(object);
        }
        log.info("query : {}, stringArray: {} !", query, array.toString());
        save(query, array.toString());

    }

    public void save(String query, String entities) {
        // jdbctemplate
        // String sql = "select * from ner_test_data";
        // List<Map<String, Object>> mapList = template.queryForList(sql);
        // System.out.println(mapList.size());

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("stId", -100);
        Integer count = mapper.selectCount(queryWrapper);
        log.info("count===>{}", count);
    }
}
