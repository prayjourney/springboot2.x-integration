package com.zgy.bootintegration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.bootintegration.service.QuartzService;
import com.zgy.bootintegration.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: z.g.y
 * @date: 2020/5/22
 * @description:
 */
@Controller
@Slf4j
public class QuartzController {

    @Autowired
    @Qualifier("quartzService")
    QuartzService service;

    @RequestMapping("printtime")
    @ResponseBody
    public String printTime() throws JsonProcessingException {
        log.info("print time ok" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        return JacksonUtil.getJsonFromObject(service.printTimeEveryMinutes());
    }
}
