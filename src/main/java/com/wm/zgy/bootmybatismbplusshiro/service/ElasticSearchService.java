package com.wm.zgy.bootmybatismbplusshiro.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wm.zgy.bootmybatismbplusshiro.pojo.Book;
import com.wm.zgy.bootmybatismbplusshiro.utils.JSONUtil;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public boolean createIndex(String indexName) throws IOException {
        // 1.创建请求索引
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        // 2.客户端执行请求 IndicesClient, 请求后获得响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();

    }

    // 测试index是否存在
    public boolean getIndex(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    // 删除index
    public boolean deleteIndex(String indexId) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexId);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    // 创建一个文档
    public int addBookDocument(Book book, String indexName, String id, Integer timeOut) throws IOException {
        // 创建请求
        IndexRequest request = new IndexRequest(indexName);

        // 规则 put /hello/_doc/1
        request.id(id);
        request.timeout(TimeValue.timeValueSeconds(timeOut));

        // 将数据放入到请求之中
        String bookJsonStr = JSONUtil.getJsonFromObject(book);
        System.out.println(bookJsonStr);
        request.source(bookJsonStr, XContentType.JSON);

        // 向客户端发送请求，获取响应的结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
        return response.status().getStatus();

    }

    // 测试文档是否存在
    public boolean existsBookDocument(String indexName, String id) throws IOException {
        GetRequest request = new GetRequest(indexName, id);
        // 不获取上下文 _source
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        return exists;

    }

    // 简单获取文档内容
    public String getBookDocument(String indexName, String id) throws IOException {
        GetRequest request = new GetRequest(indexName, id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        return response.getSourceAsString();
    }

    // 删除一个文档
    public int deleteBookDocument(String indexName, String id) throws IOException {
        DeleteRequest request = new DeleteRequest(indexName, id);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return response.status().getStatus();
    }

    // 修改一个文档
    // 会把其他的部分冲掉，覆盖其他部分，是一个全量的更新，而非增量更新
    public int updateBookDocument(Book book, String indexName, String id, Integer timeOut) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, id);
        request.timeout(TimeValue.timeValueSeconds(timeOut));
        request.doc(JSONUtil.getJsonFromObject(book), XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return response.status().getStatus();
    }

    // 修改一个文档, 使用map更新
    // 不会吧其他的部分充掉，精确的更新
    public int updateBookDocumentByMap(Map<String, Object> map, String indexName, String id, Integer timeOut) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, id);
        request.timeout(TimeValue.timeValueSeconds(timeOut));
        request.doc(map, XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return response.status().getStatus();
    }

    // 批量插入文档
    public <T> int batchAddBookDocument(List<T> as, String indexName, Integer curStart) throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout(TimeValue.timeValueSeconds(10));
        // 批量处理
        for (int i = 0; i < as.size(); i++) {
            request.add(
                    new IndexRequest(indexName)
                            .id("" + (curStart + i))
                            .source(JSONUtil.getJsonFromObject(as.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.status().getStatus());
        return bulkResponse.status().getStatus();
    }

    // 批量删除
    public <T> int batchDeleteBookDocument(String indexName, List<T> ids) throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout(TimeValue.timeValueSeconds(10));
        // 批量处理
        for (int i = 0; i < ids.size(); i++) {
            request.add(new DeleteRequest(indexName).id(String.valueOf(ids.get(i))));
        }
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.status().getStatus());
        return bulkResponse.status().getStatus();
    }
}
