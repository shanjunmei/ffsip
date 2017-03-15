package com.ffzx.ffsip.service;

import java.util.List;

import com.ffzx.common.service.BaseService;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.model.WxArticleExample;
import com.ffzx.ffsip.vo.ArticleInfo;

public interface WxArticleService extends BaseService<WxArticle,String>{

    /**
     * 获取文章列表
     * @param example
     * @return
     */
    public List<ArticleInfo> findArticleInfo(WxArticleExample example);
}
