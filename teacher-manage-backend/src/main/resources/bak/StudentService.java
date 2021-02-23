package com.zgy.learn.bootvue.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.bootvue.mapper.StudentMapper;
import com.zgy.learn.bootvue.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 8/15/2020 11:27 PM
 * @modified:
 */
@Service
public class StudentService {
    @Autowired(required = false)
    private StudentMapper mapper;

    // 获取所有学生
    public List<Student> getAll() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("id", 0);
        return mapper.selectList(wrapper);
    }

    // 添加一个学生
    public Integer addStudent(Student student) {
        return mapper.insert(student);
    }

    // 删除一个学生
    public Integer deleteStudentById(Integer id) {
        return mapper.deleteById(id);
    }

    // 更新一个学生
    public Integer updateStudentById(Student student) {
        return mapper.updateById(student);
    }

    // 查询一个学生
    public Student getStudentById(Integer id) {
        return mapper.selectById(id);
    }
}
