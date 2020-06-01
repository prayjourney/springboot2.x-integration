package com.wm.zgy.bootmybatismbplusshiroesquartz.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.mapper.StudentMapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: renjiaxin
 * @Despcription: 更新年龄
 * @Date: Created in 2020/5/31 23:14
 * @Modified by:
 */
@Service
@Slf4j
public class UpdateAgeTaskService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UpdateAgeTaskImpl ageTask;


    public Map<Integer, Student> getStudentIdMaps() {
        Map<Integer, Student> studentIdMaps = new HashMap<>();
        List<Student> allStudentList = studentMapper.getAllStudentList();
        for (Student s : allStudentList) {
            studentIdMaps.put(s.getStId(), s);
        }
        return studentIdMaps;
    }

    // 在同一个类之中调用，不会触发异步效果
    /*
    @Override
    @Async("asyncServiceExecutor") //===============>为啥线程池换了，有问题啊
    public void update(Integer key, Student student) throws InterruptedException {
        Integer age = student.getStAge();
        student.setStAge(age + 2);
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>(student);
        updateWrapper.eq("stId", key);
        log.info("开始更新， stId: " + key);
        TimeUnit.MILLISECONDS.sleep(30L);
        studentMapper.update(student, updateWrapper);
        // studentMapper.updateById(student);
        log.info("更新结束， stId: " + key);

    }
    */


    public void updateAge() throws InterruptedException {
        Map<Integer, Student> studentIdMaps = getStudentIdMaps();
        // 操作数据，放入到线程池里面
        Set<Map.Entry<Integer, Student>> entrySet = studentIdMaps.entrySet();
        Iterator<Map.Entry<Integer, Student>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Student> next = iterator.next();
            Student student = next.getValue();
            // 异步执行
            ageTask.update(student);
        }
    }
}
