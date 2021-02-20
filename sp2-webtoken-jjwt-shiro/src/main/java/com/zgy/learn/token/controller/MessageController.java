package com.zgy.learn.token.controller;

import com.zgy.learn.token.pojo.Message;
import com.zgy.learn.token.pojo.OpUser;
import com.zgy.learn.token.service.MessageService;
import com.zgy.learn.token.util.result.Result;
import com.zgy.learn.token.util.result.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Message表控制层
 *
 * @author z.g.y
 * @since 2021-02-02 15:36:44
 */
@Slf4j
@Controller
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    @GetMapping("index")
    public String index() {
        return "message";
    }


    @GetMapping("addpage")
    public String addpage() {
        return "add";
    }


    /**
     * 到达添加message页面, 添加权限注解
     *
     * @return
     */
    @ResponseBody
    @GetMapping("add")
    @RequiresPermissions("addMessage")
    public Result<String> addMessage() {
        log.info("达到添加消息页面");
        return new Result<String>(Status.OKAY, "to add message oaky!", "/message/addpage");
    }


    /**
     * 添加message, 需要添加的权限
     *
     * @param name
     * @param content
     * @return
     */
    @ResponseBody
    @PostMapping("addmsg")
    //@RequiresPermissions("addMessage")
    public Result<String> createMessage(@RequestParam("name")String name, @RequestParam("content")String content) {
        Message message = new Message();
        Date now = new Date();

        // shiro获取当前对象
        Subject subject = SecurityUtils.getSubject();
        OpUser opUser = (OpUser) subject.getPrincipal();

        // 去掉首尾空格
        message.setName(name).setContent(content.trim()).setCreateTime(now).setUpdateTime(now).setUserId(opUser.getId());
        messageService.insert(message);
        return new Result<String>(Status.OKAY, "to add message oaky!", "/message/index");
    }


    /**
     * 删除message, 必须是admin角色
     *
     * @param id
     * @return
     */
    @GetMapping("delete")
    @RequiresRoles("admin")
    public String deleteMessage(Integer id) {
        messageService.deleteById(id);
        return "message";
    }


    /**
     * 阅读message, admin角色或者是user角色, 默认是AND
     *
     * @param id
     * @return
     */
    @GetMapping("read")
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    @ResponseBody
    public String readMessage(Integer id) {
        Message message = messageService.queryById(id);
        if (null == message) {
            return "没有这条消息";
        } else {
            return "消息内容：" + message.getContent();
        }
    }

}