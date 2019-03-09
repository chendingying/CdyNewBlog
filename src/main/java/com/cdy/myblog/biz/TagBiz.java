package com.cdy.myblog.biz;

import com.cdy.myblog.mapper.TagMapper;
import com.cdy.myblog.model.Tag;
import com.cdy.myblog.util.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * @Author: cdy
 * @Date: 2019/3/9 16:21
 * @Version 1.0
 */
@Service
public class TagBiz extends BaseBiz<TagMapper,Tag> {


    /**
     * 加入标签
     * @param tags 一群标签
     * @param tagSize 标签大小
     */
    public void addTags(String[] tags, int tagSize){
        for(String tag : tags){
            if(mapper.findIsExitByTagName(tag) == 0){
                Tag t = new Tag(tag, tagSize);
                mapper.insert(t);
            }
        }
    }
}
