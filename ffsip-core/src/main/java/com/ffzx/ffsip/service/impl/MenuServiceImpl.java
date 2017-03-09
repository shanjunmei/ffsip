package com.ffzx.ffsip.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.MenuMapper;
import com.ffzx.ffsip.model.Menu;
import com.ffzx.ffsip.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,String> implements MenuService {

    @Resource
    private MenuMapper mapper;

    @Override
    public MenuMapper getMapper() {
        return mapper;
    }
}
