package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/17 22:00
 * @modified:
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    List<Student> getAllStudentList();

    void updateStudent(Student student);

    int deleteByStudentId(int stId);
}
