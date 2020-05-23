package com.wm.zgy.bootmybatismbplusshiroesquartz.controller;

import com.wm.zgy.bootmybatismbplusshiroesquartz.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/23 0:47
 * @Modified by:
 */
@Controller
@RequestMapping("jd")
public class JdSearchController {

    @Autowired
    private ContentService service;

    // 设置两个url
    @GetMapping({"/", "index"})
    public String helloJdSearch() {
        return "jd";
    }
}
