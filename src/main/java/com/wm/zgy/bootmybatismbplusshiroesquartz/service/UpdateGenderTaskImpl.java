package com.wm.zgy.bootmybatismbplusshiroesquartz.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.mapper.StudentMapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author renjiaxin
 * @Date 2020/6/1
 * @Description
 */
@Service
@Slf4j
public class UpdateGenderTaskImpl implements UpdateTask {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Async("asyncServiceExecutor")
    public void update(Integer key, Student student) throws InterruptedException {
        student.setStGender('女');
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>(student);
        updateWrapper.eq("stId", key);
        // System.out.println(updateWrapper.getSqlSet().toString());
        log.info("开始更新， stId: " + key);
        TimeUnit.MILLISECONDS.sleep(30L);
        // studentMapper.update(student, updateWrapper);
        // studentMapper.updateById(student);
        studentMapper.updateStudent(key, student);
        log.info("更新结束， stId: " + key);
    }
}
