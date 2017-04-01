package com.ffzx.ffsip.web.api;

import com.ffzx.ffsip.util.HttpClient;
import com.ffzx.weixin.message.WxMessageCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/3/1.
 */
@Controller
@RequestMapping("/OpenApi")
public class OpenApiController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private WxMessageCoreService wxMessageCoreService;


    @RequestMapping(value = "onWxMessage", method = RequestMethod.POST)
    //@ResponseBody
    public void onWxMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String res = wxMessageCoreService.processRequest(request.getInputStream());
            response.getWriter().write(res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "onWxMessage", method = RequestMethod.GET)
    public void echo(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("request params :{}", request.getParameterMap());
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");

            PrintWriter out = response.getWriter();

            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            // if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
            // }

            out.close();
            out = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(value = "resource")
    public void getResource(String url, HttpServletResponse response) {
        try {
            HttpClient.getResource(url, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
