package com.ffzx.ffsip.web.api;


import com.ffzx.common.utils.WebUtils;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.MemberExample;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.util.JsonConverter;
import com.ffzx.ffsip.wechat.WechatApiService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    private Logger logger = LoggerFactory.getLogger(getClass());

 
    @Resource
    private MemberService memberService;

    @Resource
    private WechatApiService wechatApiService;


    /**
     * 登录
     *
     * @param entity wxOpenid 实参为获取openid前置code
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(Member entity, HttpServletRequest request) {
        Map<String, Object> ret = new HashMap<>();
        MemberExample example = new MemberExample();
        if (StringUtils.isBlank(entity.getWxOpenid())) {
            Member m = getLoginMember();
            if (m != null) {
                ret.put("loginInfo", m);
                ret.put("refer", request.getParameter("refer"));
                return ret;
            }
        }
        Map<String, String> map = wechatApiService.oauth(entity.getWxOpenid());
        logger.info(JsonConverter.toJson(map));
        String openId = map.get("openid");
        example.createCriteria().andWxOpenidEqualTo(openId);
        // example.createCriteria().andCodeEqualTo(entity.getCode()).andPasswordEqualTo(entity.getPassword());
        List<Member> users = memberService.selectByExample(example);
        // int count= getService().countByExample(example);
        Member member = null;
        boolean iscreate = true;
        if (users.size() > 0) {
            WebUtils.createSession();
            member = users.get(0);
            iscreate = false;
            // return member;
        } else {
            member = entity;


        }
        member.setWxOpenid(map.get("openid"));
        member.setWxNickName(map.get("nickname"));
        member.setWxHeadimgurl(map.get("headimgurl"));
        if (iscreate) {
            memberService.add(member);
        } else {
            memberService.updateSelective(member);
        }
        member.setPassword(null);
        WebUtils.createSession();
        WebUtils.setSessionAttribute("loginMember", member);
        ret.put("loginInfo", member);
        ret.put("refer", request.getParameter("refer"));
        return ret;
    }

    /**
     * 获取当前登录会员信息
     *
     * @return
     */
    @RequestMapping("memberInfo")
    @ResponseBody
    public Member getLoginMember() {
        return WebUtils.getSessionAttribute("loginMember");
    }

    @RequestMapping("activities")
    @ResponseBody
    public List<Object> getActivities(Object entity) {

        return new ArrayList();
    }
}
