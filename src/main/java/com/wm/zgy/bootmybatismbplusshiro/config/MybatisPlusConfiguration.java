package com.wm.zgy.bootmybatismbplusshiro.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/18 1:34
 * @Modified by:
 */
@EnableTransactionManagement // 开启事务管理
@MapperScan("com.wm.zgy.bootmybatismbplusshiro.mapper") //扫描mapper文件夹
@Configuration
public class MybatisPlusConfiguration {
    // 注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return  new OptimisticLockerInterceptor();
    }
}
