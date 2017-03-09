package com.ffzx.common.utils;


import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ApiInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Member member= WebUtils.getSessionAttribute("loginMember");
        if(member==null){
            response.setContentType("text/json");
            Map<String,String> ret=new HashMap<>();
            ret.put("code","-1");
            response.getWriter().write(JsonConverter.toJson(ret));
            //response.getWriter().write("{code:-1}");
            //response.sendRedirect("/Signin.html");
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}