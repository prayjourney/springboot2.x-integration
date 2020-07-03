package com.zgy.bootintegration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author renjiaxin
 * @Date 2020/7/2
 * @Description
 */
@Controller
@Slf4j
@RequestMapping("student")
public class StudentController {

    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "info")
    public String studentInfo(String info){
        // 此处的info 和ajax的data: {"info": formData}，这个参数名是一样的，要对应起来
        System.out.println(info);
        // 此处相当于返回了处理的结果！
        return "hello info!";
    }
}
