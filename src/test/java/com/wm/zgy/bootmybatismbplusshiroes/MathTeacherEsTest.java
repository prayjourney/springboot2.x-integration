package com.wm.zgy.bootmybatismbplusshiroes;

import com.wm.zgy.bootmybatismbplusshiroes.pojo.MathTeacher;
import com.wm.zgy.bootmybatismbplusshiroes.service.ElasticSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/22 2:41
 * @Modified by:
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
        teachers.add(MathTeacher.builder().id(2).name("lili").gender("女").hobby("旅游").build());
        teachers.add(MathTeacher.builder().id(3).name("jhon").gender("男").hobby("冲浪").build());
        teachers.add(MathTeacher.builder().id(3).name("王二麻子").gender("男").hobby("打麻将").build());
        teachers.add(MathTeacher.builder().id(3).name("李秀丽").gender("女").hobby("做饭").build());
        esService.batchAddDocument(teachers, "teachers", 100);

    }
}
