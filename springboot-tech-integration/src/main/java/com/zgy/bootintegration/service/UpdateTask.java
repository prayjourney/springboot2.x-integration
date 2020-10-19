package com.zgy.bootintegration.service;

import com.zgy.bootintegration.pojo.Student;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/31 23:13
 * @Modified by:
 */
public interface UpdateTask {
    void update(Student student) throws InterruptedException;
}
