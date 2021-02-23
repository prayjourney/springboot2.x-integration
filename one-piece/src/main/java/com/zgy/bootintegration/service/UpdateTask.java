package com.zgy.bootintegration.service;

import com.zgy.bootintegration.pojo.Student;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/31 23:13
 * @modified:
 */
public interface UpdateTask {
    void update(Student student) throws InterruptedException;
}
