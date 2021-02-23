package com.zgy.multipledatasource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.multipledatasource.mapper.WorkerMapper;
import com.zgy.multipledatasource.pojo.Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: z.g.y
 * @date: 2020/10/20
 * @description:
 */
@Service
@Slf4j
public class WorkerService {
    // @Autowired(required = false) 解决注入报错
    @Autowired(required = false)
    private WorkerMapper workerMapper;

    public Worker selectWorkerById(Integer id) {
        log.info("selectWorkerById, WorkerMapper!");
        return workerMapper.selectById(id);
    }

    public int deleteWorkerById(Integer id) {
        log.info("deleteWorkerById, WorkerMapper!");
        return workerMapper.deleteById(id);
    }

    public String getWorkerNameById(Integer id) {
        log.info("getWorkerNameById, WorkerMapper!");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        return workerMapper.selectOne(wrapper).getName();
    }
}
