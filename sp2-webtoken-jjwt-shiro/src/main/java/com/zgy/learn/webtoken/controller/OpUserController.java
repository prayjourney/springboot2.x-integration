package com.zgy.learn.webtoken.controller;

import com.zgy.learn.webtoken.pojo.OpUser;
import com.zgy.learn.webtoken.service.OpUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * OpUser表控制层
 *
 * @author z.g.y
 * @since 2021-02-01 00:57:55
 */
@RestController
@RequestMapping("opUser")
public class OpUserController {
    /**
     * 服务对象
     */
    @Resource
    private OpUserService opUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OpUser selectOne(Integer id) {
        return this.opUserService.queryById(id);
    }

}