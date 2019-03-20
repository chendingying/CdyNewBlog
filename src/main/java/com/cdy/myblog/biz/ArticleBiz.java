package com.cdy.myblog.biz;

import com.cdy.myblog.component.StringAndArray;
import com.cdy.myblog.mapper.ArticleMapper;
import com.cdy.myblog.model.Article;
import com.cdy.myblog.model.User;
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
import java.util.HashMap;
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

    @Autowired
    UserBiz userBiz;

    @Autowired
    ArticleLikesRecordBiz articleLikesRecordBiz;

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
            User user = userBiz.selectById(1);
            if(user.getPersonalBrief().equals("1")){
                articleReturn.put("status",200);
                articleReturn.put("message","您没有权限发表文章");
                return articleReturn;
            }
            if(article == null || "".equals(article.getOriginalAuthor())){
                article.setOriginalAuthor(article.getAuthor());
            }
            if("".equals(article.getArticleUrl())){
                String url = "http://localhost/findArticle?articleId=" + article.getArticleId() + "&originalAuthor=" + article.getOriginalAuthor();
//                String url = "https://www.zhyocean.cn/findArticle?articleId=" + article.getArticleId() + "&originalAuthor=" + article.getOriginalAuthor();
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
            articleReturn.put("message","发布成功");
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

    /**
     * 通过文章id和原作者获得文章名
     * @param articleId 文章id
     * @param originalAuthor 文章原作者
     * @return 文章名
     */
    public Map<String,String> findArticleTitleByArticleIdAndOriginalAuthor(long articleId, String originalAuthor) {
        Article articleInfo = mapper.findArticleTitleByArticleIdAndOriginalAuthor(articleId, originalAuthor);
        Map<String, String> articleMap = new HashMap<>();
        articleMap.put("articleTitle", articleInfo.getArticleTitle());
        articleMap.put("articleTabloid", articleInfo.getArticleTabloid());
        return articleMap;
    }

    public JSONObject getArticleByArticleIdAndOriginalAuthor(long articleId, String originalAuthor, String username) {
        Article article = mapper.getArticleByArticleIdAndOriginalAuthor(articleId, originalAuthor);

        JSONObject jsonObject = new JSONObject();
        if(article != null){
            Article lastArticle = mapper.findArticleByArticleId(article.getLastArticleId());
            Article nextArticle = mapper.findArticleByArticleId(article.getNextArticleId());
            jsonObject.put("status","200");
            jsonObject.put("author",article.getAuthor());
            jsonObject.put("articleId",articleId);
            jsonObject.put("originalAuthor",article.getOriginalAuthor());
            jsonObject.put("articleTitle",article.getArticleTitle());
            jsonObject.put("publishDate",article.getPublishDate());
            jsonObject.put("updateDate",article.getUpdateDate());
            jsonObject.put("articleContent",article.getArticleContent());
            jsonObject.put("articleTags", StringAndArray.stringToArray(article.getArticleTags()));
            jsonObject.put("articleType",article.getArticleType());
            jsonObject.put("articleCategories",article.getArticleCategories());
            jsonObject.put("articleUrl",article.getArticleUrl());
            jsonObject.put("likes",article.getLikes());
            if(username == null){
                jsonObject.put("isLiked",0);
            }else {
                if(articleLikesRecordBiz.isLiked(articleId, originalAuthor,username)){
                    jsonObject.put("isLiked",1);
                }else {
                    jsonObject.put("isLiked",0);
                }
            }
            if(lastArticle != null){
                jsonObject.put("lastStatus","200");
                jsonObject.put("lastArticleTitle",lastArticle.getArticleTitle());
                jsonObject.put("lastArticleUrl","/findArticle?articleId=" + lastArticle.getArticleId() + "&originalAuthor=" + lastArticle.getOriginalAuthor());
            } else {
                jsonObject.put("lastStatus","500");
                jsonObject.put("lastInfo","无");
            }
            if(nextArticle != null){
                jsonObject.put("nextStatus","200");
                jsonObject.put("nextArticleTitle",nextArticle.getArticleTitle());
                jsonObject.put("nextArticleUrl","/findArticle?articleId=" + nextArticle.getArticleId() + "&originalAuthor=" + nextArticle.getOriginalAuthor());
            } else {
                jsonObject.put("nextStatus","500");
                jsonObject.put("nextInfo","无");
            }
            return jsonObject;
        } else {
            jsonObject.put("status","500");
            jsonObject.put("errorInfo","获取文章信息失败");
            logger.error("获取文章id " + articleId + " 失败");
            return jsonObject;
        }
    }
}
