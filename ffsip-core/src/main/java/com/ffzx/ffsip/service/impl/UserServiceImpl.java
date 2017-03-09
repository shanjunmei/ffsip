package com.ffzx.ffsip.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.UserMapper;
import com.ffzx.ffsip.model.User;
import com.ffzx.ffsip.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public UserMapper getMapper() {
        return mapper;
    }
}
