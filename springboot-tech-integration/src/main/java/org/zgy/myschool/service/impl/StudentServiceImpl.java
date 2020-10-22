package org.zgy.myschool.service.impl;

import org.zgy.myschool.pojo.Student;
import org.zgy.myschool.mapper.StudentMapper;
import org.zgy.myschool.service.StudentSevice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zgy
 * @since 2020-05-19
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentSevice {

}
