package com.zgy.learn.bootvue.controller;

import com.zgy.learn.bootvue.pojo.Student;
import com.zgy.learn.bootvue.service.StudentService;
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
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // 获取所有学生
    @GetMapping("all")
    public List<Student> getAllStudent() {
        return studentService.getAll();
    }

    // 查询一个学生
    @GetMapping("get")
    public Student getStudentById(Integer id) {
        return studentService.getStudentById(id);
    }

    // 添加一个学生
    @PostMapping("add")
    public Integer addStudent(Student student) {
        return studentService.addStudent(student);
    }

    // 删除一个学生
    @GetMapping("delete")
    public Integer deleteStudentById(Integer id) {
        return studentService.deleteStudentById(id);
    }

    // 更新一个学生
    @PostMapping("update")
    public Integer updateTeacherById(Student student) {
        return studentService.updateStudentById(student);
    }
}
