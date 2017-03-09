package com.ffzx.ffsip.admin.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.ffsip.model.Contact;
import com.ffzx.ffsip.model.ContactExample;
import com.ffzx.ffsip.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/Contact")
public class ContactController extends BaseController<Contact, String, ContactExample> {

    @Resource
    private ContactService service;


    @Override
    public ContactService getService() {
        return service;
    }

}
