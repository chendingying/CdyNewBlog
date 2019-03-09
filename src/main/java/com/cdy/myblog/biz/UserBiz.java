package com.cdy.myblog.biz;

import com.cdy.myblog.mapper.UserMapper;
import com.cdy.myblog.model.User;
import com.cdy.myblog.util.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * @Author: cdy
 * @Date: 2019/3/8 10:32
 * @Version 1.0
 */
@Service
public class UserBiz extends BaseBiz<UserMapper,User> {

    /**
     * 统计总用户量
     * @return
     */
    public int countUserNum(){
        return mapper.countUserNum();
    }


}
