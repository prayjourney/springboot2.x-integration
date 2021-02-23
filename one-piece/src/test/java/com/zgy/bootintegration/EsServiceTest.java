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
 * @date: 2020/5/26
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsServiceTest {
    @Autowired
    ElasticSearchService esService;


    @Test
    public void testUpdateBookDocumentByMap() throws IOException {
        esService.updateBookDocumentByName("books", "author", "吴承恩");
    }

    @Test
    public void batchUpdateDocument() {

    }

    @Test
    public void nullFieldTest() throws IOException {
        esService.checkNullField("kuangsheng", 200);
    }

}
