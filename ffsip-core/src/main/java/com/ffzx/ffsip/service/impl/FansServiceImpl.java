package com.ffzx.ffsip.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.FansMapper;
import com.ffzx.ffsip.model.Fans;
import com.ffzx.ffsip.service.FansService;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class FansServiceImpl extends BaseServiceImpl<Fans,String> implements FansService {

    @Resource
    private FansMapper mapper;

    @Override
    public FansMapper getMapper() {
        return mapper;
    }
}
