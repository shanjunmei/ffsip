package com.ffzx.ffsip.admin.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.MemberExample;
import com.ffzx.ffsip.model.Menu;
import com.ffzx.ffsip.model.MenuExample;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/Menu")
public class MenuController extends BaseController<Menu, String, MenuExample> {

    @Resource
    private MenuService service;


    @Override
    public MenuService getService() {
        return service;
    }

}
