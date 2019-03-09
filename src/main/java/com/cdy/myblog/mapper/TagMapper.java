package com.cdy.myblog.mapper;

import com.cdy.myblog.model.Tag;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: cdy
 * @Date: 2019/3/9 16:20
 * @Version 1.0
 */
public interface TagMapper extends Mapper<Tag> {

    int findIsExitByTagName(@Param("tagName") String tagName);

}
