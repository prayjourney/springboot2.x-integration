package com.wm.zgy.bootmybatismbplusshiroesquartz.service;

import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.Content;
import com.wm.zgy.bootmybatismbplusshiroesquartz.utils.HtmlParseUtil;
import com.wm.zgy.bootmybatismbplusshiroesquartz.utils.JSONUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author renjiaxin
 * @Date 2020/5/23
 * @Description
 */
@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private HtmlParseUtil htmlParseUtil;

    /**
     * 解析爬虫爬到的结果，然后放入到es之中
     *
     * @param keywords
     * @return
     * @throws Exception
     */
    public boolean parseContentAdd2Es(String keywords) throws Exception {
        List<Content> contents = htmlParseUtil.parseJd(keywords);

        // 把查询出来的数据放入到es之中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (int i = 0; i < contents.size(); i++) {
            bulkRequest.add(new IndexRequest("jd_goods").source(JSONUtil.getJsonFromObject(contents.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    //获取数据实现搜索功能
    public List<Map<String, Object>> searchContentWithPage(String keyword, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        // 精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueMinutes(2));

        // 执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);


        // 解析结果, 所有的结果都放在searchResponse之中，我们需要的内容，就是在Hits之中
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

}
