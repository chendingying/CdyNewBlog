package com.cdy.myblog.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: zhangocean
 * @Date: 2018/7/7 15:44
 * Describe: 文章点赞记录
 */
@Table(name = "article_likes_record")
public class ArticleLikesRecord {

    @Id
    private long id;

    /**
     * 文章id
     */
    @Column(name = "articleId")
    private long articleId;

    /**
     * 原作者
     */

    @Column(name = "originalAuthor")
    private String originalAuthor;

    /**
     * 点赞人
     */
    @Column(name = "likerId")
    private int likerId;

    /**
     * 点赞时间
     */
    @Column(name = "likeDate")
    private String likeDate;

    public ArticleLikesRecord() {
    }

    public ArticleLikesRecord(long articleId, String originalAuthor, int likerId, String likeDate) {
        this.articleId = articleId;
        this.originalAuthor = originalAuthor;
        this.likerId = likerId;
        this.likeDate = likeDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public int getLikerId() {
        return likerId;
    }

    public void setLikerId(int likerId) {
        this.likerId = likerId;
    }

    public String getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(String likeDate) {
        this.likeDate = likeDate;
    }
}
