package com.wm.zgy.bootmybatismbplusshiroesquartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/23 0:47
 * @Modified by:
 */
@Controller
public class JdSearchController {
    @GetMapping("jd")
    public String helloJdSearch(){
        return "jd";

    }
}
