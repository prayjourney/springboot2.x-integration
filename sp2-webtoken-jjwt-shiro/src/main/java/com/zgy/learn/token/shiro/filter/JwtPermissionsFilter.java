package com.zgy.learn.token.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author renjiaxin
 * @date 2021/2/20
 */
@Slf4j
@Component
public class JwtPermissionsFilter extends PermissionsAuthorizationFilter {
    /**
     * 用户无权访问url时，此方法会被调用
     * 默认实现为org.apache.shiro.web.filter.authz.AuthorizationFilter#onAccessDenied()，覆盖父类的方法，返回自定义信息给前端
     * 接口doc上说：
     * AuthorizationFilter子类(权限授权过滤器)的onAccessDenied()应该永远返回false，那么在onAccessDenied()内就必然要发送response响应给前端，不然前端就收不到任何数据
     * AuthenticationFilter, AuthenticatingFilter子类(身份认证过滤器)的onAccessDenied()的返回值则没有限制
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("权限不足！");
        }
        return false;
    }
}
