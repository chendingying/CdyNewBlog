package com.cdy.myblog.controller;

import com.cdy.myblog.biz.ArticleBiz;
import com.cdy.myblog.util.TransCodingUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author: cdy
 * @Date: 2019/3/11 10:51
 * @Version 1.0
 */
@RestController
public class ShowArticleController {

    private Logger logger = LoggerFactory.getLogger(ShowArticleController.class);

    @Autowired
    ArticleBiz articleBiz;
    /**
     *  获取文章
     * @param articleId 文章id
     * @param originalAuthor 原作者
     * @return
     */
    @PostMapping("/getArticleByArticleIdAndOriginalAuthor")
    public @ResponseBody
    JSONObject getArticleByIdAndOriginalAuthor(@RequestParam("articleId") String articleId,
                                               @RequestParam("originalAuthor") String originalAuthor,
                                               @AuthenticationPrincipal Principal principal){
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            logger.info("This user is not login");
        }
        JSONObject jsonObject = articleBiz.getArticleByArticleIdAndOriginalAuthor(Long.parseLong(articleId), TransCodingUtil.unicodeToString(originalAuthor),username);
        return jsonObject;
    }
}
