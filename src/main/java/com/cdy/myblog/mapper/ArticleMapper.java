package com.cdy.myblog.mapper;

import com.cdy.myblog.model.Article;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: cdy
 * @Date: 2019/3/7 16:52
 * @Version 1.0
 */
public interface ArticleMapper extends Mapper<Article> {

    int countArticle();
}
