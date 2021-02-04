package com.zgy.learn.webtoken.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.stereotype.Component;

/**
 * @author renjiaxin
 * @date 2021/2/4
 */
@Component
public class JwtTokenWebSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        // 禁用session, // fix: 浏览器session ? shiro的session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
