package com.zgy.bootintegration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.bootintegration.pojo.Student;
import com.zgy.bootintegration.service.RedisService;
import com.zgy.bootintegration.service.StudentService;
import com.zgy.bootintegration.utils.JacksonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @author: z.g.y
 * @date: 2020/7/2
 * @description:
 */
@Slf4j
@Controller
@RequestMapping("student")
@Api(value = "学生Controller", tags = "学生管理的接口")
public class StudentController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "info")
    public String studentInfo(String info) {
        // 此处的info 和ajax的data: {"info": formData}，这个参数名是一样的，要对应起来
        System.out.println(info);
        // 此处相当于返回了处理的结果！
        return "hello info!";
    }

    @ResponseBody
    @GetMapping("get")
    @ApiOperation(value = "按照id查询学生", notes = "按照id查询学生", httpMethod = "GET")
    public String getStudentById(Integer stId) throws JsonProcessingException {
        // 先到缓存之中查，如果没有然后到数据库中查询并且缓存到缓存之中
        if (redisService.hasKey("student:" + String.valueOf(stId))) {
            log.info("redis之中有该数据，key是{}", "student:" + stId);
            return JacksonUtil.obj2Str(redisService.get("student:" + String.valueOf(stId)) + "===》redis中获取!");
        } else {
            Student student = studentService.getStudentById(stId);
            log.info("redis之中没有该数据，需要从数据库之中查询，然后写入redis数据库之中", stId);
            redisService.set("student:" + String.valueOf(stId), student);
            return JacksonUtil.obj2Str(student + "===》从数据库之中获取，并且缓存到redis之中!");
        }
    }

    @ResponseBody
    @PostMapping("delete")
    @ApiOperation(value = "按照id删除学生", notes = "按照id删除学生", httpMethod = "POST")
    public String deleteStudentById(Integer stId) throws JsonProcessingException {
        if (redisService.hasKey("student:" + String.valueOf(stId))) {
            // 先删除redis缓存，然后删除数据库值
            redisService.del("student:" + String.valueOf(stId));
            studentService.deleteStudentById(stId);
            log.warn("先删除redis缓存, 然后删除了数据库值! 时间是：{}", LocalDateTime.now());
            return JacksonUtil.obj2Str("删除redis缓存, 删除了数据库值!");
        } else {
            studentService.deleteStudentById(stId);
            log.warn("删除了数据库值! 时间是：{}", LocalDateTime.now());
            return JacksonUtil.obj2Str("删除了数据库值!");
        }
    }
}
