package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/17 22:00
 * @Modified by:
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    List<Student> getAllStudentList();

    void updateStudent(Student student);
}
