package com.wm.zgy.bootmybatismbplusshiroesquartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/6/21 0:28
 * @Modified by: java8之中的optional类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class J8Optional {
    @Test
    public void testCreateOptional(){
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

}
