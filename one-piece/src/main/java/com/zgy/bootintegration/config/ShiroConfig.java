package com.zgy.bootintegration.config;

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
 * @author: z.g.y
 * @description: 配置Shiro，层层递进，ShiroFilterFactoryBean-> DefaultWebSecurityManager-> Realm
 * @date: Created in 2020/6/14 22:58
 * @modified:
 * @url: https://www.cnblogs.com/tuifeideyouran/p/7696055.html
 * https://blog.csdn.net/zalan01408980/article/details/85402528
 * https://www.jianshu.com/p/74304ebb7d30/
 * https://blog.csdn.net/yali_aini/article/details/84000271
 * https://www.jianshu.com/p/1f77702e4947
 * https://blog.csdn.net/zhuzhaofeng0703/article/details/86574022  没有权限时候就去跳转的设置
 * https://juejin.im/post/5ac78b31f265da237411387e
 * https://www.cnblogs.com/qlqwjy/p/9032968.html
 * https://objcoding.com/2017/05/27/Shiro(1)/#%E6%8E%88%E6%9D%83
 * http://jeesite.com/docs/permi-shiro/
 * https://www.kancloud.cn/digest/javaframe/125587
 * https://www.jianshu.com/p/09abe74c3d48
 * http://www.zhaojun.im/shiro-10/
 * https://cloud.tencent.com/developer/article/1448332
 * https://blog.csdn.net/qq_35981283/article/details/78631924
 * https://www.cnblogs.com/BAYOUA/p/11827239.html
 * https://www.cnblogs.com/pingxin/p/p00115.html
 * https://developer.okta.com/blog/2017/07/13/apache-shiro-spring-boot
 * https://www.programcreek.com/java-api-examples/?api=org.apache.shiro.authz.annotation.RequiresRoles
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
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
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

    /**
     * 如果没有登录, 则直接跳到登录页面, 如果没有权限, 则跳到未授权页面, 在此处设置
     */
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
