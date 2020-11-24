package com.zgy.learn.richtextwangeditor.controller;

import com.zgy.learn.richtextwangeditor.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020-11-25 上午 12:27
 * @Modified by:
 */
@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("article")
    public String go2ArticlePage() {
        return "article";
    }

    @RequestMapping("browse")
    public String go2BrowsePage() {
        return "browse";
    }

}
