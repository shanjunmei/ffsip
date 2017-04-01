package com.ffzx.ffsip.admin.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.model.WxArticleExample;
import com.ffzx.ffsip.service.CompanyService;
import com.ffzx.ffsip.service.WxArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/WxArticle")
public class WxArticleController extends BaseController<WxArticle, String, WxArticleExample> {

    @Resource
    private WxArticleService service;


    @Override
    public WxArticleService getService() {
        return service;
    }

}
