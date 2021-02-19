package com.zgy.learn.token.config;

import com.zgy.learn.token.shiro.JwtTokenRealm;
import com.zgy.learn.token.shiro.filter.JwtTokenFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author z.g.y
 * @date 2021/2/4
 */
@Configuration
public class ShiroJwtConfig {
    private JwtTokenFilter jwtTokenFilter;

    public ShiroJwtConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }


    /**
     * 1.身份认证realm(这个需要自己写，账号密码校验；权限等)
     */
    @Bean
    public Realm realm() {
        return new JwtTokenRealm();
    }


    /**
     * 2.SecurityManager设置，shiro的安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // ①设置realm
        securityManager.setRealm(realm());
        // ②关闭shiro自带的session
        // 文档：http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        // 禁止Subject的getSession方法
        // securityManager.setSubjectFactory(subjectFactory());
        // 禁用rememberMe
        // securityManager.setRememberMeManager(null);
        return securityManager;
    }


    /**
     * 3.ShiroFilterFactoryBean设置, 处理拦截资源文件问题
     * 单独一个ShiroFilterFactoryBean配置是或报错的, 在初始化ShiroFilterFactoryBean的时候需要注入:SecurityManager,FilterChain定义说明
     * ①一个URL可以配置多个Filter, 使用逗号分隔, ②当设置多个过滤器时, 全部验证通过才视为通过, ③部分过滤器可指定参数, 如perms, roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // ①必须设置SecurityManager
        filterFactoryBean.setSecurityManager(securityManager());

        // ②设置自定义的filter, shiro禁用session后, 再使用shiro内置过滤器authc会报错, 所以要使用自定义的Filter
        Map<String, Filter> filters = filterFactoryBean.getFilters();
        filters.put("jwtTokenFilter", jwtTokenFilter);
        filterFactoryBean.setFilters(filters);

        // 设置一些自定义的拦截链
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


    /**
     * 开启shiro aop注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 下面的代码是添加注解支持, DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


    @Bean("authorizationAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
