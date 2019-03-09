package com.cdy.myblog.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: zhangocean
 * @Date: 2018/6/20 15:36
 * Describe: 标签
 */
@Table(name = "tags")
public class Tag {

    @Id
    private int id;

    /**
     * 标签名
     */
    @Column(name = "tagName")
    private String tagName;

    /**
     * 标签大小
     */
    @Column(name = "tagSize")
    private int tagSize;

    public Tag() {
    }

    public Tag(String tagName, int tagSize) {
        this.tagName = tagName;
        this.tagSize = tagSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getTagSize() {
        return tagSize;
    }

    public void setTagSize(int tagSize) {
        this.tagSize = tagSize;
    }
}
