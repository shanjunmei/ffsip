package com.ffzx.ffsip.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ffzx.ffsip.model.WxArticle;
import tk.mybatis.mapper.common.Mapper;

public interface WxArticleMapper extends Mapper<WxArticle> {

	public List<WxArticle> selectByPage(@Param("params") Map<String, Object> params);
}