package com.cdy.myblog.controller;

import com.cdy.myblog.biz.CategoriesBiz;
import com.cdy.myblog.model.Categories;
import com.cdy.myblog.util.ObjectRestResponse;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cdy
 * @Date: 2019/3/9 15:47
 * @Version 1.0
 */
@RestController
public class CategoriesController {

    @Autowired
    CategoriesBiz categoriesBiz;

    /**
     * 获得所有的分类
     * @return
     */
    @GetMapping("/findCategoriesName")
    @ResponseBody
    public ObjectRestResponse findCategoriesName(){
        return categoriesBiz.findCategoriesName();
    }
}
