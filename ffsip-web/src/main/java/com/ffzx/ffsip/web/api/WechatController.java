package com.ffzx.ffsip.web.api;


import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.wechat.WechatApiService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
public class WechatController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private MemberService memberService;

    @Resource
    private WechatApiService wechatApiService;


    /**
     * 微信接口token认证
     *
     * @return
     */
    @RequestMapping("/Checktoken")
    public void checktoken(HttpServletRequest request, PrintWriter out) {

        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        ArrayList<String> list = new ArrayList<String>();
        list.add(nonce);
        list.add(timestamp);
        list.add("weixin");
        Collections.sort(list);
        String sign = DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));
        if (sign.equals(signature)) {
            //验证成功
            out.print(echostr);
        }

    }

    @RequestMapping("/Oauth")
    public void oauth(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        String code = request.getParameter("code");
        Map<String, String> map = wechatApiService.oauth(code);
        Member info = new Member();
        info.setWxOpenid(map.get("openid"));
        info.setWxNickName(map.get("nickname"));
        info.setWxHeadimgurl(map.get("headimgurl"));
        Member member = memberService.findByOpenId(map.get("openid"));
        BeanUtils.copyProperties(info,member);
        if (member != null) {
            //该微信已经绑定
            info.setWxNickName(member.getWxNickName());
            info.setWxHeadimgurl(member.getWxHeadimgurl());
            info.setPhone(member.getPhone());
            info.setId(member.getId());
        }


        try {
            response.sendRedirect("http://ffsip.ffzxnet.com");
        } catch (IOException e) {
            logger.info("Oauth fail", e);
        }
    }
}
