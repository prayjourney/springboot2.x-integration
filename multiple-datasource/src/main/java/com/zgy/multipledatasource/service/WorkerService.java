package com.zgy.multipledatasource.service;

import com.zgy.multipledatasource.mapper.WorkerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author renjiaxin
 * @Date 2020/10/20
 * @Description
 */
@Service
@Slf4j
public class WorkerService {
    // @Autowired(required = false) 解决注入报错
    @Autowired(required = false)
    private WorkerMapper workerMapper;

}
