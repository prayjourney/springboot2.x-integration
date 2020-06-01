package com.wm.zgy.bootmybatismbplusshiroesquartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.Student;
import io.swagger.models.auth.In;
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

    void updateStudent(Integer key, Student student);
}
