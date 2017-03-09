package com.ffzx.ffsip.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.ContactMapper;
import com.ffzx.ffsip.model.Contact;
import com.ffzx.ffsip.service.ContactService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class ContactServiceImpl extends BaseServiceImpl<Contact, String> implements ContactService {

    @Resource
    private ContactMapper mapper;

    @Override
    public ContactMapper getMapper() {
        return mapper;
    }
}
