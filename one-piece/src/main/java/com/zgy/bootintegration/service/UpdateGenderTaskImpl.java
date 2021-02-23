package com.zgy.bootintegration.service;

import com.zgy.bootintegration.mapper.StudentMapper;
import com.zgy.bootintegration.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: z.g.y
 * @date: 2020/6/1
 * @description:
 */
@Service
@Slf4j
public class UpdateGenderTaskImpl implements UpdateTask {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Async("asyncServiceExecutor")
    public void update(Student student) throws InterruptedException {
        student.setStGender('1');
        // UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>(student);
        // updateWrapper.eq("stId", key);
        // System.out.println(updateWrapper.getSqlSet().toString());
        log.info("开始更新， stId: " + student.getStId());
        TimeUnit.MILLISECONDS.sleep(30L);
        // studentMapper.update(student, updateWrapper);
        // studentMapper.updateById(student);
        studentMapper.updateStudent(student);
        log.info("更新结束， stId: " + student.getStId());
    }
}
