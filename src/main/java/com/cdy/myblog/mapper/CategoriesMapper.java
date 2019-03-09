package com.cdy.myblog.mapper;

import com.cdy.myblog.model.Categories;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: cdy
 * @Date: 2019/3/9 15:46
 * @Version 1.0
 */
public interface CategoriesMapper extends Mapper<Categories> {

    List<Map<String,Object>> findCategoriesName();
}
