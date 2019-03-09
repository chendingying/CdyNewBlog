package com.cdy.myblog.mapper;

import com.cdy.myblog.model.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: cdy
 * @Date: 2019/3/7 16:52
 * @Version 1.0
 */
public interface ArticleMapper extends Mapper<Article> {

    int countArticle();

    Article findEndArticleId();

    void updateArticleLastId(@Param("lastArticleLd") long lastArticleLd, @Param("articleId") long articleId);

    void updateArticleNextId(@Param("nextArticleId") long nextArticleId, @Param("articleId") long articleId);

    Article getArticleUrlById(int id);

    void updateArticleById(Article article);
}
