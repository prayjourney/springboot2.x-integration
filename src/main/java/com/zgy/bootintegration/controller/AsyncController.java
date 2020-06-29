package com.zgy.bootintegration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.bootintegration.service.AsyncService;
import com.zgy.bootintegration.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/31 18:54
 * @Modified by:
 */
@RestController
@Slf4j
@RequestMapping("async")
public class AsyncController {
    @Autowired
    private AsyncService asyncService;


    @RequestMapping("testwork")
    public String testWork() throws JsonProcessingException {
        log.info("start submit!");
        asyncService.executeAsync();
        log.info("end submit!");
        return JSONUtil.getJsonFromObject("任务已经提交！");
    }
}
