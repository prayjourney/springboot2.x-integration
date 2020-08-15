package com.zgy.learn.bootvue.controller;

import com.zgy.learn.bootvue.pojo.Teacher;
import com.zgy.learn.bootvue.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 8/15/2020 11:37 PM
 * @Modified by:
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    // 获取所有老师
    @GetMapping("all")
    public List<Teacher> getAllTeacher() {
        return teacherService.getAll();
    }

    // 查询一个老师
    @GetMapping("get")
    public Teacher getTeacherById(Integer id) {
        return teacherService.getTeacherById(id);
    }

    // 添加一个老师
    @PostMapping("add")
    public Integer addTeacher(Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    // 删除一个老师
    @GetMapping("delete")
    public Integer deleteTeacherById(Integer id) {
        return teacherService.deleteTeacherById(id);
    }

    // 更新一个老师
    @PostMapping("update")
    public Integer updateTeacherById(Teacher teacher) {
        return teacherService.updateTeacherById(teacher);
    }
}
