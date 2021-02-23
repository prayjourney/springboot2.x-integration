package com.zgy.bootintegration;

import com.zgy.bootintegration.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: z.g.y
 * @date: 2020/5/25
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StudentTest {
    @Autowired
    StudentService studentService;

    @Test
    public void testInsertStudent() {
        studentService.insertStudent();
    }


    // 会更新全部的字段，需要注意
    @Test
    public void testUpdateStudent() {
        studentService.updateStudent();
    }


    @Test
    public void testGetStudentById() {
        studentService.getStudentById(101);
    }

    @Test
    public void testGetStudentByName() {
        studentService.getStudentByName("关羽");
    }
}
