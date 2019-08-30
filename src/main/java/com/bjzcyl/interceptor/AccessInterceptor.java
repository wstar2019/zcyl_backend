package com.bjzcyl.interceptor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2016/11/7.
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
    @Value("#{baseconfig.serveState}")
    private String serveState;
    Logger log = Logger.getLogger(AccessInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] exports =new String[]{"jsp","css","jpg","png","gif","js","jpeg","otf","eot","svg","ttf","woff","woff2"};
        String requestUrl = request.getRequestURL().toString().toLowerCase();
        for (String s : exports) {
            if (requestUrl.endsWith(s)) {
                return super.preHandle(request, response, handler);
            }
        }

        boolean flag1 = serveState!=null&&serveState.equalsIgnoreCase("user") && !(request.getRequestURL().toString().contains("weixin/"));
        boolean flag2 = serveState!=null&&serveState.equalsIgnoreCase("MANAGER") && request.getRequestURL().toString().contains("weixin/");
        boolean flag3 = !request.getRequestURL().toString().contains("template/");
        boolean flag4 = !request.getRequestURL().toString().contains("static/");
        if((flag1||flag2)&&flag3&&flag4){
            log.debug("不符合规定："+serveState);
            response.setStatus(404);
            request.getRequestDispatcher("/404.jsp").forward(request,response);

        }
        return super.preHandle(request, response, handler);
    }
}
