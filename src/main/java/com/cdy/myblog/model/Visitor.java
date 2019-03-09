package com.cdy.myblog.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: zhangocean
 * @Date: 2018/6/16 16:03
 * Describe: 访客
 */
@Table(name = "Visitor")
public class Visitor {

    @Id
    private int id;

    /**
     * 访客人数
     */
    @Column(name = "visitorNum")
    private long visitorNum;

    /**
     * 当前页的name or 文章名
     */
    @Column(name = "pageName")
    private String pageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(long visitorNum) {
        this.visitorNum = visitorNum;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
