package com.zgy.learn.sp2swaggerknife4j.controller;

import com.zgy.learn.sp2swaggerknife4j.pojo.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: z.g.y
 * @date: 2021/3/1
 */
@Api(tags = "学生模块")
@RestController
@RequestMapping("/student")
public class StudentController {

    @ApiOperation(value = "使用id查询姓名")
    @GetMapping("/name")
    public String getNameById(Integer id) {
        return "张三";
    }

    @ApiOperation(value = "使用id查询学生全部信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @GetMapping("/info")
    public Student getStudentById(Integer id) {
        return new Student().setId(1).setName("张三").setAge(12).setGender(false).setGrade("5年级").setRoom("二班")
                .setAddress("北京三里屯牛批村22号");
    }

    @ApiOperation(value = "更新学生名字")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true),
            @ApiImplicitParam(name = "name", value = "姓名", required = true)
    })
    @PostMapping("/update")
    public Student updateNameById(Integer id, @RequestParam("name") String name) {
        return new Student().setId(1).setName(name).setAge(12).setGender(false).setGrade("5年级").setRoom("二班")
                .setAddress("北京三里屯牛批村22号");
    }

}
