package com.cdy.myblog.mapper;

import com.cdy.myblog.model.Visitor;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: cdy
 * @Date: 2019/3/8 10:55
 * @Version 1.0
 */
public interface VisitorMapper extends Mapper<Visitor> {
    void updateVisitorNumByTotalVisitorAndPageName(@Param("pageName") String pageName);

    void updateVisitorNumByTotalVisitor();

    Long getVisitorNumByPageName(@Param("pageName") String pageName);

    Long getAllVisitor();

    void insertVisitorArticlePage(String pageName);
}
