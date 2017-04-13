package com.ffzx.ffsip.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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

    /**
     * 通过文章列表获取有用户信息的文章列表
     * @param list
     * @return
     */
    public List<ArticleInfo> getArticleInfoByList(List<WxArticle> list);
    
    /**
     * 通过粉丝code查询
     * @param params
     * @return
     */
	public List<WxArticle> selectByPageFansCode(Map<String, Object> params);

    /**
     * 发布者转换
     * @param article
     */
    public void convertPublisher(WxArticle article);
}
