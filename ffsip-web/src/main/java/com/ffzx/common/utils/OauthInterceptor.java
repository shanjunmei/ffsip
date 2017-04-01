package com.ffzx.common.utils;

import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.service.CompanyService;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.util.JsonConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权拦截器
 *
 * @author 李淼淼
 */
public class OauthInterceptor extends HandlerInterceptorAdapter {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private MemberService memberService;
    @Resource
    private CompanyService companyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String WECHAT_WEB_DEBUG = System.getProperty("wechat.web.debug");

        Member member = com.ffzx.common.utils.WebUtils.getSessionAttribute("loginMember");
        //判断浏览器类型
      /*  String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
        boolean isWechat = false;
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            isWechat = true;
        }

        if ("true".equals(WECHAT_WEB_DEBUG) && member == null && !isWechat) {
            //开启了网页调试
            String WECHAT_WEB_DEBUG_OPENID = System.getProperty("wechat.web.debug.openid");
            member.setWxOpenid(WECHAT_WEB_DEBUG_OPENID);

        }*/
       /* Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            logger.info(key + " : " + value);
        }*/

        String ua = ((HttpServletRequest) request).getHeader("user-agent");
       // logger.info("user-agent:{}",ua);
        ua=ua.toLowerCase();
        boolean isWechat = false;
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            isWechat = true;
        }

       // member=new Member();
    /*    logger.info("request url：{}",request.getRequestURL());
        logger.info("member:{}",JsonConverter.toJson(member));*/
        if (member == null&&isWechat) {
            String refer=request.getHeader("Referer");
            String baseUrl = request.getScheme() + "://" + request.getServerName()+request.getContextPath() + "/Signin.html"; //服务器地址
            if(StringUtils.isNotBlank(refer)){
                baseUrl=baseUrl+"?refer="+refer;
                // String redirectUrl = URLEncoder.encode(baseUrl + "/ffsip-admin/Oauth.do", "utf-8");
            }else{
                baseUrl=baseUrl+"?refer="+request.getRequestURI();
            }
            String redirectUrl = URLEncoder.encode(baseUrl, "utf-8");
        /*    logger.info("redirect url:{}",redirectUrl);*/
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + System.getProperty("wechat.appid") + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
          /* if(!isWechat) {
               url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + System.getProperty("wechat.appid") + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
           }*/
           response.sendRedirect(url);
           /* response.setContentType("text/json");
            PrintWriter out = response.getWriter();
	        Map ret=new HashMap<>();
            ret.put("code",-2);
            ret.put("url",url);
	        out.print(JsonConverter.toJson(ret));*/

           // response.sendRedirect(url);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
