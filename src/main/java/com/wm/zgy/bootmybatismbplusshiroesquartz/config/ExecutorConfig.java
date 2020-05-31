package com.wm.zgy.bootmybatismbplusshiroesquartz.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: renjiaxin
 * @Despcription: springboot的线程池配置
 * @Date: Created in 2020/5/31 17:19
 * @Modified by:
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {
    @Value("${thread.coresize}")
    private int coreSize;
    @Value("${thread.maxsize}")
    private int maxSize;
    @Value("${thread.queuesize}")
    private int queueSize;
    @Value("${thread.taskprefix}")
    private String taskPrefix;
    @Value("${thread.outtimeseconds}")
    private Integer outTimeSeconds;

    @Bean
    public Executor asyncServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(coreSize);
        // 配置最大线程数
        executor.setMaxPoolSize(maxSize);
        // 配置队列大小
        executor.setQueueCapacity(queueSize);
        // 配置线程任务超时时间
        executor.setKeepAliveSeconds(outTimeSeconds);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(taskPrefix);
        // 配置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();

        return  executor;
    }
}
