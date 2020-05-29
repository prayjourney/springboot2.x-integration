package com.wm.zgy.bootmybatismbplusshiroesquartz;

import com.wm.zgy.bootmybatismbplusshiroesquartz.service.ElasticSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Author renjiaxin
 * @Date 2020/5/29
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsScrollTest {
    @Autowired
    ElasticSearchService esService;


    @Test
    public void testAggDocumentMax() throws IOException {
        esService.testScroll("kuangsheng");

    }
}