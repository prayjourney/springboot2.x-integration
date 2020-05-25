package com.wm.zgy.bootmybatismbplusshiroesquartz.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.mapper.StudentMapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Author renjiaxin
 * @Date 2020/5/25
 * @Description
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper mapper;

    List<String> names = Arrays.asList("张三", "lisi", "雨涵", "kiristina", "MG-HOTdog!");
    List<String> cities = Arrays.asList("四川省乐山市", "四川省成都市", "江苏省南京市", "广东省广州市", "北京市");

    // 插入学生
    public void insertStudent() {
        Student student = Student.builder().cityId(1).classId(2).hobbyGroupId(1).stAge(22).stGender('男')
                .stHome(cities.get(new Random().nextInt(5)))
                .stName(names.get(new Random().nextInt(5)))
                .stId(Integer.valueOf(String.valueOf(UUID.randomUUID().getLeastSignificantBits()).substring(10, 15)))
                .build();
        mapper.insert(student);
    }

    // 更新学生信息，部分更新===============》会更新全部的字段不符合要求，需要注意！
    public void updateStudent() {
        Student student = Student.builder().hobbyGroupId(1).stAge(22)
                .stHome(cities.get(new Random().nextInt(5)))
                .stName(names.get(new Random().nextInt(5)))
                .build();
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        // 更新条件
        wrapper.eq("stId", 1111);
        mapper.update(student, wrapper);
    }
}
