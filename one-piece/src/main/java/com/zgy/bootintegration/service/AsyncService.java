package com.zgy.bootintegration.service;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/31 18:42
 * @modified:
 */
public interface AsyncService {
    void executeAsync();
    /**
     * springboot @async 无效的问题解决
     * 1. 在@SpringBootApplication启动类或者是Executor线程池的配置类，添加注解@EnableAsync，二选一即可；
     * 2. 异步方法使用注解@Async，返回值为void或者Future；
     * 3. 切记一点，异步方法和调用方法一定要写在不同的类中，如果写在一个类中，是没有效果的！
     * ref:
     *  https://blog.csdn.net/qq_34545192/article/details/80484780
     *  https://www.cnblogs.com/asker009/p/9967053.html
     *  https://blog.csdn.net/YoungLee16/article/details/88398045
     *  https://blog.csdn.net/hry2015/article/details/67640534
     */
}
