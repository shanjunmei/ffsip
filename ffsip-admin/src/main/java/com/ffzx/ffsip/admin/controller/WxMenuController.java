package com.ffzx.ffsip.admin.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.MemberExample;
import com.ffzx.ffsip.model.WxMenu;
import com.ffzx.ffsip.model.WxMenuExample;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.service.WxMenuService;
import com.ffzx.ffsip.util.JsonConverter;
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
        ret.put("code",0);
        logger.info("publish menu result:{}",res);
        Map result= JsonConverter.from(res,Map.class);
        if(Integer.parseInt(result.get("errcode").toString())>0){
            ret.put("msg","注意事项：1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。\n" +
                    "2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。\n"+
                result.get("errmsg"));
            ret.put("code",101);
        }



        return ret;
    }

}
