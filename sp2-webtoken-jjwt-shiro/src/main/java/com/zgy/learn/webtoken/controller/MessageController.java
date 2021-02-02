package com.zgy.learn.webtoken.controller;


import com.zgy.learn.webtoken.pojo.Message;
import com.zgy.learn.webtoken.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Message表控制层
 *
 * @author z.g.y
 * @since 2021-02-02 15:36:44
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Message selectOne(Integer id) {
        return this.messageService.queryById(id);
    }

}