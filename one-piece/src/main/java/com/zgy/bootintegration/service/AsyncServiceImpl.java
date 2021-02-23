package com.zgy.bootintegration.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/31 18:52
 * @modified:
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        log.info("start executeAsync");
        try {
            Thread.sleep(10000);
            System.out.println("好瞌睡啊。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("end executeAsync");
    }
}
