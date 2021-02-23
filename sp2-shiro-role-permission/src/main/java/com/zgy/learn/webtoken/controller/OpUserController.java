package com.zgy.learn.webtoken.controller;

import com.zgy.learn.webtoken.pojo.OpUser;
import com.zgy.learn.webtoken.service.OpUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * OpUser表控制层
 *
 * @author: z.g.y
 * @date: 2021/2/1
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

    /**
     * 创建用户
     *
     * @param username
     * @param password
     * @param email
     * @param realName
     * @param tel
     * @return
     */
    @PostMapping("createUser")
    public OpUser createUser(String username, String password, String email, String realName, String tel) {
        Date now = new Date();
        // 加密密码
        String salt = UUID.randomUUID().toString();
        String algorithmName = "SHA-256";
        Integer hashNumber = 1024;
        String encryptPassword = new SimpleHash(algorithmName, password, salt, hashNumber).toString();

        // 生成用户
        OpUser opUser = new OpUser();
        opUser.setUsername(username).setPassword(encryptPassword).setSalt(salt).setEmail(email).setRealName(realName).setTel(tel);
        opUser.setAccountDisabled(0).setAccountExpired(0).setAccountLocked(0).setCredentialsExpired(0);
        opUser.setCreateTime(now).setUpdateTime(now);
        return opUserService.insert(opUser);
    }

}