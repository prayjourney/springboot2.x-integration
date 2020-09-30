package com.zgy.bootintegration.config;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author renjiaxin
 * @Date 2020/9/30
 * @Description zipkin收集器的配置
 */
@Configuration
public class ZipkinSpanCollectorConfig {
    @Value("${zipkin.url}")
    private String url;

    @Value("${zipkin.serviceName}")
    private String serviceName;

    /**
     * 连接超时时间
     */
    @Value("${zipkin.connectTimeout}")
    private int connecTimeout;

    /**
     * 是否启动压缩
     */
    @Value("${zipkin.compressionEnabled}")
    private boolean compressionEnabled;

    /**
     * 上传 span 的间隔时间
     */
    @Value("${zipkin.flushInterval}")
    private int flushInterval;

    /**
     * 读取超时时间
     */
    @Value("${zipkin.readTimeout}")
    private int readTimeout;

    @Value("${zipkin.samplerRate}")
    private float samplerRate;

    /**
     * 配置 span 收集器
     *
     * @return
     */
    @Bean
    public SpanCollector spanCollector() {
        HttpSpanCollector.Config config = HttpSpanCollector.Config.builder()
                .connectTimeout(connecTimeout)
                .compressionEnabled(compressionEnabled)
                .flushInterval(flushInterval)
                .readTimeout(readTimeout)
                .build();
        return HttpSpanCollector.create(url, config, new EmptySpanCollectorMetricsHandler());
    }

    /**
     * 配置采集率
     *
     * @param spanCollector
     * @return
     */
    @Bean
    public Brave brave(SpanCollector spanCollector) {
        Brave.Builder builder = new Brave.Builder(serviceName);
        builder.spanCollector(spanCollector)
                .traceSampler(Sampler.create(samplerRate))
                .build();
        return builder.build();
    }

    /**
     * 设置server的(服务端收到请求和服务端完成处理，并将结果发送给客户端)过滤器
     *
     * @param brave
     * @return
     */
    @Bean
    public BraveServletFilter braveServletFilter(Brave brave) {
        BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
                brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
        return filter;
    }

    /**
     * 设置client的rs和cs的拦截器
     *
     * @param brave
     * @return
     */
    @Bean
    public OkHttpClient okHttpClient(Brave brave) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new BraveOkHttpRequestResponseInterceptor(
                        brave.clientRequestInterceptor(),
                        brave.clientResponseInterceptor(),
                        new DefaultSpanNameProvider())).build();
        return httpClient;
    }
}
