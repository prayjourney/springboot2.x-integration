package com.wm.zgy.bootmybatismbplusshiroesquartz.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: renjiaxin
 * @Despcription: 配置Shiro，层层递进，ShiroFilterFactoryBean-> DefaultWebSecurityManager-> Realm
 * @Date: Created in 2020/6/14 22:58
 * @Modified by:
 */
@Configuration
public class ShiroConfig {
    // ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        // 添加shiro的内置过滤器， 在这个bean之中配置
        /**
         * anon:  无需认证就可访问， 不是anno, 而是anon
         * authc: 必须认证了才能访问
         * user:  必须拥有记住我的功能才能访问
         * perms: 必须拥有对某个资源的全向才能访问
         * role:  拥有某个角色权限才能访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/shiro/addmsg", "authc"); //需要认证
        filterChainDefinitionMap.put("/shiro/getmsg", "anon");  // 不需要认证

        // 授权，正常情况下，没有授权跳到未授权页面, 意思是访问/shiro/addmsg页面，必须要有perms[user:add]权限
        filterChainDefinitionMap.put("/shiro/addmsg", "perms[user:add]");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 设置登录页面
        bean.setLoginUrl("/shiro/login");
        // 设置未授权页面
        bean.setUnauthorizedUrl("/shiro/unauthor");
        return bean;
    }

    // DefaultWebSecurityManager, 使用@Qualifier指定名字
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") MyRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 创建realm， 需要自定义类
    @Bean
    public MyRealm userRealm() {
        return new MyRealm();
    }
}
