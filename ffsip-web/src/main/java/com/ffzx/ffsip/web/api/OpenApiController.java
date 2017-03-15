package com.ffzx.ffsip.web.api;

import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.model.WxEditArticle;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.service.WxArticleService;
import com.ffzx.ffsip.service.WxEditArticleService;
import com.ffzx.ffsip.util.HttpClient;
import com.ffzx.ffsip.wechat.WechatApiService;
import com.ffzx.weixin.message.WxMessageCoreService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/1.
 */
@Controller
@RequestMapping("/OpenApi")
public class OpenApiController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private WxEditArticleService wxEditArticleService;

    @Resource
    private WxArticleService wxArticleService;


    @Resource
    private WxMessageCoreService wxMessageCoreService;

    @Resource
    private WechatApiService wechatApiService;

    @Resource
    private MemberService memberService;

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

    @RequestMapping(value = "editArticle", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object editArticle(String code) {
        WxEditArticle editArticle = wxEditArticleService.findByCode(code);
        return editArticle.getContent();
    }

    @RequestMapping(value = "article", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object article(String code) {
        WxArticle editArticle = wxArticleService.findByCode(code);
        return editArticle.getContent();
    }

    @RequestMapping(value = "resource")
    public void getResource(String url, HttpServletResponse response) {
        try {
            HttpClient.getResource(url, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("addArticle")
    @ResponseBody
    public Object addArticle(WxArticle article, HttpServletRequest request) {
        Document doc = Jsoup.parse(article.getContent());

        String sourceCode = request.getParameter("sourceCode");
        String previewPic=request.getParameter("previewPic");
        WxEditArticle wxEditArticle = wxEditArticleService.findByCode(sourceCode);
        Document sourceDoc = Jsoup.parse(wxEditArticle.getSourceContent());
        doc.head().html(sourceDoc.head().html());
        doc.title(article.getTitle());
        //logger.info("doc:{}",doc.toString());
        article.setContent(doc.toString());
        article.setPublisher(wxEditArticle.getCreateBy());
        article.setCreateBy(wxEditArticle.getCreateBy());
        article.setCoverImg(previewPic);
       /* if(doc.select("img")!=null){
            article.setCoverImg(doc.select("img").get(0).attr("src"));
        }*/

        int i = wxArticleService.add(article);


        Map<String, String> ret = new HashMap<>();
        if (i > 0) {
            String url="http://ffsip.ffzxnet.com/ffsip-web/WxArticle/detail.do?articleCode=" + article.getCode();
            ret.put("msg", "success");
            ret.put("returnStr", url);
            Member member=memberService.findByCode(article.getCreateBy());
            wechatApiService.sendMsg(member.getWxOpenid(),"<a href=\""+url+"\">"+article.getTitle()+"</a>");
        } else {
            ret.put("msg", "发布失败");
        }
        return ret;
    }

}
