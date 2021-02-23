package com.zgy.learn.bootvue.controller;

import com.zgy.learn.bootvue.pojo.Teacher;
import com.zgy.learn.bootvue.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 8/15/2020 11:37 PM
 * @modified:
 */
@Slf4j
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    // 获取所有老师
    @CrossOrigin
    @GetMapping("all")
    public List<Teacher> getAllTeacher() {
        log.info("获取所有老师");
        return teacherService.getAll();
    }

    // 查询一个老师
    @CrossOrigin
    @GetMapping("get")
    public Teacher getTeacherById(Integer id) {
        log.info("查询一个老师， id是:{} !", id);
        return teacherService.getTeacherById(id);
    }

    // 添加一个老师
    @CrossOrigin
    @PostMapping("add")
    public Integer addTeacher(@RequestBody Teacher teacher) {
        log.info("添加一个老师， 老师是:{} !", teacher.toString());
        return teacherService.addTeacher(teacher);
    }

    // 删除一个老师
    @CrossOrigin
    @GetMapping("delete")
    public Integer deleteTeacherById(Integer id) {
        log.info("删除一个老师， id是:{} !", id);
        return teacherService.deleteTeacherById(id);
    }

    // 更新一个老师
    @CrossOrigin
    @PostMapping("update")
    public Integer updateTeacherById(@RequestBody Teacher teacher) {
        log.info("更新一个老师， 老师是:{} !", teacher.toString());
        return teacherService.updateTeacherById(teacher);
    }
}
