package org.zgy.myschool.service.impl;

import org.zgy.myschool.pojo.Teacher;
import org.zgy.myschool.mapper.TeacherMapper;
import org.zgy.myschool.service.TeacherSevice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zgy
 * @since 2020-05-19
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherSevice {

}
