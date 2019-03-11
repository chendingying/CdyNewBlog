package com.cdy.myblog.mapper;

import com.cdy.myblog.model.ArticleLikesRecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: cdy
 * @Date: 2019/3/11 11:00
 * @Version 1.0
 */
public interface ArticleLikesRecordMapper extends Mapper<ArticleLikesRecord> {

    ArticleLikesRecord isLiked(@Param("articleId") long articleId, @Param("originalAuthor") String originalAuthor, @Param("likerId") int likerId);
}
