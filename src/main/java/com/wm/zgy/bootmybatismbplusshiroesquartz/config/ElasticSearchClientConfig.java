package com.wm.zgy.bootmybatismbplusshiroesquartz.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author renjiaxin
 * @Date 2020/5/21
 * @Description
 */
@Configuration
public class ElasticSearchClientConfig {

    // 通过配置文件，获取值
    @Value("${elastic.host}")
    private String esHost;

    @Value("${elastic.port}")
    private Integer port;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient highLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost(esHost, port, "http")
        ));
        return highLevelClient;
    }
}
