package com.cdy.myblog.mapper;

import com.cdy.myblog.model.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: cdy
 * @Date: 2019/3/8 10:34
 * @Version 1.0
 */
public interface UserMapper extends Mapper<User> {

    int countUserNum();

    int findIdByUsername(String username);
}
