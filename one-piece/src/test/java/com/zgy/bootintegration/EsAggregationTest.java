package com.zgy.bootintegration;

import com.zgy.bootintegration.service.ElasticSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/25 2:42
 * @modified:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsAggregationTest {
    @Autowired
    ElasticSearchService esService;

    @Test
    public void testAggDocumentMax() throws IOException {
        esService.aggDocumentMax("kuangsheng", "age");

    }

    @Test
    public void testAggDocumentMax02() throws IOException {
        esService.aggDocumentMax02("kuangsheng", "age");

    }

    @Test
    public void testAggDocumentMax03() throws IOException {
        String q1 = "{\"size\":0,\"aggs\":{\"the_terms\":{\"terms\":{\"size\":10,\"field\":\"age\"}}}}";
        String myQuery = "{size:0, aggs:{the_terms:{terms:{size:10,field:age}}}}";
        esService.aggDocumentMax03("kuangsheng", "age", myQuery);

    }

    @Test
    public void testAggDocumentMax04() throws IOException {
        esService.aggDocumentMax04("kuangsheng");

    }


//    @Test
//    public void testAggDocumentMax05() throws IOException {
//        esService.agg1();
//
//    }

    @Test
    public void testAggDocumentMax05() throws IOException {
        esService.aggDocumentMax05("kuangsheng");

    }

    // 这个是可以查出来我们想要的值得
    @Test
    public void testAggDocumentMax06() throws IOException {
        esService.aggDocumentMax06("kuangsheng");

    }

    @Test
    public void testAggDocumentGroupBy01() throws IOException {
        esService.aggDocumentGroupBy("kuangsheng");

    }

    @Test
    public void testAggDocumentGroupBy02() throws IOException {
        esService.aggDocumentGroupBy02("kuangsheng");

    }
}

