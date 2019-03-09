package com.cdy.myblog.biz;

import com.cdy.myblog.mapper.CategoriesMapper;
import com.cdy.myblog.model.Categories;
import com.cdy.myblog.util.BaseBiz;
import com.cdy.myblog.util.ObjectRestResponse;
import org.springframework.stereotype.Service;

/**
 * @Author: cdy
 * @Date: 2019/3/9 15:47
 * @Version 1.0
 */
@Service
public class CategoriesBiz extends BaseBiz<CategoriesMapper,Categories> {

    public ObjectRestResponse findCategoriesName(){
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(mapper.findCategoriesName());
        return objectRestResponse;
    }
}
