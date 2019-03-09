package com.cdy.myblog.biz;

import com.cdy.myblog.mapper.VisitorMapper;
import com.cdy.myblog.model.Visitor;
import com.cdy.myblog.util.BaseBiz;
import com.cdy.myblog.util.ObjectRestResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: cdy
 * @Date: 2019/3/8 10:55
 * @Version 1.0
 * 访客业务操作
 */
@Service
public class VisitorBiz extends BaseBiz<VisitorMapper,Visitor> {

    /**
     * 通过页名增加访客量
     * @param pageName
     */
    public void addVisitorNumByPageName(String pageName, HttpServletRequest request){
        String visitor;
        if("visitorVolume".equals(pageName)){
            visitor = (String) request.getSession().getAttribute("visitor");
            if(visitor == null){
                mapper.updateVisitorNumByTotalVisitorAndPageName(pageName);
                request.getSession().setAttribute("visitor","yes");
            }else {
                mapper.updateVisitorNumByTotalVisitor();
            }
        } else {
            visitor = (String) request.getSession().getAttribute(pageName);
            if(visitor == null){
                mapper.updateVisitorNumByTotalVisitorAndPageName(pageName);
                request.getSession().setAttribute(pageName, "yes");
            } else {
                mapper.updateVisitorNumByTotalVisitor();
            }
        }
    }

    /**
     * 通过页名获得总访问量和访客量
     * @param pageName 页名
     * @return
     */
    public JSONObject getVisitorNumByPageName(String pageName) {
        long totalVisitor = mapper.getVisitorNumByPageName("totalVisitor");
        long pageVisitor = mapper.getVisitorNumByPageName(pageName);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalVisitor", totalVisitor);
        jsonObject.put("pageVisitor", pageVisitor);
        return jsonObject;
    }

    /**
     * 获得总访问量
     * @return
     */
    public Long getAllVisitor(){
        return mapper.getAllVisitor();
    }
}
