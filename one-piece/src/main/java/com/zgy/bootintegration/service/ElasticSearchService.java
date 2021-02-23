package com.zgy.bootintegration.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zgy.bootintegration.mapper.SearchLocationMapper;
import com.zgy.bootintegration.pojo.Book;
import com.zgy.bootintegration.pojo.MathTeacher;
import com.zgy.bootintegration.pojo.SearchLocation;
import com.zgy.bootintegration.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.ParsedComposite;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.IncludeExclude;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedMax;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @author: z.g.y
 * @date: 2020/5/21
 * @description:
 */
@Service
@Slf4j
public class ElasticSearchService {
    @Autowired
    @Qualifier("restHighLevelClient")   //限定名字和我们的esconfig之中的一致
    private RestHighLevelClient client;

    @Autowired
    private SearchLocationMapper searchLocationMapper;

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
        String bookJsonStr = JacksonUtil.getJsonFromObject(book);
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
        request.doc(JacksonUtil.getJsonFromObject(book), XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return response.status().getStatus();
    }

    // 修改一个文档, 使用map更新
    // 不会把其他的部分充掉，精确的更新
    public int updateBookDocumentByMap(Map<String, Object> map, String indexName, String id, Integer timeOut) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, id);
        request.timeout(TimeValue.timeValueSeconds(timeOut));
        request.doc(map, XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return response.status().getStatus();
    }

    // 按照单一的条件去搜索, Map<String, Object> map,
    public int updateBookDocumentByName(String indexName, String filedName, String name) throws IOException {
        // 先查出来
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder builder = new MatchQueryBuilder(filedName, name);
        sourceBuilder.query(builder);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            sourceAsMap.put("name", "西游记1");
            // 这个id是book的id，而update的时候，是需要整个文档的_id的  _id = hit.getId()
            // String id = sourceAsMap.get("id").toString();
            String id = hit.getId();
            updateBookDocumentByMap(sourceAsMap, "books", id, 10);
        }
        return 1;
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
                            .source(JacksonUtil.getJsonFromObject(as.get(i)), XContentType.JSON)
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
        MatchAllQueryBuilder queryBuilder = matchAllQuery();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(10));
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(JacksonUtil.getJsonFromObject(response.getHits()));
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
        System.out.println(JacksonUtil.getJsonFromObject(response.getHits()));
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
            request.add(new UpdateRequest(indexName, ids.get(i)).doc(JacksonUtil.getJsonFromObject(contents.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.status().getStatus());
        return bulkResponse.status().getStatus();
    }

    // 去重计数，计算每个作者的书籍量
    public void searchCount(String indexName, String author) throws IOException {
        CountRequest countRequest = new CountRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchAllQuery());
        countRequest.source(searchSourceBuilder);
    }

    /**
     * GET kuangsheng/user/_search
     * {
     * "size": 0,
     * "aggs":
     * {
     * "mytest1":{
     * "max":{
     * "field": "age"
     * }
     * }
     * }
     * }
     */
    // 聚合操作, 目前有问题
    public void aggDocumentMax(String indexName, String filedName) throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        // request.searchType(typeName);
        // 构造查询条件, 不管是聚合还是查询，都是通过GET，后面都是一个_search, 所以就需要构建SearchRequest
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询条件,这个使用的是聚合的Builders和Builder
        // hello-mmm是取得一个名字，而不是字段
        MaxAggregationBuilder maxBuilder = AggregationBuilders.max("hello-mmm").field(filedName);
//        XContentBuilder builder = new XContentBuilder.Writer();
//        ToXContent.Params params = new ToXContent.Params()
//        maxBuilder.doXContentBody(builder,"age");

        searchSourceBuilder.aggregation(maxBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(10));
        //request.searchType(SearchType.DEFAULT);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(JacksonUtil.getJsonFromObject(response.getHits()));
        System.out.println(JacksonUtil.getJsonFromObject(response.getAggregations()));
    }

    // 聚合操作, 目前有问题
    public void aggDocumentMax02(String indexName, String filedName) throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.indices(indexName);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max(filedName);
        searchSourceBuilder.aggregation(maxAggregationBuilder).size(0);


        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("返回结果:" + response);
    }

    // 聚合操作, 目前有问题
    public void aggDocumentMax03(String indexName, String filedName, String myQueryString) throws IOException {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery(myQueryString));

        TermsAggregationBuilder aggregation = AggregationBuilders.terms("test")
                .field(filedName + ".keyword").size(0);
        aggregation.subAggregation(AggregationBuilders.count("count"));
        searchSourceBuilder.aggregation(aggregation);


        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);


        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        Aggregations aggregations = searchResponse.getAggregations();
        Terms byMsisdn = aggregations.get("test");
    }

    // 聚合操作, 目前有问题
    public void aggDocumentMax04(String indexName) throws IOException {


        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询条件
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("age.keyword", 23));
        //统计条件
        TermsAggregationBuilder serviceLineAgg = AggregationBuilders.
                terms("hello").field("age.keyword").size(30);
        TermsAggregationBuilder appNameAgg = AggregationBuilders.terms("hello2").
                field("age.keyword").size(30);

        searchSourceBuilder.query(query).size(0);
        searchSourceBuilder.aggregation(serviceLineAgg.subAggregation(appNameAgg)).size(0);


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexName);
        searchRequest.source(searchSourceBuilder);
        System.out.println(searchRequest);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);


    }

    // https://blog.csdn.net/zhangshng/article/details/95946596
    // 聚合操作, 目前有问题
    public void aggDocumentMax05(String indexName) throws IOException {

        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // TermsAggregationBuilder aggregation = AggregationBuilders.terms("ddddd").field("age.keyword");
        // aggregation.subAggregation(AggregationBuilders.max("dddddsf").field("age"));
        // searchSourceBuilder.aggregation(aggregation);
        MaxAggregationBuilder aggregation1 = AggregationBuilders.max("hello-test").field("age");
        searchSourceBuilder.aggregation(aggregation1);
        searchRequest.source(searchSourceBuilder);
        System.out.println("==================");
        System.out.println(searchSourceBuilder.toString());
        System.out.println("==================");


        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getAggregations().getAsMap().get("hello-test"));
        ParsedMax sss = (ParsedMax) searchResponse.getAggregations().asList().get(0);
        System.out.println(sss.getValue());

        /**
         Map<String, Aggregation> serviceLineMap = searchResponse.getAggregations().asMap();
         ParsedStringTerms serviceLineTerms = (ParsedStringTerms) serviceLineMap.get("dddddsf");
         List serviceLists = serviceLineTerms.getBuckets();
         for (Object serviceList : serviceLists) {
         ParsedStringTerms.ParsedBucket serviceListObj = (ParsedStringTerms.ParsedBucket) serviceList;
         String serviceLine = serviceListObj.getKeyAsString();
         Map<String, Aggregation> appNameMap = serviceListObj.getAggregations().asMap();
         ParsedStringTerms appNameTerms = (ParsedStringTerms) appNameMap.get("dddddsf");
         List appNameLists = appNameTerms.getBuckets();
         for (Object appNameList : appNameLists) {
         ParsedStringTerms.ParsedBucket appNameObj = (ParsedStringTerms.ParsedBucket) appNameList;
         String appName = appNameObj.getKeyAsString();
         Long count = appNameObj.getDocCount();
         System.out.println(serviceLine);
         System.out.println(appName);
         System.out.println(count);
         }
         }*/

    }

    // 聚合操作： 求取最大的数===》OKAY
    public void aggDocumentMax06(String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //        TermsAggregationBuilder aggregation = AggregationBuilders.terms("ddddd")
        //                .field("age.keyword");
        //        aggregation.subAggregation(AggregationBuilders.max("dddddsf")
        //                .field("age"));
        //        searchSourceBuilder.aggregation(aggregation);
        MaxAggregationBuilder aggregation1 = AggregationBuilders.max("hello-test").field("age");
        searchSourceBuilder.aggregation(aggregation1);
        searchRequest.source(searchSourceBuilder);
        System.out.println("==================");
        System.out.println(searchSourceBuilder.toString());
        System.out.println("==================");


        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        // 通过map的方式可以看到值，但是取不到，转换成list
        // System.out.println(searchResponse.getAggregations().getAsMap().get("hello-test"));
        ParsedMax sss = (ParsedMax) searchResponse.getAggregations().asList().get(0);
        System.out.println(sss.getValue());

    }


    // 聚合操作：Group By 的分组统计===》OKAY
    public void aggDocumentGroupBy(String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder termsAggregation = AggregationBuilders.terms("age-first").field("age");
        // 有多个需要操作的时候，先后顺序
        // termsAggregation.subAggregation(AggregationBuilders.max("test2").field("age"));
        // searchSourceBuilder.aggregation(aggregation);
        searchSourceBuilder.aggregation(termsAggregation);
        searchRequest.source(searchSourceBuilder);
        System.out.println("==================");
        System.out.println(searchSourceBuilder.toString());
        System.out.println("==================");


        // 解析数据
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        ParsedLongTerms longTerms = (ParsedLongTerms) searchResponse.getAggregations().asList().get(0);
        System.out.println(longTerms);
        List<? extends Terms.Bucket> buckets = longTerms.getBuckets();
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println(buckets.get(i).getKey() + ", " + buckets.get(i).getDocCount());
        }

    }

    // 聚合操作：Group By 的分组统计, 桶的数量不够了
    public void aggDocumentGroupBy02(String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 设置了
        TermsAggregationBuilder termsAggregation = AggregationBuilders.terms("age_first").field("age").size(15);
        searchSourceBuilder.aggregation(termsAggregation);
        searchRequest.source(searchSourceBuilder);
        System.out.println("==================");
        System.out.println(searchSourceBuilder.toString());
        System.out.println("==================");


        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        // 解析数据，桶不够了
        /**
         ParsedLongTerms longTerms = (ParsedLongTerms) searchResponse.getAggregations().asList().get(0);
         System.out.println(longTerms);
         List<? extends Terms.Bucket> buckets = longTerms.getBuckets();
         for (int i = 0; i < buckets.size(); i++) {
         System.out.println(buckets.get(i).getKey() + ", " +buckets.get(i).getDocCount());
         }
         */

        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();
        ParsedLongTerms age_first = (ParsedLongTerms) aggregationMap.get("age_first");
        for (Terms.Bucket groupByAgeBucket : age_first.getBuckets()) {
            System.out.println(groupByAgeBucket.getKey() + ":" + groupByAgeBucket.getDocCount());
        }

    }

    // scroll适合于查询，对于聚合，当桶特别多的时候，它并不适用
    public void searchUseScroll(String indexName) {
        // 初始化scroll
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L)); //设定滚动时间间隔
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchAllQuery());
        searchSourceBuilder.size(5); //设定每次返回多少条数据
        searchRequest.source(searchSourceBuilder);


        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        System.out.println("-----首页-----");

        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getSourceAsString());
        }

        //遍历搜索命中的数据，直到没有数据
        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            try {
                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
            if (searchHits != null && searchHits.length > 0) {
                System.out.println("-----下一页-----");
                for (SearchHit searchHit : searchHits) {
                    System.out.println(searchHit.getSourceAsString());
                }
            }

        }

        //清除滚屏
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);//也可以选择setScrollIds()将多个scrollId一起使用
        ClearScrollResponse clearScrollResponse = null;
        try {
            clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean succeeded = clearScrollResponse.isSucceeded();
        System.out.println("succeeded:" + succeeded);

    }


    // 当桶特别多的时候, scroll不适用, 所以需要使用partition, 把数据分片, 然后自己去组装结果
    public Map<String, Integer> aggUsePartition(String indexName) throws IOException {
        Map<String, Integer> resultTemp = new HashMap<>(); //存放临时数据，等待合并

        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        int partitionNo = 6;
        for (int i = 0; i < partitionNo; i++) {
            // 聚类的时候，一个里面放5个数据
            String name = "hello-age" + i;
            TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms(name).field("age").size(3);
            // partition, 将数据分为10份，每次自己去组装
            IncludeExclude includeExclude = new IncludeExclude(i, partitionNo);
            termsAggregationBuilder.includeExclude(includeExclude);
            searchSourceBuilder.aggregation(termsAggregationBuilder);
            searchSourceBuilder.size(10); //设定每次返回多少条数据，要放得下每页返回的数量
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            ParsedLongTerms longTerms = (ParsedLongTerms) searchResponse.getAggregations().getAsMap().get(name);

            for (int loc = 0; loc < longTerms.getBuckets().size(); loc++) {
                String age = String.valueOf(longTerms.getBuckets().get(loc).getKey());
                Integer ageCountPeople = Math.toIntExact(longTerms.getBuckets().get(loc).getDocCount());
                System.out.println("age: " + age + ", ageCountPeople: " + ageCountPeople);
                if (resultTemp.containsKey(age)) {
                    Integer tempValue = resultTemp.get(age);
                    resultTemp.put(age, (tempValue + ageCountPeople));
                }
                resultTemp.put(age, ageCountPeople);
            }

            /*
            // 合并处理结果
            if (null == result || result.size() == 0) {
                result.putAll(resultTemp);
            } else {
                Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator();
                Set<String> resultTempKeySet = resultTemp.keySet();
                while (iterator.hasNext()) {
                    String key = iterator.next().getKey();
                    if (resultTempKeySet.contains(key)) {
                        Integer value = result.get(key) + resultTemp.get(key);
                        result.put(key, value);
                    }
                }
            }*/
        }

        return resultTemp;

    }

    // update: 我的感觉，这样是不行的，就是说，在聚合的时候，在聚合的结果里面去分页，这样不行
    // 为什么不行，简单来说，agg聚合操作和搜索操作的结果是分开的，一个在aggs下面，一个在searchhit里面，所以这样不行
    // 从实际的业务而言，scroll和partition已经足够了，他们面对的是不同的场景，scroll面对的是搜索，而partition面对的是聚合
    // 搜索：结果很多，然后每个窗口有大小限制，那这个时候，我们可以设置搜索的窗口的上限，然后再去分页，每一页里面不要超过这个上限即可，这是搜索
    // 聚合：结果很多，我们也可以调整桶的大小，然后再去使用partition，相当于我们把所有的要聚合的结果，分了n份，每一分差不多多，然后我们对每一份
    //      去聚合，然后这个时候，结果可能会重复，那么我们就需要自己去手动去重，统计聚合的结果，这两个在本质上，都是一样的。
    // 业务：从业务出发，比如说有10万条工作岗位，每个工作有行业，城市，公司的属性区别。行业，城市，其实就那么多，多的是公司和工作岗位，工作岗位可能
    //      会有10万个，可能会有100万，甚至上亿也不奇怪，公司也很多，如果有100万个公司，如果我们要按照公司去聚合，那么每一个公司就是一个桶，我们
    //      聚合出来的结果，比如是每个公司有几个岗位，那么就是公司id，对应公司的招聘工作数量，这个时候，有多少个公司，就有多少个桶，这个是没有办法
    //      改变的，因为数据实实在在就是这么多，如果非要按照公司id，对应公司招聘职位的数量，这个方式去聚合，那么能做的，就是扩大桶的数量，然后呢，
    //      我们根据数据的多少，把所有的数据，按照比如10份，分开，一份一份的去聚合，然后自己处理聚合的结果，当然，每一份聚合的操作的桶的数量，都要
    //      在我们设置的范围之内，总的桶数量，也要在这个范围之内，所以设置桶的配置和搜索窗口的大小，也同样重要！

    /**
     * 当桶的数量非常多, 那么我们就需要使用partition, 如果搜索的数据很多, 那么我们就要使用Scroll
     * 但是, 如果桶很多, 而每个桶里面的数据也很多, 那么我们就需要同一时间使用partition和Scroll了
     *
     * @param indexName    es的索引名称
     * @param partitionNum 我们设置的分区数量
     * @param maxDataSize  每个分区之中最多可以返回的数量
     * @return
     */
    public Map<String, Integer> aggUsePartitionWithScroll(String indexName, int partitionNum, int maxDataSize, int everyAggNum) throws IOException {
        // 存放临时数据，等待合并
        Map<String, Integer> resultTemp = new HashMap<>();

        for (int i = 0; i < partitionNum; i++) {
            // 初始化scroll, 设定滚动时间间隔
            final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
            SearchRequest searchRequest = new SearchRequest(indexName);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            // 每次分块的名称
            String name = "hello-age-" + i;
            // 使用age去聚类, 每次聚类3个
            TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms(name).field("age").size(everyAggNum);
            // partition, 将数据分为partitionNum份, 每次自己去组装
            IncludeExclude includeExclude = new IncludeExclude(i, partitionNum);
            termsAggregationBuilder.includeExclude(includeExclude);
            searchSourceBuilder.aggregation(termsAggregationBuilder);
            // 设定每次最多返回多少条数据, 要放得下每页返回的数量
            searchSourceBuilder.size(maxDataSize);
            searchRequest.source(searchSourceBuilder);
            searchRequest.scroll(scroll);

            // 得到了搜索结果, 然后获取scrollId, 去依次获取内容
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            String scrollId = searchResponse.getScrollId();

            ParsedLongTerms longTerms = (ParsedLongTerms) searchResponse.getAggregations().getAsMap().get(name);

            for (int loc = 0; loc < longTerms.getBuckets().size(); loc++) {
                String age = String.valueOf(longTerms.getBuckets().get(loc).getKey());
                Integer ageCountPeople = Math.toIntExact(longTerms.getBuckets().get(loc).getDocCount());
                System.out.println("age: " + age + ", ageCountPeople: " + ageCountPeople);
                if (resultTemp.containsKey(age)) {
                    Integer tempValue = resultTemp.get(age);
                    resultTemp.put(age, (tempValue + ageCountPeople));
                }
                resultTemp.put(age, ageCountPeople);
            }
        }

        return resultTemp;
    }


    /**
     * 组合桶
     *
     * @param indexName
     * @throws IOException
     */
    public void multiCompositeBuckets(String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 聚合之中不去带source数据
        searchSourceBuilder.size(0);

        /********************以下组装聚合的三个字段****************************/
        List<CompositeValuesSourceBuilder<?>> sources = new ArrayList<>();

        TermsValuesSourceBuilder cityIdTerms = new TermsValuesSourceBuilder("ageTerm").field("age").
                missingBucket(false).order(SortOrder.ASC);
        sources.add(cityIdTerms);

        //TermsValuesSourceBuilder industrySecondIdTerms = new TermsValuesSourceBuilder("industrySecondId").
        //        field("industry_second_id").missingBucket(false).order(SortOrder.ASC);
        //sources.add(industrySecondIdTerms);

        //TermsValuesSourceBuilder companyIdTerms = new TermsValuesSourceBuilder("companyId").field("company_id")
        //        .missingBucket(false).order(SortOrder.ASC);
        //sources.add(companyIdTerms);

        CompositeAggregationBuilder composite = new CompositeAggregationBuilder("compositeBuckets", sources);
        //这个就可以以定位了。。。。。
        //composite.aggregateAfter(.......)
        composite.size(2);


//        // 将job_id进行排序，升序排序======》这个只是一个搜索的时候才能用到的
//        searchSourceBuilder.sort("job_id", SortOrder.ASC).from(0).size(100);
//        BoolQueryBuilder bool = new BoolQueryBuilder();
//        bool.filter(QueryBuilders.rangeQuery("job_id").gte("afddsafdsa"));
//        searchSourceBuilder.query(bool);

        /*********************执行查询******************************/
        searchSourceBuilder.aggregation(composite);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 问题，聚合的时候，能不能直接定位到一个点上面？
        /********************取出数据*******************/
        Aggregations aggregations = searchResponse.getAggregations();
        ParsedComposite parsedComposite = aggregations.get("compositeBuckets");
        Map<String, Object> afterKey = parsedComposite.afterKey();
        System.out.println(afterKey);

        List<ParsedComposite.ParsedBucket> list = parsedComposite.getBuckets();


        Map<String, Object> data = new HashMap<>();
        for (ParsedComposite.ParsedBucket parsedBucket : list) {
            data.clear();
            for (Map.Entry<String, Object> m : parsedBucket.getKey().entrySet()) {
                data.put(m.getKey(), m.getValue());
            }
            data.put("count", parsedBucket.getDocCount());
            System.out.println(data);
        }

        int num = 0;
        // 拿完了一轮
        while (afterKey != null) {
            CompositeAggregationBuilder composite02 = new CompositeAggregationBuilder("compositeBuckets" + num, sources).aggregateAfter(afterKey);
            // composite02.aggregateAfter(afterKey);
            /*********************执行查询******************************/
            composite02.size(2);  // 聚类的时候，不管有多少个，一次可以拿完，只是有两个拿到所有结果后的桶
            searchSourceBuilder.aggregation(composite02);
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchNewResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            /********************取出数据*******************/
            aggregations = searchNewResponse.getAggregations();
            ParsedComposite newParsedComposite = aggregations.get("compositeBuckets" + num);
            afterKey = newParsedComposite.afterKey();
            System.out.println(afterKey);

            List<ParsedComposite.ParsedBucket> newList = newParsedComposite.getBuckets();


            Map<String, Object> newData = new HashMap<>();
            for (ParsedComposite.ParsedBucket parsedBucket : newList) {
                newData.clear();
                for (Map.Entry<String, Object> m : parsedBucket.getKey().entrySet()) {
                    newData.put(m.getKey(), m.getValue());
                }
                newData.put("count", parsedBucket.getDocCount());
                System.out.println(newData);
            }
            num++;

        }

    }

    /**
     * 排序搜索，设置了一次搜索的大小和显示的列，lastKey需要在数据库之中额外记录读入，用于断点续传
     *
     * @param indexName
     * @param dataMaxSize
     * @param lastKey
     * @throws IOException
     */
    public int searchSort01(String indexName, Integer dataMaxSize, Map<String, Object> lastKey) throws IOException {
        // 只是显示如下三个列, 默认返回所有的内容
        String[] columns = new String[]{"name", "age", "type"};
        SearchRequest searchRequest001 = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder001 = new SearchSourceBuilder();
        searchSourceBuilder001.size(dataMaxSize);
        // 按照name排序
        searchSourceBuilder001.fetchSource(columns, null).sort("name", SortOrder.ASC);

        BoolQueryBuilder bool = new BoolQueryBuilder();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("name");
        // 如果不为空, 从上一个点开始计算
        if (null != lastKey) {
            rangeQueryBuilder.gt(lastKey.get("name_location"));
            bool.filter(rangeQueryBuilder);
            searchSourceBuilder001.query(bool);
        }
        searchRequest001.source(searchSourceBuilder001);

        // 获取结果
        SearchResponse searchResponse = client.search(searchRequest001, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();

        // 如果此次为空, 表示已经没有数据了, 则可以结束运行, 并且删除, 因为这是正常的结束, 最后一次记录了位置, 所以需要删除
        if (null == hits || hits.length == 0) {
            // searchLocationMapper.deleteLocation("name_location");
            return -1;
        }

        // 用于计数，计算轮次
        int num = 0;
        // 判断是否为最后一页
        boolean isLastPage = false;
        Map<String, Object> location = new HashMap<>();

        // 第一次循环
        for (SearchHit oneHit : hits) {
            Map<String, Object> sourceAsMap = oneHit.getSourceAsMap();
            // docId用于更新, 在此处获取
            String docId = oneHit.getId();

            // 获取值
            String name = sourceAsMap.get("name").toString();
            String age = sourceAsMap.get("age").toString();
            String type = sourceAsMap.get("type").toString();

            // 业务
            log.info("name: {}, age : {}, type: {}!", name, age, type);

            if (hits.length < dataMaxSize) {
                // 表示为最后一页
                isLastPage = true;
                // searchLocationMapper.deleteLocation("name_location");
            } else {
                location.put("name_location", name);
            }
        }
        // 写入数据库
        // recordLocation(location);

        // 再次去循环
        while (!isLastPage) {
            num++;
            log.info("第 {} 次循环！", num + 1);
            SearchRequest sq2 = new SearchRequest(indexName);
            SearchSourceBuilder sb2 = new SearchSourceBuilder();
            sb2.size(dataMaxSize);
            // 按照name排序
            sb2.fetchSource(columns, null).sort("name", SortOrder.ASC);

            BoolQueryBuilder bb2 = new BoolQueryBuilder();
            RangeQueryBuilder rb2 = QueryBuilders.rangeQuery("name");

            // 从上一个点开始计算
            if (null != location) {
                rb2.gt(location.get("name_location"));
                bb2.filter(rb2);
                sb2.query(bb2);
            }
            sq2.source(sb2);

            // 获取结果
            SearchResponse searchResponse002 = client.search(sq2, RequestOptions.DEFAULT);
            SearchHit[] hits002 = searchResponse002.getHits().getHits();

            // 如果此次为空, 表示已经没有数据了, 则可以结束运行, 并且删除, 因为这是正常的结束, 最后一次记录了位置, 所以需要删除
            if (null == hits002 || hits002.length == 0) {
                // 从数据删除location记录
                return -1;
            }

            for (SearchHit oneHit : hits) {
                Map<String, Object> sourceAsMap = oneHit.getSourceAsMap();
                // docId用于更新, 在此处获取
                String docId02 = oneHit.getId();

                // 获取值
                String name02 = sourceAsMap.get("name").toString();
                String age02 = sourceAsMap.get("age").toString();
                String type02 = sourceAsMap.get("type").toString();
                log.info("name: {}, age : {}, type: {}!", name02, age02, type02);

                if (hits.length < dataMaxSize) {
                    // 表示为最后一页
                    isLastPage = true;
                    // 从数据删除location记录
                    // searchLocationMapper.deleteLocation("name_location");
                } else {
                    location.put("name_location", name02);
                }
            }
        }

        // 从数据删除location记录
        // searchLocationMapper.deleteLocation("name_location");
        return 0;

    }


    // 参数之中的lastKey不能赋值，这是为什么？ 可以赋值，但是为null的时候，就会报错
    /*这个逻辑相比于searchSort01, 反而是很清晰, 而且代码也减少了一半*/
    public int searchSort02(String indexName, Integer dataMaxSize, Map<String, Object> lastKey) throws IOException {
        // 记录次数
        int num = 0;
        // 记录是否为首页
        boolean boolLastPage = false;
        Map<String, Object> locationMap = new HashMap<>();
        // 只是显示如下三个列, 默认返回所有的内容
        String[] columns = new String[]{"name", "age", "type"};

        while (!boolLastPage) {
            log.info("这是第: {} 轮！", num + 1);
            SearchRequest searchRequest001 = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder001 = new SearchSourceBuilder();
            searchSourceBuilder001.size(dataMaxSize);
            // 按照name排序
            searchSourceBuilder001.fetchSource(columns, null).sort("name", SortOrder.ASC);
            BoolQueryBuilder bool = new BoolQueryBuilder();
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("name");

            // 如果不为空, 从上一个点开始计算
            if (null != lastKey && num == 0) {
                rangeQueryBuilder.gt(lastKey.get("name_location"));
                bool.filter(rangeQueryBuilder);
                searchSourceBuilder001.query(bool);
            } else {
                rangeQueryBuilder.gt(locationMap.get("name_location"));
                bool.filter(rangeQueryBuilder);
                searchSourceBuilder001.query(bool);
            }
            searchRequest001.source(searchSourceBuilder001);

            // 获取结果
            SearchResponse searchResponse = client.search(searchRequest001, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();

            // 如果此次为空, 表示已经没有数据了, 则可以结束运行, 并且删除, 因为这是正常的结束, 最后一次记录了位置, 所以需要删除
            if (null == hits || hits.length == 0) {
                // 删除数据库记录
                // searchLocationMapper.deleteLocation("name_location");
                return -1;
            } else {
                for (SearchHit sh : hits) {
                    Map<String, Object> sourceAsMap = sh.getSourceAsMap();
                    // docId用于更新, 在此处获取
                    String docId = sh.getId();

                    // 获取值
                    String name = sourceAsMap.get("name").toString();
                    String age = sourceAsMap.get("age").toString();
                    String type = sourceAsMap.get("type").toString();

                    // 业务
                    log.info("docId: {} , name: {} , age : {} , type: {} !", docId, name, age, type);

                    if (hits.length < dataMaxSize) {
                        // 表示为最后一页, 从数据库之中删除, 删除数据库记录
                        boolLastPage = true;
                        // searchLocationMapper.deleteLocation("name_location");
                    } else {
                        locationMap.put("name_location", name);
                    }
                }
            }
            num++;
            // 正常执行完一次， 就去记录最后一个的位置
            // recordLocation(locationMap);
        }

        // 所有都正常完成，就去删除数据库之中的记录
        // searchLocationMapper.deleteLocation("name_location");
        return 0;
    }

    // 记录位置到数据库
    private void recordLocation(Map<String, Object> mp) {
        QueryWrapper<SearchLocation> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "name_location");
        int count = searchLocationMapper.selectCount(wrapper);
        if (count >= 1) {
            UpdateWrapper<SearchLocation> searchLocationUpdateWrapper = new UpdateWrapper<>();
            searchLocationUpdateWrapper.eq("name", "name_location");
            SearchLocation searchLocation = SearchLocation.builder()
                    .location(String.valueOf(mp.get("name")))
                    .updateTime(new Date())
                    .info("correct")
                    .build();
            searchLocationMapper.update(searchLocation, searchLocationUpdateWrapper);
        } else {
            Date date = new Date();
            SearchLocation searchLocation = SearchLocation.builder()
                    .name("name_location")
                    .location(String.valueOf(mp.get("name")))
                    .createTime(date)
                    .updateTime(date)
                    .info("correct")
                    .build();
            searchLocationMapper.insert(searchLocation);
        }
    }

    // 检测某个字段是否存在
    public void checkNullField(String indexName, Integer dataMaxSize) throws IOException {
        Function<Integer, String[]> arrayFunction = x -> new String[x];
        String[] myColumns = arrayFunction.apply(3);
        myColumns[0] = "tags";
        myColumns[1] = "type";
        myColumns[2] = "desc";

        SearchRequest searchRequest001 = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder001 = new SearchSourceBuilder();
        searchSourceBuilder001.size(dataMaxSize);

        // 按照name排序
        searchSourceBuilder001.fetchSource(myColumns, null).sort("name", SortOrder.ASC);

        // {"query":{"bool":{"must_not":[{"exists":{"field":"city_hot_value"}}]}},"from": 0, "size": 10,"sort": []}
        BoolQueryBuilder bool = new BoolQueryBuilder();

        // 不存在type字段的文档
        // // 这样是and的关系，两个都是为空
        // ExistsQueryBuilder existsQueryBuilder1 = QueryBuilders.existsQuery("type");
        // ExistsQueryBuilder existsQueryBuilder2 = QueryBuilders.existsQuery("desc");
        // bool.mustNot(existsQueryBuilder1);
        // bool.mustNot(existsQueryBuilder2);

        // // or的关系呢？ 这个就是
        // BoolQueryBuilder bool001 = new BoolQueryBuilder();
        // ExistsQueryBuilder existsQueryBuilder001 = QueryBuilders.existsQuery("type");
        // bool001.mustNot(existsQueryBuilder001);
        // BoolQueryBuilder bool002 = new BoolQueryBuilder();
        // ExistsQueryBuilder existsQueryBuilder002 = QueryBuilders.existsQuery("desc");
        // bool002.mustNot(existsQueryBuilder002);

        // bool.should(bool001);
        // bool.should(bool002);

        // // or misssing的关系
        BoolQueryBuilder boolFilter = new BoolQueryBuilder();
        BoolQueryBuilder bool001 = new BoolQueryBuilder();
        ExistsQueryBuilder existsQueryBuilder001 = QueryBuilders.existsQuery("type");
        bool001.mustNot(existsQueryBuilder001);
        BoolQueryBuilder bool002 = new BoolQueryBuilder();
        ExistsQueryBuilder existsQueryBuilder002 = QueryBuilders.existsQuery("desc");
        bool002.mustNot(existsQueryBuilder002);

        boolFilter.should(bool001);
        boolFilter.should(bool002);

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("name");
        // rangeQueryBuilder.gt(lastKey.get("es_hot_value_location"));
        // filter之间的话是and
        bool.filter(rangeQueryBuilder);
        bool.filter(boolFilter);


        searchSourceBuilder001.query(bool);

        searchRequest001.source(searchSourceBuilder001);
        System.out.println(searchSourceBuilder001);
        //{"size":200,"query":{"bool":{"filter":[{"range":{"name":{"from":null,"to":null,"include_lower":true,
        //        "include_upper":true,"boost":1.0}}}],"must_not":[{"exists":{"field":"type","boost":1.0}}],
        //"adjust_pure_negative":true,"boost":1.0}},"_source":{"includes":["tags","type","desc"],"excludes":[]},"sort"
        //:[{"name":{"order":"asc"}}]}

        // 获取结果
        SearchResponse searchResponse = client.search(searchRequest001, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        System.out.println(hits.length);

        // {"size":200,"query":{"bool":{"filter":[{"range":{"name":{"from":null,"to":null,"include_lower":true,
        //         "include_upper":true,"boost":1.0}}},{"bool":{"should":[{"bool":{"must_not":[{"exists":{"field":
        //"type","boost":1.0}}],"adjust_pure_negative":true,"boost":1.0}},{"bool":{"must_not":[{"exists":{"field":
        //"desc","boost":1.0}}],"adjust_pure_negative":true,"boost":1.0}}],"adjust_pure_negative":true,"boost":1.0}}],
        //"adjust_pure_negative":true,"boost":1.0}},"_source":{"includes":["tags","type","desc"],"excludes":[]},"sort"
        //:[{"name":{"order":"asc"}}]}

    }

    // 循环滚动 更新
    public void loopToUpdateWithScroll(String indexName) throws IOException {
        int m = 1;
        Date date = new Date();
        String[] columnsInner = {"job_id", "industry_second_id", "id", "job_hot_value", "monthly_salary_upper"};

        // 初始化scroll
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L)); //设定滚动时间间隔
        SearchRequest searchRequest001 = new SearchRequest(indexName);
        searchRequest001.scroll(scroll);

        SearchSourceBuilder sourceBuilder001 = new SearchSourceBuilder();
        sourceBuilder001.size(200);
        sourceBuilder001.fetchSource(columnsInner, null).sort("id", SortOrder.ASC);

        BoolQueryBuilder bool = new BoolQueryBuilder();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("job_hot_value");
        rangeQueryBuilder.lt(0);
        bool.filter(rangeQueryBuilder);
        sourceBuilder001.query(bool);
        searchRequest001.source(sourceBuilder001);
        // log.info(sourceBuilder001.toString());

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest001, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        log.info("第{}次进入处理job_hot_value小于0的循环！日期是: {}!", m, date);
        List<String> jobIdList01 = new ArrayList<>();
        List<Map<String, Object>> hotValueList01 = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> mp = new HashMap<>();

            String docId = searchHit.getId();
            Object oldJobHotValue = searchHit.getSourceAsMap().get("job_hot_value");
            if (Double.valueOf(oldJobHotValue.toString()) < 0) {
                mp.put("job_hot_value", 0.0);
            }

            jobIdList01.add(docId);
            hotValueList01.add(mp);
        }
        // 批量更新插入
        // batchUpdateByMap.batchUpdateByMap(indexName, jobIdList01, hotValueList01);

        // 遍历搜索命中的数据，直到没有数据
        while (searchHits != null && searchHits.length > 0) {
            m++;
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            try {
                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
            if (searchHits != null && searchHits.length > 0) {
                log.info("第{}次进入处理job_hot_value小于0的循环！日期是: {}!", m, date);

                List<String> jobIdList02 = new ArrayList<>();
                List<Map<String, Object>> hotValueList02 = new ArrayList<>();
                for (SearchHit searchHit : searchHits) {
                    Map<String, Object> mp = new HashMap<>();

                    String docId = searchHit.getId();
                    Object oldJobHotValue = searchHit.getSourceAsMap().get("job_hot_value");
                    if (Double.valueOf(oldJobHotValue.toString()) < 0) {
                        mp.put("job_hot_value", 0.0);
                    }

                    jobIdList02.add(docId);
                    hotValueList02.add(mp);
                }
                // 批量更新插入
                // batchUpdateByMap.batchUpdateByMap(indexName, jobIdList02, hotValueList02);
            }
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = null;
        try {
            clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean succeeded = clearScrollResponse.isSucceeded();
        log.info("scroll清除是否成功: " + succeeded);
    }

}
