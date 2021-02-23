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
 * @author: z.g.y
 * @date: 2020/9/30
 * @description: zipkin收集器的配置
 * zipkin是cs的架构，代码之中集成了c端，要使用就需要让server启动，server启动有两种方式，要么自己打包，要么启动别人编译好的包，参见：
 * # 已经下载好的时候
 * curl -sSL https://zipkin.io/quickstart.sh | bash -s
 * java -jar zipkin.jar
 * # 自己去打包运行
 * ## get the latest source
 * git clone https://github.com/openzipkin/zipkin
 * cd zipkin
 * ## Build the server and also make its dependencies
 * ./mvnw -DskipTests --also-make -pl zipkin-server clean install
 * ## Run the server
 * java -jar ./zipkin-server/target/zipkin-server-*exec.jar
 * 下载位置：https://zipkin.io/pages/quickstart.html，https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec
 * 参考：
 * * https://blog.csdn.net/ffzhihua/article/details/95474166，https://www.cnblogs.com/xushuyi/p/10443769.html，
 * * https://www.cnblogs.com/mengyixin/p/9839049.html，https://www.cnblogs.com/haixiang/p/11498439.html，
 * * https://zipkin.io/pages/quickstart，https://blog.csdn.net/yangchuanan/article/details/83549345，
 * * https://github.com/openzipkin/zipkin，https://www.csdn.net/gather_2b/MtjaEgzsNjQwNDYtYmxvZwO0O0OO0O0O.html
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
