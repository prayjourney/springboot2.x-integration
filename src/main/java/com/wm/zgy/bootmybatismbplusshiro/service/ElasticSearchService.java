package com.wm.zgy.bootmybatismbplusshiro.service;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author renjiaxin
 * @Date 2020/5/21
 * @Description
 */
@Service
public class ElasticSearchService {
    @Autowired
    @Qualifier("restHighLevelClient")   //限定名字和我们的esconfig之中的一致
    private RestHighLevelClient client;

    // 创建index
    public void createIndex(String indexName) throws IOException {
        // 1.创建请求索引
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        // 2.客户端执行请求 IndicesClient, 请求后获得响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);

    }
}
