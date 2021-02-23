package com.zgy.bootintegration;

import com.zgy.bootintegration.pojo.MathTeacher;
import com.zgy.bootintegration.service.ElasticSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/22 2:41
 * @modified:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MathTeacherEsTest {
    @Autowired
    @Qualifier("elasticSearchService")
    ElasticSearchService esService;

    @Test
    public void testBatchAddDocument() throws IOException {
        List<MathTeacher> teachers = new ArrayList<>();
        teachers.add(MathTeacher.builder().id(1).name("张三").gender("男").hobby("打麻将").build());
        teachers.add(MathTeacher.builder().id(2).name("lili").gender("女").build());
        teachers.add(MathTeacher.builder().id(3).name("jhon").gender("男").hobby("冲浪").build());
        teachers.add(MathTeacher.builder().id(4).name("王二麻子").hobby("打麻将").build());
        teachers.add(MathTeacher.builder().id(5).name("李秀丽").gender("女").hobby("做饭").build());
        esService.batchAddDocument(teachers, "teachers", 100);

    }

    @Test
    public void testBatchUpdateMathTeacherDocument() throws IOException {
        List<String> ids = Arrays.asList("100", "101", "103");
        List<Map<String, Object>> maps = new ArrayList<>();
        HashMap<String, Object> mp1 = new HashMap<>();
        HashMap<String, Object> mp2 = new HashMap<>();
        HashMap<String, Object> mp3 = new HashMap<>();
        // 实现精确的更新，这样的话，性别字段的内容不会被覆盖，也不会被更新，还是原来的内容，没有做修改
        mp1.put("name", "zhangsan====");
        mp1.put("hobby", "吃瓜子==");
        mp1.put("id", "123");
        mp2.put("hobby", "烹饪， 打麻将， 旅游");
        mp3.put("gender", "第三性");
        maps.add(mp1);
        maps.add(mp2);
        maps.add(mp3);
        esService.batchUpdateMathTeacherDocument("teachers", ids, maps);
    }

    // 批量更新，这样会导致全量的更新，还是map的方式最好，可以精确更新，其他的都不会变
    @Test
    public void testBatchUpdateMathTeacherDocument2() throws IOException {
        List<String> ids = Arrays.asList("100", "101", "103");
        List<MathTeacher> mts = new ArrayList<>();
        MathTeacher lisi = MathTeacher.builder().id(100).name("lisi").hobby("抠脚").gender("boy").build();
        MathTeacher kristina = MathTeacher.builder().id(101).name("kristina").hobby("参加海天盛筵").build();
        MathTeacher wangwu = MathTeacher.builder().id(103).gender("女").hobby("movie").build();
        mts.add(lisi);
        mts.add(kristina);
        mts.add(wangwu);
        esService.batchUpdateMathTeacherDocument2("teachers", ids, mts);
    }

    @Test
    public void searchBookDocument() throws IOException {
        esService.searchAllDocument("teachers");
    }

    @Test
    public void searchBookDocumentByName() throws IOException {
        esService.searchDocumentByName("teachers", "girl");
        // esService.searchDocumentByName("teachers","boy");  //可以查询出来
    }
}
