package com.ffzx.common.utils;


import com.ffzx.ffsip.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {


        Object basePathObj = request.getSession().getAttribute("BasePath");
        String basePath = basePathObj == null ? "" : (String) basePathObj;
        if (StringUtils.isBlank(basePath)) {
            request.getSession().setAttribute("BasePath", System.getProperty("web.basepath"));
        }

        User loginUser= WebUtils.getSessionAttribute("loginUser");
        if(loginUser==null){
            response.sendRedirect(request.getContextPath()+"/login.html");
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}