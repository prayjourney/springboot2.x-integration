package com.zgy.learn.bootshiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author renjiaxin
 * @Date 2020/11/5
 * @Description ShiroFilterFactoryBean用来拦截相关的请求(A), 需要交给SecurityManager(B)处理, DefaultWebSecurityManager是web上面的
 * shiro功能的总管理者，而具体的配置的情况，需要从我们的自定义的realm(C)之中去获取，所以三个依赖的关系， A--->B--->C的关系。
 */
@Configuration
public class ShiroConfig {

    @Autowired
    UserRealm userRealm;

    // 3. ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        filterFactoryBean.setSecurityManager(securityManager);
        return filterFactoryBean;
    }


    // 2. DefaultWebSecurityManager, 限定为userRealm
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    // 1. 创建realm对象， 一般使用自定义的类
    @Bean
    public UserRealm userRealm() {
        return userRealm;
    }
}
