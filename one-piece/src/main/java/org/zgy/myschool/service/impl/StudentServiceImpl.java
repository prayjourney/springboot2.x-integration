package org.zgy.myschool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zgy.myschool.mapper.StudentMapper;
import org.zgy.myschool.pojo.Student;
import org.zgy.myschool.service.StudentSevice;

/**
 * @author: zgy
 * @since: 2020-05-19
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentSevice {

}
