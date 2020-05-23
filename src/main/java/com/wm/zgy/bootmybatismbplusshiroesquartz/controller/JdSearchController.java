package com.wm.zgy.bootmybatismbplusshiroesquartz.controller;

import com.wm.zgy.bootmybatismbplusshiroesquartz.service.ContentService;
import com.wm.zgy.bootmybatismbplusshiroesquartz.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @ResponseBody
    @GetMapping("search")
    public String search(String keyword, int pageNo, int pageSize) throws IOException {
        List<Map<String, Object>> vueBooks = service.searchContentWithPage(keyword, pageNo, pageSize);
        return JSONUtil.getJsonFromObject(vueBooks);
    }
}
