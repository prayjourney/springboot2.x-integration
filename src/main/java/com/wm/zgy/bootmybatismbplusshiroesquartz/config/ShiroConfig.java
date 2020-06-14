package com.wm.zgy.bootmybatismbplusshiroesquartz.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: renjiaxin
 * @Despcription: 配置Shiro，层层递进，ShiroFilterFactoryBean-> DefaultWebSecurityManager-> Realm
 * @Date: Created in 2020/6/14 22:58
 * @Modified by:
 */
@Configuration
public class ShiroConfig {
    // ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        return bean;
    }

    // DefaultWebSecurityManager, 使用@Qualifier指定名字
    @Bean(name = "securityManager")
    public DefaultSecurityManager getDefaultSecurityManager(@Qualifier("userRealm") MyRealm userRealm) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
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
