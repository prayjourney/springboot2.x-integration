package com.zgy.learn.webtoken.config;

import com.zgy.learn.webtoken.config.filter.JwtTokenFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author z.g.y
 * @date 2021/2/4
 */
@Configuration
public class ShiroJwtConfig {
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Bean
    public Realm realm() {
        return new JwtTokenRealm();
    }

    @Bean
    public DefaultWebSubjectFactory subjectFactory() {
        return new JwtTokenWebSubjectFactory();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(realm());
        // 关闭ShiroDAO功能
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 禁用session存储
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        // 禁止Subject的getSession方法
        securityManager.setSubjectFactory(subjectFactory());
        // 禁用rememberMe
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());

        // 设置自定义的filter
        // shiro禁用session后, 如果再使用shiro的内置过滤器authc会报错, 所以就不能再用authc, 使用自定义的Filter就行
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwtTokenFilter", jwtTokenFilter);
        filterFactoryBean.setFilters(filterMap);

        // 设置拦截链
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/home", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setUnauthorizedUrl("/401");
        return filterFactoryBean;
    }

}
