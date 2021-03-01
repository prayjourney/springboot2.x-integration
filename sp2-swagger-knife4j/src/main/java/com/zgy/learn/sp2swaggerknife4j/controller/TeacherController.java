package com.zgy.learn.sp2swaggerknife4j.controller;

import com.zgy.learn.sp2swaggerknife4j.pojo.Teacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: z.g.y
 * @date: 2021/3/1
 */
@Api(tags = "教师模块")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @ApiOperation(value = "使用id查询姓名")
    @GetMapping("/name")
    public String getNameById(Integer id) {
        return "小花老师";
    }

    @ApiOperation(value = "使用id查询老师详细信息")
    @GetMapping("/info")
    public Teacher getTeacherById(Integer id) {
        return new Teacher().setId(1).setName("小花老师").setAge(12).setGender(true).setSubject("自然")
                .setAddress("天津市塘沽区霍元甲村33号").setPhone("18899992222");
    }

}
