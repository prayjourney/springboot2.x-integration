package com.zgy.learn.richtextwangeditor.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.richtextwangeditor.mapper.ArticleMapper;
import com.zgy.learn.richtextwangeditor.pojo.Article;
import com.zgy.learn.richtextwangeditor.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020-11-25 上午 12:23
 * @modified:
 */
@Service
public class ArticleService {
    @Autowired
    ArticleMapper mapper;

    /**
     * 增加文章
     *
     * @param article
     * @return
     */
    public Result addArticle(Article article) {
        Result result = new Result();
        int insert = mapper.insert(article);

        if (insert == 1) {
            result.setCode(100).setMessage("success");
        } else {
            result.setCode(123).setMessage("fail");
        }
        return result;
    }


    /**
     * 删除文章byId
     *
     * @param id
     * @return
     */
    public Result deleteArticleById(Integer id) {
        Result result = new Result();
        int delete = mapper.deleteById(id);

        if (delete == 1) {
            result.setCode(100).setMessage("success");
        } else {
            result.setCode(123).setMessage("fail");
        }
        return result;
    }


    /**
     * 查询文章byId
     *
     * @param id
     * @return
     */
    public Result<Article> selectArticleById(Integer id) {
        Result<Article> result = new Result();
        Article article = mapper.selectById(id);

        if (null != article.getTitle()) {
            result.setCode(100).setMessage("success").setData(article);
        } else {
            result.setCode(123).setMessage("fail").setData(article);
        }
        return result;
    }


    /**
     * 查询文章byTitle
     *
     * @param title
     * @return
     */
    public Result<Article> selectArticleByTitle(String title) {
        Result<Article> result = new Result();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        Article article = mapper.selectOne(wrapper);

        if (null != article.getTitle()) {
            result.setCode(100).setMessage("success").setData(article);
        } else {
            result.setCode(123).setMessage("fail").setData(article);
        }
        return result;
    }

}
