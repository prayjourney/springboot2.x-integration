package com.zgy.multipledatasource.config;

// import com.alibaba.druid.pool.DruidDataSource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 这个配置其实现在是可有可无的，我们只需要在application.properties之中配置好相关的参数即可
 * 这个类最大的作用就是提供了配置，和过滤，配置用户名密码，ip,  过滤请求的类型，比如是js，图片，他就不去检测了，最好加上，全面而完整
 */

@Configuration
public class DruidConfiguration {
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DruidDataSource druidDataSource() {
//        return new DruidDataSource();
//    }

    /**
     * 配置监控服务器
     *
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");
        // 添加IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        // 添加控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "zgy");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 配置服务过滤器，这个里面的如果使用statFilter，会有冲突，因为已经配置好了
     *
     * @return 返回过滤器配置对象
     */
    @Bean("myStatFilter")
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }
}
