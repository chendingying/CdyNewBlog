package com.cdy.myblog.controller;

import com.cdy.myblog.biz.ArticleBiz;
import com.cdy.myblog.biz.UserBiz;
import com.cdy.myblog.biz.VisitorBiz;
import com.cdy.myblog.util.ObjectRestResponse;
import com.cdy.myblog.util.Query;
import com.cdy.myblog.util.TableResultResponse;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @Author: cdy
 * @Date: 2019/3/8 15:01
 * @Version 1.0
 * 后台接口
 */
@RestController
public class SuperAdminController {

    @Autowired
    VisitorBiz visitorBiz;

    @Autowired
    UserBiz userBiz;

    @Autowired
    ArticleBiz articleBiz;

    /**
     * 获得统计信息
     * @return
     */
    @GetMapping("/getStatisticsInfo")
    public ObjectRestResponse getStatisticsInfo(){
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        Map<String,Object> map = new HashedMap();
        long num = visitorBiz.getAllVisitor();
        map.put("allVisitor", num);
        map.put("allUser", userBiz.countUserNum());
        map.put("yesterdayVisitor", num);
        map.put("articleNum", articleBiz.countArticle());
        objectRestResponse.setData(map);
        return objectRestResponse;
    }

    /**
     * 获得文章管理
     * @return
     */
    @GetMapping("/getArticleManagement")
    public TableResultResponse getArticleManagement(@AuthenticationPrincipal Principal principal,
                                                    @RequestParam Map<String, Object> params){
        String username = null;
        JSONObject returnJson = new JSONObject();
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            returnJson.put("status",403);
        }
        Query query = new Query(params);
        return articleBiz.selectByQuery(query);
    }
}
