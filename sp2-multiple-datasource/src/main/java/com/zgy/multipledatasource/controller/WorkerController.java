package com.zgy.multipledatasource.controller;

import com.zgy.multipledatasource.pojo.Worker;
import com.zgy.multipledatasource.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: z.g.y
 * @date: 2020/10/20
 * @description:
 */
@RestController
@Slf4j
@RequestMapping(value = "worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @RequestMapping("info")
    public Worker selectWorkerById(Integer id) {
        log.info("selectWorkerById");
        return workerService.selectWorkerById(id);
    }

    @RequestMapping("delete")
    public int deleteWorkerById(Integer id) {
        log.info("deleteWorkerById");
        return workerService.deleteWorkerById(id);
    }

    @RequestMapping("name")
    public String getWorkerNameById(Integer id) {
        log.info("getWorkerNameById");
        return workerService.getWorkerNameById(id);
    }
}
