package com.zgy.bootintegration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/6/21 0:28
 * @modified: java8之中的optional类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class J8Optional {
    @Test
    public void testCreateOptional() {
        // empty, 只是创建空实例
        Optional<Object> empty = Optional.empty();
        // of不能创建null实例
        Optional<String> hello = Optional.of("hello");
        // ofNullable, 如果为空就创建null, 不为空就正常创建
        Optional<String> s = Optional.ofNullable("123");

        System.out.println(empty.get());
        System.out.println(s.get());
        System.out.println(hello.get());
    }

    @Test
    public void testCreateOptional02() {
        // ofNullable, 如果为空就创建null, 不为空就正常创建
        Optional<String> s = Optional.ofNullable(null);
        if (s.isPresent()) {
            System.out.println();
        }
        // 如果为空，提供了一个默认值
        String ss = s.orElse(new String("hello mmmm"));
        System.out.println(ss);
    }

}
