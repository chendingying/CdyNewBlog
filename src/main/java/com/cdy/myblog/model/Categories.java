package com.cdy.myblog.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: zhangocean
 * @Date: 2018/7/17 20:49
 * Describe: 文章分类
 */
@Table(name = "categories")
public class Categories {

    @Id
    private int id;

    @Column(name = "categoryName")
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
