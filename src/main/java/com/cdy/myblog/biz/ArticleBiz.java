package com.cdy.myblog.biz;

import com.cdy.myblog.mapper.ArticleMapper;
import com.cdy.myblog.model.Article;
import com.cdy.myblog.util.BaseBiz;
import com.cdy.myblog.util.Query;
import com.cdy.myblog.util.TableResultResponse;
import com.cdy.myblog.util.TimeUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    Logger logger = LoggerFactory.getLogger(ArticleBiz.class);
    @Autowired
    ArchiveBiz archiveBiz;

    @Autowired
    VisitorBiz visitorBiz;

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

    /**
     * 添加文章
     * @param article
     * @return
     */
    public JSONObject insertArticle(Article article){
        JSONObject articleReturn = new JSONObject();
        try {
            if(article == null || "".equals(article.getOriginalAuthor())){
                article.setOriginalAuthor(article.getAuthor());
            }
            if("".equals(article.getArticleUrl())){
//                String url = "http://localhost/findArticle?articleId=" + article.getArticleId() + "&originalAuthor=" + article.getOriginalAuthor();
                String url = "https://www.zhyocean.cn/findArticle?articleId=" + article.getArticleId() + "&originalAuthor=" + article.getOriginalAuthor();
                article.setArticleUrl(url);
            }
            Article endArticleId = mapper.findEndArticleId();
            //设置文章的上一篇文章id
            if(endArticleId != null){
                article.setLastArticleId(endArticleId.getArticleId());
            }
            mapper.insertSelective(article);
            //判断发表文章的归档日期是否存在，不存在则插入归档日期
            TimeUtil timeUtil = new TimeUtil();
            String archiveName = timeUtil.timeWhippletreeToYear(article.getPublishDate().substring(0, 7));
            archiveBiz.addArchiveName(archiveName);
            //新文章加入访客量
            visitorBiz.insertVisitorArticlePage("findArticle?articleId=" + article.getArticleId() + "&originalAuthor=" + article.getOriginalAuthor());
            //设置上一篇文章的下一篇文章id
            if(endArticleId != null){
                updateArticleLastOrNextId("nextArticleId", article.getArticleId(), endArticleId.getArticleId());
            }

            articleReturn.put("status",200);
            articleReturn.put("articleTitle",article.getArticleTitle());
            articleReturn.put("updateDate",article.getUpdateDate());
            articleReturn.put("author",article.getOriginalAuthor());
            //本博客中的URL
            articleReturn.put("articleUrl","/findArticle?articleId=" + article.getArticleId() + "&originalAuthor=" + article.getOriginalAuthor());
            return articleReturn;
        } catch (Exception e){
            articleReturn.put("status",500);
            logger.error("用户 " + article.getAuthor() + " 保存文章 " + article.getArticleTitle() + " 失败");
            e.printStackTrace();
            return articleReturn;
        }
    }

    /**
     * 通过文章id更新它的上一篇或下一篇文章id
     * @param lastOrNext
     * @param lastOrNextArticleId
     * @param articleId
     */
    public void updateArticleLastOrNextId(String lastOrNext, long lastOrNextArticleId, long articleId) {
        if("lastArticleId".equals(lastOrNext)){
            mapper.updateArticleLastId(lastOrNextArticleId, articleId);
        }
        if("nextArticleId".equals(lastOrNext)){
            mapper.updateArticleNextId(lastOrNextArticleId, articleId);
        }
    }

    /**
     * 修改文章
     * @return
     */
    public JSONObject updateArticleById(Article article){
        Article a = mapper.getArticleUrlById(article.getId());
        if("原创".equals(article.getArticleType())){
            article.setOriginalAuthor(article.getAuthor());
            String url = "https://www.zhyocean.cn/findArticle?articleId=" + a.getArticleId() + "&originalAuthor=" + a.getOriginalAuthor();
            article.setArticleUrl(url);
        }
        mapper.updateArticleById(article);
        JSONObject articleReturn = new JSONObject();
        articleReturn.put("status",200);
        articleReturn.put("articleTitle",article.getArticleTitle());
        articleReturn.put("updateDate",article.getUpdateDate());
        articleReturn.put("author",article.getOriginalAuthor());
        //本博客中的URL
        articleReturn.put("articleUrl","/findArticle?articleId=" + a.getArticleId() + "&originalAuthor=" + a.getOriginalAuthor());
        return articleReturn;
    }

}
