package com.zgy.learn.richtextwangeditor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.richtextwangeditor.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020-11-25 上午 12:22
 * @modified:
 */
@Repository
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
