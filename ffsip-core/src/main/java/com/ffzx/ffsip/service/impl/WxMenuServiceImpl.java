package com.ffzx.ffsip.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.WxMenuMapper;
import com.ffzx.ffsip.model.WxMenu;
import com.ffzx.ffsip.service.WxMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class WxMenuServiceImpl extends BaseServiceImpl<WxMenu,String> implements WxMenuService {

    @Resource
    private WxMenuMapper mapper;

    @Override
    public WxMenuMapper getMapper() {
        return mapper;
    }
}
