package com.zgy.learn.springsecurity.config;

import com.zgy.learn.springsecurity.handler.JwtAuthorizationFilter;
import com.zgy.learn.springsecurity.service.MyAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author: pray-journey.io
 * @despcription: Security的Config
 * @date: created in 2021-02-21
 * @modified: pray-journey.io
 * @question: 原先使用的登录成功之后跳转的页面, successForwardUrl("/hello"), 跳转到/hello对应的页面
 * 现在登录陈工使用的是successHandler处理, 返回了一个token字符串, 所以就不会跳转页面了, 所以前端拿到的是字符串, 前端需要自己处理跳转。
 * ①如果使用window.location.href="/hello", 没有传入token, 又会被拦截, 就出现了很大的问题, 所以不要使用浏览器测试, ②使用postman登录,
 * 能不能访问, 这种情况下是要查看返回的json而不是跳转的页面, 因为此时, 后端已经不会转发页面了,③所以后端纯粹负责权限登录的验证, 全部返回json,
 * 前端根据json结果, 自主跳转页面。
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @Autowired
    MyAuthenticationService myAuthenticationService;

    @Autowired
    AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    AuthenticationFailureHandler loginFailureHandler;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;


    /**
     * 自定义登录
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf防护, 才能进行登录
        http.csrf().disable();

        // 使用token, 关闭session, 不创建会话
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 表单登录
        // 其实/login和logout已经是SpringSecurity提供好的接口了, 我们只需要调用即可
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/index")
                // 原先使用的登录成功之后跳转的页面
                // .successForwardUrl("/hello")
                // 现在登录成功使用的是successHandler处理, 返回了一个token字符串, 所以就不会跳转页面了
                .loginProcessingUrl("/login").successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
                .and()
                // 授权的拦截器和异常处理器, token的解析, 获取权限
                // jwtAuthorizationFilter在UsernamePasswordAuthenticationFilter之前执行，查看有无token和解析token
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        // 解决不允许显示在iframe的问题
        http.headers().frameOptions().disable();
        http.headers().cacheControl();

        // 授权(配置方式)
        http.authorizeRequests()
                // 放行/index页面, /error页面, /test.html, /scenery02
                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/error").permitAll()
                // 为何/test.html被拦截, 而/scenery02放行了?
                .antMatchers("/test.html").permitAll()
                .antMatchers("/scenery02").permitAll()
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

        // 记住我
        // 设置数据源, 超时时间(单位秒), 自定义登录逻辑, 设置remember-me的入参名称, 默认是remember-me
        http.rememberMe().tokenRepository(persistentTokenRepository).tokenValiditySeconds(120)
                .userDetailsService(myAuthenticationService).rememberMeParameter("remember-me");

        // 退出登录
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/index");

    }


    /**
     * 自定义登录逻辑与密码加密和解析器
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
