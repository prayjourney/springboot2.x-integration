package com.zgy.learn.richtextwangeditor.controller;

import com.zgy.learn.richtextwangeditor.pojo.Article;
import com.zgy.learn.richtextwangeditor.service.ArticleService;
import com.zgy.learn.richtextwangeditor.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020-11-25 上午 12:27
 * @Modified by:
 */
@Controller
@Slf4j
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


    @ResponseBody
    @PostMapping("add")
    public Result addArticle(Article article) {
        if (null == article) {
            log.error("文章不能为空！");
            return new Result().setCode(101).setMessage("fail");
        }
        log.info("开始创建文章");
        return articleService.addArticle(article);
    }


    @ResponseBody
    @GetMapping("get")
    public Result selectArticleById(Integer id) {
        if (null == id || id <= 0) {
            log.error("文章id有误！");
            return new Result().setCode(101).setMessage("fail");
        }
        log.info("开始获取文章");
        return articleService.selectArticleById(id);
    }

}
