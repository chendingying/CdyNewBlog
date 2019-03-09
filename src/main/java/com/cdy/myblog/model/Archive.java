package com.cdy.myblog.model;


import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author: zhangocean
 * @Date: 2018/7/18 11:52
 * Describe: 文章归档
 */
public class Archive {

    @Id
    private int id;

    /**
     * 归档日期
     */
    @Column(name = "archiveName")
    private String archiveName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }
}
