package com.zgy.bootintegration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.bootintegration.annotation.KidLoginToken;
import com.zgy.bootintegration.annotation.PassToken;
import com.zgy.bootintegration.pojo.Kid;
import com.zgy.bootintegration.service.KidService;
import com.zgy.bootintegration.service.MongoService;
import com.zgy.bootintegration.service.TokenService;
import com.zgy.bootintegration.utils.JacksonUtil;
import com.zgy.bootintegration.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/8/9 12:29
 * @modified:
 */
@Slf4j
@Controller
@RequestMapping("kidtoken")
@Api(value = "KidController", tags = "kid管理的接口")
public class KidTokenController {
    @Autowired
    KidService kidService;

    @Autowired
    TokenService tokenService;

    @Autowired
    MongoService mongoService;

    /**
     * 登录, 生成token, 可以设置token的位置
     *
     * @param kid
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Kid kid, HttpServletResponse response) throws JSONException {
        Kid origin = kidService.findKidById(kid.getId());
        Kid kid001 = new Kid();

        kid001.setId(origin.getId());
        kid001.setUsername(origin.getUsername());
        kid001.setPassword(origin.getPassword());
        if (!kid001.getPassword().equals(kid.getPassword())) {
            log.error("登录失败,密码错误!");
            return "index";
        } else {
            String token = tokenService.createToken(kid001);
            // String token = tokenService.createTokenComplete(kid001);

            // 1. 把token添加到了cookie之中
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);

            // 2. 把token添加到了header之中
            // response.addHeader("token", token);

            log.info("token");
            return "index";
        }
    }

    /**
     * 这个请求需要验证token才能访问
     */
    @ResponseBody
    @KidLoginToken
    @RequestMapping(value = "/getmessage", method = RequestMethod.GET)
    public String getMessage() {
        // 取出token中带的用户id 进行操作
        log.info("用户id是：" + JwtTokenUtil.getTokenKidId());
        return "您已通过验证";
    }

    /**
     * 跳过token验证
     *
     * @return
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/getmessagenotoken", method = RequestMethod.GET)
    public String getMessageNoToken() {
        log.info("不需要token验证");
        return "不需要token验证";
    }

    // 获取所有的kids
    @ApiOperation(value = "查询所有的学生", notes = "查询所有的学生", httpMethod = "GET")
    @ResponseBody
    @GetMapping("allkids")
    public String getAllKids() throws JsonProcessingException {
        return JacksonUtil.obj2Str(mongoService.queryAll());
    }

    // 按照ID查询kid
    @ApiOperation(value = "按照id查询kid的信息", notes = "按照id查询kid的信息", httpMethod = "GET")
    @ApiImplicitParam(name = "id", dataType = "Integer", required = true)
    @ResponseBody
    @GetMapping("getkidbyid")
    public String getKidById(@RequestParam("id") Integer id) throws JsonProcessingException {
        if (null == id) {
            log.error("kid信息有误！{}", id);
            return "学生信息有误！";
        }
        log.info("====>从mongo数据库之中查找学生的数据！{}", id);
        return JacksonUtil.obj2Str(mongoService.queryById(id));
    }

    // 添加一个kid
    @ApiOperation(value = "添加一个kid", notes = "添加一个kid", httpMethod = "POST")
    @ApiImplicitParam(name = "kid", dataTypeClass = Kid.class, required = true)
    @PostMapping("addkid")
    @ResponseBody
    public String addKid(Kid kid) throws JsonProcessingException {
        if (null == kid) {
            return "kid信息有误！";
        } else {
            List<Kid> list = mongoService.queryAll();
            List<Integer> kidIds = new ArrayList<>();
            for (Kid one : list) {
                kidIds.add(one.getId());
            }
            if (kidIds.contains(kid.getId())) {
                log.warn("Kid Id已经存在！{}", kid.getId());
                return "Kid Id已经存在！";
            } else {
                // 写入到数据库
                String str = JacksonUtil.obj2Str(mongoService.insert(kid));
                log.info("mongo之中成功插入了学号为 {}的学生!", kid.getId());
                return str;
            }
        }
    }

    // 删除一个kid
    @ApiOperation(value = "删除一个kid", notes = "删除一个kid", httpMethod = "POST")
    @ApiImplicitParam(name = "id", dataTypeClass = Integer.class, required = true)
    @PostMapping("deletekidbyid")
    @ResponseBody
    public String deleteKidById(@RequestParam("id") Integer id) throws JsonProcessingException {
        if (null == id) {
            log.error("kid信息有误！");
            return "kid信息有误！";
        }
        long count = mongoService.delete(id);
        if (count >= 1) {
            log.info("===>mongo之中成功删除了id为 {}的kid!", id);
            return JacksonUtil.obj2Str("删除成功！");
        }
        return JacksonUtil.obj2Str("学生不存在！");
    }

    // 更新一个学生
    @ApiOperation(value = "更新学生信息", notes = "更新学生信息", httpMethod = "POST")
    @ApiImplicitParam(name = "kid", dataTypeClass = Kid.class, required = true)
    @ResponseBody
    @RequestMapping("updatekidbyid")
    public String updateKidById(Kid kid) throws JsonProcessingException {
        if (null == kid) {
            log.warn("kid信息不允许为空！");
            return "kid信息不允许为空！";
        } else if (null != kid.getId() || null == kid.getUsername() || null == kid.getPassword()) {
            log.warn("kid信息不能为空！");
            return "kid信息不能为空！";
        } else if (kid.getId() <= 0) {
            log.warn("kid信息错误！");
            return "kid信息错误！";
        } else {
            return JacksonUtil.obj2Str(mongoService.update(kid) == 1L ? "更新成功" : "更新出错");
        }
    }
}
