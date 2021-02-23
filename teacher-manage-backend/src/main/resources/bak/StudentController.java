package com.zgy.learn.bootvue.controller;

import com.zgy.learn.bootvue.pojo.Student;
import com.zgy.learn.bootvue.service.StudentService;
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
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // 获取所有学生
    @CrossOrigin
    @GetMapping("all")
    public List<Student> getAllStudent() {
        log.info("获取所有学生");
        return studentService.getAll();
    }

    // 查询一个学生
    @CrossOrigin
    @GetMapping("get")
    public Student getStudentById(Integer id) {
        log.info("查询一个学生, id是:{} !", id);
        return studentService.getStudentById(id);
    }

    // 添加一个学生
    @CrossOrigin
    @PostMapping("add")
    public Integer addStudent(@RequestBody Student student) {
        log.info("添加一个学生, 学生是:{} !", student.toString());
        return studentService.addStudent(student);
    }

    // 删除一个学生
    @CrossOrigin
    @GetMapping("delete")
    public Integer deleteStudentById(Integer id) {
        log.info("删除一个学生, id是:{} !", id);
        return studentService.deleteStudentById(id);
    }

    // 更新一个学生
    @CrossOrigin
    @PostMapping("update")
    public Integer updateTeacherById(@RequestBody Student student) {
        log.info("更新一个学生, 学生是:{} !", student.toString());
        return studentService.updateStudentById(student);
    }
}
