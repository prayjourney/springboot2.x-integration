package com.zgy.learn.bootvue.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.bootvue.mapper.TeacherMapper;
import com.zgy.learn.bootvue.pojo.Teacher;
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
public class TeacherService {
    @Autowired(required = false)
    private TeacherMapper mapper;

    // 获取所有老师
    public List<Teacher> getAll() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("id", 0);
        return mapper.selectList(wrapper);
    }

    // 添加一个老师
    public Integer addTeacher(Teacher teacher) {
        return mapper.insert(teacher);
    }

    // 删除一个老师
    public Integer deleteTeacherById(Integer id) {
        return mapper.deleteById(id);
    }

    // 更新一个老师
    public Integer updateTeacherById(Teacher teacher) {
        return mapper.updateById(teacher);
    }

    // 查询一个学生
    public Teacher getTeacherById(Integer id) {
        return mapper.selectById(id);
    }
}
