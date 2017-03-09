package com.ffzx.ffsip.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.WxArticleMapper;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.service.WxArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class WxArticleServiceImpl extends BaseServiceImpl<WxArticle,String> implements WxArticleService {

    @Resource
    private WxArticleMapper mapper;

    @Override
    public WxArticleMapper getMapper() {
        return mapper;
    }
}
