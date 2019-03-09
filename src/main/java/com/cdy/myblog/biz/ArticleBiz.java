package com.cdy.myblog.biz;

import com.cdy.myblog.mapper.ArticleMapper;
import com.cdy.myblog.model.Article;
import com.cdy.myblog.util.BaseBiz;
import com.cdy.myblog.util.Query;
import com.cdy.myblog.util.TableResultResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @Author: cdy
 * @Date: 2019/3/7 16:38
 * @Version 1.0
 */
@Service
public class ArticleBiz extends BaseBiz<ArticleMapper,Article> {

    /**
     * 分页获得所有文章
     * @return 该页所有文章
     */
    public TableResultResponse findAllArticles(Query query){
        Class<Article> clazz = (Class<Article>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        example.setOrderByClause("publishDate DESC");
        if(query.entrySet().size()>0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Article> list = mapper.selectByExample(example);
        return new TableResultResponse<Article>(result.getTotal(), list);
    }

    /**
     * 计算所有文章的数量
     * @return 所有文章的数量
     */
    public int countArticle(){
        return mapper.countArticle();
    }

}
