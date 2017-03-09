package com.ffzx.ffsip.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.WxEditArticleMapper;
import com.ffzx.ffsip.model.WxEditArticle;
import com.ffzx.ffsip.service.WxEditArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class WxEditArticleServiceImpl extends BaseServiceImpl<WxEditArticle,String> implements WxEditArticleService {

    @Resource
    private WxEditArticleMapper mapper;

    @Override
    public WxEditArticleMapper getMapper() {
        return mapper;
    }
}
