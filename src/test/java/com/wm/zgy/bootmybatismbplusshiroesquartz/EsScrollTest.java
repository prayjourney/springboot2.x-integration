package com.wm.zgy.bootmybatismbplusshiroesquartz;

import com.wm.zgy.bootmybatismbplusshiroesquartz.service.ElasticSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

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
    public void testSearchUseScroll() throws IOException {
        esService.searchUseScroll("kuangsheng");

    }

    @Test
    public void testAggUsePartition() throws IOException {
        Map<String, Integer> kuangsheng = esService.aggUsePartition("kuangsheng");
        Iterator<Map.Entry<String, Integer>> iterator = kuangsheng.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> map = iterator.next();
            System.out.println("key : " + map.getKey() + ", value : " +map.getValue());
        }


    }
}