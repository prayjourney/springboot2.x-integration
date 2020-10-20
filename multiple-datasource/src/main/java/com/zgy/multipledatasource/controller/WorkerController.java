package com.zgy.multipledatasource.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.multipledatasource.pojo.Worker;
import com.zgy.multipledatasource.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author renjiaxin
 * @Date 2020/10/20
 * @Description
 */
@RestController
@Slf4j
@RequestMapping(value = "worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @RequestMapping("info")
    public Worker selectWorkerById(Integer id) {
        return workerService.selectWorkerById(id);
    }

    @RequestMapping("delete")
    public int deleteWorkerById(Integer id) {
        return workerService.deleteWorkerById(id);
    }

    @RequestMapping("name")
    public String getWorkerNameById(Integer id) {
        return workerService.getWorkerNameById(id);
    }
}
