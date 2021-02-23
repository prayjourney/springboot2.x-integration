package com.zgy.learn.richtextwangeditor.controller;

import com.zgy.learn.richtextwangeditor.pojo.Article;
import com.zgy.learn.richtextwangeditor.service.ArticleService;
import com.zgy.learn.richtextwangeditor.util.ImgInfo;
import com.zgy.learn.richtextwangeditor.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020-11-25 上午 12:27
 * @modified:
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


    @RequestMapping("article2")
    public String go2Article2Page() {
        return "article2";
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


    @PostMapping("/img/upload")
    @ResponseBody
    public ImgInfo setImgUrl(@RequestParam("file") MultipartFile file, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        byte[] bytes = file.getBytes();
        String path = request.getServletContext().getRealPath("/image/");
        File imgFile = new File(path);
        if (!imgFile.exists()) {
            imgFile.mkdirs();
        }
        // 文件名称
        String fileName = file.getOriginalFilename();
        log.info("img name {}", fileName);

        try (FileOutputStream fos = new FileOutputStream(new File(path + fileName))) {
            int len = 0;
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // url设置
        String value = "http://localhost:8896/image/" + fileName;
        String[] values = {value};

        ImgInfo imgInfo = new ImgInfo();
        imgInfo.setError(0);
        imgInfo.setUrl(values);
        log.info("img info {}", imgInfo.toString());
        return imgInfo;
    }

}
