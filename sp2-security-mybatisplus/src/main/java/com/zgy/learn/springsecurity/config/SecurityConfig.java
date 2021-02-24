package com.zgy.learn.springsecurity.config;

import com.zgy.learn.springsecurity.handler.MyAccessDeniedHandler;
import com.zgy.learn.springsecurity.service.MyAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-21
 * @modified :
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @Autowired
    MyAuthenticationService myAuthenticationService;


    /**
     * 自定义登录
     * 其实/login和logout已经是SpringSecurity提供好的接口了, 我们只需要调用即可
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单登录
        http.formLogin()
                // 自定义入参名称, 默认就是username和password
                .usernameParameter("usr")
                .passwordParameter("pwd")
                // 自定义登录页面
                .loginPage("/index")
                // 当发现/login时认为是登录, 必须和表单提交的地址一样, 执行LoginService的登录服务
                .loginProcessingUrl("/login")
                // 登录成功后跳转页面, 必须是post请求
                .successForwardUrl("/tohello")
                // 自定义的登录成功跳转handler
                // .successHandler(new MySuccessForwardHandler("http://www.google.com.hk"))
                // 登录失败后跳转的页面, 必须是post请求
                .failureForwardUrl("/toError");

        // 授权
        // 这儿的授权是采用的配置方式, 如果使用注解方式, 我们可以把下面的特定网页的这些配置注释掉
        http.authorizeRequests()
                // 放行login.html页面
                .antMatchers("/index").permitAll()
                // 放行error.html页面
                .antMatchers("/error").permitAll()
                // 放行静态资源
                .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                .antMatchers("/**/*.png", "/**/*.js").permitAll()
                // 对特定的网页或者资源进行权限或者角色的限定
                .antMatchers("/hello").hasAnyAuthority("admin", "user")
                // .antMatchers("/scenery").hasRole("vip")
                // 使用access达成同样的效果, 使用@Secured注解来起到相同作用
                //// .antMatchers("/scenery").access("hasRole('vip')")
                // access中通过@bean的id名.方法(参数)的形式进行调用, 对/admin页面进行访问控制
                .antMatchers("/admin").access("@myAccessService.hasPermission(request,authentication)")
                // 所有请求都必须被认证, 必须登录后被访问
                .anyRequest().authenticated();

        // 关闭csrf防护, 关闭csrf防护, 才能进行登录
        http.csrf().disable();

        // 记住我, remember-me的配置
        http.rememberMe()
                // 设置数据源, 保存到的地方
                .tokenRepository(persistentTokenRepository)
                // 超时时间, 单位是秒s
                .tokenValiditySeconds(120)
                // 自定义登录逻辑
                .userDetailsService(myAuthenticationService)
                // 设置remember-me的入参名称, 默认是remember-me
                .rememberMeParameter("remember-me");

        // 异常处理
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        // 退出登录
        http.logout()
                // 退出链接和退出成功后跳转到的页面
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index");
    }


    /**
     * 自定义登录逻辑与密码加密和解析器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myAuthenticationService).passwordEncoder(passwordEncoder());
    }


    /**
     * 密码加密和解析器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
