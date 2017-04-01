package com.ffzx.ffsip.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.CommentMapper;
import com.ffzx.ffsip.model.Comment;
import com.ffzx.ffsip.service.CommentService;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment,String> implements CommentService {

    @Resource
    private CommentMapper mapper;

    @Override
    public CommentMapper getMapper() {
        return mapper;
    }
}
