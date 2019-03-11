package com.cdy.myblog.biz;

import com.cdy.myblog.mapper.ArticleLikesRecordMapper;
import com.cdy.myblog.model.ArticleLikesRecord;
import com.cdy.myblog.util.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cdy
 * @Date: 2019/3/11 10:59
 * @Version 1.0
 */
@Service
public class ArticleLikesRecordBiz extends BaseBiz<ArticleLikesRecordMapper,ArticleLikesRecord> {

    @Autowired
    UserBiz userBiz;
    public boolean isLiked(long articleId, String originalAuthor, String username) {
        ArticleLikesRecord articleLikesRecord = mapper.isLiked(articleId, originalAuthor, userBiz.findIdByUsername(username));

        return articleLikesRecord != null;
    }
}
