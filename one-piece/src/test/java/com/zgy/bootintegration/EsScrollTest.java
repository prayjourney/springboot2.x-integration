package com.zgy.bootintegration;

import com.zgy.bootintegration.service.ElasticSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: z.g.y
 * @date: 2020/5/29
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsScrollTest {
    @Autowired
    ElasticSearchService esService;


    @Test
    public void testSearchUseScroll() throws IOException {
        esService.searchUseScroll("kuangsheng");

    }

    @Test
    public void testAggUsePartition() throws IOException {
        Map<String, Integer> kuangsheng = esService.aggUsePartition("kuangsheng");
        Iterator<Map.Entry<String, Integer>> iterator = kuangsheng.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> map = iterator.next();
            System.out.println("key : " + map.getKey() + ", value : " + map.getValue());
        }


    }

    @Test
    public void testAggUsePartitionWithScroll() throws IOException {
        esService.aggUsePartitionWithScroll("kuangsheng", 10, 10, 2);
    }


    @Test
    public void testMultiCompositeBuckets() throws IOException {
        esService.multiCompositeBuckets("kuangsheng");

    }

    @Test
    public void testSearchSort01() throws IOException {
        esService.searchSort01("kuangsheng", 5, null);
    }

    @Test
    public void testSearchSort02() throws IOException {
        esService.searchSort02("kuangsheng", 5, null);
    }
}