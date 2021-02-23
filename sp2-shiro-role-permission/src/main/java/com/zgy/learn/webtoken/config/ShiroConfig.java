package com.zgy.learn.webtoken.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: z.g.y
 * @date: 2021/2/1
 * @description: ①创建realm对象->②配置DefaultWebSecurityManager, 设置使用的realm->③配置ShiroFilterFactoryBean
 */
@Configuration
public class ShiroConfig {
    @Autowired
    OpUserRealm opUserRealm;

    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("opUserRealm") OpUserRealm opUserRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(opUserRealm);
        return securityManager;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        filterFactoryBean.setSecurityManager(securityManager);

        // 添加shiro的内置过滤器
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/", "anon");
        filterMap.put("/home", "anon");
        filterMap.put("/index", "anon");
        filterMap.put("/login", "anon");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setUnauthorizedUrl("/401");
        return filterFactoryBean;
    }

    // 去掉登陆的jessionid
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

}
