package com.cdy.myblog.controller;

import com.cdy.myblog.biz.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: cdy
 * @Date: 2019/3/8 10:29
 * @Version 1.0
 */
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserBiz userBiz;
}
