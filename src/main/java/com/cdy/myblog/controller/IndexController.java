package com.cdy.myblog.controller;

import com.cdy.myblog.biz.ArticleBiz;
import com.cdy.myblog.biz.VisitorBiz;
import com.cdy.myblog.model.Visitor;
import com.cdy.myblog.util.ObjectRestResponse;
import com.cdy.myblog.util.Query;
import com.cdy.myblog.util.TableResultResponse;
import com.cdy.myblog.util.TransCodingUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author: cdy
 * @Date: 2019/3/6 15:21
 * @Version 1.0
 */
@RestController
public class IndexController {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    ArticleBiz articleBiz;

    @Autowired
    VisitorBiz visitorBiz;


    /**
     * 分页获得当前页文章
     */
    @GetMapping("/myArticles")
    @ResponseBody
    public TableResultResponse myArticles(@RequestParam Map<String, Object> params){

        //查询列表数据
        Query query = new Query(params);
        return articleBiz.findAllArticles(query);

    }

    /**
     * 增加访客量
     * @return  网站总访问量以及访客量
     */
    @GetMapping("/getVisitorNumByPageName")
    @ResponseBody
    public
    JSONObject getVisitorNumByPageName(HttpServletRequest request,
                                       @RequestParam("pageName") String pageName) throws UnsupportedEncodingException {

        int index = pageName.indexOf("?");
        if(index == -1){
            pageName = "visitorVolume";
        } else {
            String subPageName = pageName.substring(0, index);
            if("archives".equals(subPageName) || "categories".equals(subPageName) || "tags".equals(subPageName) || "login".equals(subPageName) || "register".equals(subPageName)){
                pageName = "visitorVolume";
            } else {
                //接收到文章的url将url中utf8的16进制数转换成汉字
                int originalAuthorIndex = pageName.indexOf("originalAuthor");
                String originalAuthorUtf18 = pageName.substring(originalAuthorIndex + 15);
                pageName = pageName.substring(0, originalAuthorIndex + 15) + TransCodingUtil.utf16ToUtf8(originalAuthorUtf18);
            }
        }
        visitorBiz.addVisitorNumByPageName(pageName,request);
        return visitorBiz.getVisitorNumByPageName(pageName);
    }
}
