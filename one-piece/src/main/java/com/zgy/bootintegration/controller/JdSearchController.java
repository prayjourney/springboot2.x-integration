package com.zgy.bootintegration.controller;

import com.zgy.bootintegration.service.ContentService;
import com.zgy.bootintegration.utils.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/23 0:47
 * @modified:
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
    @GetMapping("search/{keyword}/{pageNo}/{pageSize}")
    public String search(@PathVariable("keyword") String keyword,
                         @PathVariable("pageNo") int pageNo,
                         @PathVariable("pageSize") int pageSize) throws IOException {
        List<Map<String, Object>> vueBooks = service.searchContentWithPage(keyword, pageNo, pageSize);
        return JacksonUtil.getJsonFromObject(vueBooks);
    }
}
