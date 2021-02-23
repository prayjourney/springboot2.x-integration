package com.zgy.learn.bootvue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.bootvue.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 8/15/2020 11:14 PM
 * @modified:
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
