package com.zgy.learn.bootshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.bootshiro.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author zuiguangyin
 * @Date 2020/11/5
 * @Description
 */
@Mapper
@Repository
public interface PersonMapper extends BaseMapper<Person> {

    @Select("select * from `person` where name = #{name}")
    public Person queryPersonByName(String name);
}
