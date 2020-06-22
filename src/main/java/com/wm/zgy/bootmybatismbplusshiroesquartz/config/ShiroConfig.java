package com.wm.zgy.bootmybatismbplusshiroesquartz.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: renjiaxin
 * @Despcription: 配置Shiro，层层递进，ShiroFilterFactoryBean-> DefaultWebSecurityManager-> Realm
 * @Date: Created in 2020/6/14 22:58
 * @Modified by:
 * @URL
 * https://www.cnblogs.com/tuifeideyouran/p/7696055.html
 * https://blog.csdn.net/zalan01408980/article/details/85402528
 * https://www.jianshu.com/p/74304ebb7d30/
 * https://blog.csdn.net/yali_aini/article/details/84000271
 * https://www.jianshu.com/p/1f77702e4947
 */
@Configuration
public class ShiroConfig {
    // ShiroFilterFactoryBean, 在此处我们可以自己去定义访问页面和登录未登录等情况的设置, 这样集中管理, 不太灵活
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        /**
         * 添加shiro的内置过滤器, 在这个ShiroFilterFactoryBean之中配置, 即可生效
         *
         * anon: 无需认证就可访问, 不是anno, 而是anon
         * authc: 必须认证了才能访问
         * user: 必须拥有记住我的功能才能访问
         * perms: 必须拥有对某个资源的全向才能访问
         * role: 拥有某个角色权限才能访问
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

    // 创建realm, 需要自定义类
    @Bean
    public MyRealm userRealm() {
        return new MyRealm();
    }

    /**
     * Shiro 生命周期处理器, 这个在简单的操作之中，其实加不加都可以的
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 使用getShiroFilterFactoryBean, 在filterChainDefinitionMap之中添加授权的map链接和角色和权限等配置, 是传统的授权的方式,
     * 我们也可以使用@RequiresPermissions, @RequiresRoles, @RequiresAuthentication, @RequiresGuest, @RequiresUser注解,
     * 使用注解这样更加方便和灵活, 而且可以对每个URL进行设置, 配置明确, 容易检查。但是这个需要需借助SpringAOP扫描使用Shiro注解的类,
     * 并在必要时进行安全逻辑验证, 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        // 生成代理, 用于注解的解析和相关权限的验证
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持, 将我们的securityManager注入到其中
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getDefaultWebSecurityManager(userRealm()));
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();

        /**
         * 未授权处理页, 未授权: 没有某种权限, 没有某种角色都是未授权, 不是指定用户, 或者不是游客等, 都是未授权, 有如下的情况
         *     @RequiresUser
         *     @RequiresGuest
         *     @RequiresAuthentication
         *     @RequiresPermissions("user:money")
         *     @RequiresRoles("role:admin")
         */
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/unauthor");
        /**
         * 身份没有验证
         */
        properties.setProperty("org.apache.shiro.authz.UnauthenticatedException", "/login");
        resolver.setExceptionMappings(properties);
        return resolver;
    }

}
