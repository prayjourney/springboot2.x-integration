package com.zgy.bootintegration.service;

import com.zgy.bootintegration.mapper.StudentMapper;
import com.zgy.bootintegration.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: z.g.y
 * @description: 更新性别
 * @date: Created in 2020/5/31 23:14
 * @modified:
 */
@Service
@Slf4j
//@EnableAspectJAutoProxy(proxyTargetClass = true) //此处不需要这个
public class UpdateGenderTaskService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UpdateGenderTaskImpl genderTask;


    public Map<Integer, Student> getStudentIdMaps() {
        Map<Integer, Student> studentIdMaps = new HashMap<>();
        List<Student> allStudentList = studentMapper.getAllStudentList();
        for (Student s : allStudentList) {
            studentIdMaps.put(s.getStId(), s);
        }
        return studentIdMaps;
    }


    public void updateGender() throws InterruptedException {
        Map<Integer, Student> studentIdMaps = getStudentIdMaps();
        // 操作数据，放入到线程池里面
        Set<Map.Entry<Integer, Student>> entrySet = studentIdMaps.entrySet();
        Iterator<Map.Entry<Integer, Student>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            TimeUnit.MILLISECONDS.sleep(60L);
            Map.Entry<Integer, Student> next = iterator.next();
            Student student = next.getValue();
            // 执行更新任务, 异步执行
            genderTask.update(student);
        }
    }
}
