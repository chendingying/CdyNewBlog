package com.cdy.myblog.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Author: cdy
 * @Date: 2019/3/7 16:41
 * @Version 1.0
 *  Describe: 文章
 */

@Table(name = "article")
public class Article {

    @Id
    private Integer id;

    /**
     * 文章id
     */
    @Column(name = "articleId")
    private long articleId;

    /**
     * 文章作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 文章原作者
     */
    @Column(name = "originalAuthor")
    private String originalAuthor;

    /**
     * 文章名
     */
    @Column(name = "articleTitle")
    private String articleTitle;

    /**
     * 发布时间
     */
    @Column(name = "publishDate")
    private String publishDate;

    /**
     * 最后一次修改时间
     */
    @Column(name = "updateDate")
    private String updateDate;

    /**
     * 文章内容
     */
    @Column(name = "articleContent")
    private String articleContent;

    /**
     * 文章标签
     */
    @Column(name = "articleTags")
    private String articleTags;

    /**
     * 文章类型
     */
    @Column(name = "articleType")
    private String articleType;

    /**
     * 博客分类
     */
    @Column(name = "articleCategories")
    private String articleCategories;


    /**
     * 原文链接
     * 转载：则是转载的链接
     * 原创：则是在本博客中的链接
     */
    @Column(name = "articleUrl")
    private String articleUrl;

    /**
     * 文章摘要
     */
    @Column(name = "articleTabloid")
    private String articleTabloid;

    /**
     * 上一篇文章id
     */
    @Column(name = "lastArticleId")
    private long lastArticleId;

    /**
     * 下一篇文章id
     */
    @Column(name = "nextArticleId")
    private long nextArticleId;

    /**
     * 喜欢
     */
    @Column(name = "likes")
    private int likes = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getAuthor() {
        return author ="陈定颖";
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(String articleCategories) {
        this.articleCategories = articleCategories;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleTabloid() {
        return articleTabloid;
    }

    public void setArticleTabloid(String articleTabloid) {
        this.articleTabloid = articleTabloid;
    }

    public long getLastArticleId() {
        return lastArticleId;
    }

    public void setLastArticleId(long lastArticleId) {
        this.lastArticleId = lastArticleId;
    }

    public long getNextArticleId() {
        return nextArticleId;
    }

    public void setNextArticleId(long nextArticleId) {
        this.nextArticleId = nextArticleId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
