package com.ffzx.ffsip.web.api;


import com.ffzx.common.utils.WebUtils;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.MemberExample;
import com.ffzx.ffsip.service.CompanyService;
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
import java.util.HashMap;
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

    @Resource
    private CompanyService companyService;

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

        String authCode = request.getParameter("authCode");
        String refer = request.getParameter("refer");
        logger.info("authCode :{}", authCode);
        logger.info("refer :{}", refer);
        String openId = null;

        Member member = getLoginMember();
        boolean iscreate = true;

        if(member==null){
            if(StringUtils.isNotBlank(authCode)){
                Map<String, String> map = wechatApiService.oauth(authCode);
                logger.info(JsonConverter.toJson(map));
                openId = map.get("openid");
                member = memberService.findByOpenId(openId);
                if (member != null) {
                    iscreate = false;
                } else {
                    member = new Member();
                }

                member.setWxOpenid(map.get("openid"));
                member.setWxNickName(map.get("nickname"));
                member.setWxHeadimgurl(map.get("headimgurl"));
                if (iscreate) {
                    memberService.add(member);
                } else {
                    memberService.updateSelective(member);
                }
            }else{
                ret.put("code", "-3");
                ret.put("msg", "未登录，且授权信息为空");
                return ret;
            }
        }

        CompanyExample companyExample = new CompanyExample();
        companyExample.createCriteria().andMemberCodeEqualTo(member.getCode());
        Company company = companyService.findByMemberCode(member.getCode());
        member.setPassword(null);
        WebUtils.createSession();
        WebUtils.setSessionAttribute("loginMember", member);
        ret.put("loginInfo", member);
        ret.put("company", company);
        ret.put("refer", refer);
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


}
