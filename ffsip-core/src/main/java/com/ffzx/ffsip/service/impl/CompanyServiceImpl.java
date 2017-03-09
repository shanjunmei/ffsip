package com.ffzx.ffsip.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.CompanyMapper;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.service.CompanyService;
import com.ffzx.ffsip.service.CompanyService1;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company, String> implements CompanyService {

    @Resource
    private CompanyMapper mapper;

    @Resource
    private CompanyService1 service1;

    @Override
    public CompanyMapper getMapper() {
        return mapper;
    }
}