package com.ffzx.ffsip.admin.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/Company")
public class CompanyController extends BaseController<Company, String, CompanyExample> {

    @Resource
    private CompanyService service;


    @Override
    public CompanyService getService() {
        return service;
    }

}
