package com.zgy.bootintegration.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: z.g.y
 * @date: 2020/5/21
 * @description:
 */
@Configuration
public class ElasticSearchClientConfig {

    // 通过配置文件，获取值
    @Value("${elastic.host}")
    private String esHost;

    @Value("${elastic.port}")
    private Integer port;

    // 使用默认参数

    /**
     * @Bean public RestHighLevelClient restHighLevelClient1() {
     * RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
     * new HttpHost(esHost, port, "http")
     * ));
     * return restHighLevelClient;
     * }
     */

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost(esHost, port, "http")).setRequestConfigCallback(
                new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder
                                .setConnectTimeout(3600000)
                                .setConnectionRequestTimeout(3600000)
                                .setSocketTimeout(3600000);
                    }
                }
        );
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        return restHighLevelClient;
    }
}
