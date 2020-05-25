package com.wm.zgy.bootmybatismbplusshiroesquartz.service;

import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.Book;
import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.MathTeacher;
import com.wm.zgy.bootmybatismbplusshiroesquartz.utils.JSONUtil;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    // 创建book一个文档
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
    public boolean existsDocument(String indexName, String id) throws IOException {
        GetRequest request = new GetRequest(indexName, id);
        // 不获取上下文 _source
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        return exists;

    }

    // 简单获取文档内容
    public String getDocument(String indexName, String id) throws IOException {
        GetRequest request = new GetRequest(indexName, id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        return response.getSourceAsString();
    }

    // 删除一个文档
    public int deleteDocument(String indexName, String id) throws IOException {
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
    public <T> int batchAddDocument(List<T> as, String indexName, Integer curStart) throws IOException {
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
    public <T> int batchDeleteDocument(String indexName, List<T> ids) throws IOException {
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

    /**
     * 批量更新，这个不行，有点问题，更新不到我们想要的字段上面去
     *
     * @param as
     * @param indexName
     * @param ids
     * @param <T>
     * @param <N>
     * @return
     * @throws IOException
     */
    // 批量更新
    public <T, N> int batchUpdateDocument(List<T> as, String indexName, List<N> ids) throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout(TimeValue.timeValueSeconds(10));
        // 批量处理
        for (int i = 0; i < ids.size(); i++) {
            // map不会冲掉，String会冲掉，更新的部分，放在map之中，使用key-value键值对，更加好一些
            request.add(new UpdateRequest(indexName, String.valueOf(ids.get(i))).doc(as.get(i), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.status().getStatus());
        return bulkResponse.status().getStatus();
    }

    // 查询所有的BookDocument
    public void searchAllDocument(String indexName) throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        // 构造查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询条件
        // QueryBuilders来构造条件查询
        // QueryBuilders.termQuery()实现精确查询
        // TermQueryBuilder queryBuilder = QueryBuilders.termQuery();
        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(10));
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(JSONUtil.getJsonFromObject(response.getHits()));
    }

    // 按照名字精确查询Document
    public void searchDocumentByName(String indexName, String name) throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        // 构造查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询条件，这儿的name,是我们的文档的字段名称
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("gender", name);
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(10));
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(JSONUtil.getJsonFromObject(response.getHits()));
    }


    // 批量更新，这个可以正常更新
    public int batchUpdateMathTeacherDocument(String indexName, List<String> ids, List<Map<String, Object>> contents) throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout(TimeValue.timeValueSeconds(10));
        // 批量处理
        for (int i = 0; i < ids.size(); i++) {
            request.add(new UpdateRequest(indexName, ids.get(i)).doc(contents.get(i), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.status().getStatus());
        return bulkResponse.status().getStatus();
    }
    // 批量更新，这样会导致全量的更新，还是map的方式最好
    public int batchUpdateMathTeacherDocument2(String indexName, List<String> ids, List<MathTeacher> contents) throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout(TimeValue.timeValueSeconds(10));
        // 批量处理
        for (int i = 0; i < ids.size(); i++) {
            request.add(new UpdateRequest(indexName, ids.get(i)).doc(JSONUtil.getJsonFromObject(contents.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.status().getStatus());
        return bulkResponse.status().getStatus();
    }

    // 去重计数，计算每个作者的书籍量
    public void searchCount(String indexName, String author) throws IOException {
        CountRequest countRequest = new CountRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        countRequest.source(searchSourceBuilder);
    }

    /**
    GET kuangsheng/user/_search
    {
        "size": 0,
            "aggs":
        {
            "mytest1":{
            "max":{
                "field": "age"
            }
        }
        }
    }*/
    // 聚合操作, 目前有问题
    public void aggDocumentMax(String indexName, String filedName) throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        // request.searchType(typeName);
        // 构造查询条件, 不管是聚合还是查询，都是通过GET，后面都是一个_search, 所以就需要构建SearchRequest
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询条件,这个使用的是聚合的Builders和Builder
        MaxAggregationBuilder maxBuilder = AggregationBuilders.max(filedName);

        searchSourceBuilder.aggregation(maxBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(10));
        //request.searchType(SearchType.DEFAULT);
        request.indices(indexName);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(JSONUtil.getJsonFromObject(response.getHits()));
        System.out.println(JSONUtil.getJsonFromObject(response.getAggregations()));
    }

}
