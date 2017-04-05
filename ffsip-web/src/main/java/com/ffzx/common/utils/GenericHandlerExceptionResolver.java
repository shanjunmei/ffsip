//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ffzx.common.utils;

import com.ffzx.commerce.framework.model.ServiceException;
import com.ffzx.ffsip.util.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class GenericHandlerExceptionResolver implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GenericHandlerExceptionResolver() {
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String errorCode = CodeGenerator.code();
        String errorMassage = ex.getMessage();

        logger.info("errorCode:" + errorCode);
        if(!(ex instanceof ServiceException)){
            logger.warn("错误信息:", ex);
        }else{
            logger.warn("错误信息:{}", errorMassage);
        }

        if (request.getHeader("accept").indexOf("application/json") > -1 || request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1) {
            Map ret = new HashMap();
            ret.put("errorCode", errorCode);
            ret.put("msg", errorMassage);

            // String message = JsonConverter.toJson(ret);
            // response.reset();
            //   response.setContentType("application/json;charset=utf-8");

            // response.getWriter().print(message);
            // response.getWriter().flush();
            return new ModelAndView(new MappingJackson2JsonView(), ret);

        } else {
            throw new RuntimeException(ex);
        }
    }
}
