package com.wm.zgy.bootmybatismbplusshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wm.zgy.bootmybatismbplusshiro.pojo.Student;
import com.wm.zgy.bootmybatismbplusshiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/17 22:00
 * @Modified by:
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
}
