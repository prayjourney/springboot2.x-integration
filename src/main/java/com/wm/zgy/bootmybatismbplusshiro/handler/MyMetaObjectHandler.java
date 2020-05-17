package com.wm.zgy.bootmybatismbplusshiro.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/17 23:56
 * @Modified by:
 */
@Slf4j
@Component // 加入到IOC容器之中
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入时候的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    // 更新时候的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
