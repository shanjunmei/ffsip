package com.ffzx.ffsip.web.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.MemberExample;
import com.ffzx.ffsip.model.WxMenu;
import com.ffzx.ffsip.model.WxMenuExample;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.service.WxMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/WxMenu")
public class WxMenuController extends BaseController<WxMenu, String, WxMenuExample> {

    @Resource
    private WxMenuService service;


    @Resource
    private com.ffzx.weixin.menu.WxMenuService wxMenuService;

    @Override
    public WxMenuService getService() {
        return service;
    }

    @RequestMapping("publishWxMenu")
    @ResponseBody
    public Object publishWxMenu(){
        Map<String,Object> ret=new HashMap<>();
       List<WxMenu> menus= service.selectByExample(null);
        String res= wxMenuService.createMenu(menus);
        logger.info("publish menu result:{}",res);
        ret.put("code",0);
        return ret;
    }

}
